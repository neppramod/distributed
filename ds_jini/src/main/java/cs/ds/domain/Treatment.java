package cs.ds.domain;


import org.hibernate.annotations.CollectionOfElements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Treatment: One instance of patient's treatment, including location of treatment files as list of treatmentReports
 */
@Entity
@Table(name = "treatment")
public class Treatment {
    private Long id;
    private String title;
    private String nameofDoctor;
    private String nameOfHospital;
    private Address addressOfHospital;
    private Date dateOfTreatment;
    private String symptoms;
    private String descriptionOfTreatment;
    private List<String> treatmentReports = new ArrayList<String>();

    @Id
    @GeneratedValue
    @Column(name="treatment_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameofDoctor() {
        return nameofDoctor;
    }

    public void setNameofDoctor(String nameofDoctor) {
        this.nameofDoctor = nameofDoctor;
    }

    public String getNameOfHospital() {
        return nameOfHospital;
    }

    public void setNameOfHospital(String nameOfHospital) {
        this.nameOfHospital = nameOfHospital;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    public Address getAddressOfHospital() {
        return addressOfHospital;
    }

    public void setAddressOfHospital(Address addressOfHospital) {
        this.addressOfHospital = addressOfHospital;
    }

    public Date getDateOfTreatment() {
        return dateOfTreatment;
    }

    public void setDateOfTreatment(Date dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDescriptionOfTreatment() {
        return descriptionOfTreatment;
    }

    public void setDescriptionOfTreatment(String descriptionOfTreatment) {
        this.descriptionOfTreatment = descriptionOfTreatment;
    }


    @ElementCollection
    @JoinTable(name="treatment_reports",
            joinColumns = @JoinColumn(name="treatment_id") // References parent
    )
    @Column(name="report_uri", nullable=false)
    public List<String> getTreatmentReports() {
        return treatmentReports;
    }


    public void setTreatmentReports(List<String> treatmentReports) {
        this.treatmentReports = treatmentReports;
    }

}
