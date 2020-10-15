package com.test.db.city;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;

@CCSelect
@Parameters(names = "city_name", 
            types = Parameters.ParamTypes.String)
public class CitySelectByName {
	public final static String sqlString = CitySelectAll.MAIN_QUERY + " where city_name = ?";
}
