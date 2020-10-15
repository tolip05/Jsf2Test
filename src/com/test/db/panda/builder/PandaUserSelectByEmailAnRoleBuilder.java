package com.test.db.panda.builder;

public class PandaUserSelectByEmailAnRoleBuilder{

private static String sqlString = "select user_id, user_name, password, email, role from panda.user where email = ? and role = ?";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		Param2 setParam1_Email(java.lang.String email);

}

public static interface Param2 {
		BuildStep setParam2_Role(java.lang.String role);

}

private static class Steps implements Param1, Param2, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_Email(java.lang.String email){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, email));
	return this;
	}

	public BuildStep setParam2_Role(java.lang.String role){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, role));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
