package com.laput.dao;

public class HospitalQueries {

	
	public static final String insert_patient_info = "insert into hospital.patient_info (first_name,last_name ,phone_no ,address_ln1,address_ln2,city,state,created_dt,updated_dt,updated_user,queue_name)  values(?,?,?,?,?,?,?,current_timestamp,current_timestamp,?,?)";
	
	public static final String 	insert_user_info = "insert into hospital.user_info(first_name,last_name,user_id,password,role,updated_dt) values(?,?,?,?,?,current_timestamp)";
	public static final String 	check_user_id = "select count(*) from hospital.user_info where user_id=? ";
	
	public static final String 	auth_user_id = "select role from hospital.user_info where user_id=? and password=? ";

	public static final String update_patient_info = "update hospital.patient_info set first_name=?,last_name=? ,phone_no=? ,address_ln1=?,address_ln2=?,city=?,state=?,updated_dt=current_timestamp,updated_user=?,queue_name=? where patient_id=?";

	public static final String dbURL = "jdbc:derby:C:\\Users\\su146429\\hospdb";
	
	public static final String insert_diagnosis_info = "insert into hospital.diagnosis_info (Patient_id,diagnosis_id,diagnisis_txt,updated_dt,updated_user,diagnosis_status)  values(?,?,?,current_timestamp,?,?)";
	
	public static final String insert_prescription_info = "insert into hospital.prescription_info (Patient_id,diagnosis_id,prescription_id,prescription,updated_dt,updated_user,prescription_status)  values(?,?,?,?,current_timestamp,?,?)";
	
	public static final String	select_patient_info = "select patient_id,first_name,last_name ,phone_no ,address_ln1,address_ln2,city,state from hospital.patient_info where last_name=?";
	public static final String	patient_queue_query = "update hospital.patient_info set queue_name=? where patient_id=?";
	
	public static final String	select_patient_queue = "select patient_id,first_name,last_name ,phone_no ,address_ln1,address_ln2,city,state from hospital.patient_info where queue_name=?";
	
	public static final String	select_full_diagnosis_info = "";
	
	public static final String	select_patient_info_by_id = "select patient_id,first_name,last_name ,phone_no ,address_ln1,address_ln2,city,state from hospital.patient_info where patient_id=?";
	
	public static final String	select_diagnosis_presc_info_by_id = "SELECT d.patient_id,d.diagnosis_id,p.prescription_id,d.diagnisis_txt,p.prescription,p.PRESCRIPTION_STATUS,d.updated_dt "
																	+"	FROM hospital.diagnosis_info d "
																	+"	left JOIN hospital.prescription_info p"
																	+"	ON d.patient_id = p.patient_id and d.diagnosis_id=p.diagnosis_id and p.patient_id=?"
																	+"	order by d.diagnosis_id desc";
	
	
	public static final String	select_all_active_prescrptions = "select pi.patient_id,pi.first_name,pi.last_name ,pi.phone_no ,pi.address_ln1,pi.address_ln2,pi.city,state," 
									+ " prescinfo.diagnosis_id, prescinfo.prescription_id, prescinfo.prescription"
									+ " from hospital.patient_info pi, hospital.prescription_info prescinfo"
									+ " where pi.patient_id = prescinfo.patient_id and prescinfo.prescription_status = 'OPEN'";

	public static final String	update_prescription_status = "update hospital.prescription_info set prescription_status = ?  where Patient_id=? and " 
											+ " diagnosis_id=? and  prescription_id=?";
}
