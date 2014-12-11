package in.co.sunrays.proj1.dao;

import in.co.sunrays.proj1.dto.UserDTO;
import in.co.sunrays.proj1.exception.DatabaseException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Hibernate Implementation of UserDAO
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Repository("userDAO")
public class UserDAOHibImpl implements UserDAOInt {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Add a User
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(UserDTO dto) throws DatabaseException {
		long pk = 0;
		try {
			pk = (Long) sessionFactory.getCurrentSession().save(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in User add");
		}
		System.out.println("DAO add End");
		return pk;
	}

	/**
	 * Update a User
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void update(UserDTO dto) throws DatabaseException {
		System.out.println("DAO update Started");
		try {
			sessionFactory.getCurrentSession().merge(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in User Update");
		}
		System.out.println("DAO update End");
	}

	/**
	 * Delete a User
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void delete(UserDTO dto) throws DatabaseException {
		System.out.println("DAO delete Started");
		try {
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"DELETE FROM UserDTO WHERE id = " + dto.getId())
					.executeUpdate();
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in User delete");
		}
		System.out.println("DAO delete End");
	}

	/**
	 * Find User by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */

	public UserDTO findByLogin(String login) throws DatabaseException {
		System.out.println("DAO findByName Started");
		UserDTO dto = null;
		try {
			List list = sessionFactory.getCurrentSession()
					.createCriteria(UserDTO.class)
					.add(Restrictions.eq("emailId", login)).list();

			System.out.println("list size in find by name dao" + list.size());
			if (list.size() == 1) {
				dto = (UserDTO) list.get(0);
				System.out.println("DTO not null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in getting User by name");
		}
		System.out.println("DAO findByName End");
		return dto;
	}

	/**
	 * Get List of Users with pagination
	 * 
	 * @return list : List of User
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws DatabaseException {
		System.out.println("DAO list Started");
		List list = null;
		try {
			Criteria c = sessionFactory.getCurrentSession().createCriteria(
					UserDTO.class);
			// if page size is greater than zero then apply pagination

			System.out.println(pageNo);
			System.out.println(pageSize);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				c.setFirstResult(pageNo);
				c.setMaxResults(pageSize);
			}

			System.out.println("before size list");
			list = c.list();
			System.out.println("Session factory in dao :" + sessionFactory);
			System.out.println("list size in dao :" + list.size());
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException(
					"Exception : Exception in  college list");
		}
		System.out.println("DAO list End");
		return list;
	}

	/**
	 * Get List of Marksheets
	 * 
	 * @return list : List of Marksheets
	 * @throws DatabaseException
	 */

	public List list() throws DatabaseException {
		return list(0, 0);
		/*
		 * Criteria c = sessionFactory.getCurrentSession().createCriteria(
		 * UserDTO.class); List list = c.list(); return list;
		 */}

	/**
	 * Search Users with pagination
	 * 
	 * @return list : List of Users
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List search(UserDTO dto, int pageNo, int pageSize)
			throws DatabaseException {
		System.out.println("DAO search Started");

		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				UserDTO.class);

		if (dto.getId() > 0) {
			c.add(Restrictions.eq("id", dto.getId()));
		}
		if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
			c.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
		}
		if (dto.getLastName() != null && dto.getLastName().length() > 0) {
			c.add(Restrictions.like("lastName", dto.getLastName() + "%"));
		}
		if (dto.getGender() != null && dto.getGender().length() > 0) {
			c.add(Restrictions.like("gender", dto.getGender() + "%"));
		}

		if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
			c.add(Restrictions.eq("mobileNo", dto.getMobileNo()));
		}
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			c.setFirstResult((pageNo - 1) * pageSize);
			c.setMaxResults(pageSize);
		}
		List list = null;
		try {
			list = c.list();
			System.out.println("Session Factory in search :" + sessionFactory);
		} catch (Exception e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception : Exception in search user");
		}
		System.out.println("DAO search End");
		return list;
	}

	/**
	 * Search Users
	 * 
	 * @return list : List of Users
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search(UserDTO dto) throws DatabaseException {

		return search(dto, 0, 0);
		/*
		 * Criteria c = sessionFactory.getCurrentSession().createCriteria(
		 * UserDTO.class); c.add(Restrictions.like("name", dto.getName() +
		 * "%")); List list = c.list(); return list;
		 */
	}

	/**
	 * Find User by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */

	public UserDTO findByPK(long pk) throws DatabaseException {
		System.out.println("DAO findByPK Started");
		UserDTO dto = null;
		try {
			dto = (UserDTO) sessionFactory.getCurrentSession().get(
					UserDTO.class, pk);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException(
					"Exception : Exception in getting User by pk");
		}
		System.out.println("DAO findByPK End");
		return dto;
	}

}
