
package Systeme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Archive {
	
	
	private static int code;
	private static String nom;
	private static String typeMenu;
	private static String description;
	private static double prix;
	
	 Connection connection;  
	 ResultSet rs; 
	 Statement statement; 
	
	
	
	public Archive (){
		connection = null;  
		rs = null;  
		 statement = null; 
	}
	
	public void openConnection() {
		// load the sqlite-JDBC driver using the current class loader
	   
	   // Connection connection = null;
	    try
	    {
	    	
	    	Class.forName("org.sqlite.JDBC");
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Guillaume707\\Documents\\CodeSource\\Restaurant\\dbRestaurant.sqlite");
	    
	      statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	    }
	    catch(Exception e)
	    {
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	    	e.printStackTrace(); 
	     System.err.println("erreur1"+e.getMessage());
	     System.out.println("SQLException: " + e.getMessage());
	   
	    }
	}
	
	public void closeConnection() throws ClassNotFoundException{
		
		try
	      {
			rs.close();  
            statement.close();  
            connection.close();
	      }
	      catch(Exception e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }	
	}
	
	public int countElement(String tableNum,String colonne, String valeurEle) throws ClassNotFoundException{
		int valeur=0;
		try{
			openConnection();
			
			rs = statement.executeQuery("SELECT count(*) as Nbre FROM menu WHERE "+colonne+" == \""+valeurEle+"\"");
			valeur=rs.getInt("Nbre");
			
			closeConnection();	
		}
		catch(Exception e)
		{
			
			System.err.println(e);
		}
			return(valeur);
		
		}
	
	
	public int countElement2(String tableNum,String colonne1, String valeurEle1,String colonne2, String valeurEle2) throws ClassNotFoundException{
		int valeur=0;
		try{
			openConnection();
			
			rs = statement.executeQuery("SELECT count(*) as Nbre FROM menu WHERE "+colonne1+" == \""+valeurEle1+"\"AND "+colonne2+" == \""+valeurEle2+"\"");
			valeur=rs.getInt("Nbre");
			
			closeConnection();	
		}
		catch(Exception e)
		{
			
			System.err.println(e);
		}
			return(valeur);
		
		}
	
	public int ElementPeriod(String date1, String date2) throws ClassNotFoundException{
		ArrayList<String> valeur = new ArrayList<String>();
		int total=0;
		try{
			openConnection();
			
			rs = statement.executeQuery("SELECT * FROM tableCommande WHERE dateCreation >= \""+date1+"\" AND dateCreation  <= \""+date2+"\"" );
			
			while(rs.next()) {

				valeur.add(rs.getString ("numeroCommande"));
			
			    //System.out.println(valeur);
			}
			
			for (int i=0;i<valeur.size();i++){
				rs = statement.executeQuery("SELECT sum(\"quantite\") FROM ligneCommande WHERE numeroCommande = "+valeur.get(i));
				total=total+rs.getInt(1);
			}
			
			
			
			
			closeConnection();	
		}
		catch(Exception e)
		{
			
			System.err.println("erroor"+e);
		}
			return(total);
		
		}
	
	
	
	
	
	
	
	/*
	
	public static void getDescPrix(int code) throws ClassNotFoundException
  {
	// load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");
    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Guillaume707\\Documents\\CodeSource\\Restaurant\\src\\Systeme\\dbRestaurant.sqlite");
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
	*/
	
	
	
	
	/*
	private static String nom;
	private static String typeMenu;
	private static String description;
	private static double prix;
	
	
	public countElement(String tableNum, String eLement){
	SELECT count(nom) FROM table1 WHERE (nom == "nom1" OR nom == "nom2")
	}
	
	public static void selectArticle(int code){
		try {
			Menu.getDescPrix(code); //appeler cette methode pour avoir prix, nom, description, etc correspondant
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		nom = Menu.getNom();
		typeMenu = Menu.getTypeMenu();
		description = Menu.getDescription();
		prix = Menu.getPrix();
	}
	
	public static double getPrix1(){
		return prix;
	}
	
	public static String getDescription1(){
		return description;
	}
	
	public static String getNom1(){
		return nom;
	}
	
	public static String getTypeMenu1(){
		return typeMenu;
	}
	*/
}
