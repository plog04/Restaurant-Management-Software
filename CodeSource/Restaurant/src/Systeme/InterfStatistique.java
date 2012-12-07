package Systeme;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
//import java.util.ArrayList;
//import java.util.*;


import javax.swing.*;
//import javax.swing.table.JTableHeader;


public class InterfStatistique extends Fenetre implements ActionListener{
	
		/**
		 * 
		 */
		Statistique Stats;// = new Statistique();
		//Statistique Stats;
		private static final long serialVersionUID = 1L;
		
		InterfDemarrer monEntree;
		private JPanel pCommandes = new JPanel();
		
		private JComboBox<String> cbListeTable;
		private String[] listeTable = new String[] {"Type de donnée", "Popularité du menu", "Popularité d'un article du menu", "Temps moyen par client"};
		
		private JComboBox<String> cbListeCommande;
		private String[] listeCommande = new String[] {"Periode","Journalier", "Hebdomadaire", "Mensuelle"};
		
		private JComboBox<String> cbListeMenu;
		//private String[] listeMenu;
		
		private JPanel pListeTables = new JPanel();
		private JPanel plisteMenu = new JPanel();
		private JPanel pListeCommandes = new JPanel();
		private JPanel pAjouterCommande = new JPanel();
		
		private JButton bAjouterCommande = new JButton("Generer la table");
		
		
				
		//Table des resultat pour faire des tests
		private String[][] tableResultat=new String[52][2];
	
		private String[] entetes = {"Quantite","Periode"};
		
		
		JTable tableau= new JTable(tableResultat, entetes); 
		

		
		//Archive mesArchives;
		
		
		public InterfStatistique(InterfDemarrer Entree, Archive mesArchives) {
			monEntree= Entree;
			Stats = new Statistique(mesArchives);
			//super();
			//Stats=monStats;
			mesArchives = Stats.archDonnee;
			this.setTitle("Gestionnaire");
			//cFenetre.setLayout(new BorderLayout());
			cFenetre.add(pCommandes, BorderLayout.WEST);
			
			
			
			for (int i =0;i<tableResultat.length;i++){
				
					tableResultat[i][0]= "jour"+i;
					tableResultat[i][1]= String.valueOf(i);
				
			}
			
			cbListeTable = new JComboBox<String>(listeTable);
			cbListeTable.setSize(new Dimension(100,100));
			
			cbListeCommande = new JComboBox<String>(listeCommande);
			cbListeCommande.setSize(new Dimension(100,100));
			
			try{
				cbListeMenu = new JComboBox<String>(mesArchives.getArticleMenuList());
			}
			catch(Exception e){
				System.err.println(e);
			}
			
			cbListeMenu.setSize(new Dimension(100,100));
			
			
			pCommandes.setLayout(new GridLayout(4, 1));
			
			pCommandes.add(pListeTables);
			pListeTables.setLayout(new FlowLayout());
			pListeTables.add(cbListeTable);
			
			pCommandes.add(plisteMenu);
			plisteMenu.setLayout(new FlowLayout());
			plisteMenu.add(cbListeMenu);
			
			
			pCommandes.add(pListeCommandes);
			pListeCommandes.setLayout(new FlowLayout());
			pListeCommandes.add(cbListeCommande);
			
			pCommandes.add(pAjouterCommande);
			pAjouterCommande.setLayout(new BorderLayout());
			pAjouterCommande.add(bAjouterCommande, BorderLayout.SOUTH);
			
			
			JPanel p = new JPanel();
			p.setLayout(new BorderLayout(2, 2));
			p.add(tableau.getTableHeader(),BorderLayout.NORTH);
			p.add(new JScrollPane(tableau),BorderLayout.CENTER);
			add(p, BorderLayout.CENTER);
			
			
			
			
			bAjouterCommande.addActionListener(this);
			cbListeTable.addActionListener(this);
			bRetour.addActionListener(this);
			
			//this.setVisible(true);
			plisteMenu.setVisible(false);
		}
		
		
		public void actionPerformed(ActionEvent e){
		
			
			Object source = e.getSource();
			if (source==bRetour){
				
				this.setVisible(false);
				monEntree.setVisible(true);
			}
			
			
			if (source == cbListeTable){
				if (String.valueOf(cbListeTable.getSelectedItem())=="Popularité d'un article du menu"){
					plisteMenu.setVisible(true);
				}
				else{
					plisteMenu.setVisible(false);
				}
				
			}
			else if(source == bAjouterCommande){
				
				
				Object selected1= cbListeTable.getSelectedItem();
				Object selected2= cbListeCommande.getSelectedItem();
				if (selected2!="Periode"){
				try{
					switch (String.valueOf(selected1)){
						case "Popularité d'un article du menu":
					
					tableau.getColumnModel().getColumn(0).setHeaderValue("Quantite");
					tableau.getColumnModel().getColumn(1).setHeaderValue("Periode");
					
					Stats.creerTableUnArticleMenu(String.valueOf(cbListeMenu.getSelectedItem()), String.valueOf(selected2));
					//Stats.setTableauUnArticle(String.valueOf(selected1), String.valueOf(selected2), String.valueOf(cbListeMenu.getSelectedItem()));
					setDonneeDansTable(Stats.getTableau());
					break;
							case "Popularité du menu":
					tableau.getColumnModel().getColumn(0).setHeaderValue("Quantite");
					tableau.getColumnModel().getColumn(1).setHeaderValue("Periode");
					Stats.creerTableToutArticleMenu(String.valueOf(selected2));
					//Stats.setTableauToutArticle(String.valueOf(selected1), String.valueOf(selected2));
					setDonneeDansTable(Stats.getTableau());
					break;
							case "Temps moyen par client":
					tableau.getColumnModel().getColumn(0).setHeaderValue("Durée moyenne (min)");
					tableau.getColumnModel().getColumn(1).setHeaderValue("Periode");
					Stats.creerTableDureeCommande(String.valueOf(selected2));
					//Stats.setTableauToutArticle(String.valueOf(selected1), String.valueOf(selected2));
					setDonneeDansTable(Stats.getTableau());
					break;	
							default: break;
					}
				}
				
				
				catch(Exception ex){
					System.err.println(ex);
				}
				}
				
			}
			
			
		}
		
		
		public void setDonneeDansTable(String[][] nouvelleTableDonnee){
				
			
			
			for (int i =0;i<nouvelleTableDonnee.length;i++){
				
				tableResultat[i][0]= nouvelleTableDonnee[i][0];
				tableResultat[i][1]= nouvelleTableDonnee[i][1];
			
			}
			
			for (int i=nouvelleTableDonnee.length;i<tableResultat.length;i++){
				tableResultat[i][0]= "";
				tableResultat[i][1]= "";
			}
			
			repaint();
		}
	
	

}
