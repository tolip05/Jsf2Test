package com.test.db.panda.builder;

public class PandaUserSelectByIdsBuilder{

private static String sqlString = "select user_id, user_name, email, role from panda.user where user_id = any(?)";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		BuildStep setParam1_Ids(java.util.List<java.lang.Long> ids);

}

private static class Steps implements Param1, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public BuildStep setParam1_Ids(java.util.List<java.lang.Long> ids){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.LongArray, ids));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
