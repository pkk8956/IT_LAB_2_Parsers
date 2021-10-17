package main;

import org.xml.sax.SAXException;
import parsers.DOOMParser;
import parsers.JAXBParser;

public class Demo {

    public static void main(String[] args) throws SAXException {
        new JAXBParser().main();
        new DOOMParser().main();




    }
}
