package com.test.db.address;

import com.cc.annotation.CCPagingSelect;
import com.cc.annotation.NoParameters;

@CCPagingSelect
@NoParameters
public class AddressSelectAll {
	public final static String sqlString="select address_id,city_id,street,number_of_street from registers.address";
}
