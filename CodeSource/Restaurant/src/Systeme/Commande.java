package Systeme;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Commande {
	private Calendar calendar;
	SimpleDateFormat date_format2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat date_format = new SimpleDateFormat("HH:mm");
	//private Date date = new Date();
	private ArrayList<LigneCommande> listeLigneCommande = new ArrayList<LigneCommande>();
	private double total;
	private String tableAssigne;
	private int identifiant;
	
	private String heureDebut;
	private String heureFin;
	private String dateCreation;
	
	public Commande(String table, int id){
		tableAssigne = table;
		identifiant = id;
		heureDebut=getHeure();
		dateCreation = getDate();
	}
	
	public void creerLigneCommande(int code, int quantite, Archive monArchive){
		LigneCommande lignec = new LigneCommande(code, quantite, monArchive, this);
		if(lignec.getDescrip() != null){
		listeLigneCommande.add(lignec);
		total = total + lignec.getSousTotal();
		}
		
	}
	
	public void creerLigneCommande(String description, int quantite, Archive monArchive){
		LigneCommande lignec = new LigneCommande(description, quantite, monArchive, this);
		if(lignec.getDescrip() != null){
		listeLigneCommande.add(lignec);
		total = total + lignec.getSousTotal();
		}
	}
	
	public ArrayList<LigneCommande> getListeLigneCommande(){
		return listeLigneCommande;
	}
	public String getDateCreation(){
		return (dateCreation);
	}
	public String getHeureDebut(){
		return (heureDebut);
	}
	public String getHeureFin(){
		return (heureFin);
	}
	
	public double getTotal(){
		return total;
	}
	
	public void supprimerLigneCommande(int index){
		total = total - listeLigneCommande.get(index).getSousTotal();
		listeLigneCommande.remove(index);
	}
	
	public String getTable(){
		return tableAssigne;
	}
	
	public void setTable(String table){
		tableAssigne = table;
	}
	
	public int getId(){
		return identifiant;
	}
	
	public void setHeureFin(){
		heureFin=getHeure();
	}
	public String getDate(){
		calendar = Calendar.getInstance();
		String temps=date_format2.format(calendar.getTime());		
		return (temps);
	}
	public String getHeure(){		
		calendar = Calendar.getInstance();
		String temps=date_format.format(calendar.getTime());		
		return (temps);
	}
	public String toString(){
		return "Commande" + identifiant;
	}
	
	//Utilisé pour afficher les lignes de commandes dans les interfaces
	public String[][] creerTableLigneCommande(){
		String[][] tableauLigneCommande;
		tableauLigneCommande = new String[listeLigneCommande.size()][4];
		for(int i=0; i<listeLigneCommande.size(); i++){
			LigneCommande ligneComm = listeLigneCommande.get(i);
			tableauLigneCommande[i][3] = ligneComm.getEtat();
			tableauLigneCommande[i][0] = ligneComm.getDescrip();
			tableauLigneCommande[i][1] = ligneComm.getQuantite()+"";
			tableauLigneCommande[i][2] = ligneComm.getSousTotal()+"";
		}
		return tableauLigneCommande;
	}
	
	//Mets tous les états des lignes de commandes qui ont l'état préciser à un autre
	public void setAllEtats(String etatAvant, String etatApres){
		for(int i = 0; i<listeLigneCommande.size();i++){
			LigneCommande ligneActive = listeLigneCommande.get(i);
			if(ligneActive.getEtat()==etatAvant){
				ligneActive.setEtat(etatApres);
			}
		}
	}
	
}
