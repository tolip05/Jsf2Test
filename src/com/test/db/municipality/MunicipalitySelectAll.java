package com.test.db.municipality;

import com.cc.annotation.CCSelect;
import com.cc.annotation.NoParameters;

@CCSelect
@NoParameters
public class MunicipalitySelectAll {
	public final static String sqlString = "SELECT municipality_id,municipality_name FROM registers.municipality order by municipality_name asc";
}
