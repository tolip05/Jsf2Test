package com.test.db.city;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;
@CCSelect
@Parameters(names = "citiesId",
	        types = ParamTypes.LongArray)
public class CitySelectByMultipleIds {
	    public final static String sqlString = CitySelectAll.MAIN_QUERY +  " where city_id = any(?)";
}
