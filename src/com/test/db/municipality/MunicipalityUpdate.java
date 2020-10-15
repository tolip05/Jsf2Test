package com.test.db.municipality;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;

@CCUpdate
@Parameters(names = {"municipalityName","municipalityId"},
            types = {Parameters.ParamTypes.String,Parameters.ParamTypes.Long})
public class MunicipalityUpdate {
    public final static String sqlString = "update registers.municipality set municipality_name = ? where municipality_id = ?";
}
