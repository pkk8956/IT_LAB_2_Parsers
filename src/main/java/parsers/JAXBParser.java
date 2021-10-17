package parsers;

import entity.Request;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBParser {

    public void parsing(){

        try {
            File file = new File(Properties.XML_PATH);

            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Request request = (Request) jaxbUnmarshaller.unmarshal(file);
            System.out.println(request.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
