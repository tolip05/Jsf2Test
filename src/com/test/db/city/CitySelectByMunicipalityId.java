package com.test.db.city;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCSelect
@Parameters(names = "municipality_id",
	        types = ParamTypes.Long)
public class CitySelectByMunicipalityId {
	 public final static String sqlString= CitySelectAll.MAIN_QUERY +  " where municipality_id = ?";
}
