package Systeme;

import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws ClassNotFoundException{
		// TODO Auto-generated method stub
		//IConsulterCommandeServeur fenetre = new IConsulterCommandeServeur();
		
		Archive monArchive = new Archive();
		
		
		//System.out.println(monArchive.countElement("menu", "typeMenu", "Boisson"));
		//System.out.println(monArchive.countElement("menu", "typeMenu", "Boisson", ));
		int nbreArticle=0;
	//ArrayList<String> ListeCommande = new ArrayList<String>();	
	/*
		nbreArticle = monArchive.ElementPeriod("2012-11-22","2012-11-22"); //marche bien!!
	System.out.println(nbreArticle); // marche bien!!
		*/
	//SELECT count(*) FROM tableCommande WHERE dateCreation >= '2012-11-17'  AND dateCreation <'2012-11-30'
		//InterfStatistique fenetre = new InterfStatistique();
		
		Statistique Stat = new Statistique();
		Stat.creerTableToutArticleMenu("Journalier");
		
		
	}

}
