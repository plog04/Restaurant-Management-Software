package Systeme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.TableColumn;


public class InterfServeur extends Fenetre implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pCommandes = new JPanel();
	
	private JComboBox cbListeTable;
	private JComboBox cbListeCommande;
	private JComboBox cbListeMenu;
	private JPanel pListeTables = new JPanel();
	private JPanel pListeCommandes = new JPanel();
	private JPanel pAjouterCommande = new JPanel();
	private JButton bAjouterCommande = new JButton("Ajouter commande");
	
	private InterfDemarrer monEntree;
	

	private String[][] ligneCommandeActives = new String[30][4];
	private String[] colonnesTableau = {"Description","Quantité","Sous-Total","État"};
	
	private JTable tCommande= new JTable(ligneCommandeActives, colonnesTableau);
	private JScrollPane spCommande = new JScrollPane(tCommande);
	
	private JPanel pBoutons = new JPanel();
	private JLabel lQuantite = new JLabel("Quantité:");
	private JTextField tQuantite = new JTextField();
	private JButton bAjouterItem = new JButton("Ajouter item");
	private JButton bRetirerItem = new JButton("Retirer item");
	private JButton bNotifier = new JButton("Notifier");
	private JButton bPayerCommande = new JButton("Payer la commande");
	private Archive monArchive;
	JFrame frame = new JFrame();
	InterfServeur(InterfDemarrer Entree){
		
		
		
		monEntree = Entree;
		
		monArchive = monEntree.monArchive;
		
		/*final JOptionPane optionPane = new JOptionPane(
			    "The only way to close this dialog is by\n"
			    + "pressing one of the following buttons.\n"
			    + "Do you understand?",
			    JOptionPane.QUESTION_MESSAGE,
			    JOptionPane.YES_NO_OPTION);*/
		
		
		
		this.setTitle("Serveur");
		cFenetre.add(pCommandes, BorderLayout.WEST);
		
		cbListeTable = new JComboBox(Restaurant.getListeTable());
		cbListeTable.setSize(new Dimension(100,100));
		cbListeCommande = new JComboBox();
		cbListeCommande.setSize(new Dimension(100,100));
		
		pCommandes.setLayout(new GridLayout(4, 1));
		
		pCommandes.add(pListeTables);
		pListeTables.setLayout(new FlowLayout());
		pListeTables.add(cbListeTable);
		
		pCommandes.add(pListeCommandes);
		pListeCommandes.setLayout(new FlowLayout());
		pListeCommandes.add(cbListeCommande);
		
		try {
			cbListeMenu = new JComboBox(monArchive.getArticleMenuList());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cbListeMenu.setSize(new Dimension(100,100));
		pCommandes.add(cbListeMenu);
		
		pCommandes.add(pAjouterCommande);
		
		
		
		pAjouterCommande.setLayout(new GridLayout(3,1));
		
		pAjouterCommande.add(lQuantite);
		pAjouterCommande.add(tQuantite);
		pAjouterCommande.add(bAjouterCommande);
		
		cFenetre.add(spCommande, BorderLayout.CENTER);
		pRetour.add(pBoutons, BorderLayout.NORTH);
		
		pBoutons.setLayout(new GridLayout(1,4));
		pBoutons.add(bAjouterItem);
		pBoutons.add(bRetirerItem);
		pBoutons.add(bNotifier);
		pBoutons.add(bPayerCommande);
		bRetour.addActionListener(this);
		cbListeTable.addActionListener(this);
		bAjouterCommande.addActionListener(this);
		cbListeCommande.addActionListener(this);
		bAjouterItem.addActionListener(this);
		
		bRetirerItem.addActionListener(this);
		bNotifier.addActionListener(this);
		bPayerCommande.addActionListener(this);
		//this.setVisible(true);
	}
	
	private void afficherLignesCommande(Commande commandeActive){
		try{
		String[][] tableLigneCommande = commandeActive.creerTableLigneCommande();
		//System.out.println(.length);
		int i=0;
		while (i<(tableLigneCommande.length)){
			for (int j=0; j<4; j++){
				ligneCommandeActives[i][j] = tableLigneCommande[i][j];
			}
			i++;
		}
		while (i<(ligneCommandeActives.length)){
			for (int j=0; j<4; j++){
				ligneCommandeActives[i][j] = "";
			}
			i++;
		}
		tCommande.repaint();
		}catch(Exception e){
			
		}
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if (source==bRetour){
			
			this.setVisible(false);
			monEntree.setVisible(true);
			
		}else if(source==cbListeTable){
			ArrayList<Commande> listeCommande = new ArrayList<Commande>();
			listeCommande = Restaurant.getListeCommandePourTable(cbListeTable.getSelectedItem().toString());
			cbListeCommande.removeAllItems();
			for(int i=0; i < listeCommande.size(); i++){
				cbListeCommande.addItem(listeCommande.get(i));
			}
			
		}else if(source==bAjouterCommande){
			Restaurant.creerCommande(cbListeTable.getSelectedItem().toString());
			ArrayList<Commande> listeCommande = new ArrayList<Commande>();
			listeCommande = Restaurant.getListeCommandePourTable(cbListeTable.getSelectedItem().toString());
			cbListeCommande.addItem(listeCommande.get(listeCommande.size()-1));
			
			
		}else if(source==cbListeCommande){
			Commande commandeActive = (Commande) cbListeCommande.getSelectedItem();
			afficherLignesCommande(commandeActive);
			//ligneCommandeActives
			
			
			
		}else if(source==bAjouterItem){
			
			try{
			Commande commandeActive = (Commande) cbListeCommande.getSelectedItem();
			String itemSelection = (String) cbListeMenu.getSelectedItem();
			int quantite = 1;
			try{
				quantite = Integer.parseInt(tQuantite.getText());
			}catch(NumberFormatException q){
				quantite = 1;
			}
			commandeActive.creerLigneCommande(itemSelection, quantite, monEntree.monArchive);
			
			afficherLignesCommande(commandeActive);
			}catch(NullPointerException n){
				JOptionPane.showMessageDialog(frame,
					    "Veuillez créer et sélectionner une commande",
					    "Erreur",
					    JOptionPane.ERROR_MESSAGE);
			}
		}else if(source==bPayerCommande){
			try{
			Object[] options = {"OK","Annuler",};
			Commande commandeActive = (Commande) cbListeCommande.getSelectedItem();
			int n = JOptionPane.showOptionDialog(frame,
					"Le total de la commande est de "+ Math.floor(commandeActive.getTotal() * 100) / 100,"Payer",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[1]);
			if(n==0){
				System.out.println("Victoire");
				monArchive.createNewCommande(commandeActive);
			}
			}catch(NullPointerException n){
				JOptionPane.showMessageDialog(frame,
					    "Veuillez créer et sélectionner une commande",
					    "Erreur",
					    JOptionPane.ERROR_MESSAGE);
			}catch(Exception e2){
				System.out.println("Erreur lors de l'envoi");
			}
		}else if(source==bRetirerItem){
			try{
				Commande commandeActive = (Commande) cbListeCommande.getSelectedItem();
				commandeActive.supprimerLigneCommande(tCommande.getSelectedRow());
				afficherLignesCommande(commandeActive);
			}catch(IndexOutOfBoundsException o){
				JOptionPane.showMessageDialog(frame,
					    "Veuillez sélectionner un item",
					    "Erreur",
					    JOptionPane.ERROR_MESSAGE);
			}catch(NullPointerException n){
				JOptionPane.showMessageDialog(frame,
					    "Veuillez créer et sélectionner une commande",
					    "Erreur",
					    JOptionPane.ERROR_MESSAGE);
			}
		}else if(source==bNotifier){
			try{
			Commande commandeActive = (Commande) cbListeCommande.getSelectedItem();
			commandeActive.setAllEtats("Non notifié", "En attente");
			afficherLignesCommande(commandeActive);
			}catch(NullPointerException n){
				JOptionPane.showMessageDialog(frame,
					    "Veuillez créer et sélectionner une commande",
					    "Erreur",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}
