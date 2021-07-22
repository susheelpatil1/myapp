package com.laput.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.laput.vo.DiagnosisInfo;
import com.laput.vo.PatientInfo;
import com.laput.vo.PrescriptionInfo;

public class DiagnosisDAO extends BaseDAO{

	
	//Patient_id,diagnisis_txt,updated_dt,updated_user,diagnosis_status
	
	public void saveDiagnosisandPresc(PatientInfo patientInfo){
		
		try{
			createConnection();
			int diagId = getDiagnosisId();
			
			patientInfo.getDiagnosisInfo().setDiagnosisId(diagId);
			patientInfo.getDiagnosisInfo().getPrescription().setDiagnosisId(diagId);
			patientInfo.getDiagnosisInfo().getPrescription().setPrescriptionId(getPrescriptionId());
			saveDiagnosisInfo(patientInfo);
			
			if(patientInfo.getPrescriptionTxt() != null){
				savePrescriptionInfo(patientInfo);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			shutdown();
		}
		
	}
	
	private int getPrescriptionId(){

		ResultSet rs = null;
		int val = 0;
		PreparedStatement ps = null;  

		try{
			ps = conn.prepareStatement("VALUES (NEXT VALUE FOR prescription_id_seq)");
			rs = ps.executeQuery();
			while( rs.next()){
				val = rs.getInt(1);
			}
			ps.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return val;
			
	}
	
	private int getDiagnosisId(){
		ResultSet rs = null;
		int val = 0;
		PreparedStatement ps = null;  

		try{
			ps = conn.prepareStatement("VALUES (NEXT VALUE FOR diagnosis_id_seq)");
			rs = ps.executeQuery();
			while( rs.next()){
				val = rs.getInt(1);
			}
			ps.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return val;
	}
	
	private void saveDiagnosisInfo(PatientInfo patientInfo){
		
		PreparedStatement ps = null;  

		try{
			//Patient_id,diagnosis_id,diagnisis_txt,updated_dt,updated_user,diagnosis_status
			ps = conn.prepareStatement(HospitalQueries.insert_diagnosis_info);
			ps.setInt(1, patientInfo.getPatientID());
			ps.setInt(2, patientInfo.getDiagnosisInfo().getDiagnosisId());

			ps.setString(3, patientInfo.getDiagnosisTxt());
			ps.setString(4, "test user");
			ps.setString(5, "");
	
			ps.executeUpdate();
			ps.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	private void savePrescriptionInfo(PatientInfo patientInfo){
			
			PreparedStatement ps = null;  

			
			try{
				//Patient_id,diagnosis_id,diagnisis_txt,updated_dt,updated_user,diagnosis_status
				ps = conn.prepareStatement(HospitalQueries.insert_prescription_info);
				ps.setInt(1, patientInfo.getPatientID());
				ps.setInt(2, patientInfo.getDiagnosisInfo().getPrescription().getDiagnosisId());
				ps.setInt(3, patientInfo.getDiagnosisInfo().getPrescription().getPrescriptionId());


				ps.setString(4, patientInfo.getPrescriptionTxt());
				ps.setString(5, "test user");
				ps.setString(6, "OPEN");
		
				ps.executeUpdate();
				ps.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
	}
	
	
	
	public PatientInfo getFullDiagnosisData(int id){
		
		PreparedStatement ps = null;  
		PatientInfo pi = null;

		try{
			createConnection();
			ps = conn.prepareStatement(HospitalQueries.select_patient_info_by_id);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			pi = formPatientData(rs);
			ps.close();
			
			//form diagnosis data
			ps = conn.prepareStatement(HospitalQueries.select_diagnosis_presc_info_by_id);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			pi.setOldDiagnosisList(formDiagnosisList(rs));
			ps.close();		
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			shutdown();
		}
		
		return pi;
	}

	
	private PatientInfo formPatientData(ResultSet rs){
		
		PatientInfo pi = new PatientInfo();
		
		try{
			while( rs.next()){
				//patient_id,first_name,last_name ,phone_no ,address_ln1,address_ln2,city,state
				pi.setPatientID(rs.getInt("patient_id"));
				pi.setFirstName(rs.getString("first_name"));
				pi.setLastName(rs.getString("last_name"));
				pi.setPhone(rs.getString("phone_no"));
				pi.setAddressLine1(rs.getString("address_ln1"));
				pi.setAddressLine2(rs.getString("address_ln2"));
				pi.setCity(rs.getString("city"));
				
				
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally{
		}
		

		return pi;
	}
	

	private List<DiagnosisInfo> formDiagnosisList(ResultSet rs){
		
		List<DiagnosisInfo> list = new ArrayList<DiagnosisInfo>();
		
		try{
			while( rs.next()){
				DiagnosisInfo di = new DiagnosisInfo();
				//patient_id,first_name,last_name ,phone_no ,address_ln1,address_ln2,city,state
				di.setDiagnosisId(rs.getInt("diagnosis_id"));
				di.setDiagnosisTxt(rs.getString("diagnisis_txt"));
				di.getPrescription().setDiagnosisId(rs.getInt("diagnosis_id"));
				di.getPrescription().setPrescriptionId(rs.getInt("prescription_id"));
				di.getPrescription().setPrescriptionTxt(rs.getString("prescription"));
				di.getPrescription().setStatus(rs.getString("prescription_status"));
				di.setUpdatedTime(rs.getDate("updated_dt"));
				
				list.add(di);
				
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally{
		}
		

		return list;
	}

	
	public List<PatientInfo> getPrescriptions(){
		
		PreparedStatement ps = null;  
		List<PatientInfo> prescList = null;

		try{
			createConnection();
			ps = conn.prepareStatement(HospitalQueries.select_all_active_prescrptions);

			ResultSet rs = ps.executeQuery();
			prescList = formPrescriptionList(rs);
			ps.close();
			

		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			shutdown();
		}
		
		return prescList;
	}
	
	private List<PatientInfo>  formPrescriptionList(ResultSet rs) throws Exception{
		List<PatientInfo> list = new ArrayList<PatientInfo>();
		
//		select pi.patient_id,pi.first_name,pi.last_name ,pi.phone_no ,pi.address_ln1,pi.address_ln2,pi.city,state, 
//		prescinfo.diagnosis_id, prescinfo.prescription_id, prescinfo.prescription
//		from hospital.patient_info pi, hospital.prescription_info prescinfo
//		where pi.patient_id = prescinfo.patient_id and prescinfo.prescription_status = 'OPEN'
		
		
		while( rs.next()){
			PatientInfo pi = new PatientInfo();
			DiagnosisInfo di = new DiagnosisInfo();
			PrescriptionInfo prescInfo = new PrescriptionInfo();
			
			pi.setPatientID(rs.getInt("patient_id"));
			pi.setFirstName(rs.getString("first_name"));
			pi.setLastName(rs.getString("last_name"));
			pi.setPhone(rs.getString("phone_no"));
			pi.setAddressLine1(rs.getString("address_ln1"));
			pi.setAddressLine2(rs.getString("address_ln2"));

			pi.setCity(rs.getString("city"));
			pi.setState(rs.getString("state"));
			prescInfo.setDiagnosisId(rs.getInt("diagnosis_id"));
			prescInfo.setPrescriptionId(rs.getInt("prescription_id"));
			prescInfo.setPrescriptionTxt(rs.getString("prescription"));
			pi.setDiagnosisInfo(new DiagnosisInfo());
			pi.getDiagnosisInfo().setPrescription(prescInfo);
			list.add(pi);
		}
		return list;
	}
	
	public void updatePrescriptionStatus(PatientInfo pInfo) throws Exception{
		
		PreparedStatement ps = null;  

		try{
			createConnection();
			ps = conn.prepareStatement(HospitalQueries.update_prescription_status);
			ps.setString(1, pInfo.getDiagnosisInfo().getPrescription().getStatus());
			ps.setInt(2, pInfo.getPatientID());
			ps.setInt(3, pInfo.getDiagnosisInfo().getPrescription().getDiagnosisId());
			ps.setInt(4, pInfo.getDiagnosisInfo().getPrescription().getPrescriptionId());

			ps.executeUpdate();
			ps.close();
			

		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			shutdown();
		}
	}
	
}
