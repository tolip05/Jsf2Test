package com.test.db.address;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCUpdate
@Parameters(names = { "cityId","street","numberOfStreet" }, 
            types = { ParamTypes.Long,
            		  ParamTypes.String, 
            		  ParamTypes.Integer })
public class AddressInsertFullParams {
	public final static String sqlString = "insert into registers.address (city_id,street,number_of_street) values (?,?,?)";
}
