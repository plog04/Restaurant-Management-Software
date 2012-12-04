package Systeme;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//protected Container cFenetre;
	protected Container cFenetre;
	protected JPanel pRetour = new JPanel();
	//protected BorderLayout layout = new BorderLayout();
	protected JButton bRetour = new JButton("Retour aux choix de compte");
	
	public Fenetre(){
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		pRetour.setLayout(new BorderLayout());
		pRetour.add(bRetour,BorderLayout.SOUTH);
		cFenetre = getContentPane();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		cFenetre.setLayout(new BorderLayout());
		cFenetre.add(pRetour, BorderLayout.SOUTH);
		
		pRetour.setVisible(false);
	}

}
