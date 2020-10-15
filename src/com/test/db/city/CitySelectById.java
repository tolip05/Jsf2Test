package com.test.db.city;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;

@CCSelect
@Parameters(names = "city_id", 
            types = Parameters.ParamTypes.Long)
public class CitySelectById {
	public final static String sqlString = CitySelectAll.MAIN_QUERY + " where city_id = ?";
}
