package cs.ds.mvc.service.interfaces;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, ID extends Serializable> {
	T findById(ID id);

	List<T> findAll();

	List<T> findAll(int startIndex, int fetchSize);

	void create(T entity);

	void update(T entity);

	void merge(T entity);

	void delete(ID id);
}
