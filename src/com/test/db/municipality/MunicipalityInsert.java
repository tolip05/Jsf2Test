package com.test.db.municipality;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;
import com.cc.annotation.PojoProperty;

@CCUpdate
@Parameters(names = "municipalityName",
            types = Parameters.ParamTypes.String)
public class MunicipalityInsert {
    public final static String sqlString = "Insert into registers.municipality (municipality_name) values (?)";
}
