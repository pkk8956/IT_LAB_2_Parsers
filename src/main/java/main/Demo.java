package main;

import entity.Passport;
import entity.Person;
import entity.Request;
import entity.Worker;
import org.xml.sax.SAXException;
import parsers.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {

        Passport passport1 = new Passport("28132168","2032-05-23", "1582", "8543957523", "2022-09-23");
        Passport passport2 = new Passport("73464601","2032-05-23", "8924", "7947613467", "2022-09-23");
        Person person1 = new Person("4313843153445", "Веснянка", "Владислав", "Олександрович", "Балаклея", "М", "UA", "2000-08-11");
        Person person2 = new Person("5513813125135", "Тарасенко", "Денис", "Петрович", "Краснокутск", "М", "UA", "2000-05-18");
        Worker worker = new Worker(6, "Главный работник");

        Request request1 = new Request(passport1, person1, worker, 9, true);
        Request request2 = new Request(passport2, person2, worker, 10, true);

        new MyJAXBParser().main(request1);
        new MyDOMParser().main(request2);
        new MySAXParser().main();

        if (new Validator().validation(Properties.XML_PATH)){
            if(XSLTransform.main()){
                System.out.println("src/xml/Request.html is created");
            }
        }
        else System.out.println("html-file is not created");


    }
}
