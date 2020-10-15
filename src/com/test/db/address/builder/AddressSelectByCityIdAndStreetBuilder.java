package com.test.db.address.builder;

public class AddressSelectByCityIdAndStreetBuilder{

private static String sqlString = "select address_id,city_id,street,number_of_street from registers.address where city_id = ? and upper(street) = coalesce(?, upper(street)) and number_of_street = coalesce(?, number_of_street)";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		Param2 setParam1_CitiesId(java.lang.Long citiesId);

}

public static interface Param2 {
		Param3 setParam2_Street(java.lang.String street);

}

public static interface Param3 {
		BuildStep setParam3_StreetNumber(java.lang.Integer streetNumber);

}

private static class Steps implements Param1, Param2, Param3, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_CitiesId(java.lang.Long citiesId){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, citiesId));
	return this;
	}

	public Param3 setParam2_Street(java.lang.String street){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, street));
	return this;
	}

	public BuildStep setParam3_StreetNumber(java.lang.Integer streetNumber){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, streetNumber));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
