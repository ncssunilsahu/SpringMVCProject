package in.co.sunrays.proj1.service;

import in.co.sunrays.proj1.dao.MarksheetDAOInt;
import in.co.sunrays.proj1.dto.MarksheetDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DatabaseException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * JavaBean Implementation of Marksheet Service
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Service("marksheetService")
public class MarksheetServiceSpringImpl implements MarksheetServiceInt {

	@Autowired
	private MarksheetDAOInt dao;

	/**
	 * Add a Marksheet
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when updated RollNo. is already exists
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(MarksheetDTO dto) throws ApplicationException,
			DuplicateRecordException {

		System.out.println("Service add Started");
		long pk = 0;
		try {
			MarksheetDTO dtoExist = dao.findByRollNo(dto.getRollNo());
			if (dtoExist != null) {
				throw new DuplicateRecordException("Roll No already exists");
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
	 * Update a Marksheet
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when RollNo. is already exists
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void update(MarksheetDTO dto) throws ApplicationException,
			DuplicateRecordException {

		System.out.println("Service update Started");
		try {
			MarksheetDTO dtoExist = dao.findByRollNo(dto.getRollNo());
			if (dtoExist != null && dtoExist.getId() != dto.getId()) {
				throw new DuplicateRecordException("Duplicate Roll Number");
			}
			dao.update(dto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service update End");
	}

	/**
	 * Delete a Marksheet
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(MarksheetDTO dto) throws ApplicationException {

		System.out.println("Service delete Started");
		try {
			MarksheetDTO dtoExist = findByPK(dto.getId());
			if (dtoExist == null) {
				throw new ApplicationException("Marksheet does not exist");
			}
			dao.delete(dto);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service delete End");
	}

	/**
	 * Find Marksheet by Roll No
	 * 
	 * @param rollNo
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException {

		System.out.println("Service findByRollNo Started");
		MarksheetDTO dto;
		try {
			System.out.println("RollNo is in service:" + rollNo);
			dto = dao.findByRollNo(rollNo);

		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service findByRollNo End");
		return dto;
	}

	/**
	 * Find Marksheet by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public MarksheetDTO findByPK(long pk) throws ApplicationException {

		System.out.println("Service findById Started");
		MarksheetDTO dto;
		try {
			dto = dao.findByPK(pk);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service findByPK End");
		return dto;
	}

	/**
	 * Search Marksheets
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List search(MarksheetDTO dto) throws ApplicationException {
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
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List search(MarksheetDTO dto, int pageNo, int pageSize)
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

	/**
	 * Get List of Marksheets
	 * 
	 * @return list : List of Marksheets
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
	 * Get List of Marksheets with pagination
	 * 
	 * @return list : List of Marksheets
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
}
