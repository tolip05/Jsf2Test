package com.test.db.pack.builder;

public class PackageInsertIntoDbBuilder{

private static String sqlString = "insert into panda.package (description,weight,estimated_delivery_time,recipient,status,user_id,shipping_address,title) values (?,?,?,?,?,?,?,?)";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoUpdate build();
}

public static interface Param1 {
		Param2 setParam1_Description(java.lang.String description);

}

public static interface Param2 {
		Param3 setParam2_Weight(java.lang.Integer weight);

}

public static interface Param3 {
		Param4 setParam3_EstimatedDate(java.util.Date estimatedDate);

}

public static interface Param4 {
		Param5 setParam4_Recipient(java.lang.String recipient);

}

public static interface Param5 {
		Param6 setParam5_Status(java.lang.String status);

}

public static interface Param6 {
		Param7 setParam6_UserId(java.lang.Long userId);

}

public static interface Param7 {
		Param8 setParam7_AddressId(java.lang.Long addressId);

}

public static interface Param8 {
		BuildStep setParam8_Title(java.lang.String title);

}

private static class Steps implements Param1, Param2, Param3, Param4, Param5, Param6, Param7, Param8, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_Description(java.lang.String description){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, description));
	return this;
	}

	public Param3 setParam2_Weight(java.lang.Integer weight){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, weight));
	return this;
	}

	public Param4 setParam3_EstimatedDate(java.util.Date estimatedDate){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Date, estimatedDate));
	return this;
	}

	public Param5 setParam4_Recipient(java.lang.String recipient){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, recipient));
	return this;
	}

	public Param6 setParam5_Status(java.lang.String status){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, status));
	return this;
	}

	public Param7 setParam6_UserId(java.lang.Long userId){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, userId));
	return this;
	}

	public Param8 setParam7_AddressId(java.lang.Long addressId){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, addressId));
	return this;
	}

	public BuildStep setParam8_Title(java.lang.String title){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, title));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
