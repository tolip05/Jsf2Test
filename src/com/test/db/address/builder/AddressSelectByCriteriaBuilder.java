package com.test.db.address.builder;

public class AddressSelectByCriteriaBuilder{

private static String sqlString = "select * from (select COUNT(*) OVER(ORDER BY 0) rows_count, a.*, ROW_NUMBER() OVER (ORDER BY 0) rnum from ( select address_id, city_id, street ,number_of_street from registers.address where city_id = coalesce(?,city_id) and upper(street) = coalesce(?, upper(street)) and number_of_street = coalesce(?, number_of_street) ) a ) b where rnum  >= coalesce(?,rnum) and rnum <= coalesce(?,rnum) ";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		Param2 setParam1_City_id(java.lang.Integer city_id);

}

public static interface Param2 {
		Param3 setParam2_Street(java.lang.String street);

}

public static interface Param3 {
		Param4 setParam3_Number_of_street(java.lang.Integer number_of_street);

}

public static interface Param4 {
		Param5 setParam4_RowBegin(java.lang.Integer rowBegin);

}

public static interface Param5 {
		BuildStep setParam5_RowEnd(java.lang.Integer rowEnd);

}

private static class Steps implements Param1, Param2, Param3, Param4, Param5, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_City_id(java.lang.Integer city_id){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, city_id));
	return this;
	}

	public Param3 setParam2_Street(java.lang.String street){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, street));
	return this;
	}

	public Param4 setParam3_Number_of_street(java.lang.Integer number_of_street){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, number_of_street));
	return this;
	}

	public Param5 setParam4_RowBegin(java.lang.Integer rowBegin){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, rowBegin));
	return this;
	}

	public BuildStep setParam5_RowEnd(java.lang.Integer rowEnd){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, rowEnd));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
