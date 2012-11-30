package Systeme;

import java.awt.*;
import javax.swing.*;

public class InterfStatistique extends Fenetre{
	
		/**
		 * 
		 */
	
	
		private static final long serialVersionUID = 1L;
		private JPanel pCommandes = new JPanel();
		
		private JComboBox cbListeTable;
		private String[] listeTable = new String[] {"Type de donnée", "Popularité du menu", "Popularité d'un article du menu", "Temps moyen par client"};
		private JComboBox cbListeCommande;
		private String[] listeCommande = new String[] {"Période","Journalier", "Hebdomadaire", "Mensuelle"};
		private JPanel pListeTables = new JPanel();
		private JPanel pListeCommandes = new JPanel();
		private JPanel pAjouterCommande = new JPanel();
		private JButton bAjouterCommande = new JButton("Generer la table");
		
				
		//Table des resultat pour faire des tests
		private String[][] tableResultat = new String[52][2];
		//private String[][] tableResultat= {{"jour1","1"},{"jour2","2"},{"jour3","3"},{"jour4","5"}};
		private String[] entetes = {"jour","Quantite"};
		
		
		JTable tableau = new JTable(tableResultat, entetes);
		
		
		//test de commande
		private testlignecommande test1 = new testlignecommande("test1");
		private testlignecommande test2 = new testlignecommande("test2");
		
		private testlignecommande[] listeUneCommande = new testlignecommande[] {test1, test2};
		//private String[] listeUneCommande = new String[] {"item","zitem","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","item","itemfin"};
		
		
		private JList tCommande = new JList(listeUneCommande);
		private JScrollPane spCommande = new JScrollPane(tCommande);
		
		private JPanel pBoutons = new JPanel();
		private JButton bAjouterItem = new JButton("Ajouter item");
		private JButton bRetirerItem = new JButton("Retirer item");
		private JButton bNotifier = new JButton("Notifier");
		private JButton bPayerCommande = new JButton("Payer la commande");
		
		public InterfStatistique() {
		
			
			
			super();
			this.setTitle("Serveur");
			cFenetre.setLayout(new BorderLayout());
			cFenetre.add(pCommandes, BorderLayout.WEST);
			
			for (int i =0;i<52;i++){
				
					tableResultat[i][0]= "jour"+i;
					tableResultat[i][1]= String.valueOf(i);
				
			}
			
			cbListeTable = new JComboBox(listeTable);
			cbListeTable.setSize(new Dimension(100,100));
			cbListeCommande = new JComboBox(listeCommande);
			cbListeCommande.setSize(new Dimension(100,100));
			
			pCommandes.setLayout(new GridLayout(3, 1));
			
			pCommandes.add(pListeTables);
			pListeTables.setLayout(new FlowLayout());
			pListeTables.add(cbListeTable);
			
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
			
			
			//cFenetre.add(tableau.getTableHeader(), BorderLayout.NORTH);
			//cFenetre.add(tableau, BorderLayout.CENTER);
			
			//cFenetre.add(spCommande, BorderLayout.CENTER);
			cFenetre.add(pBoutons, BorderLayout.SOUTH);
			
			pBoutons.setLayout(new GridLayout(1,4));
			//pBoutons.add(bAjouterItem);
			//pBoutons.add(bRetirerItem);
			//pBoutons.add(bNotifier);
			//pBoutons.add(bPayerCommande);
			
			this.setVisible(true);
		}
	

}
