package Pruttlab5;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class LinkGetter{

	
	
	public static String[][] getLinksFromPage(String webpage,int numberToGet) throws IOException, BadLocationException{
			
			InputStream in=new URL(webpage).openConnection().getInputStream();
			InputStreamReader reader= new InputStreamReader(in);
			HTMLDocument doc = new HTMLDocument();
			doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
			new HTMLEditorKit().read(reader,doc,0);
			String[][] links=new String[numberToGet][2];
			int i=0;
			for (HTMLDocument.Iterator iterator = doc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {
				if(i>=links.length){
					break;
				}
				
				AttributeSet attributes=iterator.getAttributes();
				links[i][0]= (String) attributes.getAttribute(HTML.Attribute.HREF);
				
				int startOffset = iterator.getStartOffset();
			    int endOffset = iterator.getEndOffset();
			    int length = endOffset - startOffset;
			    links[i][1] = doc.getText(startOffset, length);
			    i++;
			}
			return links;
			
				
	}
	
	/*public void getLinksFromPage(String webpage){
		int maxRows=50;
		
		try {
			InputStream in=new URL(webpage).openConnection().getInputStream();
			Scanner sc=new Scanner(in);
			Pattern pat = Pattern.compile(linkPattern,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			String[] link;
			int i=0;
			model.setRowCount(0);
			while (((sc.findWithinHorizon(pat, 0) != null)&&(i<maxRows)))
			{
				link=new String[2];
				MatchResult m = sc.match();
				link[0]=m.group(1);
				link[1]=m.group(2);
				model.addRow(link);
				i++;
			}
			model.fireTableDataChanged();
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}*/

}
