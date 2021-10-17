package parsers;

import entity.Passport;
import entity.Person;
import entity.Request;
import entity.Worker;
import main.Color;
import main.Properties;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class JAXBParser {

    public Request unmarshallingWithValidation() throws SAXException {
        System.out.println(Color.GREEN + "Unmarshalling..." + Color.RESET);

        if (validation()){
            System.out.println(Properties.XML_PATH + " is valid.");

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

    public void marshallingWithValidation(Request request) throws SAXException {
        System.out.println(Color.GREEN + "Marshalling..." + Color.RESET);

        if(validation()){
            System.out.println(Properties.XML_PATH + " is valid.");

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
        }
    }

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

    public void main() throws SAXException {

        System.out.println("\n=================JAXB Parser is working =================");

        System.out.println(unmarshallingWithValidation());

        Passport passport = new Passport("28132168","2032-05-23", "1582", "8543957523", "2022-09-23");
        Person person = new Person("4313843153445", "Веснянка", "Владислав", "Олександрович", "Балаклея", "М", "2000-08-11");
        Worker worker = new Worker(6, "Главный работник");
        Request request = new Request(passport, person, worker, 9, true);

        marshallingWithValidation(request);

    }

}
