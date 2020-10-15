package com.test.db.person;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCUpdate
@Parameters(names = { "firstName","lastName","uid","address","gender" }, 
            types = {ParamTypes.String,
            		 ParamTypes.String,
            		 ParamTypes.String, 
            		 ParamTypes.Long,
            		 ParamTypes.String})
public class PersonInsertFullParams {
	public final static String sqlString = "insert into registers.person "
			+ "(first_name,last_name,uid,address_id,gender) values (?,?,?,?,?)";
}
