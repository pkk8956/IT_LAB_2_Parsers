package entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Person {

    private String identificationNumber;
    private String surname;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String nationality;
    private String date;

    public Person(){}

    public Person(String identificationNumber, String surname, String firstName, String lastName, String address, String gender, String nationality, String date) {
        super();
        this.identificationNumber = identificationNumber;
        this.surname = surname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.nationality = nationality;
        this.date = date;
    }



    @XmlAttribute(name = "identification_number")
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    @XmlElement(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlElement(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @XmlElement(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlElement(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return
                        "Surname = '" + surname + '\'' +
                        ", FirstName = '" + firstName + '\'' +
                        ", LastName = '" + lastName + '\'' +
                        ", BirthDate = '" + date + '\'' +
                                ", Nationality = '" + nationality + '\'';
    }
}
