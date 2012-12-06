package Systeme;


import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Restaurant {
	private static int terminer = 0;
	private static Restaurant copieRestaurant = null;
	private static ArrayList<Commande> listeCommande = new ArrayList<Commande>();
	private static String[] listeTable = new String[] {"Table1", "Table2", "Table3", "Table4"};
	
	public static void creerCommande(String table){
		Date date = new Date();
		String idCommande = "Commande" + (getListeCommandePourTable(table).size()+1);
		listeCommande.add(new Commande(date, table, idCommande));
		
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

		
		public static Restaurant copieRestaurant(){
			if(copieRestaurant == null){
				copieRestaurant = new Restaurant();
			}
			return copieRestaurant;
		}
		

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

}
}
