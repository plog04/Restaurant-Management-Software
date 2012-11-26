import java.awt.Container;
import javax.swing.JFrame;


public class Fenetre extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Container cFenetre;
	//protected BorderLayout layout = new BorderLayout();
	
	public Fenetre(){
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		cFenetre = getContentPane();
		//cFenetre.setLayout(layout);
	}

}
