package com.laput.vo;

import java.sql.Clob;

public class PrescriptionInfo {
	
	private int diagnosisId;
	private int prescriptionId;

	private String status;
	private String prescriptionTxt;

	
	public String getPrescriptionTxt() {
		return prescriptionTxt;
	}
	public void setPrescriptionTxt(String prescriptionTxt) {
		this.prescriptionTxt = prescriptionTxt;
	}
	public int getPrescriptionId() {
		return prescriptionId;
	}
	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public int getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
