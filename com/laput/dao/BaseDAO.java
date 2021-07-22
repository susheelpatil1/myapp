package com.laput.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BaseDAO {
	
	Connection conn = null;

	public void createConnection()
    {

        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(HospitalQueries.dbURL,"hospital","pass"); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
	
	
	public  void shutdown()
    {
        try
        {
			conn.commit();
			conn.close();

            if (conn != null)
            {
                DriverManager.getConnection(HospitalQueries.dbURL + ";shutdown=true");
            }           
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}
