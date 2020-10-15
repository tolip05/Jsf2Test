package com.test.db.municipality.builder;

public class MunicipalityUpdateBuilder{

private static String sqlString = "update registers.municipality set municipality_name = ? where municipality_id = ?";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoUpdate build();
}

public static interface Param1 {
		Param2 setParam1_MunicipalityName(java.lang.String municipalityName);

}

public static interface Param2 {
		BuildStep setParam2_MunicipalityId(java.lang.Long municipalityId);

}

private static class Steps implements Param1, Param2, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_MunicipalityName(java.lang.String municipalityName){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, municipalityName));
	return this;
	}

	public BuildStep setParam2_MunicipalityId(java.lang.Long municipalityId){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, municipalityId));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
