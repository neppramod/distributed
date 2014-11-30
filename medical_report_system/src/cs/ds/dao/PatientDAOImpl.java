package cs.ds.dao;

import com.thoughtworks.xstream.XStream;
import cs.ds.dao.interfaces.PatientDAO;
import cs.ds.domain.Patient;
import cs.ds.domain.Patients;
import cs.ds.domain.Treatment;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl extends GenericDAOImpl<Patient, Long> implements PatientDAO{

    @Override
    public byte[] downloadFile(String fileName) throws RemoteException {
        try {
            File file = new File(fileName);
            byte buffer[] = new byte[(int)file.length()];
            BufferedInputStream input = new
                    BufferedInputStream(new FileInputStream(fileName));

            input.read(buffer, 0, buffer.length);
            input.close();

            return buffer;
        } catch (Exception e) {
            System.out.println("PatientDAOImple file: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Patient> readPatients() {
        XStream readPatientStream = new XStream();
        List<Patient> patients = new ArrayList<Patient>();


        // For correctly getting the object
        Patients patientsObj = new Patients();
        patientsObj.setPatientsList(new ArrayList<Patient>());


        readPatientStream.alias("patient", Patient.class);
        readPatientStream.alias("treatment", Treatment.class);
        readPatientStream.alias("patientsList", patientsObj.getPatientsList().getClass());

        try {

            System.out.println("Trying to get patients");

            ObjectInputStream in = readPatientStream.createObjectInputStream(new FileInputStream(new File("patients.xml")));

            //System.out.println("After ObjectInputStream");

            patients = (List<Patient>) in.readObject();


            /*
            Patient patient = null;
            while ((patient = (Patient) in.readObject()) != null)
                patients.add(patient);
            */

            System.out.println("Read patients from xml file patients.xml");

        } catch (EOFException e) {
            System.out.println("File end reached");
        }
        catch (Exception e) {   // Any other exception
            e.printStackTrace();
        }

        return patients;
    }

    public void writePatients(Patients patients) {
        XStream writePatientStream = new XStream();

        writePatientStream.alias("patient", Patient.class);
        writePatientStream.alias("treatment", Treatment.class);
        writePatientStream.alias("patientsList", patients.getPatientsList().getClass());

        try {
            PrintWriter out = new PrintWriter("patients.xml");

            System.out.println("Saving patients.xml to file");
            out.println(writePatientStream.toXML(patients.getPatientsList()));
            out.close();

        } catch (Exception ex) {
            System.out.println("File write exception: " + ex);
        }
    }

    @Override
    public void addPatient(Patients patients, Patient newPatient) {
        if (!patients.getPatientsList().contains(newPatient)) {
            patients.addPatient(newPatient);

            // Save it back to xml
            writePatients(patients);
        } else {
            System.out.println("Patient already exists in xml file.");
        }
    }

    @Override
    public void removePatient(Patients patients, Patient removedPatient) {
        patients.removePatient(removedPatient);

        // Save it back to xml
        writePatients(patients);
    }

    @Override
    public void printPatients() {
        for (Patient p: readPatients()) {
            System.out.println(p);
        }
    }


}
