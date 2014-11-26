package cs.ds.dao;

import cs.ds.dao.interfaces.GenericDAO;
import cs.ds.domain.Patient;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GenericDAOImpl <T, ID extends Serializable> implements
        GenericDAO<T, ID> {

    /**
     * All of these methods have to be implemented individually
     * For the shake of simplicity I am just going to return
     * One or two patient objects just with name and id
     * Remove all of those and use my original dao example to interact with proper
     * database, if you desire to use hibernate (However on a standalone application) the configuration
     * file for hibernate should be created. That example runs on web context.
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public T findById(ID id) throws RemoteException {
        System.out.println("Returning patient");

        return (T) get_tmp_1st_Patient();
    }

    @Override
    public List<T> findAll() throws RemoteException {
        System.out.println("Returning list of patients");

        List<T> list = new ArrayList<T>();

        list.add((T)get_tmp_1st_Patient());
        list.add((T)get_tmp_2nd_Patient());

        return list;
    }

    @Override
    public List<T> findAll(int startIndex, int fetchSize) throws RemoteException {
        System.out.println("Returning list of patients");

        return findAll();
    }

    @Override
    public void create(T entity) throws RemoteException {
        System.out.println("Created patient");
    }

    @Override
    public void update(T entity) throws RemoteException {
        System.out.println("Updated patient");
    }

    @Override
    public void delete(T entity) throws RemoteException {
        System.out.println("Deleted patient");
    }

    @Override
    public void merge(T entity) throws RemoteException {
        System.out.println("Merged patient");
    }

    @Override
    public void delete(ID id) throws RemoteException {
        System.out.println("Delete patient with id " + id);
    }

    // Throw this
    private Patient get_tmp_1st_Patient() {
        Patient tmpPatient = new Patient();
        tmpPatient.setName("Harry Potter");
        tmpPatient.setId(1L);
        return tmpPatient;
    }

    private Patient get_tmp_2nd_Patient() {
        Patient secondPatient = new Patient();
        secondPatient.setName("Voldemort");
        secondPatient.setId(2L);
        return secondPatient;
    }
}
