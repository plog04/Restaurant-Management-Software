package Systeme;
import java.util.*;
public class Statistique {
	
	String[][] v; // table avec dimension initial 
	String axeY;
	String periode;
	Calendar calendar;
	int dayOfMonth;
	int monthOfYear;
	int weekOfYear;
	int dayOfWeek;
	int year;
	
	int COLONNE_JOUR=3;
	int COLONNE_MOIS=4;
	int COLONNE_ANNEE=5;
	int COLONNE_QTY=2;
	int COLONNE_ARTICLEMENU=1;
	int COLONNE_COMMANDE=0;
	int COLONNE_HEUREDEBUT=2;
	int COLONNE_HEUREFIN=3;
	
	public Statistique(){
		calendar = Calendar.getInstance();
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		monthOfYear = calendar.get(Calendar.MONTH);
		dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		year=calendar.get(Calendar.YEAR);
	}
	
	
	public void afficherTableau (){
		
	}

	
	public static int mod(int N, int M){
		if (N<0){
			return mod(M+N, M);
		}
		else{
			if(N<M)
				return N;
			else
				return mod(N-M,M);		
		}
	}
	
	
	/**
	 *  Cas 1 Cherche la quantité par periode choisie d'un articleMenu specifique dans base de donnée
	 *  
	 */
	 
