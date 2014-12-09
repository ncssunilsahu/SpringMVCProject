package in.co.sunrays.proj1.service;

import in.co.sunrays.proj1.dto.MarksheetDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;

import java.util.List;

/**
 * Business Service Interface of Marksheet
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */
public interface MarksheetServiceInt {

	/**
	 * Add a Marksheet
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when RollNo. already exists
	 */
	public long add(MarksheetDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Update a Marksheet
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when updated RollNo. is already exists
	 */
	public void update(MarksheetDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Delete a Marksheet
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(MarksheetDTO dto) throws ApplicationException;

	/**
	 * Find Marksheet by Roll No
	 * 
	 * @param rollNo
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException;

	/**
	 * Find Marksheet by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public MarksheetDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Search Marksheet with pagination
	 * 
	 * @return list : List of Marksheet
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List search(MarksheetDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	/**
	 * Search Marksheet
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(MarksheetDTO dto) throws ApplicationException;

	/**
	 * Get List of Marksheet
	 * 
	 * @return list : List of Marksheet
	 * @throws ApplicationException
	 */
	public List list() throws ApplicationException;

	/**
	 * Get List of Marksheet with pagination
	 * 
	 * @return list : List of Marksheet
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

}
