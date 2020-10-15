package com.test.db.pack;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;

@CCUpdate
@Parameters(names = {"description","weight","estimatedDate", "recipient",
		             "status","userId","addressId","title"}, 
            types = { Parameters.ParamTypes.String,
            		  Parameters.ParamTypes.Integer,
            		  Parameters.ParamTypes.Date,
            		  Parameters.ParamTypes.String,
            		  Parameters.ParamTypes.String,
            		  Parameters.ParamTypes.Long,
		              Parameters.ParamTypes.Long,
		              Parameters.ParamTypes.String })
public class PackageInsertIntoDb {
	public final static String sqlString = "insert into panda.package (description,weight,estimated_delivery_time,recipient,status,user_id,shipping_address,title)"
			+ " values (?,?,?,?,?,?,?,?)";
}
