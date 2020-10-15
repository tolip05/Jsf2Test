package com.test.db.municipality;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCSelect
@Parameters(names = "municipalityName",
	        types = ParamTypes.String)
public class MunicipalitySelectByName {
	public final static String sqlString = 
			"select municipality_id, municipality_name from registers.municipality where municipality_name = ? order by municipality_name asc";
}
