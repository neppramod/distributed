package cs.ds.domain;

import java.util.List;

public class Patients {
    //private List<Patient> patients = new ArrayList<Patient>();
    private  List<Patient> patientsList;

    public List<Patient> getPatientsList() {
        return patientsList;
    }

    public void setPatientsList(List<Patient> patientsList) {
        this.patientsList = patientsList;
    }

    public void addPatient(Patient patient) {patientsList.add(patient); }

    public void removePatient(Patient patient) {patientsList.remove(patient);}
}
