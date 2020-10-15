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
import com.test.db.city.builder.CitySelectAllfieldsBuilder;
import com.test.db.municipality.builder.MunicipalitySelectByMultipleIdsBuilder;
import com.test.models.City;
import com.test.models.Municipality;

@ManagedBean
@SessionScoped
public class TestBean {
private List<City> cities;


public TestBean() throws PojoCreationException, EmptyResultSetException, SQLException {
	this.cities = PojoHelper
			.fillListOfPojos(City.class, CitySelectAllfieldsBuilder.create().build().selectListObjects());
	filMunicipality();
}

private void filMunicipality() throws PojoCreationException, EmptyResultSetException, SQLException {
	Set<Long>municipalityIds = cities.stream()
			.map(City::getMunicipality).map(Municipality::getId).collect(Collectors.toSet());
	List<Municipality>municipalities = PojoHelper.fillListOfPojos(Municipality.class, MunicipalitySelectByMultipleIdsBuilder
			.create().setParam1_MunicipalityIds(new ArrayList<>(municipalityIds)).build().selectListObjects());
	Map<Long,Municipality>municipalityId = municipalities.stream().collect(Collectors.toMap(Municipality::getId, x->x));
	cities.forEach(city -> city.setMunicipality(municipalityId.get(city.getMunicipality().getId())));
	
}
public List<City> getCities() {
	
	return cities;
}

public void setCities(List<City> cities) {
	this.cities = cities;
}

}
