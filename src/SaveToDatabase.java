//  Establish a connection to an database using JDBC 
import java.sql.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SaveToDatabase
{
	// we create a method, which we call and pass to it parameters, which are then saved into a database.
  public static  void saveToDatabase(String carPlate, String carType, String timePassed,String Speed, int Station_id)
  { 
	  Connection conn = null;
	  try {
		  //create a connection to the database. you set the db username and password from here.
		    conn =    DriverManager.getConnection("jdbc:mysql://localhost/tms_viewer?" +
		                                   "user=root&password=");
		    // add the object data, parsed as parameters, to the database
		    Statement st = conn.createStatement(); 
            st.executeUpdate("INSERT INTO cardata(car_plate, car_type, time_passed, speed, station) " + 
                "VALUES ('"+carPlate+"', '"+carType+"', '"+timePassed+"', "+Speed+", "+Station_id+")"); 
            
            //close the connection
            conn.close(); 
		   
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
  } 
}