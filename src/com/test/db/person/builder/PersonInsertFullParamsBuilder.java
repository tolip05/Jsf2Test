package com.test.db.person.builder;

public class PersonInsertFullParamsBuilder{

private static String sqlString = "insert into registers.person (first_name,last_name,uid,address_id,gender) values (?,?,?,?,?)";

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
		Param4 setParam3_Uid(java.lang.String uid);

}

public static interface Param4 {
		Param5 setParam4_Address(java.lang.Long address);

}

public static interface Param5 {
		BuildStep setParam5_Gender(java.lang.String gender);

}

private static class Steps implements Param1, Param2, Param3, Param4, Param5, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_FirstName(java.lang.String firstName){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, firstName));
	return this;
	}

	public Param3 setParam2_LastName(java.lang.String lastName){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, lastName));
	return this;
	}

	public Param4 setParam3_Uid(java.lang.String uid){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, uid));
	return this;
	}

	public Param5 setParam4_Address(java.lang.Long address){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, address));
	return this;
	}

	public BuildStep setParam5_Gender(java.lang.String gender){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, gender));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
