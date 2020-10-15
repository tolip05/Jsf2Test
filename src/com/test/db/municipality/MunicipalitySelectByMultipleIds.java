package com.test.db.municipality;

import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;
import com.cc.annotation.CCSelect;

@CCSelect
@Parameters(names = "municipalityIds",
	        types = ParamTypes.LongArray)
public class MunicipalitySelectByMultipleIds {
    public final static String sqlString = "select municipality_id, municipality_name from registers.municipality where municipality_id = any(?) order by municipality_name asc";
}
