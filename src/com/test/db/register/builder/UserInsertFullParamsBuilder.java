package com.test.db.register.builder;

public class UserInsertFullParamsBuilder{

private static String sqlString = "insert into registers.users (first_name,last_name,email,password) values (?,?,?,?)";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoUpdate build();
}

public static interface Param1 {
		Param2 setParam1_FirstName(java.lang.String firstName);

}

public static interface Param2 {
		Param3 setParam2_LastName(java.lang.String lastName);

}

public static interface Param3 {
		Param4 setParam3_Email(java.lang.String email);

}

public static interface Param4 {
		BuildStep setParam4_Password(java.lang.String password);

}

private static class Steps implements Param1, Param2, Param3, Param4, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_FirstName(java.lang.String firstName){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, firstName));
	return this;
	}

	public Param3 setParam2_LastName(java.lang.String lastName){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, lastName));
	return this;
	}

	public Param4 setParam3_Email(java.lang.String email){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, email));
	return this;
	}

	public BuildStep setParam4_Password(java.lang.String password){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, password));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
