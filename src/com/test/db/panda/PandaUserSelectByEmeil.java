package com.test.db.panda;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;

@CCSelect
@Parameters(names="email", 
            types = Parameters.ParamTypes.String)
public class PandaUserSelectByEmeil {
	public final static String sqlString = "select user_id, user_name, password, email, role from panda.user where email = ?";
}
