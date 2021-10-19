package parsers;

import entity.Passport;
import entity.Person;
import entity.Request;
import entity.Worker;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandlers extends DefaultHandler {

    private final StringBuilder currentValue = new StringBuilder();

    private Request currentRequest;
    private Passport currentPassport;
    private Person currentPerson;
    private Worker currentWorker;

    private int id;
    private boolean paid;

    public Request getCurrentRequest() {
        return currentRequest;
    }

    @Override
    public void startDocument(){
    }

    @Override
    public void endDocument() {
        currentRequest = new Request(currentPassport, currentPerson, currentWorker, id, paid);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){

        currentValue.setLength(0);

        if (qName.equalsIgnoreCase("request")) {
            id = Integer.parseInt(attributes.getValue("id"));
            paid = Boolean.parseBoolean(attributes.getValue("paid"));
        }

        if (qName.equalsIgnoreCase("passport")) {
            currentPassport = new Passport();
            currentPassport.setSerialNumber(attributes.getValue("serial_number"));
        }

        if (qName.equalsIgnoreCase("person")) {
            currentPerson = new Person();
            currentPerson.setIdentificationNumber(attributes.getValue("identification_Number"));
        }

        if (qName.equalsIgnoreCase("worker")) {
            currentWorker = new Worker();
            currentWorker.setId(Integer.parseInt(attributes.getValue("worker_id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equalsIgnoreCase("validity")) {
            currentPassport.setValidity(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("issued_by")) {
            currentPassport.setIssuedBy(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("data")) {
            currentPassport.setDate(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("surname")) {
            currentPerson.setSurname(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("nationality")) {
            currentPerson.setNationality(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("first_name")) {
            currentPerson.setFirstName(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("last_name")) {
            currentPerson.setLastName(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("address")) {
            currentPerson.setAddress(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("gender")) {
            currentPerson.setGender(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("date")) {
            currentPerson.setDate(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("post")) {
            currentWorker.setPost(currentValue.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }
}
