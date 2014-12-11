package in.co.sunrays.proj1.dao;

import in.co.sunrays.proj1.dto.MarksheetDTO;
import in.co.sunrays.proj1.exception.DatabaseException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Hibernate Implementation of MarksheetDAO
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Repository("marksheetDAO")
public class MarksheetDAOHibImpl implements MarksheetDAOInt {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Add a Marksheet
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(MarksheetDTO dto) throws DatabaseException {

		long pk = 0;
		try {
			pk = (Long) sessionFactory.getCurrentSession().save(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in MarSheet add");
		}
		System.out.println("DAO add End");
		return pk;
	}

	/**
	 * Update a Marksheet
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void update(MarksheetDTO dto) throws DatabaseException {

		System.out.println("DAO update Started");
		try {
			sessionFactory.getCurrentSession().merge(dto);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in Marksheet Update");
		}
		System.out.println("DAO update End");
	}

	/**
	 * Delete a Marksheet
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void delete(MarksheetDTO dto) throws DatabaseException {

		System.out.println("DAO delete Started");
		try {
			System.out.println("in dao delete pk is :" + dto.getId());
			sessionFactory
					.getCurrentSession()
					.createQuery(
							"DELETE FROM MarksheetDTO WHERE id = "
									+ dto.getId()).executeUpdate();
			System.out.println("in dao after delete");
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException("Exception in Marksheet delete");
		}
		System.out.println("DAO delete End");
	}

	/**
	 * Find Marksheet by Roll No
	 * 
	 * @param rollNo
	 *            : get parameter
	 * @return dto
	 * @throws DuplicateRecordException
	 */
	public MarksheetDTO findByRollNo(String rollNo) throws DatabaseException {

		System.out.println("DAO findByRollNo Started");
		MarksheetDTO dto = null;
		try {
			List list = sessionFactory.getCurrentSession()
					.createCriteria(MarksheetDTO.class)
					.add(Restrictions.eq("rollNo", rollNo)).list();

			System.out.println("list size in find by rollNo dao" + list.size());
			if (list.size() == 1) {
				dto = (MarksheetDTO) list.get(0);
				System.out.println("DTO not null");
			}
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException(
					"Exception in getting Marksheet by rollno");
		}
		System.out.println("DAO findByName End");
		return dto;
	}

	/**
	 * Find Marksheet by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public MarksheetDTO findByPK(long pk) throws DatabaseException {

		System.out.println("DAO findByPK Started");
		MarksheetDTO dto = null;
		try {
			dto = (MarksheetDTO) sessionFactory.getCurrentSession().get(
					MarksheetDTO.class, pk);
		} catch (HibernateException e) {
			System.out.println("Database Exception.." + e);
			throw new DatabaseException(
					"Exception : Exception in getting Marksheet by pk");
		}
		System.out.println("DAO findByPK End");
		return dto;
	}

	/**
	 * Search Marksheets
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(MarksheetDTO dto) throws DatabaseException {
		return search(dto, 0, 0);
	}

	/**
	 * Search Marksheets with pagination
	 * 
	 * @return list : List of Marksheets
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List search(MarksheetDTO dto, int pageNo, int pageSize)
			throws DatabaseException {
		System.out.println("DAO search Started");
		List list = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(MarksheetDTO.class);
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getRollNo() != null && dto.getRollNo().length() > 0) {
				criteria.add(Restrictions.like("rollNo", dto.getRollNo() + "%"));
			}
			if (dto.getPhysics() != null && dto.getPhysics() > 0) {
				criteria.add(Restrictions.eq("physics", dto.getPhysics()));
			}
			if (dto.getChemistry() != null && dto.getChemistry() > 0) {
				criteria.add(Restrictions.eq("chemistry", dto.getChemistry()));
			}
			if (dto.getMaths() != null && dto.getMaths() > 0) {
				criteria.add(Restrictions.eq("maths", dto.getMaths()));
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
			throw new DatabaseException("Exception in search Marksheet");
		}
		System.out.println("DAO search End");
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
	}

	/**
	 * Get List of Marksheets with pagination
	 * 
	 * @return list : List of Marksheets
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
					MarksheetDTO.class);
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
					"Exception : Exception in  Marksheet list");
		}
		System.out.println("DAO list End");
		return list;
	}
}
