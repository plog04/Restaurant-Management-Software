package Systeme;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Commande {
	
	private Date date = new Date();
	//private 
	ArrayList<LigneCommande> listeLigneCommande = new ArrayList<LigneCommande>();
	private double total;
	private String tableAssigne;
	private String identifiant;
	
	public Commande(Date date, String table, String id){
		this.date = date;
		tableAssigne = table;
		identifiant = id;
	}
	
	public void creerLigneCommande(int code, int quantite, Archive monArchive){
		LigneCommande lignec = new LigneCommande(code, quantite, monArchive);
		if(lignec.getDescrip() != null){
		listeLigneCommande.add(lignec);
		total = total + lignec.getSousTotal();
		}
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
	
	public String toString(){
		return identifiant;
	}
	
	public String[][] creerTableLigneCommande(){
		String[][] tableauLigneCommande;
		tableauLigneCommande = new String[listeLigneCommande.size()][4];
		for(int i=0; i<listeLigneCommande.size(); i++){
			LigneCommande ligneComm = listeLigneCommande.get(i);
			tableauLigneCommande[0][i] = ligneComm.getEtat();
			tableauLigneCommande[1][i] = ligneComm.getDescrip();
			tableauLigneCommande[2][i] = ligneComm.getQuantite()+"";
			tableauLigneCommande[3][i] = ligneComm.getSousTotal()+"";
		}
		return tableauLigneCommande;
	}
	
}
