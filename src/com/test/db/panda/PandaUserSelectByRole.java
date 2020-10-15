package com.test.db.panda;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;

@CCSelect
@Parameters(names="role", 
            types = Parameters.ParamTypes.String)
public class PandaUserSelectByRole {
	public final static String sqlString = "select user_id, user_name, password from panda.user where role = ?";
}
