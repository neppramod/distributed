package cs.ds.service;

import cs.ds.dao.interfaces.PatientDAO;
import cs.ds.domain.Patient;
import cs.ds.service.interfaces.PatientService;

public class PatientServiceImpl extends  GenericServiceImpl<Patient, Long> implements PatientService{
    public PatientServiceImpl(PatientDAO patientDAO) {
        super(patientDAO);
    }
}
