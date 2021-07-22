package com.laput.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.laput.vo.Credential;
import com.laput.vo.DiagnosisInfo;
import com.laput.vo.PatientInfo;
import com.laput.vo.UserInfo;
import com.laput.dao.DiagnosisDAO;
import com.laput.dao.PatientDAO;
import com.laput.dao.UserDAO;
import com.laput.jwt.JWTTokenizer;




@RestController
public class HospController {
	

	
	@RequestMapping("/PatientInfo")
	public String homepage() {
		System.out.println("MVC homepage");
		return "redirect:/pages/index.html";

	}
		
	@RequestMapping("/testcall")
	public void testcall(){
		System.out.println("MVC working");
	}


	@RequestMapping(value="/validateuser", method = RequestMethod.GET)
	public @ResponseBody String validateUser(HttpServletRequest request, HttpServletResponse response){
		System.out.println("in validateuser");
		if(!validateUser(request.getHeader("jwttoken")))
			return "N";
		else
			return "Y";
		
	}
	
	public boolean validateUser(String token){
		
		JWTTokenizer jwt = new JWTTokenizer();
		return jwt.validateUser(token);
		
		
	}
	
	
	@RequestMapping(value="/savepatientinfo", method = RequestMethod.POST)
	public @ResponseBody void savepatientinfo(@RequestBody PatientInfo patientInfo, HttpServletRequest request, HttpServletResponse response){
		System.out.println("in savepatientinfo "+patientInfo);
		
		if(!validateUser(request.getHeader("jwttoken"))){
			response.setIntHeader("hospauth", 0);
		}
		
		PatientDAO patientDAO = new PatientDAO();
		patientDAO.savePatientInfo(patientInfo);
		
	}	

	
	@RequestMapping(value="/updatepatientinfo", method = RequestMethod.POST)
	public @ResponseBody void updatepatientinfo(@RequestBody PatientInfo patientInfo, HttpServletRequest request, HttpServletResponse response){
		System.out.println("in updatepatientinfo "+patientInfo);
		
		if(!validateUser(request.getHeader("jwttoken"))){
			response.setIntHeader("hospauth", 0);
		}		
		PatientDAO patientDAO = new PatientDAO();
		patientDAO.updatePatientDetails(patientInfo);
		
	}
	
	
	@RequestMapping(value="/searchPatient", method = RequestMethod.POST)
	public @ResponseBody List<PatientInfo> searchPatient(@RequestBody PatientInfo patientInfo, HttpServletRequest request, HttpServletResponse response){
		System.out.println("in searchPatient "+patientInfo);
		
		if(!validateUser(request.getHeader("jwttoken"))){
			response.setIntHeader("hospauth", 0);
		}	
		
		PatientDAO patientDAO = new PatientDAO();
		return patientDAO.searchPatient(patientInfo);
		
		
	}	
	
	
	@RequestMapping(value="/checkin", method = RequestMethod.POST)
	public @ResponseBody void checkinPatient(@RequestBody PatientInfo patientInfo, HttpServletRequest request, HttpServletResponse response){
		System.out.println("in checkinPatient "+patientInfo);
		if(!validateUser(request.getHeader("jwttoken"))){
			response.setIntHeader("hospauth", 0);
		}			
		PatientDAO patientDAO = new PatientDAO();
		patientDAO.checkinPatient(patientInfo);
	}	

	@RequestMapping(value="/saveDiagnandpresc", method = RequestMethod.POST)
	public @ResponseBody void saveDiagnosis(@RequestBody PatientInfo patientInfo, HttpServletRequest request, HttpServletResponse response){
		System.out.println("in saveDiagnandpresc "+patientInfo);
		
		if(!validateUser(request.getHeader("jwttoken"))){
			response.setIntHeader("hospauth", 0);
		}			
		DiagnosisDAO diagnosisDAO = new DiagnosisDAO();
		diagnosisDAO.saveDiagnosisandPresc(patientInfo);
	}	
	
	
	@RequestMapping(value="/getqueuelist", method = RequestMethod.GET)
	public @ResponseBody List<PatientInfo> getQueueList( HttpServletRequest request, HttpServletResponse response){
		System.out.println("in getQueueList ");
		if(!validateUser(request.getHeader("jwttoken"))){
			response.setIntHeader("hospauth", 0);
		}			
		PatientDAO patientDAO = new PatientDAO();
		return patientDAO.getQueueList();
	}
	
	
	@RequestMapping(value="/getFullDiagnosisData/{id}", method = RequestMethod.GET)
	public @ResponseBody PatientInfo getFullDiagnosisData(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
		System.out.println("in getFullDiagnosisData ");
		if(!validateUser(request.getHeader("jwttoken"))){
			response.setIntHeader("hospauth", 0);
		}			
		DiagnosisDAO diagnosisDAO = new DiagnosisDAO();
		return diagnosisDAO.getFullDiagnosisData(Integer.valueOf(id));
	}	
	
	
	@RequestMapping(value="/getPrescriptions", method = RequestMethod.GET)
	public @ResponseBody List<PatientInfo> getPrescriptions( HttpServletRequest request, HttpServletResponse response){
		System.out.println("in getPrescriptions");
		if(!validateUser(request.getHeader("jwttoken"))){
			response.setIntHeader("hospauth", 0);
		}			
		DiagnosisDAO diagnosisDAO = new DiagnosisDAO();
		return diagnosisDAO.getPrescriptions();
	}	
	
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public @ResponseBody String registerUser(@RequestBody UserInfo userInfo){
		System.out.println(userInfo.getFirstName()+userInfo.getLastName()+userInfo.getUserId()+userInfo.getRole());
		UserDAO userDAO = new UserDAO();
		return "{\"success\":\""+userDAO.saveUserInfo(userInfo)+"\"}";
	}		
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public @ResponseBody boolean login(@RequestBody UserInfo userInfo, HttpServletRequest request,HttpServletResponse response ){	
		boolean flag = false;
		
		try {
			System.out.println(userInfo.getFirstName()+userInfo.getLastName()+userInfo.getUserId()+userInfo.getRole());
			String resp = "{\"";
			UserDAO userDAO = new UserDAO();
			String role = userDAO.getUserRole(userInfo);
			if(role != null){
	
				userInfo.setRole(role);
				JWTTokenizer jwt = new JWTTokenizer();
				String token = jwt.generateJWTToken(userInfo);
				resp += token+"\"}";
				flag = true;
		        response.addHeader("jwttoken", token);
		        response.addHeader("role", role);
		        response.addHeader("loggedin", "Y");


			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		 return flag;
	}		
	
	@RequestMapping(value="/fulfillprescription", method = RequestMethod.POST)
	public @ResponseBody boolean login(@RequestBody PatientInfo patientInfo, HttpServletRequest request,HttpServletResponse response ){	
		
		boolean flag = false;
		
		try{
			DiagnosisDAO diagnosisDAO = new DiagnosisDAO();
			patientInfo.getDiagnosisInfo().getPrescription().setStatus("FULFILLED");
			diagnosisDAO.updatePrescriptionStatus(patientInfo);
			flag = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return flag;
	}
	
	
}
