package com.test.db.person;

import com.cc.annotation.CCPagingSelect;
import com.cc.annotation.Parameters;
import com.cc.annotation.Parameters.ParamTypes;

@CCPagingSelect
@Parameters(names = { "firstName","lastName","uid","addressId","gender" }, 
            types = {ParamTypes.String,
            		 ParamTypes.String,
            		 ParamTypes.String, 
            		 ParamTypes.LongArray,
            		 ParamTypes.String})
public class PersonSelectByCriteriaAnyCity {
	public final static String sqlString = "select person_id, first_name, last_name, uid, address_id, gender"
			+ " from registers.person where "
			+ "upper(first_name) = coalesce(?, upper(first_name)) "
			+ "and upper(last_name) = coalesce(?, upper(last_name)) "
			+ "and upper(uid) = coalesce(?, upper(uid)) "
			+ "and address_id = any(?) "
			+ "and upper(gender) = coalesce(?, upper(gender))";
}
