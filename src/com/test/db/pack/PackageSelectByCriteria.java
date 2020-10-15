package com.test.db.pack;

import com.cc.annotation.CCPagingSelect;
import com.cc.annotation.Parameters;

@CCPagingSelect
@Parameters(names = { "id","title","description","weight","shippingAddress","status",
		              "recipient","estimatedDeliveryTime","userPanda" }, 
            types = { Parameters.ParamTypes.Long,
            		  Parameters.ParamTypes.String, 
            		  Parameters.ParamTypes.String, 
            		  Parameters.ParamTypes.Integer,
		              Parameters.ParamTypes.Long, 
		              Parameters.ParamTypes.String,
		              Parameters.ParamTypes.String,
		              Parameters.ParamTypes.Date, 
		              Parameters.ParamTypes.Long})
public class PackageSelectByCriteria {
	public final static String sqlString = "select package_id, title, description, weight, shipping_address, status, recipient, estimated_delivery_time, user_id from panda.package where "
			+ "package_id = coalesce(?, package_id) "
			+ "and upper(title) = coalesce(?, upper(title)) "
			+ "and upper(description) = coalesce(? ,upper(description)) "
			+ "and weight = coalesce(?, weight) "
			+ "and shipping_address = coalesce(?, shipping_address) "
			+ "and upper(status) = coalesce(?, upper(status)) "
			+ "and upper(recipient) = coalesce(?, upper(recipient)) "
			+ "and estimated_delivery_time = coalesce(?, estimated_delivery_time) "
			+ "and user_id = coalesce(?, user_id)";
}