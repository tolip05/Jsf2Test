package com.test.db.address;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCSelect
@Parameters(names = {"citiesId","street","streetNumber"},
	        types = {ParamTypes.Long,
	        		 ParamTypes.String,
	        		 ParamTypes.Integer})
public class AddressSelectByCityIdAndStreet {
	public final static String sqlString = 
			"select address_id,city_id,street,number_of_street from registers.address where city_id = ? "
			+ "and upper(street) = coalesce(?, upper(street)) "
			+ "and number_of_street = coalesce(?, number_of_street)";
}
