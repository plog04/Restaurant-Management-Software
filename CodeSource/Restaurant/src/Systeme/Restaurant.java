
public class Restaurant {
	
	private static Restaurant copieRestaurant = null;
	private LigneCommande ligneCommand;
	private Commande commandeCourante;
	
		
		public static Restaurant copieRestaurant(){
			if(copieRestaurant == null){
				copieRestaurant = new Restaurant();
			}
			return copieRestaurant;
		}
		
	
		public static void main(String[] args) {
			copieRestaurant();
			copieRestaurant.selectionArticle(501, 10);
			System.out.println();
		
		}
		
	public void selectionArticle(int code, int quantite){
		ligneCommand.selectionnerArticle(code, quantite);
		commandeCourante.creerLigneCommande(quantite);
	}

}
