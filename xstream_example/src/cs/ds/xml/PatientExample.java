package cs.ds.xml;


import com.thoughtworks.xstream.XStream;
import cs.ds.blogdomain.Author;
import cs.ds.blogdomain.Blog;
import cs.ds.blogdomain.Entry;
import cs.ds.domain.Address;
import cs.ds.domain.Patient;
import cs.ds.domain.Patients;
import cs.ds.domain.Treatment;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientExample {
    public static void main(String[] args) {
        /*Blog teamBlog = new Blog(new Author("Guilherme Silveira"));
        teamBlog.add(new Entry("first", "My first blog entry."));
        teamBlog.add(new Entry("tutorial",
                "Today we have developed a nice alias tutorial. Tell your friends! NOW!"));

        XStream xstream = new XStream();
        xstream.alias("blog", Blog.class);
        xstream.alias("entry", Entry.class);*/

        //System.out.println(xstream.toXML(teamBlog));

        // Address (3 addresses hospital, birth and current

        Address hospitalAddress = new Address();
        hospitalAddress.setId(1L);
        hospitalAddress.setAppartmentNo("Central Hospital Apartment");
        hospitalAddress.setCity("Johnson City");
        hospitalAddress.setCountry("US");
        hospitalAddress.setState("TN");
        hospitalAddress.setStreet("Seminole Drv");
        hospitalAddress.setZipCode("33434");

        Address birthAddress = new Address();
        birthAddress.setId(2L);
        birthAddress.setAppartmentNo("Outer hospital");
        birthAddress.setCity("Bristol");
        birthAddress.setCountry("Canada");
        birthAddress.setState("CN");
        birthAddress.setStreet("Long Drv");
        birthAddress.setZipCode("23434");

        Address currentAddress = new Address();
        currentAddress.setId(3L);
        currentAddress.setAppartmentNo("234");
        currentAddress.setCity("Far city");
        currentAddress.setCountry("Moon");
        currentAddress.setState("Creator");
        currentAddress.setStreet("No street defined");
        currentAddress.setZipCode("334234");

        // Treatment1
        Treatment patientTreatment = new Treatment();
        patientTreatment.setId(1L);
        patientTreatment.setTitle("Severe Heart disease");
        patientTreatment.setNameofDoctor("Dr. Superman");
        patientTreatment.setNameOfHospital("Finest medical hospital");
        patientTreatment.setAddressOfHospital(hospitalAddress);
        patientTreatment.setSymptoms("Heart attack");
        patientTreatment.setDescriptionOfTreatment("Did a surgery");

        List<String> treatmentFiles = new ArrayList<String>();
        treatmentFiles.add("op.jpg");
        treatmentFiles.add("soa.jpg");
        treatmentFiles.add("nar.jpg");
        patientTreatment.setTreatmentReports(treatmentFiles);

        // Treatment2
        Treatment patient1Treatment = new Treatment();
        patient1Treatment.setId(1L);
        patient1Treatment.setTitle("Severe Lungs disease");
        patient1Treatment.setNameofDoctor("Dr. Octopos");
        patient1Treatment.setNameOfHospital("Finest lungs hospital");
        patient1Treatment.setAddressOfHospital(hospitalAddress);
        patient1Treatment.setSymptoms("Lungs attack");
        patient1Treatment.setDescriptionOfTreatment("Lungs surgery");

        List<String> treatment1Files = new ArrayList<String>();
        treatment1Files.add("op1.jpg");
        treatment1Files.add("soa1.jpg");
        treatment1Files.add("nar1.jpg");
        patient1Treatment.setTreatmentReports(treatment1Files);


        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setName("Harry Potter");
        patient1.setAddress(currentAddress);
        patient1.setDobPlace(birthAddress);

        try {
            patient1.setDob(new SimpleDateFormat("M-d-yyyy").parse("05-21-1923"));
            patientTreatment.setDateOfTreatment(new SimpleDateFormat("M-d-yyyy").parse("05-17-1945"));
            patient1Treatment.setDateOfTreatment(new SimpleDateFormat("M-d-yyyy").parse("02-15-1935"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Treatment> treatmentList = new ArrayList<Treatment>();
        treatmentList.add(patientTreatment);
        treatmentList.add(patient1Treatment);

        patient1.setTreatmentHistory(treatmentList);



        // 2nd patient

        Address hospital2Address = new Address();
        hospital2Address.setId(1L);
        hospital2Address.setAppartmentNo("Central Hospital Apartment");
        hospital2Address.setCity("Johnson City");
        hospital2Address.setCountry("US");
        hospital2Address.setState("TN");
        hospital2Address.setStreet("Seminole Drv");
        hospital2Address.setZipCode("33434");

        Address birth2Address = new Address();
        birth2Address.setId(2L);
        birth2Address.setAppartmentNo("Outer hospital");
        birth2Address.setCity("Bristol");
        birth2Address.setCountry("Canada");
        birth2Address.setState("CN");
        birth2Address.setStreet("Long Drv");
        birth2Address.setZipCode("23434");

        Address current2Address = new Address();
        current2Address.setId(3L);
        current2Address.setAppartmentNo("234");
        current2Address.setCity("Far city");
        current2Address.setCountry("Moon");
        current2Address.setState("Creator");
        current2Address.setStreet("No street defined");
        current2Address.setZipCode("334234");

        // Treatment1
        Treatment patient2Treatment = new Treatment();
        patient2Treatment.setId(1L);
        patient2Treatment.setTitle("Severe Heart disease");
        patient2Treatment.setNameofDoctor("Dr. Superman");
        patient2Treatment.setNameOfHospital("Finest medical hospital");
        patient2Treatment.setAddressOfHospital(hospital2Address);
        patient2Treatment.setSymptoms("Heart attack");
        patient2Treatment.setDescriptionOfTreatment("Did a surgery");

        List<String> treatment2Files = new ArrayList<String>();
        treatment2Files.add("op.jpg");
        treatment2Files.add("soa.jpg");
        treatment2Files.add("nar.jpg");
        patient2Treatment.setTreatmentReports(treatment2Files);

        // Treatment2
        Treatment patient3Treatment = new Treatment();
        patient3Treatment.setId(1L);
        patient3Treatment.setTitle("Severe Lungs disease");
        patient3Treatment.setNameofDoctor("Dr. Octopos");
        patient3Treatment.setNameOfHospital("Finest lungs hospital");
        patient3Treatment.setAddressOfHospital(hospital2Address);
        patient3Treatment.setSymptoms("Lungs attack");
        patient3Treatment.setDescriptionOfTreatment("Lungs surgery");

        List<String> treatment3Files = new ArrayList<String>();
        treatment3Files.add("op1.jpg");
        treatment3Files.add("soa1.jpg");
        treatment3Files.add("nar1.jpg");
        patient3Treatment.setTreatmentReports(treatment3Files);


        Patient patient2 = new Patient();
        patient2.setId(2L);
        patient2.setName("Ron Hermaine");
        patient2.setAddress(current2Address);
        patient2.setDobPlace(birth2Address);

        try {
            patient2.setDob(new SimpleDateFormat("M-d-yyyy").parse("05-21-1923"));
            patient2Treatment.setDateOfTreatment(new SimpleDateFormat("M-d-yyyy").parse("05-17-1945"));
            patient3Treatment.setDateOfTreatment(new SimpleDateFormat("M-d-yyyy").parse("02-15-1935"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Treatment> treatmentList1 = new ArrayList<Treatment>();
        treatmentList1.add(patient2Treatment);
        treatmentList1.add(patient3Treatment);

        patient2.setTreatmentHistory(treatmentList1);

        List<Patient> patientsList = new ArrayList<Patient>();
        patientsList.add(patient1);
        patientsList.add(patient2);

        Patients patients = new Patients();
        patients.setPatientsList(patientsList);
        //patients.addPatient(patient1);
        //patients.addPatient(patient2);

        XStream xstream1 = new XStream();
        xstream1.alias("patient", Patient.class);
        xstream1.alias("treatment", Treatment.class);
        //xstream1.alias("patients", Patients.class);

        System.out.println(xstream1.toXML(patients));

        // Write to file
        try {
            PrintWriter out = new PrintWriter("patients.xml");

            System.out.println("Saving patients.xml to file");
            out.println(xstream1.toXML(patients));
            out.close();

        } catch (Exception ex) {
            System.out.println("File write exception: " + ex);
        }

        // Read from file
        XStream xstream2 = new XStream();
        xstream2.alias("patient", Patient.class);
        xstream2.alias("treatment", Treatment.class);
        xstream2.alias("patientsList", patients.getPatientsList().getClass());

        try {
            ObjectInputStream in = xstream2.createObjectInputStream(new FileInputStream(new File("patients.xml")));
            List<Patient> patientsReadList = (List<Patient>) in.readObject();

            for(Patient pt : patientsReadList) {
                System.out.println(pt);
                for(Treatment tmt : pt.getTreatmentHistory()) {
                    System.out.println("Tmt Id: " + tmt.getId() + ", Title: " + tmt.getTitle());

                    for (String tmtFile : tmt.getTreatmentReports()) {
                        System.out.println("Tmt report: " + tmtFile);
                    }
                }
                System.out.println(); // for new line
            }

            // Get only one object using arraylist id, not person id
            Patient somePt = patientsReadList.get(1);
            System.out.println("SomePt: " + somePt);

            // Let me remove first patient and save it back to file
            Patient removePatient = findPatientById(1L, patientsList);

            // Patient does not have username they only have name
            Patient somePatient = findPatientByUsername("Harry Potter", patientsList);
            System.out.println("Some patient: " + somePatient);

            patientsList.remove(removePatient);
            //patientsList.remove(somePatient);

            // Write only one patient to file
            try {
                PrintWriter out1 = new PrintWriter("patients1.xml");

                System.out.println("Saving again patients.xml to file");
                patients.setPatientsList(patientsList);
                out1.println(xstream1.toXML(patients));
                out1.close();

            } catch (Exception ex) {
                System.out.println("File write exception: " + ex);
            }


        } catch(Exception ex) {
            System.out.println("File read exception: " + ex);
        }



    }

    public static Patient findPatientById(Long id, List<Patient> patientList) {
        Patient returnPatient = null;

        for (Patient patient: patientList) {
            if (patient.getId() == id) {
                returnPatient = patient;
                break;
            }
        }

        return returnPatient;
    }

    public static Patient findPatientByUsername(String userName, List<Patient> patientList) {
        Patient returnPatient = null;

        for (Patient patient: patientList) {
            if (patient.getName().equals(userName)) {
                returnPatient = patient;
                break;
            }
        }

        return returnPatient;
    }
}
