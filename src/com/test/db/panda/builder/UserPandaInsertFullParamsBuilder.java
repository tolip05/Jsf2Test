package com.test.db.panda.builder;

public class UserPandaInsertFullParamsBuilder{

private static String sqlString = "insert into panda.user (user_name,password,email,role) values (?,?,?,?)";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoUpdate build();
}

public static interface Param1 {
		Param2 setParam1_UserName(java.lang.String userName);

}

public static interface Param2 {
		Param3 setParam2_Password(java.lang.String password);

}

public static interface Param3 {
		Param4 setParam3_Email(java.lang.String email);

}

public static interface Param4 {
		BuildStep setParam4_Role(java.lang.String role);

}

private static class Steps implements Param1, Param2, Param3, Param4, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_UserName(java.lang.String userName){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, userName));
	return this;
	}

	public Param3 setParam2_Password(java.lang.String password){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, password));
	return this;
	}

	public Param4 setParam3_Email(java.lang.String email){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, email));
	return this;
	}

	public BuildStep setParam4_Role(java.lang.String role){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, role));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
