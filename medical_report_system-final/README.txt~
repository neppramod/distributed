1. For dataservice jar (from inside out/production directory if using intellij)
$ jar cf data_service-dl.jar cs/ds/dao/ cs/ds/domain/  

1.a Jini
       at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
        at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:308)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
        at java.lang.Class.forName0(Native Method)
        at java.lang.Class.forName(Class.java:344)
        at net.jini.loader.pref.PreferredClassProvider.loadClass(PreferredClassProvider.java:613)
        at java.rmi.server.RMIClassLoader.loadClass(RMIClassLoader.java:264)
        at sun.rmi.server.MarshalInputStream.resolveClass(MarshalInputStream.java:214)
        at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1613)
        at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1518)
        at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1774)
        at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1351)
        at java.io.ObjectInputStream.readObject(ObjectInputStream.java:371)
        at java.rmi.MarshalledObject.get(MarshalledObject.java:160)
        at com.sun.jini.start.NonActivatableServiceDescriptor.create(NonActivatableServiceDescriptor.java:644)
        at com.sun.jini.start.ServiceStarter.create(ServiceStarter.java:290)
        at com.sun.jini.start.ServiceStarter.main(ServiceStarter.java:470)



2. Server (Data) From inside out/production outside cs

$ java -Djava.rmi.server.codebase=http://localhost:8080/data_service-dl.jar -Djava.security.policy=../../../all_rmi_lib/jsk-all.policy cs/ds/jiniimpl/dataservice/DataServiceServer


Exception in thread "event listener notification" java.lang.NullPointerException
        at cs.ds.jiniimpl.dataservice.DataServiceServer.discovered(DataServiceServer.java:72)
        at net.jini.discovery.LookupDiscovery$1.run(LookupDiscovery.java:977)
        at java.security.AccessController.doPrivileged(Native Method)
        at net.jini.discovery.LookupDiscovery$Notifier.run(LookupDiscovery.java:923)



3. Client

ClientServiceServer: Look service found
Returning patient
Patient: Id: 1, Name: Harry Potter



Or in Terminals

1. HTTP Server
java -jar lib/tools.jar -port 8080 -dir host_this/ -verbose

2. RMID
rmid

3. JINI
cd ~/ds/final_project/src/git_ds_jini/medical_report_system/
java -Djava.security.policy=all_rmi_lib/jsk-all.policy -jar all_rmi_lib/start.jar all_rmi_lib/start-transient-reggie.config

4. Server
cd ~/ds/final_project/src/git_ds_jini/medical_report_system/out/production/medical_report_system/
java -Djava.rmi.server.codebase=http://localhost:8080/data_service-dl.jar -Djava.security.policy=../../../all_rmi_lib/jsk-all.policy cs/ds/jiniimpl/dataservice/DataServiceServer

5. Client
cd ~/ds/final_project/src/git_ds_jini/medical_report_system/out/production/medical_report_system/
java -Djava.security.policy=../../../all_rmi_lib/jsk-all.policy cs/ds/jiniimpl/clientservice/ClientServiceServer
