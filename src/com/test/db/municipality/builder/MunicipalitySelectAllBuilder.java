package com.test.db.municipality.builder;

public class MunicipalitySelectAllBuilder{

private static String sqlString = "SELECT municipality_id,municipality_name FROM registers.municipality order by municipality_name asc";

public static BuildStep create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

private static class Steps implements BuildStep {
	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString);
	}

}
}
