package cs.ds.jiniimpl.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cs.ds.domain.Address;
import cs.ds.domain.Patient;
import cs.ds.domain.Patients;
import cs.ds.domain.Treatment;
import cs.ds.service.interfaces.PatientService;
import cs.ds.util.Encryption;
import net.jini.discovery.LookupDiscovery;
import net.jini.discovery.DiscoveryListener;
import net.jini.discovery.DiscoveryEvent;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;
import static cs.ds.util.Constants.REPORT_DIR;

public class PatientClient implements DiscoveryListener{
    protected PatientService patientService;
    private final String SERVER_REPORT_DIR = "reports" + File.separator;

    public static void main(String[] args) {
    
        new PatientClient();

        // Keep it running all the time, rather than exiting in 1 minute.
        Object keepAlive = new Object();

        synchronized (keepAlive) {
            try {
                keepAlive.wait();
            } catch(InterruptedException e) {}
        }
    }

    public PatientClient() {
        System.setSecurityManager(new RMISecurityManager());
        
        // Setup the PATIENT_RECORDS directory (if there is none)
        File reportDir = new File(REPORT_DIR);
            if (!reportDir.exists())
                reportDir.mkdir();

        LookupDiscovery discover = null;

        try {
            discover = new LookupDiscovery(LookupDiscovery.ALL_GROUPS);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(1);
        }

        discover.addDiscoveryListener(this);
    }

    @Override
    public void discovered(DiscoveryEvent discoveryEvent) {
        ServiceRegistrar[] registrars = discoveryEvent.getRegistrars();
        Class[] classes = new Class[]{PatientService.class};

        ServiceTemplate template = new ServiceTemplate(null, classes, null);

        for (int n = 0; n < registrars.length; n++) {
            System.out.println("Lookup service found");
            ServiceRegistrar registrar = registrars[n];

            try {
                patientService = (PatientService) registrar.lookup(template);
            } catch (RemoteException e) {
                System.out.println("PatientClient: PatientService null");
                continue;
            }


            try {
                System.out.println("Welcome to the Medical Records System.");
                System.out.println("");

                Scanner in = new Scanner(System.in);
                System.out.print("\nUsername: ");
                String enteredUsername = in.nextLine();
                System.out.print("\nPassword: ");
                String enteredPassword = in.nextLine();

                if (patientService.getLogin(Encryption.getMD5(enteredUsername + enteredPassword))) {
                    clientMenu(patientService);
                } else {
                    System.out.println("Entered username/password does not match. Please try again");
                }

            } catch(Exception ex) {
                System.out.println("Exception in creating client menu: " + ex);
            }


            System.exit(0);
        }
    }

    @Override
    public void discarded(DiscoveryEvent discoveryEvent) {

    }

    public void clientMenu(PatientService patientService) {
        Scanner in = new Scanner(System.in);
        int menuChoice = 0;
        Long patientId = 0L;

        Patients patientList = new Patients();
        patientList.setPatientsList(patientService.readPatients());

        while(menuChoice != 6) {
            try {
                clientMenuText();
                menuChoice = in.nextInt();
                switch (menuChoice) {
                    case 1:
                        patientService.addPatient(patientList, clientAddPatient());
                        break;
                    case 2:
                        System.out.print("\nId: ");
                        patientId = in.nextLong();
                        searchAndPrintPatient(patientId);
                        break;
                    case 3:
                        patientService.removePatient(patientList, clientAddPatient());  //use after add, sample patient
                        break;
                    case 4:
                        patientService.printPatients();
                        break;
                    case 5:
                        System.out.print("\nId: ");
                        patientId = in.nextLong();
                        downloadReportsForPatient(patientId);
                        break;
                    case 6:
                        System.out.println("Exiting the application.");
                        break;
                    default:
                        System.out.println("Invalid menu option chosen.");
                }
                System.out.println("");
            }catch (InputMismatchException e) {
                System.out.println("Sorry!! You had to enter a number. Try again. Exiting.. ");
                break;
            }
        }
    }

    public void clientMenuText() {
        System.out.println("Please choose an option below:");
        System.out.println("");
        System.out.println("1 - Add a patient");
        System.out.println("2 - Search for a patient");
        System.out.println("3 - Remove a patient");
        System.out.println("4 - List all patients");
        System.out.println("5 - Download reports for a patient");
        System.out.println("6 - Exit the application");
    }

