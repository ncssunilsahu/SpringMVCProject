package in.co.sunrays.spring.dao;

import in.co.sunrays.spring.dto.UserDTO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * User Data Access Object provides Database CRUD operations. It is implemented
 * by plain Hibernate 3 API with Spring ORM.
 * 
 * All methods propagate unchecked DataAccessException. It is a generic
 * exception handling provided by Spring-DAO.
 * 
 * If DataAccessException is propagated from method then declarative transaction
 * is rolled back by Spring AOP.
 * 
 * This is plain Hibernate 3 API implementation of DAO
 * 
 * @version 1.0
 * @since 1 Jan 2015
 * @author Sunil Sahu
 * @Copyright (c) Sunil Sahu
 * @url www.sunilbooks.com
 */

@Repository (value="userDao")
public class UserDAOHibImpl implements UserDAOInt {
	
	
	@Autowired
	private SessionFactory sessionFactory = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Adds an User
	 */
	public long add(UserDTO dto) {

		Session session = sessionFactory.getCurrentSession();
		session.save(dto);

		return dto.getId();
	}

	/**
	 * Updates a User
	 */

	public long update(UserDTO dto) {
		Session session = sessionFactory.getCurrentSession();
		session.update(dto);
		return dto.getId();
	}

	/**
	 * Deletes an User
	 */
	public UserDTO delete(long id) {
		UserDTO user = findByPk(id);
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
		return user;
	}

	public List<UserDTO> search(UserDTO dto) {
		return search(dto, 0, 0);
	}

	/**
	 * Finds User by primary key User ID.
	 */
	public UserDTO findByPk(long id) {
		Session session = sessionFactory.getCurrentSession();
		UserDTO user = null;
		user = (UserDTO) session.get(UserDTO.class, id);
		return user;
	}

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserDTO.class);

		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName()
						+ "%"));
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				criteria.add(Restrictions.like("lastName", dto.getLastName()
						+ "%"));
			}
			if (dto.getLogin() != null && dto.getLogin().length() > 0) {
				criteria.add(Restrictions.like("login", dto.getLogin() + "%"));
			}

		}
		// if page size is greater than zero the apply pagination

		if (pageSize > 0) {
			int firstRec = 1 + ((pageNo - 1) * pageSize);
			criteria.setFirstResult(firstRec);
			criteria.setMaxResults(pageSize);
		}

		List<UserDTO> list = criteria.list();

		return list;

	}
}
