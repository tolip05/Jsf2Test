package com.test.db.address;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;

@CCUpdate
@Parameters(names = { "city_id", "street", "number_of_street","address_id" }, 
types = { Parameters.ParamTypes.Integer, Parameters.ParamTypes.String, Parameters.ParamTypes.Integer,Parameters.ParamTypes.Long})
public class AddressUpdate {
	public final static String sqlString = "update registers.address set city_id = ?,street = ?, number_of_street = ? where address_id = ?";
}
