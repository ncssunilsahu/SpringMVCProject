package in.co.sunrays.proj1.dao;

import in.co.sunrays.proj1.dto.StudentDTO;
import in.co.sunrays.proj1.exception.DatabaseException;
import java.util.List;

/**
 * Data Access Object of Student
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */
public interface StudentDAOInt {

	/**
	 * Add a Student
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(StudentDTO dto) throws DatabaseException;

	/**
	 * Update a Student
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void update(StudentDTO dto) throws DatabaseException;

	/**
	 * Delete a Student
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void delete(StudentDTO dto) throws DatabaseException;

	/**
	 * Find Student by Email
	 * 
	 * @param email
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public StudentDTO findByEmail(String email) throws DatabaseException;

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public StudentDTO findByPK(long pk) throws DatabaseException;

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
	 * @throws DatabaseException
	 */
	public List search(StudentDTO dto, int pageNo, int pageSize)
			throws DatabaseException;

	/**
	 * Search Students
	 * 
	 * @return list : List of Students
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(StudentDTO dto) throws DatabaseException;

	/**
	 * Gets List of Students
	 * 
	 * @return list : List of Students
	 * @throws DatabaseException
	 */
	public List list() throws DatabaseException;

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
	public List list(int pageNo, int pageSize) throws DatabaseException;

}
