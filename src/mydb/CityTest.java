package mydb;

import com.cc.annotation.CCSelect;
import com.cc.annotation.Parameters;

@CCSelect
@Parameters(names = "city_id", types = Parameters.ParamTypes.Long)
public class CityTest {
	public static final String sqlString = "select city_id, city_name, kind_city, municipality_id from registers.city where city_id = ?";
}
