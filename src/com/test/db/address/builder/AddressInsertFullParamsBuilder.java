package com.test.db.address.builder;

public class AddressInsertFullParamsBuilder{

private static String sqlString = "insert into registers.address (city_id,street,number_of_street) values (?,?,?)";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoUpdate build();
}

public static interface Param1 {
		Param2 setParam1_CityId(java.lang.Long cityId);

}

public static interface Param2 {
		Param3 setParam2_Street(java.lang.String street);

}

public static interface Param3 {
		BuildStep setParam3_NumberOfStreet(java.lang.Integer numberOfStreet);

}

private static class Steps implements Param1, Param2, Param3, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_CityId(java.lang.Long cityId){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, cityId));
	return this;
	}

	public Param3 setParam2_Street(java.lang.String street){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, street));
	return this;
	}

	public BuildStep setParam3_NumberOfStreet(java.lang.Integer numberOfStreet){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, numberOfStreet));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
