package com.test.db.city;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCUpdate
@Parameters(names = { "cityName", "kindCity","municipalityId" }, 
            types = { ParamTypes.String,
            		  ParamTypes.Integer,
            		  ParamTypes.Long })
public class CityInsertFullParam {
	public final static String sqlString = "insert into registers.city (city_name, kind_city,municipality_id) values (?,?,?)";
}
