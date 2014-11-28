package cs.ds.service;

import cs.ds.dao.interfaces.PatientDAO;
import cs.ds.domain.Patient;
import cs.ds.service.interfaces.PatientService;

import java.rmi.RemoteException;

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
}
