package cs.ds.dao.interfaces;


import cs.ds.domain.Patient;

import java.rmi.RemoteException;

public interface PatientDAO extends GenericDAO<Patient, Long> {
    public byte[] downloadFile(String fileName) throws
            RemoteException;
}
