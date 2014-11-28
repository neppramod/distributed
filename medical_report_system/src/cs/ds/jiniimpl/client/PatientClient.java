package cs.ds.jiniimpl.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import cs.ds.service.interfaces.PatientService;
import net.jini.discovery.LookupDiscovery;
import net.jini.discovery.DiscoveryListener;
import net.jini.discovery.DiscoveryEvent;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;

public class PatientClient implements DiscoveryListener{
    protected PatientService patientService;

    public static void main(String[] args) {
        new PatientClient();

        try {
            Thread.sleep(100000L);
        } catch(InterruptedException e) {}
    }

    public PatientClient() {
        System.setSecurityManager(new RMISecurityManager());

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

            // Use patientService object we got from server
            try {
                System.out.println("Client: Found patientService");
                System.out.println(patientService.findById(1L));

                // Lets get a file
                byte[] fileData = patientService.downloadFile("op.jpg");



                // Give new file name
                String newFileName = "op_" + System.currentTimeMillis() + ".jpg";
                File file = new File(newFileName);
                BufferedOutputStream output = new
                        BufferedOutputStream(new FileOutputStream(file.getName()));

                System.out.println("Getting file: (with changed name after adding timestamp ) : " +
                        file.getName());

                output.write(fileData, 0, fileData.length);
                output.flush();
                output.close();

            } catch(RemoteException e) {
                e.printStackTrace();
                continue;
            } catch(Exception fe) {
                System.out.println("File not found/Input exception: " + fe.toString());
            }

            // Should I exit now or not, Have to look at this later
            System.exit(0);
        }
    }

    @Override
    public void discarded(DiscoveryEvent discoveryEvent) {

    }
}
