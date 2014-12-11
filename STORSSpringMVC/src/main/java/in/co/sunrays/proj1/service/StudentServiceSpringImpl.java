package in.co.sunrays.proj1.service;

import in.co.sunrays.proj1.dao.StudentDAOInt;
import in.co.sunrays.proj1.dto.StudentDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DatabaseException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Implementation of Student Service
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */
@Service("studentService")
public class StudentServiceSpringImpl implements StudentServiceInt {

	@Autowired
	private StudentDAOInt dao;

	/**
	 * Add a Student
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when Student is already exists
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(StudentDTO dto) throws ApplicationException,
			DuplicateRecordException {
		System.out.println("Service add Started");
		long pk = 0;
		try {
			StudentDTO dtoExist = dao.findByEmail(dto.getEmail());
			if (dtoExist != null) {
				throw new DuplicateRecordException("Email already exists");
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
	 * Update a Student
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when updated Email is already exists
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void update(StudentDTO dto) throws ApplicationException,
			DuplicateRecordException {
		System.out.println("Service update Started");
		try {
			StudentDTO dtoExist = dao.findByEmail(dto.getEmail());
			// Check if updated Email is already exists
			if (dtoExist != null && dtoExist.getId() != dto.getId()) {
				throw new DuplicateRecordException("Duplicate Email");
			}
			dao.update(dto);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service update End");
	}

	/**
	 * Delete a Student
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(StudentDTO dto) throws ApplicationException {
		System.out.println("Service delete Started");
		try {
			StudentDTO dtoExist = findByPK(dto.getId());
			if (dtoExist == null) {
				throw new ApplicationException("Student does not exist");
			}
			dao.delete(dto);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service delete End");
	}

	/**
	 * Find Student by Email
	 * 
	 * @param email
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public StudentDTO findByEmail(String email) throws ApplicationException {
		System.out.println("Service findByEmail Started");
		StudentDTO dto;
		try {
			dto = dao.findByEmail(email);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service findByEmail End");
		return dto;
	}

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public StudentDTO findByPK(long pk) throws ApplicationException {
		System.out.println("Service findByPK Started");
		StudentDTO dto;
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
	 * Search Students with pagination
	 * 
	 * @return list : List of Students
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List search(StudentDTO dto, int pageNo, int pageSize)
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
	 * Search Students
	 * 
	 * @return list : List of Students
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List search(StudentDTO dto) throws ApplicationException {
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
	 * Get List of Students
	 * 
	 * @return list : List of Students
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
	 * Get List of Students with pagination
	 * 
	 * @return list : List of Students
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
