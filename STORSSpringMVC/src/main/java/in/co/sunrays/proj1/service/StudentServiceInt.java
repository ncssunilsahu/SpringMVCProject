package in.co.sunrays.proj1.service;

import in.co.sunrays.proj1.dto.StudentDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;

import java.util.List;

/**
 * Business Service Interface of Student
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */
public interface StudentServiceInt {

	/**
	 * Add a Student
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when Student is already exists
	 */
	public long add(StudentDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Update a Student
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when updated Email is already exists
	 */
	public void update(StudentDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Delete a Student
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(StudentDTO dto) throws ApplicationException;

	/**
	 * Find Student by Email
	 * 
	 * @param email
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public StudentDTO findByEmail(String email) throws ApplicationException;

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public StudentDTO findByPK(long pk) throws ApplicationException;

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
	public List search(StudentDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	/**
	 * Search Students
	 * 
	 * @return list : List of Students
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(StudentDTO dto) throws ApplicationException;

	/**
	 * Get List of Students
	 * 
	 * @return list : List of Students
	 * @throws ApplicationException
	 */
	public List list() throws ApplicationException;

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
	public List list(int pageNo, int pageSize) throws ApplicationException;

}