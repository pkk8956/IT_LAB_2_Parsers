package entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Passport {

    private String serialNumber;
    private String validity;
    private String issuedBy;
    private String RNTRC;
    private String date;

    public Passport(){}

    public Passport(String serialNumber, String validity, String issuedBy, String RNTRC, String data) {
        super();
        this.serialNumber = serialNumber;
        this.validity = validity;
        this.issuedBy = issuedBy;
        this.RNTRC = RNTRC;
        this.date = data;
    }

    @XmlAttribute(name = "serial_number")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @XmlElement(name = "validity")
    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    @XmlElement(name = "Issued_by")
    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    @XmlElement(name = "RNTRC")
    public String getRNTRC() {
        return RNTRC;
    }

    public void setRNTRC(String RNTRC) {
        this.RNTRC = RNTRC;
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
        return "Passport{" +
                "serialNumber='" + serialNumber + '\'' +
                ", validity='" + validity + '\'' +
                ", issuedBy='" + issuedBy + '\'' +
                ", RNTRC='" + RNTRC + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
