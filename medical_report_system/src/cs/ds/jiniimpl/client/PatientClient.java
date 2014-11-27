package cs.ds.jiniimpl.client;

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
            } catch(RemoteException e) {
                e.printStackTrace();
                continue;
            }

            // Should I exit now or not, Have to look at this later
            System.exit(0);
        }
    }

    @Override
    public void discarded(DiscoveryEvent discoveryEvent) {

    }
}
