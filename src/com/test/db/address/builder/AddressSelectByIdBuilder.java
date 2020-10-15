package com.test.db.address.builder;

public class AddressSelectByIdBuilder{

private static String sqlString = "select address_id,city_id,street,number_of_street from registers.address where address_id = ?";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		BuildStep setParam1_Address_id(java.lang.Long address_id);

}

private static class Steps implements Param1, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public BuildStep setParam1_Address_id(java.lang.Long address_id){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, address_id));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
