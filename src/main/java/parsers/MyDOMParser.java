package parsers;

import entity.Passport;
import entity.Person;
import entity.Request;
import entity.Worker;
import main.Color;
import main.Properties;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class MyDOMParser {

    private Request unmarshallingUsingDOM() throws ParserConfigurationException, IOException, SAXException {
        System.out.println(Color.GREEN + "Unmarshalling..." + Color.RESET);

        Request request = null;

        if(new Validator().validation(Properties.XML_PATH)){

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(Properties.XML_PATH));
            document.getDocumentElement().normalize();

            NodeList nList = document.getElementsByTagName("request");

            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    request  = new Request();

                    Passport passport = new Passport();
                    passport.setSerialNumber(eElement.getElementsByTagName("passport").item(0).getAttributes().getNamedItem("serial_number").getTextContent());
                    passport.setValidity(eElement.getElementsByTagName("validity").item(0).getTextContent());
                    passport.setIssuedBy(eElement.getElementsByTagName("issued_by").item(0).getTextContent());
                    passport.setRNTRC(eElement.getElementsByTagName("RNTRC").item(0).getTextContent());
                    passport.setDate(eElement.getElementsByTagName("data").item(0).getTextContent());

                    Person person = new Person();
                    person.setIdentificationNumber(eElement.getElementsByTagName("person").item(0).getAttributes().getNamedItem("identification_number").getTextContent());
                    person.setSurname(eElement.getElementsByTagName("surname").item(0).getTextContent());
                    person.setFirstName(eElement.getElementsByTagName("first_name").item(0).getTextContent());
                    person.setLastName(eElement.getElementsByTagName("last_name").item(0).getTextContent());
                    person.setAddress(eElement.getElementsByTagName("address").item(0).getTextContent());
                    person.setNationality(eElement.getElementsByTagName("nationality").item(0).getTextContent());
                    person.setGender(eElement.getElementsByTagName("gender").item(0).getTextContent());
                    person.setDate(eElement.getElementsByTagName("date").item(0).getTextContent());

                    Worker worker = new Worker();
                    worker.setId(Integer.parseInt(eElement.getElementsByTagName("worker").item(0).getAttributes().getNamedItem("worker_id").getTextContent()));
                    worker.setPost(eElement.getElementsByTagName("post").item(0).getTextContent());

                    request.setPassport(passport);
                    request.setPerson(person);
                    request.setWorker(worker);
                    request.setId(Integer.parseInt(eElement.getAttribute("id")));
                    request.setPaid(Boolean.parseBoolean(eElement.getAttribute("paid")));
                }
            }
        }
        return request;
    }

    private void marshallingUsingDOM(Request request) throws SAXException{
        System.out.println(Color.GREEN + "Marshalling..." + Color.RESET);

        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("request");
            document.appendChild(root);

            Attr id = document.createAttribute("id");
            id.setValue(Integer.toString(request.getId()));
            root.setAttributeNode(id);

            Attr paid = document.createAttribute("paid");
            paid.setValue(Boolean.toString(request.isPaid()));
            root.setAttributeNode(paid);

            Element passport = document.createElement("passport");
            root.appendChild(passport);

            Attr serial_number = document.createAttribute("serial_number");
            serial_number.setValue(request.getPassport().getSerialNumber());
            passport.setAttributeNode(serial_number);

            Element validity = document.createElement("validity");
            validity.appendChild(document.createTextNode(request.getPassport().getValidity()));
            passport.appendChild(validity);

            Element issued_by = document.createElement("issued_by");
            issued_by.appendChild(document.createTextNode(request.getPassport().getIssuedBy()));
            passport.appendChild(issued_by);

            Element RNTRC = document.createElement("RNTRC");
            RNTRC.appendChild(document.createTextNode(request.getPassport().getRNTRC()));
            passport.appendChild(RNTRC);

            Element data = document.createElement("data");
            data.appendChild(document.createTextNode(request.getPassport().getDate()));
            passport.appendChild(data);

            Element person = document.createElement("person");
            root.appendChild(person);

            Attr identification_number = document.createAttribute("identification_number");
            identification_number.setValue(request.getPerson().getIdentificationNumber());
            person.setAttributeNode(identification_number);

            Element surname = document.createElement("surname");
            surname.appendChild(document.createTextNode(request.getPerson().getSurname()));
            person.appendChild(surname);

            Element firstName = document.createElement("first_name");
            firstName.appendChild(document.createTextNode(request.getPerson().getFirstName()));
            person.appendChild(firstName);

            Element lastName = document.createElement("last_name");
            lastName.appendChild(document.createTextNode(request.getPerson().getLastName()));
            person.appendChild(lastName);

            Element nationality = document.createElement("nationality");
            nationality.appendChild(document.createTextNode(request.getPerson().getNationality()));
            person.appendChild(nationality);

            Element address = document.createElement("address");
            address.appendChild(document.createTextNode(request.getPerson().getAddress()));
            person.appendChild(address);

            Element gender = document.createElement("gender");
            gender.appendChild(document.createTextNode(request.getPerson().getGender()));
            person.appendChild(gender);

            Element date = document.createElement("date");
            date.appendChild(document.createTextNode(request.getPerson().getDate()));
            person.appendChild(date);

            Element worker = document.createElement("worker");
            root.appendChild(worker);

            Attr workerID = document.createAttribute("worker_id");
            workerID.setValue(Integer.toString(request.getWorker().getId()));
            worker.setAttributeNode(workerID);

            Element post = document.createElement("post");
            post.appendChild(document.createTextNode(request.getWorker().getPost()));
            worker.appendChild(post);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(Properties.DOM_XML_PATH));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            transformer.transform(domSource, streamResult);
            System.out.println(Properties.DOM_XML_PATH + " is created");

        }catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
        new Validator().validation(Properties.DOM_XML_PATH);
    }

    public void main(Request request) throws IOException, SAXException, ParserConfigurationException {

        System.out.println("\n=================DOOM Parser is working =================");
        System.out.println(unmarshallingUsingDOM());
        marshallingUsingDOM(request);
    }
}
