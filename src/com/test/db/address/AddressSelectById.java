package com.test.db.address;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;
import com.test.db.city.CitySelectAll;

@CCSelect
@Parameters(names="address_id", 
            types = Parameters.ParamTypes.Long)
public class AddressSelectById {
	public final static String sqlString = "select address_id,city_id,street,number_of_street from registers.address where address_id = ?";
}
