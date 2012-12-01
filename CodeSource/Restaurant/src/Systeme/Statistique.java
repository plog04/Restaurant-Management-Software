package Systeme;
import java.util.*;
//import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Statistique {
	
	String[][] v; // table avec dimension initial 
	String axeY;
	String periode;
	Calendar calendar;
	Archive archDonnee;
	SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat time_format = new SimpleDateFormat("HH:mm");
	public Statistique(){
		calendar = Calendar.getInstance();
	
		archDonnee= new Archive();
	}
	
	
	
	
	public String[][] getTableau (){
		return(v);
	}
	
	public void setTableau(String axeDesY, String periodeTemps)throws ClassNotFoundException{
		if (axeDesY == "Popularité du menu"){
			creerTableToutArticleMenu(periodeTemps);
		}
		else if (axeDesY == "Temps moyen par client") {
				creerTablePrepCommande(periodeTemps);
			}
			else{
				System.out.println("Mauvais choix de type de donnée");
			}
				
		
	}
	
	public void setTableau(String axeDesY, String periodeTemps, String articleDuMenu)throws ClassNotFoundException{
		if (axeDesY == "Popularité d'un article du menu"){
			creerTableUnArticleMenu(articleDuMenu, periodeTemps);
		}
		else{
			System.out.println("Mauvais choix de type de donnée");
		}
	}
		
	
	/**
	 *  Cas 1 Cherche la quantité par periode choisie d'un articleMenu specifique dans base de donnée
	 *  
	 */
	 
	public void creerTableUnArticleMenu(String articleMenu, String periode)throws ClassNotFoundException{
		
		int qtyArticle=0;
		
		calendar = Calendar.getInstance();
		String dateDebut=date_format.format(calendar.getTime());
		String dateFin=date_format.format(calendar.getTime());
		
		switch (periode){
		case "Journalier":  
			v = new String[31][2];
			
			// Creons la table journalier
			int m = 0;
			
			while(m<v.length){
						
				dateDebut=date_format.format(calendar.getTime());
				dateFin=date_format.format(calendar.getTime());
				
				qtyArticle=archDonnee.getTotalUnArticle(articleMenu,dateDebut,dateFin);
				
				
				v[m][0]=String.valueOf(qtyArticle);
				v[m][1]=date_format.format(calendar.getTime());
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				m++;
				qtyArticle=0;
					
			}	
			
			break;
			
		default : 
			v = new String[1][1]; break; 
			
		
		case "Hebdomadaire": 
			 
			v = new String [52][2];
			
			// Creons la table hebdomadaire
			m = 0;
			int semaine=0;
		
			while(m<v.length){
				
				
				semaine=calendar.get(Calendar.WEEK_OF_YEAR);
				
				dateFin=date_format.format(calendar.getTime());
				
				while (calendar.get(Calendar.WEEK_OF_YEAR) == semaine) {
					calendar.add(Calendar.DAY_OF_YEAR, -1);
			    }
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				
				dateDebut=date_format.format(calendar.getTime());
				qtyArticle=archDonnee.getTotalUnArticle(articleMenu,dateDebut,dateFin);
				
				
				v[m][0]=String.valueOf(qtyArticle);
				v[m][1]="Semaine du "+date_format.format(calendar.getTime());
				m++;
				qtyArticle=0;
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				
			}		
			break;
			
		case "Mensuelle": 
		v = new String [13][2]; 
		
		
		// Creons la table hebdomadaire
		m = 0;
		int mois=0; 
		
		while(m<v.length){
			
			
			mois=calendar.get(Calendar.MONTH);
			
			dateFin=date_format.format(calendar.getTime());
			
			while (calendar.get(Calendar.MONTH) == mois) {
				calendar.add(Calendar.DAY_OF_YEAR, -1);
		    }
			
			dateDebut=date_format.format(calendar.getTime());
			
			qtyArticle=archDonnee.getTotalUnArticle(articleMenu,dateDebut,dateFin);
			
			v[m][0]=String.valueOf(qtyArticle);
			v[m][1]=calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.CANADA_FRENCH)+" "+String.valueOf(calendar.get(Calendar.YEAR));
			
			m++;
			qtyArticle=0;
			
						
		}		
		
		break;
		
		}
		
		
		
	}
	
	/**
	 *  Cas 2 Cherche la quantité par periode choisie de tout les articleMenus dans base de donnée
	 */
	
	public void creerTableToutArticleMenu(String periode)throws ClassNotFoundException{
	
		
		int qtyArticle=0;
		
		calendar = Calendar.getInstance();
		String dateDebut=date_format.format(calendar.getTime());
		String dateFin=date_format.format(calendar.getTime());
		
		switch (periode){
		case "Journalier":  
			v = new String[31][2];
			
			// Creons la table journalier
			int m = 0;
			
			while(m<v.length){
						
				dateDebut=date_format.format(calendar.getTime());
				dateFin=date_format.format(calendar.getTime());
				
				qtyArticle=archDonnee.getTotalDesArticles(dateDebut,dateFin);
				
				
				v[m][0]=String.valueOf(qtyArticle);
				v[m][1]=date_format.format(calendar.getTime());
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				m++;
				qtyArticle=0;
					
			}	
			
			break;
			
		default : 
			v = new String[1][1]; break; 
			
		
		case "Hebdomadaire": 
			 
			v = new String [52][2];
			
			// Creons la table hebdomadaire
			m = 0;
			int semaine=0;
		
			while(m<v.length){
				
				
				semaine=calendar.get(Calendar.WEEK_OF_YEAR);
				
				dateFin=date_format.format(calendar.getTime());
				
				while (calendar.get(Calendar.WEEK_OF_YEAR) == semaine) {
					calendar.add(Calendar.DAY_OF_YEAR, -1);
			    }
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				
				dateDebut=date_format.format(calendar.getTime());
				qtyArticle=archDonnee.getTotalDesArticles(dateDebut,dateFin);
				
				
				v[m][0]=String.valueOf(qtyArticle);
				v[m][1]="Semaine du "+date_format.format(calendar.getTime());
				m++;
				qtyArticle=0;
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				
			}		
			break;
			
		case "Mensuelle": 
		v = new String [13][2]; 
		
		
		// Creons la table hebdomadaire
		m = 0;
		int mois=0; 
		
		while(m<v.length){
			
			
			mois=calendar.get(Calendar.MONTH);
			
			dateFin=date_format.format(calendar.getTime());
			
			while (calendar.get(Calendar.MONTH) == mois) {
				calendar.add(Calendar.DAY_OF_YEAR, -1);
		    }
			
			dateDebut=date_format.format(calendar.getTime());
			
			qtyArticle=archDonnee.getTotalDesArticles(dateDebut,dateFin);
			
			v[m][0]=String.valueOf(qtyArticle);
			v[m][1]=calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.CANADA_FRENCH)+" "+String.valueOf(calendar.get(Calendar.YEAR));
			
			m++;
			qtyArticle=0;
			
						
		}		
		
		break;
		
		}
		
		
		
				
	}
	
	

	/**
	 * Cas 3 Chercher la durée des commandes en fonction d'une periode.
	 */
	
	public void creerTablePrepCommande(String periode)throws ClassNotFoundException{
		//Calendar tempsMoy = Calendar.getInstance();
		int tempsMoy=0;
		calendar = Calendar.getInstance();
		String dateDebut=date_format.format(calendar.getTime());
		String dateFin=date_format.format(calendar.getTime());
		
		switch (periode){
		case "Journalier":  
			v = new String[31][2];
			
			// Creons la table journalier
			int m = 0;
			
			
			while(m<v.length){
						
				dateDebut=date_format.format(calendar.getTime());
				dateFin=date_format.format(calendar.getTime());
				
				tempsMoy=archDonnee.DureeCommande(dateDebut,dateFin);
				
				if (tempsMoy == 0){
					v[m][0]="---";
				}else{
					v[m][0]=String.valueOf(tempsMoy);
				}
				
				v[m][1]=date_format.format(calendar.getTime());
				
				
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				m++;
				
					
			}	
			
			break;
			
		default : 
			v = new String[1][1]; break; 
			
		
		case "Hebdomadaire": 
			 
			v = new String [52][2];
			
			// Creons la table hebdomadaire
			m = 0;
			int semaine=0;
		
			while(m<v.length){
				
				
				semaine=calendar.get(Calendar.WEEK_OF_YEAR);
				
				dateFin=date_format.format(calendar.getTime());
				
				while (calendar.get(Calendar.WEEK_OF_YEAR) == semaine) {
					calendar.add(Calendar.DAY_OF_YEAR, -1);
			    }
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				
				dateDebut=date_format.format(calendar.getTime());
				tempsMoy=archDonnee.DureeCommande(dateDebut,dateFin);
				
				
				if (tempsMoy == 0){
					v[m][0]="---";
				}else{
					v[m][0]=String.valueOf(tempsMoy);
				}
				v[m][1]="Semaine du "+date_format.format(calendar.getTime());
				m++;
				
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				
			}		
			break;
			
		case "Mensuelle": 
		v = new String [13][2]; 
		
		
		// Creons la table hebdomadaire
		m = 0;
		int mois=0; 
		
		while(m<v.length){
			
			
			mois=calendar.get(Calendar.MONTH);
			
			dateFin=date_format.format(calendar.getTime());
			
			while (calendar.get(Calendar.MONTH) == mois) {
				calendar.add(Calendar.DAY_OF_YEAR, -1);
		    }
			
			dateDebut=date_format.format(calendar.getTime());
			
			tempsMoy=archDonnee.DureeCommande(dateDebut,dateFin);
			
			if (tempsMoy == 0){
				v[m][0]="---";
			}else{
				v[m][0]=String.valueOf(tempsMoy);
			}
			v[m][1]=calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.CANADA_FRENCH)+" "+String.valueOf(calendar.get(Calendar.YEAR));
			
			m++;
		
			
						
		}		
		
		break;
		
		}
		
		
		
	}
	
	
	
}
