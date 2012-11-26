/*import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.util.Scanner;*/
import java.util.Date;
public class Commande {
	
	//private static Scanner scanner = new Scanner(System.in);
	private Date date = new Date();
	private double total = 0.0;
	private static LigneCommande ligneCommande;
	private static String description;
	private static String typeMenu;
	private String nom;
	private double prix;
	private double sousTotal;
	private int quantite;	
	
	private Commande commande;
	/*
	public Commande(Date date, LigneCommande ligneCommande, double total){
		this.date = date;
		this.ligneCommande = ligneCommande;
		this.total = total;
	}
	
	public void creerNouvelleCommande(){
		commande = new Commande(date, ligneCommande,total);
	}*/
	
	public static void main (String Arg[]){
		
		ligneCommande.selectionnerArticle(501, 10);
		
		description = ligneCommande.getDescrip();
		nom = ligneCommande.getNom();
		typeMenu = ligneCommande.getTypeMenu();
		sousTotal = ligneCommande.getSousTotal();
		System.out.println(description+" "+nom+" "+typeMenu+" "+sousTotal);
	}
	/*
	public void creerLigneCommande(int quantite){
		LigneCommande ligneC = new LigneCommande(quantite);
		ligneC.creerNouvelleLigneCommande();
	}*/
}
