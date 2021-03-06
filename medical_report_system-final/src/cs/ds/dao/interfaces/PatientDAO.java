package cs.ds.dao.interfaces;


import cs.ds.domain.Patient;
import cs.ds.domain.Patients;

import java.rmi.RemoteException;
import java.util.List;

public interface PatientDAO extends GenericDAO<Patient, Long> {
    public byte[] downloadFile(String fileName) throws
            RemoteException;

    public List<Patient> readPatients();
    public void writePatients(Patients patients);

    public void addPatient(Patients patients, Patient newPatient);

    public void removePatient(Patients patients, Patient removedPatient);

    public void printPatients();

    public Patient findPatientById(Long id);

    public boolean getLogin(String encryptedLoginMessage);
}
