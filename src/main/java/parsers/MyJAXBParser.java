package parsers;

import entity.Request;
import main.Color;
import main.Properties;
import org.xml.sax.SAXException;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MyJAXBParser {

    private Request unmarshallingUsingJAXB() throws SAXException {
        System.out.println(Color.GREEN + "Unmarshalling..." + Color.RESET);

        if (new Validator().validation(Properties.XML_PATH)){
            Request request = null;
            try {
                File file = new File(Properties.XML_PATH);

                JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                request = (Request) jaxbUnmarshaller.unmarshal(file);

            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return request;

        }
        return null;
    }

    private void marshallingUsingJAXB(Request request) throws SAXException {

        System.out.println(Color.GREEN + "Marshalling..." + Color.RESET);

        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            jaxbMarshaller.marshal(request, new File(Properties.JAXB_XML_PATH));
            System.out.println(Properties.JAXB_XML_PATH + " is created");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        new Validator().validation(Properties.JAXB_XML_PATH);
    }

    public void main(Request request) throws SAXException {

        System.out.println("\n=================JAXB Parser is working =================");
        System.out.println(unmarshallingUsingJAXB());

        marshallingUsingJAXB(request);
    }
}
