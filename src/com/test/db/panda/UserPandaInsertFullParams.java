package com.test.db.panda;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;

@CCUpdate
@Parameters(names = { "userName","password","email","role" }, 
            types = { Parameters.ParamTypes.String,
            		  Parameters.ParamTypes.String,
            		  Parameters.ParamTypes.String, 
            		  Parameters.ParamTypes.String})
public class UserPandaInsertFullParams {
	public final static String sqlString = "insert into panda.user (user_name,password,email,role) values (?,?,?,?)";
}
