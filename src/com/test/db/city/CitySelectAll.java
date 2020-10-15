package com.test.db.city;

import com.cc.annotation.CCPagingSelect;
import com.cc.annotation.NoParameters;

@CCPagingSelect
@NoParameters
public class CitySelectAll {
	public final static String MAIN_QUERY = "select city_id, city_name, kind_city, municipality_id from registers.city ";
	public final static String sqlString = MAIN_QUERY;
}
