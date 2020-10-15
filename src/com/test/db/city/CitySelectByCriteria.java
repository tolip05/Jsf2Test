package com.test.db.city;

import com.cc.annotation.CCPagingSelect;
import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCPagingSelect
@Parameters(names = { "city_name", "kind_city", "municipality_id"}, 
            types = { ParamTypes.String, 
            		  ParamTypes.Integer, 
            		  ParamTypes.Integer})
public class CitySelectByCriteria {
	public final static String sqlString = CitySelectAll.MAIN_QUERY + " where upper(city_name) = coalesce(?, upper(city_name))"
			+ " and kind_city = coalesce(?, kind_city)"
			+ " and municipality_id = coalesce(?,municipality_id)";
}
