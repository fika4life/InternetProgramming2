package Pruttlab5;

import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;


public class WebReader extends JEditorPane  {

	private static final long serialVersionUID = 1L;
	private static final String errorMessage="The website could not be found";

	public WebReader() {
		super();
	}
	
	public void ShowPage(String webaddr){
		try {
			this.setPage(webaddr);
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, errorMessage);
		}
		catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, errorMessage);
		}
	}

	

}
