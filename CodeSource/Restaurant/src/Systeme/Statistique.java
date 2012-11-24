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
	/**
	 *  Cas 1 articleMenu, va chercher la quantité et la date de tout les articleMenus=axeY dans base de donnée
	 */
	 
	public void creerTableUnArticleMenu(String axeX){
		
		while (GetTableDonnée(tableMenu,ArticleMenu,ligne)!=null){
			while(GetTableDonnée(tableLiens,colonneArticleMenu,ligne)!=null){
				if (GetTableDonnée(tableLiens,colonneArticleMenu,ligne)==GetTableDonnée(tableMenu,colonne0,ligne)){
					
					
				}
			}
		}
		
		
		//Cas 2 tout article, va chercher la quantité et la date de tout les articles menu menu
		
		//Cas 3 duree commande, va chercher la duree de toute les durees commande
		
		//Cas 4 preparation commande, va chercher le temps de tout les preparations de tout les articles
	}
	
	
	public int recherchePremiereLigne((int valeur1, int valeur2, int valeur3, int posLigneDebut, int colonne1, int colonne2, int colonne3, String tableNom)){
		int ligne=0;
		
		for(int i=posLigneDebut;i<Archive.longueurTable(tableNom);i++){
			
			if(Archive.getTableDonnee(tableNom,colonne1,ligne)==valeur1 && Archive.getTableDonnee(tableNom,colonne2,ligne)==valeur2 && Archive.getTableDonnee(tableNom,colonne3,ligne)==valeur3){
				return (i);
			}
		}
		return (-1); 
		
		
		
		return(ligne);
	}
	
	public int recherchePremiereLigne(int valeur, int posLigneDebut, int colonne, String tableNom){
		
		for(int i=posLigneDebut;i<Archive.longueurTable(tableNom);i++){
			if(Archive.getTableDonnee(tableNom,colonne,i)==valeur){
				return (i);
			}
		}
		return (-1);
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
	
	public void creerTableToutArticleMenu(String axeX){
		int COLONNE_JOUR=3;
		int COLONNE_MOIS=4;
		int COLONNE_ANNEE=5;
		int COLONNE_QTY=2;
		int maligne=0;
		int qtyArticle=0;
		int colonneDate=0;
		
		switch (axeX){
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
	
	
	public void creerTableDureeCommande(String axeX){
		
	}
	
	public void creerTablePrepCommande(String axeX){
		
	}
	
	
	
}
