package cs.ds.service;


import cs.ds.dao.interfaces.GenericDAO;
import cs.ds.service.interfaces.GenericService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {
    GenericDAO<T, ID> genericDAO;

    public GenericServiceImpl() {}

    // Assign DAO object to this class
    public GenericServiceImpl(GenericDAO<T, ID> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public T findById(ID id) throws RemoteException {
        return genericDAO.findById(id);
    }

    @Override
    public List<T> findAll() throws RemoteException {
        return genericDAO.findAll();
    }

    @Override
    public List<T> findAll(int startIndex, int fetchSize) throws RemoteException {
        return genericDAO.findAll(startIndex, fetchSize);
    }

    @Override
    public void create(T entity) throws RemoteException {
        genericDAO.create(entity);
    }

    @Override
    public void update(T entity) throws RemoteException {
        genericDAO.update(entity);
    }

    @Override
    public void merge(T entity) throws RemoteException {
        genericDAO.merge(entity);
    }

    @Override
    public void delete(ID id) throws RemoteException {
        genericDAO.delete(id);
    }
}
