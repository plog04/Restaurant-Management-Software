package Systeme;

import java.util.ArrayList;


public class LigneCommande {

	private   String description;
	private  double prix;
	private  double sousTotal;
	private  int quantite;
	private String etat;
	private Commande parent;
	private int code;
	
	public LigneCommande(int code, int quantity, Archive monArchive, Commande parent){
		
		ArrayList<Object> descPrix = new  ArrayList<Object>();
		try {
			this.code = code;
			this.parent = parent;
			etat = "En attente";
			descPrix = monArchive.getDescPrix(code);
			quantite = quantity;
		prix = (Double) descPrix.get(1);	
		sousTotal = prix * quantite;
		description = (String) descPrix.get(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("L'article n'a pas été trouvé");
		}

	}
	public LigneCommande(String description, int quantity, Archive monArchive, Commande parent){
		
		try {
			this.parent = parent;
			etat = "Non notifié";
			ArrayList<Object> descPrix = monArchive.getDescPrix(description);
			code = (int) descPrix.get(0);
			prix = (Double) descPrix.get(1);	
			quantite = quantity;	
		sousTotal = prix * quantite;
		this.description = description;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("L'article n'a pas été trouvé");
		}

	}
	public String getEtat(){
		return etat;
	}
	
	public void setEtat(String etat){
		this.etat = etat;
	}
	public int getQuantite(){
		return quantite;
	}
	
	public void setQuantite(int quantite){
		this.quantite = quantite;
		sousTotal = prix*quantite;
	}
	
	public double getSousTotal(){
		return sousTotal;
	}
	
	public String getDescrip(){
		return description;
	}
	
	public Commande getParent(){
		return parent;
	}
	
	public int getCode(){
		return code;
	}
	public String toString(){
		return description;
	}
	
}
