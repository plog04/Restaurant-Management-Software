
package Systeme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


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
	
	
	//Compte le nbre d'un certain element dans une colonne d'une table
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
	
	
	
	// Retourne la liste des articles du menu
	public String[] getArticleMenuList() throws ClassNotFoundException{
		String[] article; 
		int count=0;
		try{
			openConnection();
			
			rs = statement.executeQuery("SELECT count(*) FROM menu" );
			article=new String[rs.getInt(1)];
			
			rs = statement.executeQuery("SELECT nom FROM menu" );
			
			while(rs.next()) {

				article[count]=rs.getString(1);
				
			    count++;
			}
			
			closeConnection();	
		}
		catch(Exception e)
		{
			article=new String[1];
			System.err.println("erroor"+e);
		}
			return(article);
		
		}
	
	// Retourne le nombre de fois qu'un article est commander entre date1 et date2
		public int getTotalUnArticle(String articleAuMenu, String date1, String date2) throws ClassNotFoundException{
			ArrayList<String> valeur = new ArrayList<String>();
			int total=0;
			try{
				openConnection();
				
				rs = statement.executeQuery("SELECT * FROM tableCommande WHERE dateCreation >= \""+date1+"\" AND dateCreation  <= \""+date2+"\"" );
				
				while(rs.next()) {

					valeur.add(rs.getString ("numeroCommande"));
				
				    
				}
				
				for (int i=0;i<valeur.size();i++){
					rs = statement.executeQuery("SELECT sum(\"quantite\") FROM ligneCommande WHERE numeroCommande = "+valeur.get(i)+" AND codeMenu IN (SELECT code FROM menu WHERE nom = \""+articleAuMenu+"\")");
					
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
	
	
	// Retourne le nombre d'article du menu commander entre date1 et date2 
	public int getTotalDesArticles(String date1, String date2) throws ClassNotFoundException{
		ArrayList<String> valeur = new ArrayList<String>();
		int total=0;
		try{
			openConnection();
			
			rs = statement.executeQuery("SELECT * FROM tableCommande WHERE dateCreation >= \""+date1+"\" AND dateCreation  <= \""+date2+"\"" );
			
			while(rs.next()) {

				valeur.add(rs.getString ("numeroCommande"));
			
			    
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
	
	
	
	
	// Retourne le temps moyen des commandes clients
		public int DureeCommande(String date1, String date2) throws ClassNotFoundException{
			ArrayList<String> valeur = new ArrayList<String>();
			DateFormat formatter = new SimpleDateFormat("HH:mm");
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			int cal3=0;
			
			
			try{
				openConnection();
				
				rs = statement.executeQuery("SELECT * FROM tableCommande WHERE dateCreation >= \""+date1+"\" AND dateCreation  <= \""+date2+"\"" );
				
				while(rs.next()) {

					valeur.add(rs.getString ("numeroCommande"));
				
				    
				}
				
				System.out.println("ici");				
				Date dt;
				for (int i=0;i<valeur.size();i++){
					try {
						rs = statement.executeQuery("SELECT heureDebut FROM tableCommande WHERE numeroCommande = "+valeur.get(i));
					
						dt = formatter.parse(rs.getString(1));
						cal1.setTime(dt);
						  				
						rs = statement.executeQuery("SELECT heureFin FROM tableCommande WHERE numeroCommande = "+valeur.get(i));
					
						dt = formatter.parse(rs.getString(1));
						cal2.setTime(dt);
					
						cal3=cal3+((int)cal2.getTimeInMillis()-(int)cal1.getTimeInMillis());	
						
					} catch (Exception e) 
						{
						  // This can happen if you are trying to parse an invalid date, e.g., 25:19:12.
						  // Here, you should log the error and decide what to do next
						  e.printStackTrace();
						}
				}
				
				if (valeur.size()!=0){
				cal3=cal3/valeur.size();
				}
				else{
					cal3=0;
				}
				
				
				closeConnection();	
			}
			catch(Exception e)
			{
				
				System.err.println("erroor"+e);
			}
				return(cal3/(1000*60));
			
			}
	
	
	
	
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
	
}
