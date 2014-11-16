package cs.ds.mvc.dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Generic interface to to mundane dao operations
 *
 * @author pramod
 *
 * @param <T>
 *            class which we pass for dao operations
 * @param <ID>
 *            id type of primary key (e.g long, int etc)
 */
public interface GenericDAO<T, ID extends Serializable> {

	T findById(ID id);

	List<T> findAll();

	List<T> findAll(int startIndex, int fetchSize);

	void create(T entity);

	void update(T entity);

	void delete(T entity);

	void merge(T entity);

	void delete(ID id);
}

