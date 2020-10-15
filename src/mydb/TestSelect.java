package mydb;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;

@CCSelect
@Parameters(names="email", types = Parameters.ParamTypes.String)
public class TestSelect {
	public final static String sqlString = "select user_id, user_name, password from posta.users where email = ?";
}
