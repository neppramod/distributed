package test.cs.ds.dao;

import cs.ds.dao.PatientDAOImpl;
import cs.ds.dao.interfaces.PatientDAO;
import cs.ds.domain.Patient;

import java.util.List;

public class TestGenericDAOImpl {
    public static void main(String[] args) {
        PatientDAO patientDAO = new PatientDAOImpl();

        TestGenericDAOImpl testGenericDAOImpl = new TestGenericDAOImpl();


        testGenericDAOImpl.printPatients(patientDAO);
    }

    private void printPatients (PatientDAO patientDAO) {
        List<Patient> patients = patientDAO.readPatients();

        if (patients == null)
            System.out.println("Patients null");

        for (Patient p : patients) {
            System.out.println(p);
        }
    }
}
