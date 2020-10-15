package com.test.db.panda;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;

@CCSelect
@Parameters(names = "name", 
            types = Parameters.ParamTypes.String)
public class PandaUserSelectByName {
	public final static String sqlString = "select user_id, user_name, password, email, role from panda.user where user_name = ?";
}
