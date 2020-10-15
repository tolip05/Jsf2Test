package com.test.db.address;

import com.cc.annotation.CCPagingSelect;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCPagingSelect
@Parameters(names = { "citiesIds", "street","number_of_street"}, 
            types = { ParamTypes.IntegerArray,
            		  ParamTypes.String,
            		  ParamTypes.Integer})
public class AddressSelectByAnyCriterias {
	public final static String sqlString = 
			"select address_id,city_id,street,number_of_street from registers.address where city_id = any(?) "
			+ "and upper(street) = coalesce(?, upper(street)) "
			+ "and number_of_street = coalesce(?, number_of_street)";
}
