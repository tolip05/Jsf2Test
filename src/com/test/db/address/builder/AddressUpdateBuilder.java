package com.test.db.address.builder;

public class AddressUpdateBuilder{

private static String sqlString = "update registers.address set city_id = ?,street = ?, number_of_street = ? where address_id = ?";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoUpdate build();
}

public static interface Param1 {
		Param2 setParam1_City_id(java.lang.Integer city_id);

}

public static interface Param2 {
		Param3 setParam2_Street(java.lang.String street);

}

public static interface Param3 {
		Param4 setParam3_Number_of_street(java.lang.Integer number_of_street);

}

public static interface Param4 {
		BuildStep setParam4_Address_id(java.lang.Long address_id);

}

private static class Steps implements Param1, Param2, Param3, Param4, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_City_id(java.lang.Integer city_id){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, city_id));
	return this;
	}

	public Param3 setParam2_Street(java.lang.String street){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, street));
	return this;
	}

	public Param4 setParam3_Number_of_street(java.lang.Integer number_of_street){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, number_of_street));
	return this;
	}

	public BuildStep setParam4_Address_id(java.lang.Long address_id){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, address_id));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
