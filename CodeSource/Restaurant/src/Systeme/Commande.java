import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
public class Commande {
	
	private Date date = new Date();
	//private 
	ArrayList<LigneCommande> listeLigneCommande = new ArrayList<LigneCommande>();
	private double total;
		
	public Commande(Date date){
		this.date = date;
	}
	
	public void creerLigneCommande(int code, int quantite){
		LigneCommande lignec = new LigneCommande(code, quantite);
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
	
	public String[][] creerTableLigneCommande(){
		String[][] tableauLigneCommande;
		tableauLigneCommande = new String[4][listeLigneCommande.size()];
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
