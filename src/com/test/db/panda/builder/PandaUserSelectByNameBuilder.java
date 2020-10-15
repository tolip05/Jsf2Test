package com.test.db.panda.builder;

public class PandaUserSelectByNameBuilder{

private static String sqlString = "select user_id, user_name, password, email, role from panda.user where user_name = ?";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		BuildStep setParam1_Name(java.lang.String name);

}

private static class Steps implements Param1, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public BuildStep setParam1_Name(java.lang.String name){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, name));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
