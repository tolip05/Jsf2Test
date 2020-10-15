package com.test.db.address;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;
import com.test.db.city.CitySelectAll;
@CCSelect
@Parameters(names = "addressesId",
	        types = ParamTypes.LongArray)
public class AddressSelectByIds {
	public final static String sqlString = 
			"select address_id,city_id,street,number_of_street from registers.address where address_id = any(?)";
	
}
