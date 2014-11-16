package cs.ds.mvc.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import cs.ds.mvc.dao.interfaces.GenericDAO;
import cs.ds.mvc.service.interfaces.GenericService;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements
		GenericService<T, ID> {

	private Class<T> persistentClass;
	private String className = "";

	GenericDAO<T, ID> genericDAO;

	// Log
	private static Logger logger = Logger.getLogger("generic service");

	public GenericServiceImpl() {

	}

	@SuppressWarnings("unchecked")
	public GenericServiceImpl(GenericDAO<T, ID> genericDAO) {
		System.out.println("Hi");

		this.genericDAO = genericDAO;

		System.out
				.println("Generic service impl creating..... !!!!!!!!!!!!!!! ");

		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		className = persistentClass.getSimpleName();
	}

	@Transactional(readOnly = true)
	public T findById(ID id) {

		logger.info("Finding " + className + " by id");

		return (T) genericDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		logger.info("Finding all " + className);

		return genericDAO.findAll();
	}

	@Transactional(readOnly = true)
	public List<T> findAll(int startIndex, int fetchSize) {
		logger.info("Fetching " + fetchSize + " elements starting from "
				+ startIndex);

		return genericDAO.findAll(startIndex, fetchSize);
	}

	@Transactional
	public void create(T entity) {
		logger.info("Creating new " + className);

		genericDAO.create(entity);
	}

	@Transactional
	public void update(T entity) {
		logger.info("Updating " + className);

		genericDAO.update(entity);
	}

	@Transactional
	public void merge(T entity) {
		logger.info("Merging " + className);

		genericDAO.merge(entity);
	}

	@Transactional
	public void delete(ID id) {
		logger.info("Deleting " + className);

		genericDAO.delete(id);
	}

}
