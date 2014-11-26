package cs.ds.jiniimpl.clientservice;

import cs.ds.service.PatientServiceImpl;
import cs.ds.service.interfaces.PatientService;
import net.jini.core.lookup.ServiceID;
import net.jini.discovery.DiscoveryEvent;
import net.jini.discovery.DiscoveryListener;
import net.jini.lease.LeaseListener;
import net.jini.lease.LeaseRenewalEvent;
import net.jini.lease.LeaseRenewalManager;

public class ClientServiceServer implements DiscoveryListener, LeaseListener{
    protected LeaseRenewalManager leaseManager = new LeaseRenewalManager();
    protected ServiceID serviceID = null;
    protected PatientService patientService = null;

    public static void main(String[] args) {
        ClientServiceServer s = new ClientServiceServer();

        Object keepAlive = new Object();

        // Keep this server running
        synchronized (keepAlive) {
            try {
                keepAlive.wait();
            } catch(InterruptedException e) {}
        }
    }

    public ClientServiceServer() {
        //lpatientService = new PatientServiceImpl()
    }

    @Override
    public void discovered(DiscoveryEvent discoveryEvent) {

    }

    @Override
    public void discarded(DiscoveryEvent discoveryEvent) {

    }

    @Override
    public void notify(LeaseRenewalEvent leaseRenewalEvent) {

    }
}
