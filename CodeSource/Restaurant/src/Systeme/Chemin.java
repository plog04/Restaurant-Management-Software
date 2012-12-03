package etudeReseau;

import java.util.ArrayList;

public class Chemin {
	private ArrayList<Voie> listeVoies = new ArrayList<Voie>();
	private int poids;


	public Chemin(){
		poids = 0;
		}

	public Chemin(ArrayList<Voie> listeVoiesChemin, int poidsChemin){
		listeVoies = listeVoiesChemin;
		poids = poidsChemin;
		}

	public Chemin(Chemin cheminChoisi){
		listeVoies.addAll(cheminChoisi.getListeVoies());
		poids = cheminChoisi.getPoids();
		}

	public int getPoids(){
		return this.poids;
		}
	
	public int getLength(){
		return listeVoies.size();
	}
	
	public ArrayList<Voie> getListeVoies(){
		return this.listeVoies;
		}

	public void addVoie(Voie voie){
		listeVoies.add(voie);
		poids = poids + voie.getPoidsAjuste();
		}
	
	public void addFirstVoie(Voie voie){
		listeVoies.add(0, voie);
		poids = poids + voie.getPoidsAjuste();
		}
	
	public Voie getFirstVoie(){
		return listeVoies.get(0);
	}
	
	public boolean voieEstAbsente(Voie voie){
		for(int i = 0; i<listeVoies.size(); i++){
			if(listeVoies.get(i) == voie){
				return false;
				
			}
		}
		return true;
	}
	
	public Voie getLast(){
		return listeVoies.get(listeVoies.size() - 1);
	}
	
	public void print(){
		for(int i = 0; i < this.getLength(); i++){
			System.out.println(this.getListeVoies().get(i).toString());
		}
		System.out.println("Poid du chemin: " + this.getPoids());
		System.out.println("Longueur du chemin: " + this.getLength());
	}
}
