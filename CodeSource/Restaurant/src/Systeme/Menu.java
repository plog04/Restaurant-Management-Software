
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Menu 
{
	private static int code;
	private static String nom;
	private static String typeMenu;
	private static String description;
	private static double prix;
	
	public static void getDescPrix(int code) throws ClassNotFoundException
  {
	// load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");
    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Public\\Documents\\dbSAGR\\dbRestaurant.sqlite");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.
      ResultSet rs = statement.executeQuery("SELECT * FROM menu WHERE code =" +code+"");
      while(rs.next())
      {
        // read the result set
    	  code =  rs.getInt("code");
          nom = rs.getString("nom");
          typeMenu = rs.getString("typeMenu");
          description =  rs.getString("description");
          prix = rs.getDouble("prix");
      }
      
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
  }
	 public static int getCode(){
		  return code;
	  }
	  public static String getNom(){
		  return nom;
	  }
	  public static String getTypeMenu(){
		  return typeMenu;
	  }
	  public static String getDescription(){
		  return description;
	  }
	  public static double getPrix(){
		  return prix;
	  }
}