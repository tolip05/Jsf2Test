package com.test.helpers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cc.db.exception.EmptyResultSetException;
import com.cc.db.exception.PojoCreationException;
import com.cc.pojo.helper.PojoHelper;
import com.test.db.address.builder.AddressInsertFullParamsBuilder;
import com.test.db.address.builder.AddressSelectAllBuilder;
import com.test.db.address.builder.AddressSelectAllWithoutParamsBuilder;
import com.test.db.address.builder.AddressSelectByIdCityBuilder;
import com.test.db.address.builder.AddressSelectByIdsBuilder;
import com.test.db.address.builder.AddressSelectByIdsCityBuilder;
import com.test.db.address.builder.AddressUpdateBuilder;
import com.test.models.Address;
import com.test.models.City;

public class AddressHelper {
	public static long insert(long id,String street,int streetNumber) throws SQLException {
		return AddressInsertFullParamsBuilder.create().setParam1_CityId(id)
				.setParam2_Street(street)
				.setParam3_NumberOfStreet(streetNumber).build().execute();
	}
	
	public static List<Address> selectAll(int rowBegin, int rowEnd) throws PojoCreationException, SQLException{
		try {
			return PojoHelper.fillListOfPojos(Address.class, AddressSelectAllBuilder.create()
					.setParam1_RowBegin(rowBegin).setParam2_RowEnd(rowEnd).build().selectListObjects());
		}catch(EmptyResultSetException e) {
			return new ArrayList<>();
		}
	}
	public static void update(int city_id,String street,int numbreOfStreet,long address_id) throws SQLException {
		 AddressUpdateBuilder.create().setParam1_City_id(city_id).setParam2_Street(street)
				.setParam3_Number_of_street(numbreOfStreet).setParam4_Address_id(address_id).build().execute();
	}
	public static List<Address> selectAddressiesByCitiesIds(List<Integer>ids) throws PojoCreationException, EmptyResultSetException, SQLException {
		return PojoHelper.fillListOfPojos(Address.class, AddressSelectByIdsCityBuilder.create()
				.setParam1_CitiesId(ids).build().selectListObjects());
	}
	
	public static List<Address> selectAllAddressWithOut() throws PojoCreationException, EmptyResultSetException, SQLException{
		return PojoHelper.fillListOfPojos(Address.class, AddressSelectAllWithoutParamsBuilder.create()
				.build().selectListObjects());
	}
	
	public static List<Address> selectAddressByIds (List<Long> addressesIds) throws PojoCreationException, EmptyResultSetException, SQLException{
		return PojoHelper.fillListOfPojos(Address.class, AddressSelectByIdsBuilder.create()
				.setParam1_AddressesId(addressesIds).build().selectListObjects());
	}
	
	public static List<Address> selectAddressByCityId(Long cityId) throws PojoCreationException, EmptyResultSetException, SQLException{
		return PojoHelper.fillListOfPojos(Address.class, AddressSelectByIdCityBuilder
				.create().setParam1_CityId(cityId).build().selectListObjects());
	}
}
