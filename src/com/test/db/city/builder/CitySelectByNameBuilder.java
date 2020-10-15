package com.test.db.city.builder;

public class CitySelectByNameBuilder{

private static String sqlString = "select city_id, city_name, kind_city, municipality_id from registers.city  where city_name = ?";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		BuildStep setParam1_City_name(java.lang.String city_name);

}

private static class Steps implements Param1, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public BuildStep setParam1_City_name(java.lang.String city_name){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, city_name));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
