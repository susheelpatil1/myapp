package com.laput.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laput.dao.HospitalQueries;
import com.laput.vo.*;

public class PatientDAO extends BaseDAO {


	public  void savePatientInfo(PatientInfo patientInfo)
    {
		try{
			createConnection();
			savePatientDetails(patientInfo);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			shutdown();
		}
		
    
    }
	
	public void checkinPatient(PatientInfo patientInfo){
		try{
			createConnection();
			checkinPatientForDoctor(patientInfo);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			shutdown();
		}
	}
	
	private void checkinPatientForDoctor(PatientInfo patientInfo){
		PreparedStatement ps = null;  

		try{
			ps = conn.prepareStatement(HospitalQueries.patient_queue_query);
			ps.setString(1, patientInfo.getQueueName());
			ps.setInt(2, patientInfo.getPatientID());

			ps.executeUpdate();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public  List<PatientInfo> searchPatient(PatientInfo patientInfo)
    {
		List<PatientInfo> list = null;
		try{
			createConnection();
			list = searchPatientInfo(patientInfo);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			shutdown();
		}
		
		return list;
    }
	
	
	private List<PatientInfo> searchPatientInfo(PatientInfo patientInfo){

		List<PatientInfo> list = null;
		PreparedStatement ps = null;  

		try{

			
			ps = conn.prepareStatement(HospitalQueries.select_patient_info);
			ps.setString(1, patientInfo.getLastName());

			ResultSet rs = ps.executeQuery();
			list = formPatientList(rs);
			ps.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//shutdown();
		}
		
		return list;
	}
	
	

	
	private List<PatientInfo> formPatientList(ResultSet rs){
		
		List<PatientInfo> list = new ArrayList<PatientInfo>();
		
		try{
			while( rs.next()){
				PatientInfo pi = new PatientInfo();
				//patient_id,first_name,last_name ,phone_no ,address_ln1,address_ln2,city,state
				pi.setPatientID(rs.getInt("patient_id"));
				pi.setFirstName(rs.getString("first_name"));
				pi.setLastName(rs.getString("last_name"));
				pi.setPhone(rs.getString("phone_no"));
				pi.setAddressLine1(rs.getString("address_ln1"));
				pi.setAddressLine2(rs.getString("address_ln2"));
				pi.setCity(rs.getString("city"));
				
				list.add(pi);
				
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		

		return list;
	}
	

    

			
	
	
	private void savePatientDetails(PatientInfo patientInfo){
		PreparedStatement ps = null;  

		try{

			
			ps = conn.prepareStatement(HospitalQueries.insert_patient_info);
			ps.setString(1, patientInfo.getFirstName());
			ps.setString(2, patientInfo.getLastName());
			ps.setString(3, patientInfo.getPhone());
			ps.setString(4, patientInfo.getAddressLine1());
			ps.setString(5, patientInfo.getAddressLine2());
			ps.setString(6, patientInfo.getCity());
			ps.setString(7, patientInfo.getState());
			ps.setString(8, "test");
			ps.setString(9, "CHECK_IN");


			ps.executeUpdate();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//shutdown();
		}
		
	}
	
	
public void updatePatientDetails(PatientInfo patientInfo){
	PreparedStatement ps = null;  

		try{

			
			ps = conn.prepareStatement(HospitalQueries.update_patient_info);
			ps.setString(1, patientInfo.getFirstName());
			ps.setString(2, patientInfo.getLastName());
			ps.setString(3, patientInfo.getPhone());
			ps.setString(4, patientInfo.getAddressLine1());
			ps.setString(5, patientInfo.getAddressLine2());
			ps.setString(6, patientInfo.getCity());
			ps.setString(7, patientInfo.getState());
			ps.setString(8, "test");
			ps.setString(9, patientInfo.getQueueName());
			ps.setInt(9, patientInfo.getPatientID());

			ps.executeUpdate();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//shutdown();
		}
		
	}
	

public  List<PatientInfo> getQueueList()
{
	List<PatientInfo> list = null;
	PreparedStatement ps = null;  

	try{
		createConnection();
		ps = conn.prepareStatement(HospitalQueries.select_patient_queue);
		ps.setString(1, "CHECK_IN");

		ResultSet rs = ps.executeQuery();
		list = formPatientList(rs);
		ps.close();
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		shutdown();
	}
	
	return list;
}



}
