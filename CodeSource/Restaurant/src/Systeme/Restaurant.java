package Systeme;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;


public class Restaurant {
	
	private static Restaurant copieRestaurant = null;
	private static ArrayList<Commande> listeCommande = new ArrayList<Commande>();
	private static String[] listeTable = new String[] {"Table1", "Table2", "Table3", "Table4"};
	public static int lastId;
	
	public static void creerCommande(String table){
		lastId++;
		listeCommande.add(new Commande(table, lastId));	
	}

	public static String[] getListeTable(){
		return listeTable;
	}
	
	public static ArrayList<Commande> getListeCommandePourTable(String table){
		ArrayList<Commande> listeCommandeTable = new ArrayList<Commande>();
		
		for (int i=0; i<listeCommande.size(); i++){
			if (listeCommande.get(i).getTable() == table){
				listeCommandeTable.add(listeCommande.get(i));
			}
		}
		
		return listeCommandeTable;
	}
	
	public static Object[][] creerTableToutesLigneCommandeEnAttente(){
		Object[][] tableauLigneCommande;
		ArrayList<LigneCommande> listeToutesLigneCommande = new ArrayList<LigneCommande>();
		for(int i = 0; i<listeCommande.size(); i++){
			Commande commandeActive = listeCommande.get(i);
			for(int j = 0; j<commandeActive.getListeLigneCommande().size(); j++){
				LigneCommande ligneActive = commandeActive.getListeLigneCommande().get(j);
				if(ligneActive.getEtat() == "En attente"){
					listeToutesLigneCommande.add(ligneActive);
				}
			}
		}
		tableauLigneCommande = new Object[listeToutesLigneCommande.size()][6];
		for(int i=0; i<listeToutesLigneCommande.size(); i++){
			LigneCommande ligneComm = listeToutesLigneCommande.get(i);
			tableauLigneCommande[i][3] = ligneComm.getEtat();
			tableauLigneCommande[i][0] = ligneComm;
			tableauLigneCommande[i][1] = ligneComm.getQuantite()+"";
			tableauLigneCommande[i][2] = ligneComm.getSousTotal()+"";
			tableauLigneCommande[i][4] = ligneComm.getParent();
		}
		return tableauLigneCommande;
	}

		
		public static Restaurant copieRestaurant(){
			if(copieRestaurant == null){
				copieRestaurant = new Restaurant();
			}
			return copieRestaurant;
		}
		
/*
		public static void main (String Arg[]){
			
			creerCommande("table1");
		
	
			
			
			for (int j =0; j<2; j++) {
				
				Scanner ligneCommand = new Scanner(System.in);
				
				ligneCommand.useDelimiter("\r\n");
				System.out.print("Entrez le code : ");
				int code = Integer.parseInt(ligneCommand.next());
				System.out.print("Entrez la quantite : ");
				int quantite = Integer.parseInt(ligneCommand.next());
				listeCommande.get(0).creerLigneCommande(code, quantite);
				
				
				System.out.println("Commande en cours: Total " + listeCommande.get(0).getTotal());
				for(int i=0; i<listeCommande.get(0).listeLigneCommande.size(); i++){
					System.out.println(listeCommande.get(0).listeLigneCommande.get(i).toString());
				}
				
			}
			System.out.println(listeCommande.get(0).creerTableLigneCommande().toString());
			listeCommande.get(0).supprimerLigneCommande(1);
			System.out.println("Commande en cours: Total " + listeCommande.get(0).getTotal());
			for(int i=0; i<listeCommande.get(0).listeLigneCommande.size(); i++){
				System.out.println(listeCommande.get(0).listeLigneCommande.get(i).toString());
			}

}*/
}
