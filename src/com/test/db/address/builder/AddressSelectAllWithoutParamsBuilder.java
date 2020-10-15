package com.test.db.address.builder;

public class AddressSelectAllWithoutParamsBuilder{

private static String sqlString = "select address_id,city_id,street,number_of_street from registers.address";

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
