package com.test.db.pack.builder;

public class PackageUpdateWithoutDateBuilder{

private static String sqlString = "update panda.package set description = ?,weight = ?,status = ?, shipping_address = ?,title = ?, recipient = ?  where package_id = ?";

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
		Param4 setParam3_Status(java.lang.String status);

}

public static interface Param4 {
		Param5 setParam4_Shipping_address(java.lang.Long shipping_address);

}

public static interface Param5 {
		Param6 setParam5_Title(java.lang.String title);

}

public static interface Param6 {
		Param7 setParam6_Recipient(java.lang.String recipient);

}

public static interface Param7 {
		BuildStep setParam7_Package_id(java.lang.Long package_id);

}

private static class Steps implements Param1, Param2, Param3, Param4, Param5, Param6, Param7, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_Description(java.lang.String description){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, description));
	return this;
	}

	public Param3 setParam2_Weight(java.lang.Integer weight){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, weight));
	return this;
	}

	public Param4 setParam3_Status(java.lang.String status){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, status));
	return this;
	}

	public Param5 setParam4_Shipping_address(java.lang.Long shipping_address){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, shipping_address));
	return this;
	}

	public Param6 setParam5_Title(java.lang.String title){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, title));
	return this;
	}

	public Param7 setParam6_Recipient(java.lang.String recipient){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, recipient));
	return this;
	}

	public BuildStep setParam7_Package_id(java.lang.Long package_id){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, package_id));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
