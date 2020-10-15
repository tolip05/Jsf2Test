package com.test.db.address;

import com.cc.annotation.CCSelect;
import com.cc.annotation.NoParameters;

@CCSelect
@NoParameters
public class AddressSelectAllWithoutParams {
	public final static String sqlString="select address_id,city_id,street,number_of_street from registers.address";
}
