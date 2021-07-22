package com.laput.dao;

public class DBHelper {
	
	private static String Patient_info = 		"create table hospital.patient_info ("
			+"		Patient_id bigint   primary key,"
			+"		first_name varchar(50),"
			+"		last_name varchar(50),"
			+"		phone_no VARCHAR(15),"
			+"		address_ln1 varchar(100),"
			+"		address_ln2 varchar(100),"
			+"		city varchar(100),"
			+"		state varchar(50)"
			+"		);";	
			
	public void createTable(){

	}

}
