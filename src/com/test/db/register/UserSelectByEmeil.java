package com.test.db.register;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;

@CCSelect
@Parameters(names="email", 
            types = Parameters.ParamTypes.String)
public class UserSelectByEmeil {
	public final static String sqlString = "select user_id,first_name,last_name, password from registers.users where email = ?";
}
