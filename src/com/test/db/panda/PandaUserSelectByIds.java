package com.test.db.panda;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;

@CCSelect
@Parameters(names="ids", 
            types = Parameters.ParamTypes.LongArray)
public class PandaUserSelectByIds {
	public final static String sqlString = "select user_id, user_name, email, role from panda.user where user_id = any(?)";
}
