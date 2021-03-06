package cs.ds.service.interfaces;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public interface GenericService<T, ID extends Serializable> extends Serializable{
    T findById(ID id) throws RemoteException;

    List<T> findAll() throws RemoteException;

    List<T> findAll(int startIndex, int fetchSize) throws RemoteException;

    void create(T entity) throws RemoteException;

    void update(T entity) throws RemoteException;

    void merge(T entity) throws RemoteException;

    void delete(ID id) throws RemoteException;
}

