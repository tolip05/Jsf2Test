package com.test.db.pack;

import com.cc.annotation.CCUpdate;
import com.cc.annotation.Parameters;

@CCUpdate
@Parameters(names = { "description", "weight", "status", "shipping_address", "title",
		"recipient", "package_id" },
types = { Parameters.ParamTypes.String, Parameters.ParamTypes.Integer,
		  Parameters.ParamTypes.String, Parameters.ParamTypes.Long,
		  Parameters.ParamTypes.String, Parameters.ParamTypes.String,
		  Parameters.ParamTypes.Long })
public class PackageUpdateWithoutDate {
	public final static String sqlString =
			"update panda.package set description = ?,weight = ?,status = ?, shipping_address = ?,title = ?, recipient = ?  where package_id = ?";
}
