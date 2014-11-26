package cs.ds.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

//import javax.persistence.*;


/**
 * Patient: A person who takes service from hospital, who gets treatment. A patient has list of
 * Treatments called treatmentHistory
 */
//@Entity
//@Table(name = "patient")
public class Patient implements Serializable {

    private Long id;
    private String name;
    private Date dob;
    private Address dobPlace;
    private String ssn;
    private String phone;
    private Address address;
    private String fathersName;
    private String mothersName;
    private List<Treatment> treatmentHistory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getDobPlace() {
        return dobPlace;
    }

    public void setDobPlace(Address dobPlace) {
        this.dobPlace = dobPlace;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public List<Treatment> getTreatmentHistory() {
        return treatmentHistory;
    }

    public void setTreatmentHistory(List<Treatment> treatmentHistory) {
        this.treatmentHistory = treatmentHistory;
    }
}
