package com.test.db.address;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.cc.db.exception.EmptyResultSetException;
import com.cc.db.exception.PojoCreationException;
import com.cc.pojo.helper.PojoHelper;
import com.test.db.address.builder.AddressSelectAllBuilder;
import com.test.db.address.builder.AddressSelectByAnyCriteriasBuilder;
import com.test.db.address.builder.AddressSelectByCriteriaBuilder;
import com.test.db.municipality.builder.MunicipalitySelectAllBuilder;
import com.test.db.person.PersonBean;
import com.test.helpers.AddressHelper;
import com.test.helpers.CityHelper;
import com.test.models.Address;
import com.test.models.City;
import com.test.models.Municipality;

@ManagedBean(name = "aBean",eager=true)
@SessionScoped
public class AddressBean {
	@ManagedProperty(value="#{pBean}")
	private PersonBean personBean;
	private List<Address> addresses;
	private List<Municipality> municipalities;
	private List<City> cities;
	private Address editObject;
	private final static int PAGE_SIZE = 20;
	private int currentPage;
	private String searchName;
	private enum ACTION {
		ADD, EDIT
	}

	private ACTION currentAction;
	private long cityId;
	private int city;
	private String street;
	private int numberOfStreet;
	private long address_id;
	private Long municipalityId;

	public AddressBean() throws PojoCreationException, EmptyResultSetException, SQLException {
		this.municipalities = PojoHelper.fillListOfPojos(Municipality.class,
				MunicipalitySelectAllBuilder.create().build().selectListObjects());
		goTo(1);
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
		goTo(addresses != null && !addresses.isEmpty() ? (addresses.get(0).getRowsCount() / PAGE_SIZE)
				+ (addresses.get(0).getRowsCount() % PAGE_SIZE > 0 ? 1 : 0) : 1);
	}

	public void addAddress() throws PojoCreationException, EmptyResultSetException, SQLException {
		currentAction = ACTION.ADD;
	}

	public void save() throws SQLException {
		switch (currentAction) {
		case ADD:
			AddressHelper.insert(cityId, street, numberOfStreet);
			clearFilters();
			goTo(1);
			break;

		case EDIT:
			city = (int) cityId;
			AddressHelper.update(city, getStreet(), getNumberOfStreet(), address_id);
			clearFilters();
			break;
		}
	}

	public void selectAll() {
		clearFilters();
		goTo(1);
	}

	private void goTo(int page) { 
			currentPage = page;
			loadPageData();
	}

	private void loadPageData() {
		try {
			if(null == this.searchName || this.searchName.isEmpty()) {
				this.addresses = PojoHelper.fillListOfPojos(Address.class,
						AddressSelectByCriteriaBuilder
						.create().setParam1_City_id(city > 0 ? city : null)
								.setParam2_Street(street != null && !street.isEmpty() ? street.toUpperCase() : null)
								.setParam3_Number_of_street(numberOfStreet > 0 ? numberOfStreet : null)
								.setParam4_RowBegin(getRowBegin()).setParam5_RowEnd(getRowEnd()).build()
								.selectListObjects());
			}else {
				this.addresses = PojoHelper.fillListOfPojos(Address.class,
						AddressSelectByAnyCriteriasBuilder.create()
						.setParam1_CitiesIds(searchCityIds(this.searchName))
						.setParam2_Street(street != null && !street.isEmpty() ? street.toUpperCase() : null)
						.setParam3_Number_of_street(numberOfStreet > 0 ? numberOfStreet : null)
						.setParam4_RowBegin(getRowBegin()).setParam5_RowEnd(getRowEnd()).build()
						.selectListObjects());
			}
			
		} catch (PojoCreationException | SQLException e) {
			e.printStackTrace();
		} catch (EmptyResultSetException e) {
			addresses = new ArrayList<>();
		}
		try {
				fillCityAndMunicipality();
			
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void fillCityAndMunicipality() throws PojoCreationException, EmptyResultSetException, SQLException {

		List<Long> ids = new ArrayList<>();

		ids = addresses.stream().map(Address::getCity).map(City::getId).collect(Collectors.toList());
		fillCities(ids);
	}

	private void fillCities(List<Long> ids) throws PojoCreationException, SQLException {
		
		try {
			this.cities = CityHelper.selectCityByMultiplyId(ids);
		} catch (EmptyResultSetException e) {
			this.cities = new ArrayList<>();
		}
		Map<Long, Municipality> municipalityById = municipalities.stream()
				.collect(Collectors.toMap(Municipality::getId, x -> x));
		
		if (!cities.isEmpty()) {
			cities.forEach(city -> city.setMunicipality(municipalityById.get(city.getMunicipality().getId())));
			Map<Long, City> cityMap = cities.stream().collect(Collectors.toMap(City::getId, x -> x));
			this.addresses.forEach(address -> address.setCity(cityMap.get(address.getCity().getId())));
		}

	}

	private int getRowBegin() {
		return (currentPage - 1) * PAGE_SIZE + 1;
	}

	private int getRowEnd() {
		return getRowBegin() + PAGE_SIZE - 1;
	}

	public boolean isNextPageDisabled() {	
			return addresses != null && !addresses.isEmpty() ? addresses.get(0).getRowsCount() < currentPage * PAGE_SIZE
					: true;
	}

	public void edit(Address address) {
		currentAction = ACTION.EDIT;
		address_id = address.getId();
		cityId = address.getCity().getId();
		street = address.getStreet();
		numberOfStreet = address.getNumberOfStreet();
	}

	public void clearCollection() {
		municipalityId = null;
	}
	
	public void clearFilters() {
		personBean.selectAll();
		currentPage = 1;
		cityId = 0;
		city = 0;
		street = null;
		numberOfStreet = 0;
		searchName = null;
		municipalityId = null;
	}

	public List<Integer> searchCityIds(String cityName) throws PojoCreationException, EmptyResultSetException, SQLException{
		List<Integer> citiesIds = new ArrayList();
		cities = CityHelper.selectCityObjectByCityName(cityName);
		citiesIds = cities.stream().map(City::getId).map(a -> a.intValue())
				.collect(Collectors.toList());
		return citiesIds;
	}
	
	public void searchCytiByName() throws PojoCreationException, EmptyResultSetException, SQLException {
	    goTo(1);
	}

	public void onMunicipalityChange() throws PojoCreationException, SQLException{
		 if(municipalityId !=null && !municipalityId.equals("")) {
			 cities = CityHelper.selectCitiesByMunicipalityId(municipalityId);
		 }else {
			 cities = new ArrayList<>();
		 }
	}
	
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumberOfStreet() {
		return numberOfStreet;
	}

	public void setNumberOfStreet(int numberOfStreet) {
		this.numberOfStreet = numberOfStreet;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Municipality> getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(List<Municipality> municipalities) {
		this.municipalities = municipalities;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public Long getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(Long municipalityId) {
		this.municipalityId = municipalityId;
	}
	public void setPersonBean(PersonBean personBean) {
		this.personBean = personBean;
	}

}