    public void downloadReportsForPatient(Long id) {
        Patient patient = patientService.findPatientById(id);

        if (patient != null) {
            System.out.println("Downloading reports for " + patient.getName());

            List<Treatment> treatmentList = patient.getTreatmentHistory();


            // First create directory for the patient, if it does not exist
            File patientDir = new File(REPORT_DIR+patient.getId());
            if (!patientDir.exists())
                patientDir.mkdir();

            if (treatmentList.size() < 1) {
                System.out.println("Patient does not contain any treatment history");
            } else {
                for (Treatment t: treatmentList) {
                    List<String> reports = t.getTreatmentReports();

                    if (reports.size() < 1) {
                        System.out.println("Patient does not contain any reports for this treatment");
                    } else {

                        // Create directory for each treatment and download reports inside that
                        String treatmentDirPath = REPORT_DIR+patient.getId()+File.separator+t.getId();
                        File treatmentDir = new File(treatmentDirPath);
                        if(!treatmentDir.exists())
                            treatmentDir.mkdir();

                        // Once we have the file we can download all the reports to that directory
                        for(String report : reports) {
                            // Get the file
                            try {
                                byte[] fileData = patientService.downloadFile(SERVER_REPORT_DIR + patient.getId() + File.separator + t.getId() + File.separator + report);

                                // Download to this name
                                String downloadFileName = treatmentDirPath + File.separator + report;
                                File downloadFile = new File(downloadFileName);
                                BufferedOutputStream output = new
                                        BufferedOutputStream(new FileOutputStream(downloadFileName));

                                System.out.println("Getting report: " + downloadFile.getName());
                                output.write(fileData, 0, fileData.length);
                                output.flush();
                                output.close();

                            } catch (RemoteException e) {
                                e.printStackTrace();
                                continue;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }

        } else {
            System.out.println("Could not find patient with id " + id);
        }
    }

    public void searchAndPrintPatient(Long id) {
        Patient patient = patientService.findPatientById(id);

        if (patient != null) {
            System.out.println("Information of patient with id : " + patient.getId());
            System.out.println("Name: " + patient.getName());
            System.out.println("Dob: " + patient.getDob());

            Address addr = patient.getAddress();

            System.out.println("Address: " + addr.getStreet() + ", " + addr.getAppartmentNo()
                    + ", " + addr.getCity() + ", " + addr.getZipCode() +
                    ", " + addr.getZipCode() + ", " + addr.getState() + ", " +
                    addr.getCountry());

            addr = patient.getDobPlace();
            System.out.println("Address (of birth): " + addr.getStreet() + ", " + addr.getAppartmentNo()
                    + ", " + addr.getCity() + ", " + addr.getZipCode() +
                    ", " + addr.getZipCode() + ", " + addr.getState() + ", " +
                    addr.getCountry());

            System.out.println("SSN: " + patient.getSsn());
            System.out.println("Phone: " + patient.getPhone());
            System.out.println("Father's name: " + patient.getFathersName());
            System.out.println("Mother's name: " + patient.getMothersName());


            System.out.println("Treatment history");
            for(Treatment t: patient.getTreatmentHistory()) {
                System.out.println("Treatment Id: " + t.getId());
                System.out.println("Name of doctor: " + t.getNameofDoctor());
                System.out.println("Name of hospital: " + t.getNameOfHospital());

                addr = t.getAddressOfHospital();

                System.out.println("Address (of hospital): " + addr.getStreet() + ", " + addr.getAppartmentNo()
                        + ", " + addr.getCity() + ", " + addr.getZipCode() +
                        ", " + addr.getZipCode() + ", " + addr.getState() + ", " +
                        addr.getCountry());

                System.out.println("Date of treatment: " + t.getDateOfTreatment());
                System.out.println("Symptoms: " + t.getSymptoms());
                System.out.println("Description: " + t.getDescriptionOfTreatment());

                System.out.print("Reports: ");
                for(String file : t.getTreatmentReports()) {
                    System.out.print(file + ", ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Could not find patient with id " + id);
        }
    }



    public Patient clientAddPatient() {
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
        patient1Treatment.setId(2L);
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
        patient1.setId(5L);
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

        //now return the patient
        return patient1;
    }
}
