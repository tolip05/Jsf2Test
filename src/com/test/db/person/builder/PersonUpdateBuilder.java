package com.test.db.person.builder;

public class PersonUpdateBuilder{

private static String sqlString = "update registers.person set first_name = ?,last_name = ?,uid = ?, address_id = ?,gender = ? where person_id = ?";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoUpdate build();
}

public static interface Param1 {
		Param2 setParam1_First_name(java.lang.String first_name);

}

public static interface Param2 {
		Param3 setParam2_Last_name(java.lang.String last_name);

}

public static interface Param3 {
		Param4 setParam3_Uid(java.lang.String uid);

}

public static interface Param4 {
		Param5 setParam4_Address_id(java.lang.Long address_id);

}

public static interface Param5 {
		Param6 setParam5_Gender(java.lang.String gender);

}

public static interface Param6 {
		BuildStep setParam6_Person_id(java.lang.Long person_id);

}

private static class Steps implements Param1, Param2, Param3, Param4, Param5, Param6, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_First_name(java.lang.String first_name){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, first_name));
	return this;
	}

	public Param3 setParam2_Last_name(java.lang.String last_name){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, last_name));
	return this;
	}

	public Param4 setParam3_Uid(java.lang.String uid){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, uid));
	return this;
	}

	public Param5 setParam4_Address_id(java.lang.Long address_id){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, address_id));
	return this;
	}

	public Param6 setParam5_Gender(java.lang.String gender){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, gender));
	return this;
	}

	public BuildStep setParam6_Person_id(java.lang.Long person_id){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, person_id));
	return this;
	}


	public com.cc.pojo.PojoUpdate build() {
		return new com.cc.pojo.PojoUpdate(this.getClass().getName(), sqlString, params);
	}

}
}
