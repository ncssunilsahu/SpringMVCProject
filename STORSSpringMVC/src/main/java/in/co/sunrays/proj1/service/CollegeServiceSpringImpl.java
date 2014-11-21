package in.co.sunrays.proj1.service;

import in.co.sunrays.proj1.dao.CollegeDAOInt;
import in.co.sunrays.proj1.dto.CollegeDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DatabaseException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("collegeService")
public class CollegeServiceSpringImpl implements CollegeServiceInt {

	@Autowired
	private CollegeDAOInt dao;

	/**
	 * Add a College
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(CollegeDTO dto) throws ApplicationException,
			DuplicateRecordException {
		System.out.println("Service add Started");
		long pk = 0;
		try {
			CollegeDTO dtoExist = dao.findByName(dto.getName());
			if (dtoExist != null) {
				throw new DuplicateRecordException("Name already exists");
			}
			pk = dao.add(dto);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service add End");
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
	public void update(CollegeDTO dto) throws ApplicationException,
			DuplicateRecordException {
		System.out.println("Service update Started");
		try {
			CollegeDTO dtoExist = dao.findByName(dto.getName());
			// Check if updated College is already exists
			if (dtoExist != null && dtoExist.getId() != dto.getId()) {
				throw new DuplicateRecordException("Duplicate Roll Number");
			}
			dao.update(dto);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service update End");
	}

	/**
	 * Delete a College
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(CollegeDTO dto) throws ApplicationException {
		System.out.println("Service delete Started");
		try {
			CollegeDTO dtoExist = findById(dto.getId());
			if (dtoExist == null) {
				throw new ApplicationException("College does not exist");
			}
			dao.delete(dto);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service delete End");
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
	public CollegeDTO findByName(String name) throws ApplicationException {
		System.out.println("Service findByName Started");
		CollegeDTO dto;
		try {
			System.out.println("name is in service:" + name);
			dto = dao.findByName(name);

		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service findByName End");
		return dto;
	}

	/**
	 * Get List of Colleges
	 * 
	 * @return list : List of Colleges
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List list() throws ApplicationException {
		System.out.println("Service list Started");
		List list;
		try {
			list = dao.list();

		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service list End");
		return list;
	}

	/**
	 * Get List of Colleges with pagination
	 * 
	 * @return list : List of Colleges
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List list(int pageNo, int pageSize) throws ApplicationException {
		System.out.println("Service list Started");
		List list;
		try {
			list = dao.list(pageNo, pageSize);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service list End");
		return list;
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
	public List search(CollegeDTO dto) throws ApplicationException {
		System.out.println("Service search Started");
		List list;
		try {
			list = dao.search(dto);

		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service search End");
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CollegeDTO findById(long id) throws ApplicationException {
		System.out.println("Service findById Started");
		CollegeDTO dto;
		try {
			dto = dao.findByPK(id);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service findById End");
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
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List search(CollegeDTO dto, int pageNo, int pageSize)
			throws ApplicationException {
		System.out.println("Service search Started");
		List list;
		try {
			list = dao.search(dto, pageNo, pageSize);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service search End");
		return list;
	}

}
