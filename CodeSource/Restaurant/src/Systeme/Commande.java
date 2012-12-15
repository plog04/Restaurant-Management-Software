package Systeme;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Commande {
	
	private Date date = new Date();
	//private 
	private ArrayList<LigneCommande> listeLigneCommande = new ArrayList<LigneCommande>();
	private double total;
	private String tableAssigne;
	private int identifiant;
	
	public Commande(String table, int id){
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
	
	public void creerLigneCommande(String description, int quantite, Archive monArchive){
		LigneCommande lignec = new LigneCommande(description, quantite, monArchive);
		if(lignec.getDescrip() != null){
		listeLigneCommande.add(lignec);
		total = total + lignec.getSousTotal();
		}
	}
	
	public ArrayList<LigneCommande> getListeLigneCommande(){
		return listeLigneCommande;
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
	public Date getDate(){
		return date;
	}
	public String toString(){
		return "Commande" + identifiant;
	}
	
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
	
	
	public void setAllEtats(String etatAvant, String etatApres){
		for(int i = 0; i<listeLigneCommande.size();i++){
			LigneCommande ligneActive = listeLigneCommande.get(i);
			if(ligneActive.getEtat()==etatAvant){
				ligneActive.setEtat(etatApres);
			}
		}
	}
	
}
