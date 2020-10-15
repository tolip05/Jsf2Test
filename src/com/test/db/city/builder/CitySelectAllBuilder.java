package com.test.db.city.builder;

public class CitySelectAllBuilder{

private static String sqlString = "select * from (select COUNT(*) OVER(ORDER BY 0) rows_count, a.*, ROW_NUMBER() OVER (ORDER BY 0) rnum from ( select city_id, city_name, kind_city, municipality_id from registers.city  ) a ) b where rnum  >= coalesce(?,rnum) and rnum <= coalesce(?,rnum) ";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		Param2 setParam1_RowBegin(java.lang.Integer rowBegin);

}

public static interface Param2 {
		BuildStep setParam2_RowEnd(java.lang.Integer rowEnd);

}

private static class Steps implements Param1, Param2, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public Param2 setParam1_RowBegin(java.lang.Integer rowBegin){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, rowBegin));
	return this;
	}

	public BuildStep setParam2_RowEnd(java.lang.Integer rowEnd){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.Integer, rowEnd));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
