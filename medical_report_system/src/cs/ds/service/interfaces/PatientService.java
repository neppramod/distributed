package cs.ds.service.interfaces;

import cs.ds.domain.Patient;

import java.rmi.RemoteException;

public interface PatientService extends GenericService<Patient, Long> {
    public byte[] downloadFile(String fileName) throws
            RemoteException;
}
