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
	
	private JComboBox<String> cbListeTable;
	private JComboBox<Commande> cbListeCommande;
	private JPanel pListeTables = new JPanel();
	private JPanel pListeCommandes = new JPanel();
	private JPanel pAjouterCommande = new JPanel();
	private JButton bAjouterCommande = new JButton("Ajouter commande");
	private InterfCompteEntree monEntree;
	

	private String[][] ligneCommandeActives = new String[30][4];
	private String[] colonnesTableau = {"Description","Quantité","Sous-Total","État"};
	
	private JTable tCommande= new JTable(ligneCommandeActives, colonnesTableau);
	private JScrollPane spCommande = new JScrollPane(tCommande);
	
	private JPanel pBoutons = new JPanel();
	private JButton bAjouterItem = new JButton("Ajouter item");
	private JButton bRetirerItem = new JButton("Retirer item");
	private JButton bNotifier = new JButton("Notifier");
	private JButton bPayerCommande = new JButton("Payer la commande");
	
	InterfServeur(InterfCompteEntree Entree){
		//super();
		//{"Description1","Quantité1","Sous-Total1","État1","Description2","Quantité2","Sous-Total2","État2"}
		//{"Description","Quantité","Sous-Total","État"}
		/*
		colonnesTableau.add("Description");
		colonnesTableau.add("Quantité");
		colonnesTableau.add("Sous-Total");
		colonnesTableau.add("État");
		
		ligneCommandeActives.add("Description1");
		ligneCommandeActives.add("Quantité1");
		ligneCommandeActives.add("Sous-Total1");
		ligneCommandeActives.add("État1");
		ligneCommandeActives.add("Description2");
		ligneCommandeActives.add("Quantité2");
		ligneCommandeActives.add("Sous-Total2");
		ligneCommandeActives.add("État2");
		tCommande.getModel().addTableModelListener(tCommande);
		//tCommande.addColumn(new TableColumn());
		*/
		monEntree = Entree;
		this.setTitle("Serveur");
		//cFenetre.setLayout(new BorderLayout());
		cFenetre.add(pCommandes, BorderLayout.WEST);
		
		cbListeTable = new JComboBox<String>(Restaurant.getListeTable());
		cbListeTable.setSize(new Dimension(100,100));
		cbListeCommande = new JComboBox<Commande>();
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
		//this.setVisible(true);
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
			//ligneCommandeActives
			
			String[][] tableLigneCommande = commandeActive.creerTableLigneCommande();
			//System.out.println(.length);
			
			int i=0;
			while (i<(tableLigneCommande.length/4)){
				for (int j=0; j<4; j++){
					ligneCommandeActives[i][j] = tableLigneCommande[i][j];
				}
				i++;
			}
			while (i<(ligneCommandeActives.length/4)){
				for (int j=0; j<4; j++){
					ligneCommandeActives[i][j] = "";
				}
				i++;
			}
			tCommande.repaint();
			
		}else if(source==bAjouterItem){
			Commande commandeActive = (Commande) cbListeCommande.getSelectedItem();
			System.out.println(commandeActive.toString());
			
			int code = 101;
			int quantite = 1;
			commandeActive.creerLigneCommande(code, quantite);
			System.out.println(commandeActive.creerTableLigneCommande().length);
		}
	}
}
