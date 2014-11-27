1. RMID
$ rmid
Activation.main: warning: sun.rmi.activation.execPolicy system
property unspecified and no ExecPermissions/ExecOptionPermissions
granted; subsequent activation attempts may fail due to unsuccessful
ExecPermission/ExecOptionPermission permission checks.  For
documentation on how to configure rmid security, refer to:

http://java.sun.com/j2se/1.4/docs/tooldocs/solaris/rmid.html
http://java.sun.com/j2se/1.4/docs/tooldocs/win32/rmid.html


2. HTTP
$ java -jar lib/tools.jar -port 8080 -dir host_this/ -verbose
Nov 26, 2014 7:03:49 PM com.sun.jini.tool.ClassServer run
INFO: ClassServer started [[host_this/], port 8080]
reggie-dl.jar requested from localhost.localdomain:34923
reggie-dl.jar probed from localhost.localdomain:34925
reggie-dl.jar requested from localhost.localdomain:34926
reggie-dl.jar probed from localhost.localdomain:34933
reggie-dl.jar requested from localhost.localdomain:34934
data_service-dl.jar probed from localhost.localdomain:34940
data_service-dl.jar requested from localhost.localdomain:34941
reggie-dl.jar probed from localhost.localdomain:34944
reggie-dl.jar requested from localhost.localdomain:34945
client_service-dl.jar probed from localhost.localdomain:34951
client_service-dl.jar requested from localhost.localdomain:34952

3. JINI
$ java -Djava.security.policy=all_rmi_lib/jsk-all.policy -jar all_rmi_lib/start.jar all_rmi_lib/start-transient-reggie.config         
Nov 26, 2014 7:06:16 PM com.sun.jini.reggie.RegistrarImpl init
INFO: started Reggie: 8b35dbd5-0e35-46bc-a9e3-03b10f3a2fc5, [], jini://dell-comp/
Nov 26, 2014 7:06:16 PM com.sun.jini.start.ServiceStarter checkResultFailures
WARNING: Exception creating service.
java.lang.ClassNotFoundException: com.sun.jini.reggie.RegistrarProxy
        at java.net.URLClassLoader$1.run(URLClassLoader.java:372)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:361)
        at java.security.AccessController.doPrivileged(Native Method)
        at java.net.URLClassLoader.findClass(URLClassLoader.java:360)
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

Nov 26, 2014 7:06:16 PM com.sun.jini.start.ServiceStarter checkResultFailures
WARNING: Associated service descriptor [0]: [http://localhost:8080/reggie-dl.jar, /home/dell/ds/final_project/src/git_ds_jini/medical_report_system/all_rmi_lib/reggie.policy, /home/dell/ds/final_project/src/git_ds_jini/medical_report_system/all_rmi_lib/reggie.jar, com.sun.jini.reggie.TransientRegistrarImpl, [/home/dell/ds/final_project/src/git_ds_jini/medical_report_system/all_rmi_lib/transient-reggie.config], com.sun.jini.start.NonActivatableServiceDescriptor$1@1810399e, BasicProxyPreparer[]]

4. DataServer
$ java -Djava.rmi.server.codebase=http://localhost:8080/data_service-dl.jar -Djava.security.policy=../../../all_rmi_lib/jsk-all.policy cs/ds/jiniimpl/dataservice/DataServiceServer
Exception in thread "event listener notification" java.lang.NullPointerException
        at cs.ds.jiniimpl.dataservice.DataServiceServer.discovered(DataServiceServer.java:72)
        at net.jini.discovery.LookupDiscovery$1.run(LookupDiscovery.java:977)
        at java.security.AccessController.doPrivileged(Native Method)
        at net.jini.discovery.LookupDiscovery$Notifier.run(LookupDiscovery.java:923)

5. ClientServiceServer
$ java -Djava.rmi.server.codebase=http://localhost:8080/client_service-dl.jar  -Djava.security.policy=../../../all_rmi_lib/jsk-all.policy cs/ds/jiniimpl/clientservice/ClientServiceServer
ClientServiceServer: Lookup service found
P1
Returning patient
ClientServiceServer: Patient: Id: 1, Name: Harry Potter
Found patientDAO now registering the patient service
ClientServiceServer: PatientService registered with id 106a1fab-636a-45da-9c20-a3e8b8e5c17a

6. PatientClient
$ java -Djava.security.policy=../../../all_rmi_lib/jsk-all.policy cs/ds/jiniimpl/client/PatientClient
Lookup service found
Client: Found patientService
Returning patient
Id: 1, Name: Harry Potter
