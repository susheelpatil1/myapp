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

public class UserDAO extends BaseDAO {

	public  boolean saveUserInfo(UserInfo userInfo) {
		try{
			createConnection();
			if(doesUserIdExist(userInfo.getUserId()))
				return false;
			saveUserDetails(userInfo);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			shutdown();
		}
		return true;
 
    }
	
	private boolean doesUserIdExist(String userId){
		PreparedStatement ps = null;  
		boolean flag = false;
		try{
			ps = conn.prepareStatement(HospitalQueries.check_user_id);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while( rs.next()){
				if(rs.getInt(1) > 0)
					flag = true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//shutdown();
		}

		return flag;
	}
	
	
	public String getUserRole(UserInfo userinfo){
		PreparedStatement ps = null;  
		String role = null;
		try{
			createConnection();

			ps = conn.prepareStatement(HospitalQueries.auth_user_id);
			ps.setString(1, userinfo.getUserId());
			ps.setString(2, userinfo.getPwd());
			
			ResultSet rs = ps.executeQuery();
			while( rs.next()){
					role = rs.getString("ROLE");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			shutdown();
		}

		return role;
	}
	
	private void saveUserDetails(UserInfo userInfo){
		PreparedStatement ps = null;  

		try{

		
			ps = conn.prepareStatement(HospitalQueries.insert_user_info);
			ps.setString(1, userInfo.getFirstName());
			ps.setString(2, userInfo.getLastName());
			ps.setString(3, userInfo.getUserId());
			ps.setString(4, userInfo.getPwd());
			ps.setString(5, userInfo.getRole());
			

			ps.executeUpdate();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//shutdown();
		}
		
	}   	

}
