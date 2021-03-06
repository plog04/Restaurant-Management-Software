package Systeme;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfDemarrer extends Fenetre implements ActionListener{
	
	/**
	 * 
	 */
	public Archive monArchive = new Archive();
	 
	
	InterfServeur fServeur = new InterfServeur(this);
	InterfCuisinier fCuisinier = new InterfCuisinier(this);	
	InterfStatistique fGestionnaire = new InterfStatistique(this,monArchive);
	
	private JButton bServeur = new JButton("Serveur");
	private JButton bCuisinier = new JButton("Cuisinier");
	private JButton bGestionnaire = new JButton("Gestionnaire");
	//private JButton bAdministrateur = new JButton("Administrateur");
	

	
	private static final long serialVersionUID = 1L;
	
	
	JPanel pCompteEntree = new JPanel();
	JPanel ptitre = new JPanel();
	
	
	public InterfDemarrer(){
		super();
		
		try {
			Restaurant.lastId = monArchive.getLastId();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fServeur.setVisible(false);
		fCuisinier.setVisible(false);
		fGestionnaire.setVisible(false);
		
		
		this.setTitle("Compte d'entr�e");
	//cFenetre.setLayout(new BorderLayout());
		cFenetre.add(ptitre,BorderLayout.NORTH);
		cFenetre.add(pCompteEntree,BorderLayout.CENTER);
		
		JLabel labTitre = new JLabel("Choissisez votre compte");
		
	
		
		ptitre.add(labTitre);
		pCompteEntree.add(bServeur);
		pCompteEntree.add(bCuisinier);
		pCompteEntree.add(bGestionnaire);
		//pCompteEntree.add(bAdministrateur);
		
		bServeur.addActionListener(this);
		bCuisinier.addActionListener(this);
		bGestionnaire.addActionListener(this);
		//bAdministrateur.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		
		if (source==bServeur){
			this.setVisible(false);
			fServeur.pRetour.setVisible(true);
			fServeur.setVisible(true);
			
			
		}
		if (source==bCuisinier){
			this.setVisible(false);
			fCuisinier.pRetour.setVisible(true);
			fCuisinier.setVisible(true);
			
			
		}
		if (source==bGestionnaire){
			this.setVisible(false);
			fGestionnaire.pRetour.setVisible(true);
			fGestionnaire.setVisible(true);
			
			
		}
		/*
		if (source==bAdministrateur){
		//	fAdministrateur.setVisible(true);
		}
		*/
	
	}

}
