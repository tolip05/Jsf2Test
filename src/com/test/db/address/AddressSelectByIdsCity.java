package com.test.db.address;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCSelect
@Parameters(names = "citiesId",
	        types = ParamTypes.IntegerArray)
public class AddressSelectByIdsCity {
   public final static String sqlString =
		   "select address_id , city_id, street, number_of_street from registers.address where city_id = any(?)";
}
