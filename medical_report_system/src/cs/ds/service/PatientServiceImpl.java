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

        return patientDAO.readPatients();
    }

    @Override
    public void printPatients() {
        patientDAO.printPatients();
    }


    @Override
    public void addPatient(Patients patients, Patient newPatient) {
        patientDAO.addPatient(patients, newPatient);
    }

    @Override
    public void removePatient(Patients patients, Patient removedPatient) {
        patientDAO.removePatient(patients, removedPatient);
    }

    @Override
    public Patient findPatientById(Long id) {
        return patientDAO.findPatientById(id);
    }

    public boolean getLogin(String encryptedLoginMessage) {
        return patientDAO.getLogin(encryptedLoginMessage);
    }
}
