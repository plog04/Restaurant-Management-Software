package Systeme;
import java.util.*;
public class Statistique {
	
	String[][] v; // table avec dimension initial 
	String axeY;
	String periode;
	
	
	public Statistique(){
		
	}
	
	
	public void afficherTableau (){
		
	}
	/**
	 *  Cas 1 articleMenu, va chercher la quantit� et la date de tout les articleMenus=axeY dans base de donn�e
	 */
	 
	public void creerTableUnArticleMenu(String axeX){
		
		while (GetTableDonn�e(tableMenu,ArticleMenu,ligne)!=null){
			while(GetTableDonn�e(tableLiens,colonneArticleMenu,ligne)!=null){
				if (GetTableDonn�e(tableLiens,colonneArticleMenu,ligne)==GetTableDonn�e(tableMenu,colonne0,ligne)){
					
					
				}
			}
		}
		
		
		//Cas 2 tout article, va chercher la quantit� et la date de tout les articles menu menu
		
		//Cas 3 duree commande, va chercher la duree de toute les durees commande
		
		//Cas 4 preparation commande, va chercher le temps de tout les preparations de tout les articles
	}
	
	public void creerTableToutArticleMenu(String axeX){
		COLONNE_JOUR=3;
		COLONNE_MOIS=4;
		COLONNE_ANNEE=5;
		int ligne=0;
		int qtyArticle;
		int colonne date;
		switch (axeX){
		case "Journalier": colonnedate=COLONNE_JOUR; 
		v = new String[31][2];
		v[0][0]="Quantit�";
		v[0][1]="Jour";
		break;
		case "Hebdomadaire": colonnedate=COLONNE_JOUR; 
		v = new String [53][2];
		v[0][0]="Quantit�";
		v[0][1]="Semaine";
		break;
		case "Mensuel": break; colonnedate=COLONNE_MOIS; 
		v = new String [13][2]; 
		v[0][0]="Quantit�";
		v[0][1]="Mois";
		break;
		
		}
		Ligne=GetlastLine(tableLiens);
		JourDonnee=GetTableDonn�e(tableLiens,COLONNE_JOUR,Ligne);
		MoisDonnee=GetTableDonn�e(tableLiens,COLONNE_MOIS,Ligne);
		AnneeDonnee=GetTableDonn�e(tableLiens,COLONNE_ANNEE,Ligne);
		String tiret="-";
		while(GetTableDonn�e(tableLiens,colonneArticleMenu,ligne)!=null){
			
			while (GetTableDonn�e(tableLiens,colonnedate,ligne)==JourDonnee){
				qtyArticle=qtyArticle+GetTableDonn�e(tableLiens,colonneArticleMenu,ligneQty);
			}
			v[0][0]=String.valueOf(qtyArticle);
			v[0][1]=String.valueOf(JourDonnee)+tiret+String.valueOf(MoisDonnee)+tiret+String.valueOf(AnneeDonnee);
			JourDonnee++;
			
			/*		
			if (GetTableDonn�e(tableLiens,colonneArticleMenu,ligne)==GetTableDonn�e(tableMenu,colonne0,ligne)){
					
					
			}
			*/
			ligne--;
		}
				
	}
	
	
	public void creerTableDureeCommande(String axeX){
		
	}
	
	public void creerTablePrepCommande(String axeX){
		
	}
	
	
	
}