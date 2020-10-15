package com.test.db.city.builder;

public class CityInsertFullParamBuilder{

private static String sqlString = "insert into registers.city (city_name, kind_city,municipality_id) values (?,?,?)";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoUpdate build();
}

public static interface Param1 {
		Param2 setParam1_CityName(java.lang.String cityName);

}

public static interface Param2 {
		Param3 setParam2_KindCity(java.lang.Integer kindCity);

}

public static interface Param3 {
		BuildStep setParam3_MunicipalityId(java.lang.Long municipalityId);

}

private static class Steps implements Param1, Param2, Param3, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_CityName(java.lang.String cityName){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, cityName));
	return this;
	}

	public Param3 setParam2_KindCity(java.lang.Integer kindCity){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, kindCity));
	return this;
	}

	public BuildStep setParam3_MunicipalityId(java.lang.Long municipalityId){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, municipalityId));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
