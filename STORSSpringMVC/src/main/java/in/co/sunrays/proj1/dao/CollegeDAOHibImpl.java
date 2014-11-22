package in.co.sunrays.proj1.dao;

import in.co.sunrays.proj1.dto.CollegeDTO;
import in.co.sunrays.proj1.exception.DatabaseException;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Hibernate Implementation of CollegeDAO
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Repository("collegeDAO")
public class CollegeDAOHibImpl implements CollegeDAOInt {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Add a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(CollegeDTO dto) throws DatabaseException {
		long pk = 0;
		try {
			pk = (Long) sessionFactory.getCurrentSession().save(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in College add");
		}
		System.out.println("DAO add End");
		return pk;
	}

	/**
	 * Update a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void update(CollegeDTO dto) throws DatabaseException {
		System.out.println("DAO update Started");
		try {
			sessionFactory.getCurrentSession().update(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in College Update");
		}
		System.out.println("DAO update End");
	}

	/**
	 * Delete a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void delete(CollegeDTO dto) throws DatabaseException {
		System.out.println("DAO delete Started");
		try {
			sessionFactory.getCurrentSession().delete(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in College delete");
		}
		System.out.println("DAO delete End");
	}

	/**
	 * Find College by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */

	public CollegeDTO findByName(String name) throws DatabaseException {
		System.out.println("DAO findByName Started");
		CollegeDTO dto = null;
		try {
			List list = sessionFactory.getCurrentSession()
					.createCriteria(CollegeDTO.class)
					.add(Restrictions.eq("name", name)).list();

			System.out.println("list size in find by name dao" + list.size());
			if (list.size() == 1) {
				dto = (CollegeDTO) list.get(0);
				System.out.println("DTO not null");
			}
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in getting College by name");
		}
		System.out.println("DAO findByName End");
		return dto;
	}

	/**
	 * Get List of Colleges with pagination
	 * 
	 * @return list : List of College
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
					CollegeDTO.class);
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
		 * CollegeDTO.class); List list = c.list(); return list;
		 */}

	/**
	 * Search Colleges with pagination
	 * 
	 * @return list : List of Colleges
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List search(CollegeDTO dto, int pageNo, int pageSize)
			throws DatabaseException {
		System.out.println("DAO search Started");

		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				CollegeDTO.class);

		if (dto.getId() > 0) {
			c.add(Restrictions.eq("id", dto.getId()));
		}
		if (dto.getName() != null && dto.getName().length() > 0) {
			c.add(Restrictions.like("name", dto.getName() + "%"));
		}
		if (dto.getAddress() != null && dto.getAddress().length() > 0) {
			c.add(Restrictions.like("addrsss", dto.getAddress() + "%"));
		}
		if (dto.getState() != null && dto.getState().length() > 0) {
			c.add(Restrictions.like("state", dto.getState() + "%"));
		}
		if (dto.getCity() != null && dto.getCity().length() > 0) {
			c.add(Restrictions.like("city", dto.getCity() + "%"));
		}
		if (dto.getPhoneNo() != null && dto.getPhoneNo().length() > 0) {
			c.add(Restrictions.eq("phoneNo", dto.getPhoneNo()));
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
			throw new DatabaseException(
					"Exception : Exception in search college");
		}
		System.out.println("DAO search End");
		return list;
	}

	/**
	 * Search Colleges
	 * 
	 * @return list : List of Colleges
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search(CollegeDTO dto) throws DatabaseException {

		return search(dto, 0, 0);
		/*
		 * Criteria c = sessionFactory.getCurrentSession().createCriteria(
		 * CollegeDTO.class); c.add(Restrictions.like("name", dto.getName() +
		 * "%")); List list = c.list(); return list;
		 */
	}

	/**
	 * Find College by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */

	public CollegeDTO findByPK(long pk) throws DatabaseException {
		System.out.println("DAO findByPK Started");
		CollegeDTO dto = null;
		try {
			dto = (CollegeDTO) sessionFactory.getCurrentSession().get(
					CollegeDTO.class, pk);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException(
					"Exception : Exception in getting College by pk");
		}
		System.out.println("DAO findByPK End");
		return dto;
	}

}
