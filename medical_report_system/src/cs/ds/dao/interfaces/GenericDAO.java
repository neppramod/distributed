package cs.ds.dao.interfaces;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Generic class that implements generic dao interface
 *
 * @author pramod
 *
 * @param <T>
 *            generic type, It is going to be casted to child type at runtime
 * @param <ID>
 *            Primary key type of child class
 */

public interface GenericDAO<T, ID extends Serializable> extends Serializable{

    T findById(ID id) throws RemoteException;

    List<T> findAll() throws RemoteException;

    List<T> findAll(int startIndex, int fetchSize) throws RemoteException;

    void create(T entity) throws RemoteException;

    void update(T entity) throws RemoteException;

    void delete(T entity) throws RemoteException;

    void merge(T entity) throws RemoteException;

    void delete(ID id) throws RemoteException;
}
