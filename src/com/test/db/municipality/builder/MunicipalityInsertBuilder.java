package com.test.db.municipality.builder;

public class MunicipalityInsertBuilder{

private static String sqlString = "Insert into registers.municipality (municipality_name) values (?)";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoUpdate build();
}

public static interface Param1 {
		BuildStep setParam1_MunicipalityName(java.lang.String municipalityName);

}

private static class Steps implements Param1, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public BuildStep setParam1_MunicipalityName(java.lang.String municipalityName){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, municipalityName));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
