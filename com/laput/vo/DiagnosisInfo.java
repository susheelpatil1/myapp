package com.laput.vo;

import java.sql.Clob;
import java.util.Date;

public class DiagnosisInfo {

	private int diagnosisId;
	private String status;

	private String diagnosisTxt;
	private PrescriptionInfo prescription;
	private Date updatedTime;
	

	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public PrescriptionInfo getPrescription() {
		return (prescription == null)? (prescription = new PrescriptionInfo()): prescription;
	}
	public void setPrescription(PrescriptionInfo prescription) {
		this.prescription = prescription;
	}
	public int getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}


	public String getDiagnosisTxt() {
		return diagnosisTxt;
	}
	public void setDiagnosisTxt(String diagnosisTxt) {
		this.diagnosisTxt = diagnosisTxt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
