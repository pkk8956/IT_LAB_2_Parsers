package entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Worker {

    private int id;
    private String post;

    public Worker(){}

    public Worker(int id, String post) {
        super();
        this.id = id;
        this.post = post;
    }

    @XmlAttribute(name = "worker_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "post")
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return
                "Id = " + id +
                ", Post = '" + post + '\'';

    }
}
