package com.test.helpers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cc.db.exception.EmptyResultSetException;
import com.cc.db.exception.PojoCreationException;
import com.cc.pojo.helper.PojoHelper;
import com.test.db.city.builder.CityInsertBuilder;
import com.test.db.city.builder.CityInsertFullParamBuilder;
import com.test.db.city.builder.CitySelectAllBuilder;
import com.test.db.city.builder.CitySelectAllfieldsBuilder;
import com.test.db.city.builder.CitySelectByIdBuilder;
import com.test.db.city.builder.CitySelectByMultipleIdsBuilder;
import com.test.db.city.builder.CitySelectByMunicipalityIdBuilder;
import com.test.db.city.builder.CitySelectByNameBuilder;
import com.test.db.city.builder.CityUpdateBuilder;
import com.test.models.City;

public class CityHelper {
	public static List<City> selectAll(int rowBegin, int rowEnd) throws SQLException, PojoCreationException {
		try {
			return PojoHelper.fillListOfPojos(City.class, CitySelectAllBuilder.create().setParam1_RowBegin(rowBegin)
					.setParam2_RowEnd(rowEnd).build().selectListObjects());
		} catch (EmptyResultSetException e) {
			return new ArrayList<>();
		}
	}

	public static City selectByName(String cityName) throws PojoCreationException, SQLException {
		try {
			return PojoHelper.fillPojo(City.class, CitySelectByNameBuilder.create().setParam1_City_name(cityName)
					.build().selectOneObject());
		}catch(EmptyResultSetException e) {
			return null;
		}
	}
	
	public static City selectById(long id) throws SQLException, PojoCreationException {
		try {
			return PojoHelper.fillPojo(City.class,
					CitySelectByIdBuilder.create().setParam1_City_id(id).build().selectOneObject());
		} catch (EmptyResultSetException e) {
			return null;
		}
	}

	public static long insert(City newCity) throws SQLException {
		return CityInsertBuilder.create().setParam1_CityName(newCity.getCityName())
				.setParam2_KindCity(newCity.getKindCity()).setParam3_MunicipalityId(newCity.getMunicipality().getId())
				.build().execute();
	}

	public static void update(City city) throws SQLException {
		CityUpdateBuilder.create().setParam1_CityName(city.getCityName()).setParam2_KindCity(city.getKindCity())
				.setParam3_CityId(city.getId()).build().execute();
	}

	public static long insert(String cityName, int kindCity, Long municipalityId) throws SQLException {
		return CityInsertFullParamBuilder.create().setParam1_CityName(cityName).setParam2_KindCity(kindCity)
				.setParam3_MunicipalityId(municipalityId).build().execute();
	}
	public static List<Long> selectByMunicipalityId(Long municipalityId) throws PojoCreationException, SQLException{
		try {
			return CitySelectByMunicipalityIdBuilder.create().setParam1_Municipality_id(municipalityId).build()
					.selectListObjects().stream().map(map -> Long.valueOf(map.get("city_id").toString()))
					.collect(Collectors.toList());
			
			/*
			 * return PojoHelper.fillListOfPojos(Long.class,
			 * CitySelectByMunicipalityIdBuilder.create()
			 * .setParam1_Municipality_id(municipalityId).build().selectListObjects());
			 */
		 
		}catch (EmptyResultSetException e) {
			return new ArrayList<>();
		}
	}
	
	public static List<City>selectCitiesByMunicipalityId(Long municipalityId) throws PojoCreationException, SQLException{
		try {
			return PojoHelper.fillListOfPojos(City.class, CitySelectByMunicipalityIdBuilder.create().setParam1_Municipality_id(municipalityId).build()
					.selectListObjects());
		}catch (EmptyResultSetException e) {
			return new ArrayList<>();
		}
	}
	public static List<String> selectCityNamesByIds(List<Long> citiesId) throws PojoCreationException, SQLException{
		try {
			return CitySelectByMultipleIdsBuilder.create()
					.setParam1_CitiesId(citiesId).build().selectListObjects()
					.parallelStream().map(m -> String.valueOf(m.get("city_name"))).collect(Collectors.toList());
		}catch (EmptyResultSetException e) {
			return new ArrayList<>();
		}
	}
	
	public static List<City> selectCityByMultiplyId(List<Long> ids) throws PojoCreationException, EmptyResultSetException, SQLException{
		return PojoHelper.fillListOfPojos(City.class, CitySelectByMultipleIdsBuilder.create()
				.setParam1_CitiesId(ids).build().selectListObjects());
	}
	
	public static List<City>selectAllFieldsCity() throws PojoCreationException, EmptyResultSetException, SQLException{
		return PojoHelper.fillListOfPojos(City.class, CitySelectAllfieldsBuilder.create().build().selectListObjects());
	}
	
	public static List<City> selectCityObjectByCityName(String cityName) throws PojoCreationException, EmptyResultSetException, SQLException{
		return PojoHelper.fillListOfPojos(City.class, CitySelectByNameBuilder.create()
				.setParam1_City_name(cityName).build().selectListObjects());
	}
	/*
	 * public static List<City> selectByName(String name){ try { return PojoHelper
	 * .fillListOfPojos(City.class,
	 * CitySelectByNameBuilder.create().setParam1_City_name(name).build().
	 * selectListObjects()); } catch (PojoCreationException |
	 * EmptyResultSetException | SQLException e) { return new ArrayList<>(); } }
	 */
}
