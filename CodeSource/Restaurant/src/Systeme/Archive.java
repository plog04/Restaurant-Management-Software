
public class Archive {
	
	private static String nom;
	private static String typeMenu;
	private static String description;
	private static double prix;
	
	
	
	public static void selectArticle(int code){
		try {
			Menu.getDescPrix(code); //appeler cette methode pour avoir prix, nom, description, etc correspondant
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		nom = Menu.getNom();
		typeMenu = Menu.getTypeMenu();
		description = Menu.getDescription();
		prix = Menu.getPrix();
	}
	
	public static double getPrix1(){
		return prix;
	}
	
	public static String getDescription1(){
		return description;
	}
	
	public static String getNom1(){
		return nom;
	}
	
	public static String getTypeMenu1(){
		return typeMenu;
	}
}