	public void creerTableUnArticleMenu(String articleMenu, String periode){
		
		int maligne=0;
		int qtyArticle=0;
		int colonneDate=0;
		
		switch (periode){
		case "Journalier":  
			v = new String[31][2];
			v[0][0]="Quantité";
			v[0][1]="Jour";
			// Creons la table journalier
			int m = 1;
			maligne=Archive.longueurTable(tableNom)-1;
			while(m<v.length){
				
				if (Archive.getTableDonnee(tableNom,COLONNE_ARTICLEMENU,maligne)==articleMenu && Archive.getTableDonnee(tableNom,COLONNE_JOUR,maligne)==calendar.get(Calendar.DATE) && Archive.getTableDonnee(tableNom,COLONNE_MOIS,maligne)==calendar.get(Calendar.MONTH)+1 && Archive.getTableDonnee(tableNom,COLONNE_ANNEE,maligne)==calendar.get(Calendar.YEAR)){
					qtyArticle=qtyArticle+Archive.getTableDonnee(tableNom,COLONNE_QTY,maligne);
					
				}
				else{
					v[m][0]=String.valueOf(qtyArticle);
					v[m][1]=String.valueOf(calendar.get(Calendar.DATE))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)+String.valueOf(calendar.get(Calendar.YEAR));
					calendar.add(Calendar.DAY_OF_YEAR, -1);
					m++;
					qtyArticle=0;
					}
				maligne--;
			}		
		
			break;
		case "Hebdomadaire": 
			 
			v = new String [53][2];
			v[0][0]="Quantité";
			v[0][1]="Semaine";
			// Creons la table hebdomadaire
			m = 1;
			int semaine=calendar.get(Calendar.WEEK_OF_YEAR);
			maligne=Archive.longueurTable(tableNom)-1;
			while(m<v.length){
							
				if (Archive.getTableDonnee(tableNom,COLONNE_ARTICLEMENU,maligne)==articleMenu && Archive.getTableDonnee(tableNom,COLONNE_JOUR,maligne)==calendar.get(Calendar.DATE) && 
						Archive.getTableDonnee(tableNom,COLONNE_MOIS,maligne)==calendar.get(Calendar.MONTH)+1 && 
						Archive.getTableDonnee(tableNom,COLONNE_ANNEE,maligne)==calendar.get(Calendar.YEAR)){
					qtyArticle=qtyArticle+Archive.getTableDonnee(tableNom,COLONNE_QTY,maligne);
					
				}
				else{
					calendar.add(Calendar.DAY_OF_YEAR, -1);
					if (semaine!=calendar.get(Calendar.WEEK_OF_YEAR)){
						v[m][0]=String.valueOf(qtyArticle);
						v[m][1]="Semaine du "+String.valueOf(calendar.get(Calendar.DATE))+"-"+String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH))+"-"+String.valueOf(calendar.get(Calendar.YEAR));
						m++;
						semaine=calendar.get(Calendar.WEEK_OF_YEAR);
						qtyArticle=0;
					}
				}
				maligne--;
			}		
			break;
			
		case "Mensuel": break; colonneDate=COLONNE_MOIS; 
		v = new String [13][2]; 
		v[0][0]="Quantité";
		v[0][1]="Mois";
		
		// Creons la table mensuel
		m = 1;
		int mois=calendar.get(Calendar.MONTH);
		maligne=Archive.longueurTable(tableNom)-1;
		while(m<v.length){
									
			if (Archive.getTableDonnee(tableNom,COLONNE_ARTICLEMENU,maligne)==articleMenu &&
				Archive.getTableDonnee(tableNom,COLONNE_JOUR,maligne)==calendar.get(Calendar.DATE) && 
				Archive.getTableDonnee(tableNom,COLONNE_MOIS,maligne)==calendar.get(Calendar.MONTH)+1 && 
				Archive.getTableDonnee(tableNom,COLONNE_ANNEE,maligne)==calendar.get(Calendar.YEAR)){
							
				qtyArticle=qtyArticle+Archive.getTableDonnee(tableNom,COLONNE_QTY,maligne);
							
			}
			else{
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				if (mois!=calendar.get(Calendar.MONTH)){
					v[m][0]=String.valueOf(qtyArticle);
					v[m][1]=String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH))+"-"+String.valueOf(calendar.get(Calendar.YEAR));
					m++;
					semaine=calendar.get(Calendar.WEEK_OF_YEAR);
					qtyArticle=0;
				}
			}
			maligne--;
		}		
		
		break;
		
		}
		
		
		
	}
	
	/**
	 *  Cas 2 Cherche la quantité par periode choisie de tout les articleMenus dans base de donnée
	 */
	
	public void creerTableToutArticleMenu(String periode){
	
		int maligne=0;
		int qtyArticle=0;
		int colonneDate=0;
		
		switch (periode){
		case "Journalier":  
			v = new String[31][2];
			v[0][0]="Quantité";
			v[0][1]="Jour";
			// Creons la table journalier
			int m = 1;
			maligne=Archive.longueurTable(tableNom)-1;
			while(m<v.length){
				
				if (Archive.getTableDonnee(tableNom,COLONNE_JOUR,maligne)==calendar.get(Calendar.DATE) && Archive.getTableDonnee(tableNom,COLONNE_MOIS,maligne)==calendar.get(Calendar.MONTH)+1 && Archive.getTableDonnee(tableNom,COLONNE_ANNEE,maligne)==calendar.get(Calendar.YEAR)){
					qtyArticle=qtyArticle+Archive.getTableDonnee(tableNom,COLONNE_QTY,maligne);
					
				}
				else{
					v[m][0]=String.valueOf(qtyArticle);
					v[m][1]=String.valueOf(calendar.get(Calendar.DATE))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)+String.valueOf(calendar.get(Calendar.YEAR));
					calendar.add(Calendar.DAY_OF_YEAR, -1);
					m++;
					qtyArticle=0;
					}
				maligne--;
			}		
		
			break;
		case "Hebdomadaire": 
			 
			v = new String [53][2];
			v[0][0]="Quantité";
			v[0][1]="Semaine";
			// Creons la table hebdomadaire
			m = 1;
			int semaine=calendar.get(Calendar.WEEK_OF_YEAR);
			maligne=Archive.longueurTable(tableNom)-1;
			while(m<v.length){
							
				if (Archive.getTableDonnee(tableNom,COLONNE_JOUR,maligne)==calendar.get(Calendar.DATE) && 
						Archive.getTableDonnee(tableNom,COLONNE_MOIS,maligne)==calendar.get(Calendar.MONTH)+1 && 
						Archive.getTableDonnee(tableNom,COLONNE_ANNEE,maligne)==calendar.get(Calendar.YEAR)){
					qtyArticle=qtyArticle+Archive.getTableDonnee(tableNom,COLONNE_QTY,maligne);
					
				}
				else{
					calendar.add(Calendar.DAY_OF_YEAR, -1);
					if (semaine!=calendar.get(Calendar.WEEK_OF_YEAR)){
						v[m][0]=String.valueOf(qtyArticle);
						v[m][1]="Semaine du "+String.valueOf(calendar.get(Calendar.DATE))+"-"+String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH))+"-"+String.valueOf(calendar.get(Calendar.YEAR));
						m++;
						semaine=calendar.get(Calendar.WEEK_OF_YEAR);
						qtyArticle=0;
					}
				}
				maligne--;
			}		
			break;
			
		case "Mensuel": break; colonneDate=COLONNE_MOIS; 
		v = new String [13][2]; 
		v[0][0]="Quantité";
		v[0][1]="Mois";
		
		// Creons la table hebdomadaire
		m = 1;
		int mois=calendar.get(Calendar.MONTH);
		maligne=Archive.longueurTable(tableNom)-1;
		while(m<v.length){
									
			if (Archive.getTableDonnee(tableNom,COLONNE_JOUR,maligne)==calendar.get(Calendar.DATE) && 
				Archive.getTableDonnee(tableNom,COLONNE_MOIS,maligne)==calendar.get(Calendar.MONTH)+1 && 
				Archive.getTableDonnee(tableNom,COLONNE_ANNEE,maligne)==calendar.get(Calendar.YEAR)){
							
				qtyArticle=qtyArticle+Archive.getTableDonnee(tableNom,COLONNE_QTY,maligne);
							
			}
			else{
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				if (mois!=calendar.get(Calendar.MONTH)){
					v[m][0]=String.valueOf(qtyArticle);
					v[m][1]=String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH))+"-"+String.valueOf(calendar.get(Calendar.YEAR));
					m++;
					semaine=calendar.get(Calendar.WEEK_OF_YEAR);
					qtyArticle=0;
				}
			}
			maligne--;
		}		
		
		break;
		
		}
		
		
		
				
	}
	
	
	
	/**
	 *  Cas 3 Chercher la durée des commandes en fonction d'une periode.
	 */
	
	public void creerTableDureeCommande(String periode){
		int maligne=0;
		int dureeMoyenneCommande=0;
		int colonneDate=0;
		
		int COLONNE_JOUR=1;
		int COLONNE_MOIS=2;
		int COLONNE_ANNEE=3;
		
		switch (periode){
		case "Journalier":  
			v = new String[31][2];
			v[0][0]="Quantité";
			v[0][1]="Jour";
			// Creons la table journalier
			int m = 1;
			int n=0;
			maligne=Archive.longueurTable(tableCommande)-1;
			while(m<v.length){
				
				if (Archive.getTableDonnee(tableNom,COLONNE_JOUR,maligne)==calendar.get(Calendar.DATE) && Archive.getTableDonnee(tableNom,COLONNE_MOIS,maligne)==calendar.get(Calendar.MONTH)+1 && Archive.getTableDonnee(tableNom,COLONNE_ANNEE,maligne)==calendar.get(Calendar.YEAR)){
					n++;
					dureeMoyenneCommande=(dureeMoyenneCommande+(Archive.getTableDonnee(tableNom,COLONNE_HEUREFIN,maligne)-Archive.getTableDonnee(tableNom,COLONNE_HEUREDEBUT,maligne)))/n;

				}
				else{
					v[m][0]=String.valueOf(qtyArticle);
					v[m][1]=String.valueOf(calendar.get(Calendar.DATE))+"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)+String.valueOf(calendar.get(Calendar.YEAR));
					calendar.add(Calendar.DAY_OF_YEAR, -1);
					m++;
					n=0;
					dureeMoyenneCommande=0;
					}
				maligne--;
			}		
		
			break;
		case "Hebdomadaire": 
			 
			v = new String [53][2];
			v[0][0]="Quantité";
			v[0][1]="Semaine";
			// Creons la table hebdomadaire
			m = 1;
			int semaine=calendar.get(Calendar.WEEK_OF_YEAR);
			maligne=Archive.longueurTable(tableNom)-1;
			while(m<v.length){
							
				if (Archive.getTableDonnee(tableNom,COLONNE_JOUR,maligne)==calendar.get(Calendar.DATE) && 
						Archive.getTableDonnee(tableNom,COLONNE_MOIS,maligne)==calendar.get(Calendar.MONTH)+1 && 
						Archive.getTableDonnee(tableNom,COLONNE_ANNEE,maligne)==calendar.get(Calendar.YEAR)){
					qtyArticle=qtyArticle+Archive.getTableDonnee(tableNom,COLONNE_QTY,maligne);
					
				}
				else{
					calendar.add(Calendar.DAY_OF_YEAR, -1);
					if (semaine!=calendar.get(Calendar.WEEK_OF_YEAR)){
						v[m][0]=String.valueOf(qtyArticle);
						v[m][1]="Semaine du "+String.valueOf(calendar.get(Calendar.DATE))+"-"+String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH))+"-"+String.valueOf(calendar.get(Calendar.YEAR));
						m++;
						semaine=calendar.get(Calendar.WEEK_OF_YEAR);
						qtyArticle=0;
					}
				}
				maligne--;
			}		
			break;
			
		case "Mensuel": break; colonneDate=COLONNE_MOIS; 
		v = new String [13][2]; 
		v[0][0]="Quantité";
		v[0][1]="Mois";
		
		// Creons la table hebdomadaire
		m = 1;
		int mois=calendar.get(Calendar.MONTH);
		maligne=Archive.longueurTable(tableNom)-1;
		while(m<v.length){
									
			if (Archive.getTableDonnee(tableNom,COLONNE_JOUR,maligne)==calendar.get(Calendar.DATE) && 
				Archive.getTableDonnee(tableNom,COLONNE_MOIS,maligne)==calendar.get(Calendar.MONTH)+1 && 
				Archive.getTableDonnee(tableNom,COLONNE_ANNEE,maligne)==calendar.get(Calendar.YEAR)){
							
				qtyArticle=qtyArticle+Archive.getTableDonnee(tableNom,COLONNE_QTY,maligne);
							
			}
			else{
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				if (mois!=calendar.get(Calendar.MONTH)){
					v[m][0]=String.valueOf(qtyArticle);
					v[m][1]=String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH))+"-"+String.valueOf(calendar.get(Calendar.YEAR));
					m++;
					semaine=calendar.get(Calendar.WEEK_OF_YEAR);
					qtyArticle=0;
				}
			}
			maligne--;
		}		
		
		break;
		
		
		
	}
	
	

	/**
	 * Cas 4 preparation commande, va chercher le temps de tout les preparations de tout les articles
	 */
	
	public void creerTablePrepCommande(String axeX){
		
	}
	
	
	
}
