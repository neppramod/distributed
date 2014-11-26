package cs.ds.domain;

import java.io.Serializable;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
*/
//@Entity
//@Table(name = "contact")
public class Contact implements Serializable{
    private Long id;
    private String address;

    //@Id
    //@GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
