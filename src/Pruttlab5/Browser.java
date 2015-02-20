package Pruttlab5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

public class Browser extends JFrame implements ActionListener, HyperlinkListener {
	private static final long serialVersionUID = 1L;
	
	private JTextField adressbar;
	private WebReader webWindow;
	private JTable linkTable;
	
	private JScrollPane tableScroll;
	private JScrollPane webScroll;
	
	private String[] columnHeads ={"Links","Text"};
	
	private static final double webWLengthRel =0.6;
	private static final int adressBarLengthPix = 60;
	
	private static final int numberOfLinks=50;
	
	public Browser() {
		super();
		
		this.setSize(new Dimension(700,500));
		
		adressbar=new JTextField();
		adressbar.setSize(this.getWidth(), adressBarLengthPix);
		adressbar.addActionListener(this);
		
		linkTable= new JTable(numberOfLinks,2);
		linkTable.setEnabled(false);
		
		
		tableScroll = new JScrollPane(linkTable);
		
		webWindow=new WebReader();
		webWindow.setEditable(false);
		webWindow.addHyperlinkListener(this);
		
		webScroll = new JScrollPane(webWindow);
		
		this.add(adressbar,BorderLayout.NORTH);
		this.add(webScroll,BorderLayout.WEST);
		this.add(tableScroll,BorderLayout.EAST);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
	@Override
	public void validate(){
		webScroll.setPreferredSize(new Dimension((int)Math.round(this.getWidth()*webWLengthRel), this.getHeight()-adressBarLengthPix));
		tableScroll.setPreferredSize(new Dimension((int)Math.round(this.getWidth()*(1-webWLengthRel)-1),this.getHeight()-adressBarLengthPix));
		super.validate();
	}
	
	


	public static void main(String[] args) {
		new Browser();

	}


	@Override
	public void actionPerformed(ActionEvent event) {
		String url=adressbar.getText();
		webWindow.ShowPage(url);
		setUpLinksTable(url);
	}
	
	private void setUpLinksTable(String url){
		try {
			String[][] linkData=LinkGetter.getLinksFromPage(url,numberOfLinks);
			DefaultTableModel model = (DefaultTableModel) linkTable.getModel();
			model.setRowCount(0);
			model.setColumnIdentifiers(columnHeads);
			for(String[] link: linkData){
				model.addRow(link);
			}
			model.fireTableDataChanged();
			
		} catch (IOException e) {
		} catch (BadLocationException e) {
			JOptionPane.showMessageDialog(null, "An error occured when displaying the links");
		}
	}
	
	


	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			String url=e.getURL().toExternalForm();
			webWindow.ShowPage(url);
			setUpLinksTable(url);
			adressbar.setText(url);
		}

	}

}
