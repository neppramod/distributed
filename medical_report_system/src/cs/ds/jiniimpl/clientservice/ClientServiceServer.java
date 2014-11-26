package cs.ds.jiniimpl.clientservice;

import cs.ds.dao.interfaces.PatientDAO;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.discovery.DiscoveryEvent;
import net.jini.discovery.DiscoveryListener;
import net.jini.discovery.LookupDiscovery;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class ClientServiceServer implements DiscoveryListener {
    //protected LeaseRenewalManager leaseManager = new LeaseRenewalManager();
    //protected ServiceID serviceID = null;
    //protected PatientService patientService = null;
    protected PatientDAO patientDAO = null;

    public static void main(String[] args) {
        ClientServiceServer s = new ClientServiceServer();

        // Act also as client
        try {
            Thread.sleep(100000L);
        } catch(InterruptedException e) {}

        // This is for service
        /*
        Object keepAlive = new Object();

        // Keep this server running
        synchronized (keepAlive) {
            try {
                keepAlive.wait();
            } catch(InterruptedException e) {}
        }
        */
    }

    public ClientServiceServer() {
        System.setSecurityManager(new RMISecurityManager());
        LookupDiscovery discover = null;

        try {
            discover = new LookupDiscovery(LookupDiscovery.ALL_GROUPS);
        } catch (Exception e) {
            System.out.println("Discovery failed " + e.toString());
            System.exit(1);
        }

        discover.addDiscoveryListener(this);
    }

    @Override
    public void discovered(DiscoveryEvent discoveryEvent) {
        ServiceRegistrar[] registrars = discoveryEvent.getRegistrars();
        Class[] classes = new Class[] {PatientDAO.class};
        ServiceTemplate template = new ServiceTemplate(null, classes, null);

        for (int n = 0; n < registrars.length; n++) {
            System.out.println("ClientServiceServer: Look service found");
            ServiceRegistrar registrar = registrars[n];

            try {
                patientDAO = (PatientDAO) registrar.lookup(template);
            } catch (RemoteException e) {
                System.out.println("PatientDAO null");
                continue;
            }

            // Use patientDAO object we got from server
            try {
                System.out.println("Patient: " + patientDAO.findById(1L));
            } catch (RemoteException e) {
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
