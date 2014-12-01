package in.co.sunrays.proj1.dao;

import in.co.sunrays.proj1.dto.CollegeDTO;
import in.co.sunrays.proj1.dto.StudentDTO;
import in.co.sunrays.proj1.exception.DatabaseException;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Hibernate Implementation of StudentDAO
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */
@Repository("studentDAO")
public class StudentDAOHibImpl implements StudentDAOInt {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Add a Student
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(StudentDTO dto) throws DatabaseException {
		System.out.println("DAO add Started");
		long pk = 0;
		try {
			pk = (Long) sessionFactory.getCurrentSession().save(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception : Exception in add Student");
		}
		System.out.println("DAO add End");
		return pk;
	}

	/**
	 * Update a Student
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void update(StudentDTO dto) throws DatabaseException {
		System.out.println("DAO update Started");
		try {
			sessionFactory.getCurrentSession().update(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException(
					"Exception : Exception in update Student");
		}
		System.out.println("DAO update End");
	}

	/**
	 * Delete a Student
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void delete(StudentDTO dto) throws DatabaseException {
		System.out.println("DAO delete Started");
		try {
			sessionFactory.getCurrentSession().delete(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException(
					"Exception : Exception in delete Student");
		}
		System.out.println("DAO delete End");
	}

	/**
	 * Find Student by Email
	 * 
	 * @param email
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public StudentDTO findByEmail(String email) throws DatabaseException {
		System.out.println("DAO findByEmail Started");
		StudentDTO dto = null;
		try {
			List list = sessionFactory.getCurrentSession()
					.createCriteria(StudentDTO.class)
					.add(Restrictions.eq("email", email)).list();
			if (list.size() == 1) {
				dto = (StudentDTO) list.get(0);
			}
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException(
					"Exception : Exception in getting Student by email");
		}
		System.out.println("DAO findByEmail End");
		return dto;
	}

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public StudentDTO findByPK(long pk) throws DatabaseException {
		System.out.println("DAO findByPK Started");
		StudentDTO dto = null;
		try {
			dto = (StudentDTO) sessionFactory.getCurrentSession().get(
					StudentDTO.class, pk);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException(
					"Exception : Exception in getting Student by pk");
		}
		System.out.println("DAO findByPK End");
		return dto;
	}

	/**
	 * Search Student with pagination
	 * 
	 * @return list : List of Student
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List search(StudentDTO dto, int pageNo, int pageSize)
			throws DatabaseException {
		System.out.println("DAO search Started");
		List list = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(StudentDTO.class);
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
			if (dto.getDob() != null) {
				criteria.add(Restrictions.eq("dob", dto.getDob()));
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				criteria.add(Restrictions.like("mobileNo", dto.getMobileNo()
						+ "%"));
			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				criteria.add(Restrictions.like("email", dto.getEmail() + "%"));
			}
			// if page size is greater than zero the apply pagination
			// if page size is greater than zero then apply pagination
			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in search Student");
		}
		System.out.println("DAO search End");
		return list;
	}

	/**
	 * Search Students
	 * 
	 * @return list : List of Student
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(StudentDTO dto) throws DatabaseException {
		return search(dto, 0, 0);
	}

	/**
	 * Gets List of Student
	 * 
	 * @return list : List of Students
	 * @throws DatabaseException
	 */
	public List list() throws DatabaseException {
		return list(0, 0);
	}

	/**
	 * Get List of Students with pagination
	 * 
	 * @return list : List of Students
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
					StudentDTO.class);
			// if page size is greater than zero then apply pagination
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
					"Exception : Exception in  Student list");
		}
		System.out.println("DAO list End");
		return list;
	}

}
