package Systeme;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InterfCuisinier extends Fenetre implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel pCommandes = new JPanel();
	JFrame frame = new JFrame();
	private Object[][] ligneCommandeActives = new Object[52][6];
	private String[] colonnesTableau = {"Description","Quantit�","Sous-Total","�tat","Commande"};
	private JTable tCommande= new JTable(ligneCommandeActives, colonnesTableau);
	private JScrollPane spCommande = new JScrollPane(tCommande);
	private JPanel pBoutons = new JPanel();
	private JButton bSetPret = new JButton("Pr�t");
	private JButton bRafraichir = new JButton("Rafra�chir");
	private InterfDemarrer monEntree;
	
	InterfCuisinier(InterfDemarrer Entree){
		monEntree = Entree;
		this.setTitle("Cuisinier");
		cFenetre.add(pCommandes, BorderLayout.WEST);
		cFenetre.add(spCommande, BorderLayout.CENTER);
		pRetour.add(pBoutons, BorderLayout.NORTH);
		pBoutons.setLayout(new GridLayout(1,4));
		pBoutons.add(bSetPret);
		pBoutons.add(bRafraichir);
		bRetour.addActionListener(this);
		bSetPret.addActionListener(this);
		bRafraichir.addActionListener(this);
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
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if (source==bRetour){
			this.setVisible(false);
			monEntree.setVisible(true);
		
		//Change l'�tat de l'item s�lectionner pour "Pr�t", il devient alors invisible au cuisinier
		}else if(source==bSetPret){
			try{
				LigneCommande ligneActive = (LigneCommande) ligneCommandeActives[tCommande.getSelectedRow()][0];
				ligneActive.setEtat("Pr�t");
				afficherLignesCommande();
			}catch(Exception o){
				JOptionPane.showMessageDialog(frame,
					    "Veuillez s�lectionner un item",
					    "Erreur",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		//Mets � jour la liste des lignes de commandes
		}else if(source==bRafraichir){
			afficherLignesCommande();
		}
	}	
	
}
