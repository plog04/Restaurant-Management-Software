package Systeme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class IConsulterCommandeServeur extends Fenetre implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pCommandes = new JPanel();
	
	private JComboBox cbListeTable;
	private String[] listeTable = new String[] {"Table1", "Table2", "Table3"};
	private JComboBox cbListeCommande;
	private String[] listeCommande = new String[] {"Commande1", "Commande2", "Commande3"};
	private JPanel pListeTables = new JPanel();
	private JPanel pListeCommandes = new JPanel();
	private JPanel pAjouterCommande = new JPanel();
	private JButton bAjouterCommande = new JButton("Ajouter commande");
	private InterfCompteEntree monEntree;
	
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
	IConsulterCommandeServeur(InterfCompteEntree Entree){
		//super();
		monEntree = Entree;
		this.setTitle("Serveur");
		//cFenetre.setLayout(new BorderLayout());
		cFenetre.add(pCommandes, BorderLayout.WEST);
		
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
		
		cFenetre.add(spCommande, BorderLayout.CENTER);
		pRetour.add(pBoutons, BorderLayout.NORTH);
		
		pBoutons.setLayout(new GridLayout(1,4));
		pBoutons.add(bAjouterItem);
		pBoutons.add(bRetirerItem);
		pBoutons.add(bNotifier);
		pBoutons.add(bPayerCommande);
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
