package com.test.db.panda.builder;

public class PandaUserSelectByRoleBuilder{

private static String sqlString = "select user_id, user_name, password from panda.user where role = ?";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		BuildStep setParam1_Role(java.lang.String role);

}

private static class Steps implements Param1, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public BuildStep setParam1_Role(java.lang.String role){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, role));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
