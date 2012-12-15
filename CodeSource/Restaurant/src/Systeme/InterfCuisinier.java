package Systeme;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class InterfCuisinier extends Fenetre implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel pCommandes = new JPanel();
	JFrame frame = new JFrame();
	//test de commande
	/*
	private testlignecommande test1 = new testlignecommande("test1");
	private testlignecommande test2 = new testlignecommande("test2");
	
	private testlignecommande[] listeUneCommande = new testlignecommande[] {test1, test2};
	//private String[] listeUneCommande = new String[] {"item","zitem","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","itemfin"};
	private LigneCommande testligne = new LigneCommande();
	*/
	private Object[][] ligneCommandeActives = new Object[52][6];
	private String[] colonnesTableau = {"Description","Quantité","Sous-Total","État","Commande"};
	
	private JTable tCommande= new JTable(ligneCommandeActives, colonnesTableau);
	private JScrollPane spCommande = new JScrollPane(tCommande);
	
	private JPanel pBoutons = new JPanel();
	private JButton bSetPret = new JButton("Prêt");
	private JButton bRafraichir = new JButton("Rafraîchir");
	private InterfDemarrer monEntree;
	
	InterfCuisinier(InterfDemarrer Entree){
		//super();
		monEntree = Entree;
		this.setTitle("Cuisinier");
		//cFenetre.setLayout(new BorderLayout());
		cFenetre.add(pCommandes, BorderLayout.WEST);
		
		cFenetre.add(spCommande, BorderLayout.CENTER);
		pRetour.add(pBoutons, BorderLayout.NORTH);
		
		pBoutons.setLayout(new GridLayout(1,4));
		pBoutons.add(bSetPret);
		pBoutons.add(bRafraichir);
		bRetour.addActionListener(this);
		bSetPret.addActionListener(this);
		bRafraichir.addActionListener(this);
		//this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if (source==bRetour){
			
			this.setVisible(false);
			monEntree.setVisible(true);
		}else if(source==bSetPret){
			try{
				LigneCommande ligneActive = (LigneCommande) ligneCommandeActives[tCommande.getSelectedRow()][0];
				ligneActive.setEtat("Prêt");
				afficherLignesCommande();
			}catch(Exception o){
				JOptionPane.showMessageDialog(frame,
					    "Veuillez sélectionner un item",
					    "Erreur",
					    JOptionPane.ERROR_MESSAGE);
			}
		}else if(source==bRafraichir){
			afficherLignesCommande();
		}
	}
	
	private void afficherLignesCommande(){
		try{
			Object[][] tableLigneCommande = Restaurant.creerTableToutesLigneCommandeEnAttente();
			int i=0;
			while (i<(tableLigneCommande.length)){
				for (int j=0; j<5; j++){
					ligneCommandeActives[i][j] = tableLigneCommande[i][j];
				}
				i++;
			}
		while (i<(ligneCommandeActives.length)){
			for (int j=0; j<5; j++){
				ligneCommandeActives[i][j] = "";
			}
			i++;
		}
		tCommande.repaint();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
