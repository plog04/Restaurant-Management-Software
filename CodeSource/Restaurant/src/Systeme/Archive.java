import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Archive {
	
	
	
	public static ArrayList<Object> getDescPrix(int code) throws ClassNotFoundException
	  {
		// load the sqlite-JDBC driver using the current class loader
	    Class.forName("org.sqlite.JDBC");
	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:dbRestaurant.sqlite");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      ResultSet rs = statement.executeQuery("SELECT * FROM menu WHERE code =" +code+"");
	      
	      ArrayList<Object> DescPrix = new ArrayList<Object>();
	      while(rs.next())
	      {
	        // read the result set
	    	 
	    	  DescPrix.add(rs.getString("description"));
	    	  DescPrix.add(rs.getDouble("prix"));
	    	 
	      }
	      return DescPrix;
	    }
	    catch(SQLException e)
	    {
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }
	    }
	    
	    return null;
	  }
}
