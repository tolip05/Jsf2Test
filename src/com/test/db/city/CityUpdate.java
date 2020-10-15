package com.test.db.city;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;

@CCUpdate
@Parameters(names = { "cityName", "kindCity", "cityId" }, 
            types = { Parameters.ParamTypes.String, 
            		  Parameters.ParamTypes.Integer, 
            		  Parameters.ParamTypes.Long})
public class CityUpdate {
    public final static String sqlString = "update registers.city set city_name = ?, kind_city = ? where city_id = ?";
}