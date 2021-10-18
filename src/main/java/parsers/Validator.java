package parsers;

import main.Properties;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Validator {

    public boolean validation() throws SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema schema = sf.newSchema(new File(Properties.XSD_PATH));
        javax.xml.validation.Validator validator = schema.newValidator();
        Source source = new StreamSource(Properties.XML_PATH);

        try {
            validator.validate(source);
            return true;
        } catch (SAXException | IOException ex) {
            System.out.println(Properties.XML_PATH + " is not valid because ");
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
