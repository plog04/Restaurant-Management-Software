
public class LigneCommande {

	private static  String description;
	private static  String typeMenu;
	private static String nom;
	private double prix;
	private static double sousTotal;
	private int quantite;	
	
	public LigneCommande(int quantite){
		this.quantite = quantite;
	}
	
	public static void selectionnerArticle(int code, int quantite) {
		Archive.selectArticle(code);
	}
	
	public double getSousTotal(){
		prix = Archive.getPrix1();
		sousTotal = prix * quantite;
		return sousTotal;
	}
	
	public String getDescrip(){
		description = Archive.getDescription1();
		return description;
	}
	public String getNom(){
		nom = Archive.getNom1();
		return nom;
	}
	public String getTypeMenu(){
		typeMenu = Archive.getTypeMenu1();
		return typeMenu;
	}
	public static void main(String [] arg){
		selectionnerArticle(801, 10);
		LigneCommande ligneC = new LigneCommande(10);
		description = ligneC.getDescrip();
		nom = ligneC.getNom();
		typeMenu = ligneC.getTypeMenu();
		sousTotal = ligneC.getSousTotal();
		System.out.println(description+" "+nom+" "+sousTotal+" "+typeMenu);
	}
}
