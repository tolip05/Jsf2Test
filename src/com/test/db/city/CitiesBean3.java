package com.test.db.city;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cc.db.exception.EmptyResultSetException;
import com.cc.db.exception.PojoCreationException;
import com.cc.pojo.helper.PojoHelper;
import com.test.db.city.builder.CitySelectByCriteriaBuilder;
import com.test.db.municipality.builder.MunicipalitySelectAllBuilder;
import com.test.db.municipality.builder.MunicipalitySelectByMultipleIdsBuilder;
import com.test.helpers.CityHelper;
import com.test.models.City;
import com.test.models.Municipality;

@ManagedBean(name="cBean")
@SessionScoped
public class CitiesBean3 {

	private List<City> cities;
	private final static int PAGE_SIZE = 20;
	private int currentPage;
	private City editObject;
	private String searchName;
	private String municipalityName;
	private int selectedKindCityFilter;
	private int municipalityId;
	private List<Municipality> municipalities;

	private enum ACTION {
		ADD, EDIT
	}

	private ACTION currentAction;

	public CitiesBean3() {
		try {
			municipalities = PojoHelper.fillListOfPojos(Municipality.class,
					MunicipalitySelectAllBuilder.create().build().selectListObjects());
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			e.printStackTrace();
		}
		goTo(1);
	}

	public void add() {
		currentAction = ACTION.ADD;
		editObject = new City();
		editObject.setMunicipality(new Municipality());
	}

	public void edit(City city) {
		currentAction = ACTION.EDIT;
		editObject = city;
	}

	public void save() throws SQLException {
		switch (currentAction) {
		case ADD:
			CityHelper.insert(editObject);
			break;

		case EDIT:
			CityHelper.update(editObject);
			break;
		}
	}

	public void nextPage() {
		goTo(currentPage + 1);
	}

	public void prevPage() {
		goTo(currentPage - 1);
	}

	public void firstPage() {
		goTo(1);
	}

	public void lastPage() {
		goTo(cities != null && !cities.isEmpty()
				? (cities.get(0).getRowsCount() / PAGE_SIZE) + (cities.get(0).getRowsCount() % PAGE_SIZE > 0 ? 1 : 0)
				: 1);
	}

	private void goTo(int page) {
		currentPage = page;
		loadPageData();
	}

	public void selectAll() {
		clearFilters();
		goTo(1);
	}

	public void clearFilters() {
		searchName = null;
		selectedKindCityFilter = 0;
		municipalityId = 0;
		municipalityName = null;
	}
	private void setMunicipalityId() {
		        municipalityId = 0;
		        Municipality municipality = this.municipalities.stream()
				.filter(m -> m.getMunicipalityName().equals(municipalityName))
				.findFirst().orElse(null);
		        if(municipality != null) {
		        municipalityId = municipality.getId().intValue();
		        }
		}
	
	private void loadPageData() {
		setMunicipalityId();
		try {
			cities = PojoHelper.fillListOfPojos(City.class, CitySelectByCriteriaBuilder.create()
					.setParam1_City_name(searchName != null && !searchName.isEmpty() ? searchName.toUpperCase() : null)
					.setParam2_Kind_city(selectedKindCityFilter > 0 ? selectedKindCityFilter : null)
					.setParam3_Municipality_id(municipalityId > 0 ? municipalityId : null)
					.setParam4_RowBegin(getRowBegin()).setParam5_RowEnd(getRowEnd()).build().selectListObjects());
			fillMunicipalities();
		} catch (PojoCreationException | SQLException e) {
			e.printStackTrace();
		} catch (EmptyResultSetException e) {
			cities = new ArrayList<>();
		}
	}

	private int getRowBegin() {
		return (currentPage - 1) * PAGE_SIZE + 1;
	}

	private int getRowEnd() {
		return getRowBegin() + PAGE_SIZE - 1;
	}

	public List<City> getCities() {
		return cities;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public boolean isNextPageDisabled() {
		return cities != null && !cities.isEmpty() ? cities.get(0).getRowsCount() <= currentPage * PAGE_SIZE : true;
	}

	public City getEditObject() {
		return editObject;
	}

	public List<Municipality> getMunicipalities() {
		return municipalities;
	}

	public void searchCytiByName() {
		goTo(1);
	}

	private void fillMunicipalities() {
		try {
			Set<Long> municipalityIds = cities.stream().map(City::getMunicipality).map(Municipality::getId)
					.collect(Collectors.toSet());
			List<Municipality> municipalities = PojoHelper.fillListOfPojos(Municipality.class,
					MunicipalitySelectByMultipleIdsBuilder.create()
							.setParam1_MunicipalityIds(new ArrayList<>(municipalityIds)).build().selectListObjects());
			Map<Long, Municipality> municipalityById = municipalities.stream()
					.collect(Collectors.toMap(Municipality::getId, x -> x));
			cities.forEach(city -> city.setMunicipality(municipalityById.get(city.getMunicipality().getId())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	collectToMap();
	}

	public void collectToMap() {
		Map<Municipality,List<City>> map =
				cities.stream().collect(Collectors.groupingBy(City::getMunicipality));
		map.entrySet().forEach(m -> {
			System.out.println(m.getKey().getMunicipalityName());
			m.getValue().stream().forEach(l -> System.out.println(l.getCityName()));
		});
		
	}
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Integer getSelectedKindCityFilter() {
		return selectedKindCityFilter;
	}

	public void setSelectedKindCityFilter(Integer selectedKindCityFilter) {
		this.selectedKindCityFilter = selectedKindCityFilter;
	}

	public int getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(int municipalityId) {
		this.municipalityId = municipalityId;
	}

	public String getMunicipalityName() {
		return municipalityName;
	}

	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}
}
