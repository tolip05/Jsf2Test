package com.test.db.city;

import com.cc.annotation.CCSelect;
import com.cc.annotation.NoParameters;

@CCSelect
@NoParameters
public class CitySelectAllfields {
	public final static String sqlString = "select city_id, city_name, kind_city, municipality_id from registers.city";
}
