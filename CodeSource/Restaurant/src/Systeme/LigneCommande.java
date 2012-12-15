package Systeme;

import java.util.ArrayList;


public class LigneCommande {

	private   String description;
	private  double prix;
	private  double sousTotal;
	private  int quantite;
	private String etat;
	
	public LigneCommande(int code, int quantity, Archive monArchive){
		
		ArrayList<Object> descPrix = new  ArrayList<Object>();
		try {
			etat = "En attente";
			descPrix = monArchive.getDescPrix(code);
			quantite = quantity;
		prix = (Double) descPrix.get(1);	
		sousTotal = prix * quantite;
		description = (String) descPrix.get(0);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("L'article n'a pas été trouvé");
		}

		//System.out.println(description+"\t"+quantite+"\t"+sousTotal);
	}
	public LigneCommande(String description, int quantity, Archive monArchive){
		
		try {
			etat = "Non notifié";
			prix = monArchive.getDescPrix(description);
			quantite = quantity;	
		sousTotal = prix * quantite;
		this.description = description;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("L'article n'a pas été trouvé");
		}

		//System.out.println(description+"\t"+quantite+"\t"+sousTotal);
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
	
	public String toString(){
		return etat+" "+description + " " + prix +" " + sousTotal+" "+quantite;
	}
	
}
