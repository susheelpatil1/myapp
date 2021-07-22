package com.laput.vo;

import java.math.BigInteger;
import java.sql.Clob;
import java.util.List;

public class PatientInfo {
	
	private int patientID;
	private String firstName;
	private String lastName;
	private String phone;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String email;
	private String comments;
	private String errorMsg;
	private String queueName;
	private String prescriptionTxt;
	private String diagnosisTxt;
	private DiagnosisInfo diagnosisInfo = new DiagnosisInfo();
	
	List<DiagnosisInfo> oldDiagnosisList;


	

	public List<DiagnosisInfo> getOldDiagnosisList() {
		return oldDiagnosisList;
	}
	public void setOldDiagnosisList(List<DiagnosisInfo> oldDiagnosisList) {
		this.oldDiagnosisList = oldDiagnosisList;
	}
	public String getPrescriptionTxt() {
		return prescriptionTxt;
	}
	public void setPrescriptionTxt(String prescriptionTxt) {
		this.prescriptionTxt = prescriptionTxt;
	}
	public String getDiagnosisTxt() {
		return diagnosisTxt;
	}
	public void setDiagnosisTxt(String diagnosisTxt) {
		this.diagnosisTxt = diagnosisTxt;
	}
	

	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	
	public DiagnosisInfo getDiagnosisInfo() {
		return (diagnosisInfo == null)? (diagnosisInfo = new DiagnosisInfo()): diagnosisInfo;

	}
	public void setDiagnosisInfo(DiagnosisInfo diagnosisInfo) {
		this.diagnosisInfo = diagnosisInfo;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	

}
