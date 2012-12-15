package Systeme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


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
	

	private String[][] ligneCommandeActives = new String[52][4];
	private String[] colonnesTableau = {"Description","Quantité","Sous-Total","État"};
	
	private JTable tCommande= new JTable(ligneCommandeActives, colonnesTableau);
	private JScrollPane spCommande = new JScrollPane(tCommande);
	
	private JPanel pBoutons = new JPanel();
	private JLabel lQuantite = new JLabel("Quantité:");
	private JTextField tQuantite = new JTextField();
	private JButton bAjouterItem = new JButton("Ajouter item");
	private JButton bRetirerItem = new JButton("Retirer item");
	private JButton bNotifier = new JButton("Notifier");
	private JButton bRafraichir = new JButton("Rafraîchir");
	private JButton bPayerCommande = new JButton("Payer la commande");
	private Archive monArchive;
	JFrame frame = new JFrame();
	InterfServeur(InterfDemarrer Entree){
		
		monEntree = Entree;
		monArchive = monEntree.monArchive;
		
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
		pBoutons.add(bRafraichir);
		pBoutons.add(bPayerCommande);
		bRetour.addActionListener(this);
		cbListeTable.addActionListener(this);
		bAjouterCommande.addActionListener(this);
		bRafraichir.addActionListener(this);
		cbListeCommande.addActionListener(this);
		bAjouterItem.addActionListener(this);
		bRetirerItem.addActionListener(this);
		bNotifier.addActionListener(this);
		bPayerCommande.addActionListener(this);
	}
	
	//Mets à jour la table de ligne de commande à partir de la commande active
	private void afficherLignesCommande(Commande commandeActive){
		try{
		String[][] tableLigneCommande = commandeActive.creerTableLigneCommande();
		int i=0;
		while (i<(tableLigneCommande.length)){
			for (int j=0; j<4; j++){
				ligneCommandeActives[i][j] = tableLigneCommande[i][j];
			}
			i++;
		}
		//Mets le reste à blanc
		while (i<(ligneCommandeActives.length)){
			for (int j=0; j<4; j++){
				ligneCommandeActives[i][j] = "";
			}
			i++;
		}
		tCommande.repaint();
		}catch(Exception e){
			System.out.println("Erreur lors de l'affichage de la ligne de commande");
		}
	}
	
	//Appelle la commande active pour changer tous les états "Non notifié" à "En attente"
	private void changerEtatLignesCommande(Commande commandeActive){
			commandeActive.setAllEtats("Non notifié", "En attente");	
	}
	
	//Retire l'item avec l'id demandé de la commande active
	private void retirerItem(Commande commandeActive, int id){
		commandeActive.supprimerLigneCommande(id);	
	}
	
	//Affiche le total et enregistre la commande dans la base de donné puis retire la commande
	private void payerCommande(Commande commandeActive){
		try{
			Object[] options = {"OK","Annuler",};
			
			int n = JOptionPane.showOptionDialog(frame,
					"Le total de la commande est de "+ Math.floor(commandeActive.getTotal() * 100) / 100,"Payer",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[1]);
			if(n==0){
				commandeActive.setHeureFin();
				if(monArchive.createNewCommande(commandeActive)){
					Restaurant.supprimerCommande(commandeActive);
					afficherCommandes();
				}
				
			}
		}catch(Exception e2){
			System.out.println("Erreur lors de l'envoi");
		}
	}
	
	private void afficherCommandes(){
		ArrayList<Commande> listeCommande = new ArrayList<Commande>();
		listeCommande = Restaurant.getListeCommandePourTable(cbListeTable.getSelectedItem().toString());
		cbListeCommande.removeAllItems();
		for(int i=0; i < listeCommande.size(); i++){
			cbListeCommande.addItem(listeCommande.get(i));
		}
	}
	
	//Ajoute un item du menu à la commande
	private void ajouterItem(Commande commandeActive, String itemSelection, int quantite){
		commandeActive.creerLigneCommande(itemSelection, quantite, monEntree.monArchive);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		try{
			if (source==bRetour){
				
				this.setVisible(false);
				monEntree.setVisible(true);
				
			//Affiche les lignes de commandes de la commande sélectionnée
			}else if(source==cbListeCommande){
				//Affiche uniquement s'il y a des commandes. Contre un bug lors du changement de table.
				if(Restaurant.getListeCommandePourTable(cbListeTable.getSelectedItem().toString()).size() > 0){
				Commande commandeActive = (Commande) cbListeCommande.getSelectedItem();
				afficherLignesCommande(commandeActive);
				}
			//Affiche les commandes actives pour la table sélectinonée
			}else if(source==cbListeTable){
				afficherCommandes();
				
			//Ajoute une commande à la table sélectionnée
			}else if(source==bAjouterCommande){
				Restaurant.creerCommande(cbListeTable.getSelectedItem().toString());
				ArrayList<Commande> listeCommande = new ArrayList<Commande>();
				listeCommande = Restaurant.getListeCommandePourTable(cbListeTable.getSelectedItem().toString());
				cbListeCommande.addItem(listeCommande.get(listeCommande.size()-1));
	
			//Ajoute un item du menu à la commande sélectionnée
			}else if(source==bAjouterItem){
				int quantite = 1;
				try{
					quantite = Integer.parseInt(tQuantite.getText());
				}catch(NumberFormatException q){
					quantite = 1;
				}
				Commande commandeActive = (Commande) cbListeCommande.getSelectedItem();
				ajouterItem(commandeActive, (String) cbListeMenu.getSelectedItem(),quantite);
				afficherLignesCommande(commandeActive);
				
			}else if(source==bPayerCommande){
				payerCommande((Commande) cbListeCommande.getSelectedItem());
				
			}else if(source==bRetirerItem){
				try{
					Commande commandeActive = (Commande) cbListeCommande.getSelectedItem();
					retirerItem(commandeActive, tCommande.getSelectedRow());
					afficherLignesCommande(commandeActive);
				}catch(IndexOutOfBoundsException o){
					JOptionPane.showMessageDialog(frame,
					"Veuillez sélectionner un item",
					"Erreur",
					JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(source==bNotifier){
				Commande commandeActive = (Commande) cbListeCommande.getSelectedItem();
				changerEtatLignesCommande(commandeActive);
				afficherLignesCommande(commandeActive);
			
			//Réaffiche les modifications à la commande
			}else if(source==bRafraichir){
				afficherLignesCommande((Commande) cbListeCommande.getSelectedItem());
			}
		
		//Utilisé pour vérifier si une commande a bien été sélectionné
		}catch(NullPointerException n){
			JOptionPane.showMessageDialog(frame,
				    "Veuillez créer et sélectionner une commande",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
