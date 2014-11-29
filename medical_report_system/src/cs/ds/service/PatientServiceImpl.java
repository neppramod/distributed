package cs.ds.service;

import cs.ds.dao.interfaces.PatientDAO;
import cs.ds.domain.Patient;
import cs.ds.domain.Patients;
import cs.ds.service.interfaces.PatientService;

import java.rmi.RemoteException;
import java.util.List;

public class PatientServiceImpl extends  GenericServiceImpl<Patient, Long> implements PatientService{
    protected PatientDAO patientDAO;

    public PatientServiceImpl(PatientDAO patientDAO) {
        super(patientDAO);
        this.patientDAO = patientDAO;
    }

    @Override
    public byte[] downloadFile(String fileName) throws RemoteException {
        return patientDAO.downloadFile(fileName);
    }

    @Override
    public List<Patient> readPatients() {

        /*
        if (patientDAO.readPatients() == null) {
            System.out.println("Patients are null");
        }
        */

        return patientDAO.readPatients();
    }

    @Override
    public void printPatients() {
        patientDAO.printPatients();
    }

    //@Override
    /*
    public void writePatients(Patients patients) {
        patientDAO.writePatients(patients);
    }
    */

    @Override
    public void addPatient(Patients patients, Patient newPatient) {
        patientDAO.addPatient(patients, newPatient);
    }

    @Override
    public void removePatient(Patients patients, Patient removedPatient) {
        patientDAO.removePatient(patients, removedPatient);
    }
}
