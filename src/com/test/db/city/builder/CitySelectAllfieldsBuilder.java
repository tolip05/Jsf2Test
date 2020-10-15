package com.test.db.city.builder;

public class CitySelectAllfieldsBuilder{

private static String sqlString = "select city_id, city_name, kind_city, municipality_id from registers.city";

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
