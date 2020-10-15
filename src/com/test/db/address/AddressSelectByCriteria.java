package com.test.db.address;

import com.cc.annotation.CCPagingSelect;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCPagingSelect
@Parameters(names = { "city_id", "street","number_of_street"}, 
            types = { ParamTypes.Integer,
            		  ParamTypes.String,
            		  ParamTypes.Integer})
public class AddressSelectByCriteria {
	public final static String sqlString = "select address_id, city_id, street ,number_of_street from registers.address where "
			+ "city_id = coalesce(?,city_id) "
			+ "and upper(street) = coalesce(?, upper(street)) "
			+ "and number_of_street = coalesce(?, number_of_street)";
}
