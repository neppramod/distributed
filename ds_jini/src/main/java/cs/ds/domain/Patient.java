package cs.ds.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * Patient: A person who takes service from hospital, who gets treatment. A patient has list of
 * Treatments called treatmentHistory
 */
@Entity
@Table(name = "patient")
public class Patient {

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

    @Id
    @GeneratedValue
    @Column(name="patient_id")

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_dob_id")
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="treatment_history_list",
            joinColumns=@JoinColumn(name="patient_id"),
            inverseJoinColumns=@JoinColumn(name="treatment_id")
    )
    public List<Treatment> getTreatmentHistory() {
        return treatmentHistory;
    }

    public void setTreatmentHistory(List<Treatment> treatmentHistory) {
        this.treatmentHistory = treatmentHistory;
    }
}
