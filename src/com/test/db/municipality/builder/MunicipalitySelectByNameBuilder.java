package com.test.db.municipality.builder;

public class MunicipalitySelectByNameBuilder{

private static String sqlString = "select municipality_id, municipality_name from registers.municipality where municipality_name = ? order by municipality_name asc";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
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


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
