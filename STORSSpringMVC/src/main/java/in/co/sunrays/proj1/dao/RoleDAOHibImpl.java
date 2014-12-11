package in.co.sunrays.proj1.dao;

import in.co.sunrays.proj1.dto.RoleDTO;
import in.co.sunrays.proj1.exception.DatabaseException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Hibernate Implementation of RoleDAO
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Repository("roleDAO")
public class RoleDAOHibImpl implements RoleDAOInt {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Add a Role
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(RoleDTO dto) throws DatabaseException {

		long pk = 0;
		try {
			pk = (Long) sessionFactory.getCurrentSession().save(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in Role add");
		}
		System.out.println("DAO add End");
		return pk;
	}

	/**
	 * Update a Role
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void update(RoleDTO dto) throws DatabaseException {

		System.out.println("DAO update Started");
		try {
			sessionFactory.getCurrentSession().merge(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in Role Update");
		}
		System.out.println("DAO update End");
	}

	/**
	 * Delete a Role
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void delete(RoleDTO dto) throws DatabaseException {
		System.out.println("DAO delete Started");
		try {
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"DELETE FROM RoleDTO WHERE id = " + dto.getId())
					.executeUpdate();
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in Role delete");
		}
		System.out.println("DAO delete End");
	}

	/**
	 * Find Role by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public RoleDTO findByName(String name) throws DatabaseException {

		System.out.println("DAO findByName Started");
		RoleDTO dto = null;
		try {
			List list = sessionFactory.getCurrentSession()
					.createCriteria(RoleDTO.class)
					.add(Restrictions.eq("name", name)).list();

			System.out.println("list size in find by name dao" + list.size());
			if (list.size() == 1) {
				dto = (RoleDTO) list.get(0);
				System.out.println("DTO not null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in getting Role by name");
		}
		System.out.println("DAO findByName End");
		return dto;
	}

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public RoleDTO findByPK(long pk) throws DatabaseException {

		System.out.println("DAO findByPK Started");
		RoleDTO dto = null;
		try {
			dto = (RoleDTO) sessionFactory.getCurrentSession().get(
					RoleDTO.class, pk);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException(
					"Exception : Exception in getting Role by pk");
		}
		System.out.println("DAO findByPK End");
		return dto;
	}

	/**
	 * Search Roles with pagination
	 * 
	 * @return list : List of Role
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List search(RoleDTO dto, int pageNo, int pageSize)
			throws DatabaseException {

		System.out.println("DAO search Started");

		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				RoleDTO.class);

		if (dto.getId() > 0) {
			c.add(Restrictions.eq("id", dto.getId()));
		}
		if (dto.getName() != null && dto.getName().length() > 0) {
			c.add(Restrictions.like("name", dto.getName() + "%"));
		}
		if (dto.getDescription() != null && dto.getDescription().length() > 0) {
			c.add(Restrictions.like("description", dto.getDescription() + "%"));
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
			throw new DatabaseException("Exception : Exception in search role");
		}
		System.out.println("DAO search End");
		return list;
	}

	/**
	 * Search Roles
	 * 
	 * @return list : List of Role
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(RoleDTO dto) throws DatabaseException {
		return search(dto, 0, 0);
	}

	/**
	 * Get List of Roles
	 * 
	 * @return list : List of Roles
	 * @throws DatabaseException
	 */
	public List list() throws DatabaseException {
		return list(0, 0);
	}

	/**
	 * Get List of Roles with pagination
	 * 
	 * @return list : List of Roles
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
					RoleDTO.class);
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
}
