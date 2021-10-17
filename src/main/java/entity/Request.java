package entity;

import main.Color;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class Request {

    private Passport passport;
    private Person person;
    private Worker worker;
    private int id;
    private boolean paid;

    public Request(){}

    public Request(Passport passport, Person person, Worker worker, int id, boolean paid) {
        super();
        this.passport = passport;
        this.person = person;
        this.worker = worker;
        this.id = id;
        this.paid = paid;
    }
    @XmlElement(name = "passport")
    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @XmlElement(name = "person")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @XmlElement(name = "worker")
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute(name = "paid")
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return Color.RED + "\nRequest:" + Color.RESET +
           Color.BLUE + " Passport =" + Color.RESET +  " [" + passport + "]" +
                Color.BLUE + ", \n         Person =" + Color.RESET +  " [" + person + "]" +
                Color.BLUE + ", \n         Worker =" + Color.RESET +  " [" + worker + "]" +
                Color.BLUE + ", \n         Id = " + Color.RESET  + id +
                Color.BLUE + ", Paid = " +Color.RESET + paid + "\n";
    }
}
