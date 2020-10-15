package com.test.helpers;

import java.sql.SQLException;

import com.test.db.person.builder.PersonInsertFullParamsBuilder;
import com.test.db.person.builder.PersonUpdateBuilder;
import com.test.models.Person;

public class PersonHelper {
	public static long insertPerson(Person person) throws SQLException {
		return PersonInsertFullParamsBuilder.create().setParam1_FirstName(person.getFirstName())
				.setParam2_LastName(person.getLastName()).setParam3_Uid(person.getUid())
				.setParam4_Address(person.getAddress().getId()).setParam5_Gender(person.getGender()).build().execute();
	}

	public static void update(Person person) throws SQLException {
		PersonUpdateBuilder.create().setParam1_First_name(person.getFirstName())
				.setParam2_Last_name(person.getLastName()).setParam3_Uid(person.getUid())
				.setParam4_Address_id(person.getAddress().getId()).setParam5_Gender(person.getGender())
				.setParam6_Person_id(person.getId()).build().execute();
	}
}
