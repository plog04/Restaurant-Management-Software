package Systeme;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class InterfCuisinier extends Fenetre implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel pCommandes = new JPanel();
	
	//test de commande
	/*
	private testlignecommande test1 = new testlignecommande("test1");
	private testlignecommande test2 = new testlignecommande("test2");
	
	private testlignecommande[] listeUneCommande = new testlignecommande[] {test1, test2};
	//private String[] listeUneCommande = new String[] {"item","zitem","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","itemfin"};
	private LigneCommande testligne = new LigneCommande();
	*/
	private String[] colonnes = new String[]{"Commande","État","Table","Attente"};
	
	
	
	private JTable tCommande = new JTable(/*,colonnes*/);
	private JScrollPane spCommande = new JScrollPane(tCommande);
	
	private JPanel pBoutons = new JPanel();
	private JButton bSetEnPreparation = new JButton("En préparation");
	private JButton bSetPret = new JButton("Prêt");
	private JButton bSetAnnuler = new JButton("Annuler");
	private JButton bSetEnAttente = new JButton("En attente");
	private JButton bRafraichir = new JButton("Rafraîchir");
	private InterfCompteEntree monEntree;
	
	InterfCuisinier(InterfCompteEntree Entree){
		//super();
		monEntree = Entree;
		this.setTitle("Cuisinier");
		//cFenetre.setLayout(new BorderLayout());
		cFenetre.add(pCommandes, BorderLayout.WEST);
		
		cFenetre.add(spCommande, BorderLayout.CENTER);
		pRetour.add(pBoutons, BorderLayout.NORTH);
		
		pBoutons.setLayout(new GridLayout(1,4));
		pBoutons.add(bSetEnPreparation);
		pBoutons.add(bSetPret);
		pBoutons.add(bSetEnAttente);
		pBoutons.add(bSetAnnuler);
		bRetour.addActionListener(this);
		//this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if (source==bRetour){
			
			this.setVisible(false);
			monEntree.setVisible(true);
		}
	}
}
