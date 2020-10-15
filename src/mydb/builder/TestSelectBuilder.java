package mydb.builder;

public class TestSelectBuilder{

private static String sqlString = "select user_id, user_name, password from posta.users where email = ?";

public static Param1 create() {
	return new Steps();
}

public static interface BuildStep {
	com.cc.pojo.PojoSelect build();
}

public static interface Param1 {
		BuildStep setParam1_Email(java.lang.String email);

}

private static class Steps implements Param1, BuildStep {

private java.util.List<com.cc.pojo.Param> params = new java.util.ArrayList<com.cc.pojo.Param>();

	public BuildStep setParam1_Email(java.lang.String email){
		params.add(new com.cc.pojo.Param(com.cc.annotation.Parameters.ParamTypes.String, email));
	return this;
	}


	public com.cc.pojo.PojoSelect build() {
		return new com.cc.pojo.PojoSelect(this.getClass().getName(), sqlString, params);
	}

}
}
