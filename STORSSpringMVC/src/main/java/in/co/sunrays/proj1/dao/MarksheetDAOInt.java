package in.co.sunrays.proj1.dao;

import in.co.sunrays.proj1.dto.MarksheetDTO;
import in.co.sunrays.proj1.exception.DatabaseException;

import java.util.List;

/**
 * Data Access Object of Marksheet
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public interface MarksheetDAOInt {

	/**
	 * Add a Marksheet
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(MarksheetDTO dto) throws DatabaseException;

	/**
	 * Update a Marksheet
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void update(MarksheetDTO dto) throws DatabaseException;

	/**
	 * Delete a Marksheet
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void delete(MarksheetDTO dto) throws DatabaseException;

	/**
	 * Find Marksheet by Roll No
	 * 
	 * @param rollNo
	 *            : get parameter
	 * @return dto
	 * @throws DuplicateRecordException
	 */
	public MarksheetDTO findByRollNo(String rollNo) throws DatabaseException;

	/**
	 * Find Marksheet by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public MarksheetDTO findByPK(long pk) throws DatabaseException;

	/**
	 * Search Marksheets
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(MarksheetDTO dto) throws DatabaseException;

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
			throws DatabaseException;

	/**
	 * Get List of Marksheets
	 * 
	 * @return list : List of Marksheets
	 * @throws DatabaseException
	 */
	public List list() throws DatabaseException;

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
	public List list(int pageNo, int pageSize) throws DatabaseException;

}
