package com.test.db.city.builder;

public class CitySelectByCriteriaBuilder{

private static String sqlString = "select * from (select COUNT(*) OVER(ORDER BY 0) rows_count, a.*, ROW_NUMBER() OVER (ORDER BY 0) rnum from ( select city_id, city_name, kind_city, municipality_id from registers.city  where upper(city_name) = coalesce(?, upper(city_name)) and kind_city = coalesce(?, kind_city) and municipality_id = coalesce(?,municipality_id) ) a ) b where rnum  >= coalesce(?,rnum) and rnum <= coalesce(?,rnum) ";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		Param2 setParam1_City_name(java.lang.String city_name);

}

public static interface Param2 {
		Param3 setParam2_Kind_city(java.lang.Integer kind_city);

}

public static interface Param3 {
		Param4 setParam3_Municipality_id(java.lang.Integer municipality_id);

}

public static interface Param4 {
		Param5 setParam4_RowBegin(java.lang.Integer rowBegin);

}

public static interface Param5 {
		BuildStep setParam5_RowEnd(java.lang.Integer rowEnd);

}

private static class Steps implements Param1, Param2, Param3, Param4, Param5, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_City_name(java.lang.String city_name){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, city_name));
	return this;
	}

	public Param3 setParam2_Kind_city(java.lang.Integer kind_city){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, kind_city));
	return this;
	}

	public Param4 setParam3_Municipality_id(java.lang.Integer municipality_id){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, municipality_id));
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
