package com.test.db.person.builder;

public class PersonSelectByCriteriaBuilder{

private static String sqlString = "select * from (select COUNT(*) OVER(ORDER BY 0) rows_count, a.*, ROW_NUMBER() OVER (ORDER BY 0) rnum from ( select person_id, first_name, last_name, uid, address_id, gender from registers.person where upper(first_name) = coalesce(?, upper(first_name)) and upper(last_name) = coalesce(?, upper(last_name)) and upper(uid) = coalesce(?, upper(uid)) and address_id = coalesce(?,address_id) and upper(gender) = coalesce(?, upper(gender)) order by first_name asc ) a ) b where rnum  >= coalesce(?,rnum) and rnum <= coalesce(?,rnum) ";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
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
		Param5 setParam4_AddressId(java.lang.Long addressId);

}

public static interface Param5 {
		Param6 setParam5_Gender(java.lang.String gender);

}

public static interface Param6 {
		Param7 setParam6_RowBegin(java.lang.Integer rowBegin);

}

public static interface Param7 {
		BuildStep setParam7_RowEnd(java.lang.Integer rowEnd);

}

private static class Steps implements Param1, Param2, Param3, Param4, Param5, Param6, Param7, BuildStep {

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

	public Param5 setParam4_AddressId(java.lang.Long addressId){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Long, addressId));
	return this;
	}

	public Param6 setParam5_Gender(java.lang.String gender){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, gender));
	return this;
	}

	public Param7 setParam6_RowBegin(java.lang.Integer rowBegin){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, rowBegin));
	return this;
	}

	public BuildStep setParam7_RowEnd(java.lang.Integer rowEnd){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, rowEnd));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
