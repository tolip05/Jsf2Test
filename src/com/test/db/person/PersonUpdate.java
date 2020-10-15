package com.test.db.person;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;

@CCUpdate
@Parameters(names = { "first_name", "last_name", "uid","address_id","gender","person_id" }, 
            types = { Parameters.ParamTypes.String, 
            		  Parameters.ParamTypes.String,
		              Parameters.ParamTypes.String,
		              Parameters.ParamTypes.Long,
		              Parameters.ParamTypes.String,
		              Parameters.ParamTypes.Long})
public class PersonUpdate {
	public final static String sqlString = "update registers.person set first_name = ?,last_name = ?,uid = ?, address_id = ?,gender = ? where person_id = ?";
}
