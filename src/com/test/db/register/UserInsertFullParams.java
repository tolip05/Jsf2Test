package com.test.db.register;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCUpdate
@Parameters(names = { "firstName","lastName","email","password"}, 
            types = {ParamTypes.String,
            		 ParamTypes.String,
            		 ParamTypes.String,
            		 ParamTypes.String})
public class UserInsertFullParams {
	public final static String sqlString = "insert into registers.users "
			+ "(first_name,last_name,email,password) values (?,?,?,?)";
}
