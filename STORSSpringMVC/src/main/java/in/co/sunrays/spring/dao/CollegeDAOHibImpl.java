package in.co.sunrays.spring.dao;

import in.co.sunrays.spring.dto.CollegeDTO;
import in.co.sunrays.spring.exception.DatabaseException;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("collegeDAO")
public class CollegeDAOHibImpl implements CollegeDAOInt {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger log = Logger.getLogger(CollegeDAOHibImpl.class);

	/**
	 * Add a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(CollegeDTO dto) {

		log.debug("DAO add Started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("DAO add Started");
		return pk;
	}

	/**
	 * Update a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void update(CollegeDTO dto) {
		log.debug("DAO update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("DAO update End");
	}

	/**
	 * Delete a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */

	public void delete(CollegeDTO dto) {
		log.debug("DAO delete Started");
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("DAO delete End");
	}

	/**
	 * Find College by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */

	public CollegeDTO findByName(String name) {
		System.out.println("DAO findByName Started");
		CollegeDTO dto = null;
		List list = sessionFactory.getCurrentSession()
				.createCriteria(CollegeDTO.class)
				.add(Restrictions.eq("name", name)).list();

		System.out.println("list size in find by name dao" + list.size());
		if (list.size() == 1) {
			dto = (CollegeDTO) list.get(0);
			System.out.println("DTO not null");
		}
		System.out.println("DAO findByName End");
		return dto;
	}

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

	public List search(CollegeDTO dto, int pageNo, int pageSize) {
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
		list = c.list();
		System.out.println("Session Factory in search :" + sessionFactory);

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

	public List search(CollegeDTO dto) {
		return search(dto, 0, 0);
	}

	/**
	 * Find College by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */

	public CollegeDTO findByPK(long pk) {
		System.out.println("DAO findByPK Started");
		CollegeDTO dto = null;
		dto = (CollegeDTO) sessionFactory.getCurrentSession().get(
				CollegeDTO.class, pk);
		System.out.println("DAO findByPK End");
		return dto;
	}

}
