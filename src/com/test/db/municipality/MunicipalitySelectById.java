package com.test.db.municipality;

import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;
import com.cc.annotation.CCSelect;

@CCSelect
@Parameters(names = "municipalityId",
	        types = ParamTypes.Long)
public class MunicipalitySelectById {
    public final static String sqlString = "select municipality_id, municipality_name from registers.municipality where municipality_id = ?";
}
