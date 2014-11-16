package cs.ds.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs.ds.domain.Contact;
import cs.ds.mvc.dao.interfaces.ContactDAO;
import cs.ds.mvc.service.interfaces.ContactService;

/**
 * Override methods from GenericService if you want different implementation
 * other than provided here. You may completely change from default CRUD methods
 * to write our own service methods. However if you need CRUD operations in addition to some more.
 * Add those new methods in ContactService and implement here. In every service implementation class
 * override the constructor to pass the associated DAO as done in following example. Our GenericServiceImpl
 * class waits for a proper DAO class which is of type (or child) of GenericDAO. ContactDAO is one of those
 * type. @Autowired below injects ContactDAOImpl class for us. I recommend you to use this type of implementation
 * in service rather than done in PersonService. Please report if this approach brings some error.
 */ 
@Service
public class ContactServiceImpl extends GenericServiceImpl<Contact, Long>
		implements ContactService {

	@Autowired
	public ContactServiceImpl(ContactDAO contactDAO) {
		super(contactDAO);
		
		System.out.println("ContactService Impl creating ...... !!!!!!!!!!!!");

	}
}
