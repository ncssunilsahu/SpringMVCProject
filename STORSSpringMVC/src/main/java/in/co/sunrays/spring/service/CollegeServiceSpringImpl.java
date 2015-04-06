package in.co.sunrays.spring.service;

import in.co.sunrays.spring.dao.CollegeDAOInt;
import in.co.sunrays.spring.dto.CollegeDTO;
import in.co.sunrays.spring.exception.ApplicationException;
import in.co.sunrays.spring.exception.DuplicateRecordException;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("collegeService")
public class CollegeServiceSpringImpl implements CollegeServiceInt {

	@Autowired
	private CollegeDAOInt dao;

	private static Logger log = Logger
			.getLogger(CollegeServiceSpringImpl.class);

	/**
	 * Add a College
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(CollegeDTO dto) throws DuplicateRecordException {

		log.debug("Service add Started");
		CollegeDTO dtoExist = dao.findByName(dto.getName());
		if (dtoExist != null) {
			throw new DuplicateRecordException("Name already exists");
		}
		long pk = dao.add(dto);
		log.debug("Service add End");
		return pk;
	}

	/**
	 * Update a College
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when updated College is already exists
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void update(CollegeDTO dto) throws DuplicateRecordException {
		log.debug("Service update Started");
		CollegeDTO dtoExist = dao.findByName(dto.getName());
		// Check if updated College is already exists
		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException("Duplicate Roll Number");
		}
		dao.update(dto);
		log.debug("Service update End");
	}

	/**
	 * Delete a College
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(CollegeDTO dto) {
		CollegeDTO dtoExist = findById(dto.getId());
		dao.delete(dto);
		log.debug("Service delete End");
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CollegeDTO findById(long id) {
		log.debug("Service findById Started");
		CollegeDTO dto = dao.findByPK(id);
		log.debug("Service findById End");
		return dto;
	}

	/**
	 * Find College by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CollegeDTO findByName(String name) {

		log.debug("Service findByName Started");

		CollegeDTO dto = dao.findByName(name);

		log.debug("Service findByName End");

		return dto;
	}

	/**
	 * Search Colleges
	 * 
	 * @return list : List of Colleges
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List search(CollegeDTO dto) {

		log.debug("Service search Started");

		List list = dao.search(dto);

		log.debug("Service search End");

		return list;
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
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List search(CollegeDTO dto, int pageNo, int pageSize) {

		log.debug("Service search Started");
		List list = dao.search(dto, pageNo, pageSize);
		log.debug("Service search End");
		return list;
	}

}
