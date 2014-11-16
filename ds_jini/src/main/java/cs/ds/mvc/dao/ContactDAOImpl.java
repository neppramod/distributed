package cs.ds.mvc.dao;

import org.springframework.stereotype.Repository;

import cs.ds.domain.Contact;
import cs.ds.mvc.dao.interfaces.ContactDAO;

@Repository
public class ContactDAOImpl extends GenericDAOImpl<Contact, Long> implements
		ContactDAO {

}
