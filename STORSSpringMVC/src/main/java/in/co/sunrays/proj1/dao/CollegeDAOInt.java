package in.co.sunrays.proj1.dao;

import in.co.sunrays.proj1.dto.CollegeDTO;
import in.co.sunrays.proj1.exception.DatabaseException;

import java.util.List;

/**
 * Data Access Object of College
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */
public interface CollegeDAOInt {

	/**
	 * Add a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(CollegeDTO dto) throws DatabaseException;

	/**
	 * Update a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void update(CollegeDTO dto) throws DatabaseException;

	/**
	 * Delete a College
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void delete(CollegeDTO dto) throws DatabaseException;

	/**
	 * Find College by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public CollegeDTO findByName(String name) throws DatabaseException;

	/**
	 * Find College by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public CollegeDTO findByPK(long pk) throws DatabaseException;

	/**
	 * Get List of Colleges
	 * 
	 * @return list : List of College
	 * @throws DatabaseException
	 */
	public List list() throws DatabaseException;

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
	public List list(int pageNo, int pageSize) throws DatabaseException;

	/**
	 * Search Colleges
	 * 
	 * @return list : List of Colleges
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(CollegeDTO dto) throws DatabaseException;

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
			throws DatabaseException;

}
