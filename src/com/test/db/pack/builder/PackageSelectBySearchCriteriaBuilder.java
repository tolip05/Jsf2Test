package com.test.db.pack.builder;

public class PackageSelectBySearchCriteriaBuilder{

private static String sqlString = "select * from (select COUNT(*) OVER(ORDER BY 0) rows_count, a.*, ROW_NUMBER() OVER (ORDER BY 0) rnum from ( select package_id,title, description, weight, shipping_address, status, recipient, estimated_delivery_time, user_id from panda.package where package_id = coalesce(?, package_id) and upper(title) = coalesce(?, upper(title)) and upper(description) = coalesce(? ,upper(description)) and weight = coalesce(?, weight) and shipping_address = any(?) and upper(status) = coalesce(?, upper(status)) and upper(recipient) = coalesce(?, upper(recipient)) and estimated_delivery_time = coalesce(?, estimated_delivery_time) and user_id = coalesce(?, user_id) ) a ) b where rnum  >= coalesce(?,rnum) and rnum <= coalesce(?,rnum) ";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		Param2 setParam1_Id(java.lang.Long id);

}

public static interface Param2 {
		Param3 setParam2_Title(java.lang.String title);

}

public static interface Param3 {
		Param4 setParam3_Description(java.lang.String description);

}

public static interface Param4 {
		Param5 setParam4_Weight(java.lang.Integer weight);

}

public static interface Param5 {
		Param6 setParam5_ShippingAddress(java.util.List<java.lang.Long> shippingAddress);

}

public static interface Param6 {
		Param7 setParam6_Status(java.lang.String status);

}

public static interface Param7 {
		Param8 setParam7_Recipient(java.lang.String recipient);

}

public static interface Param8 {
		Param9 setParam8_EstimatedDeliveryTime(java.util.Date estimatedDeliveryTime);

}

public static interface Param9 {
		Param10 setParam9_UserPanda(java.lang.Long userPanda);

}

public static interface Param10 {
		Param11 setParam10_RowBegin(java.lang.Integer rowBegin);

}

public static interface Param11 {
		BuildStep setParam11_RowEnd(java.lang.Integer rowEnd);

}

private static class Steps implements Param1, Param2, Param3, Param4, Param5, Param6, Param7, Param8, Param9, Param10, Param11, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_Id(java.lang.Long id){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, id));
	return this;
	}

	public Param3 setParam2_Title(java.lang.String title){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, title));
	return this;
	}

	public Param4 setParam3_Description(java.lang.String description){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, description));
	return this;
	}

	public Param5 setParam4_Weight(java.lang.Integer weight){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, weight));
	return this;
	}

	public Param6 setParam5_ShippingAddress(java.util.List<java.lang.Long> shippingAddress){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.LongArray, shippingAddress));
	return this;
	}

	public Param7 setParam6_Status(java.lang.String status){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, status));
	return this;
	}

	public Param8 setParam7_Recipient(java.lang.String recipient){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, recipient));
	return this;
	}

	public Param9 setParam8_EstimatedDeliveryTime(java.util.Date estimatedDeliveryTime){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Date, estimatedDeliveryTime));
	return this;
	}

	public Param10 setParam9_UserPanda(java.lang.Long userPanda){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, userPanda));
	return this;
	}

	public Param11 setParam10_RowBegin(java.lang.Integer rowBegin){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, rowBegin));
	return this;
	}

	public BuildStep setParam11_RowEnd(java.lang.Integer rowEnd){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, rowEnd));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
