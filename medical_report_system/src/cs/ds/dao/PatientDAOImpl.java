package cs.ds.dao;


import cs.ds.dao.interfaces.PatientDAO;
import cs.ds.domain.Patient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;

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
}
