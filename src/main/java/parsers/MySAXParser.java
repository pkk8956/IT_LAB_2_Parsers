package parsers;

import entity.Request;
import main.Color;
import main.Properties;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class MySAXParser {

    private Request unmarshallingUsingSAX() throws SAXException {
        System.out.println(Color.GREEN + "Unmarshalling..." + Color.RESET);

        Request request = null;

        if (new Validator().validation(Properties.XML_PATH)){
            SAXParserFactory factory = SAXParserFactory.newInstance();

            try {
                javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
                SAXHandlers handler = new SAXHandlers();
                saxParser.parse(Properties.XML_PATH, handler);
                request = handler.getCurrentRequest();

            } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
                e.printStackTrace();
            }
        }
        return request;
    }

    public void main() throws SAXException {

        System.out.println("\n=================SAX Parser is working =================");
        System.out.println(unmarshallingUsingSAX());

    }
}
