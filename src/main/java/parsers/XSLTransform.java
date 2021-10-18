package parsers;

import main.Properties;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTransform {

	public static boolean main() throws TransformerException {

		System.out.println("\n=================XSL Transformer is working =================");

		String xsl = Properties.XSL_PATH;
		String xml = Properties.XML_PATH;
		String htm = Properties.HTML_PATH;
		
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(new File(xsl)));

		transformer.transform(new StreamSource(new File(xml)), new StreamResult(new File(htm)));
		return true;
	}
}
