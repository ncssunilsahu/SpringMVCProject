package in.co.sunrays.proj1.service;

import in.co.sunrays.proj1.dto.RoleDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;

import java.util.List;

/**
 * Business Service Interface of Role
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public interface RoleServiceInt {

	/**
	 * Add a Role
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when Role is already exists
	 */
	public long add(RoleDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Update a Role
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when updated Role is already exist
	 */
	public void update(RoleDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Delete a Role
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(RoleDTO dto) throws ApplicationException;

	/**
	 * Find Role by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public RoleDTO findByName(String name) throws ApplicationException;

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public RoleDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Search Roles with pagination
	 * 
	 * @return list : List of Roles
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List search(RoleDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	/**
	 * Search Roles
	 * 
	 * @return list : List of Roles
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(RoleDTO dto) throws ApplicationException;

	/**
	 * Get List of Roles
	 * 
	 * @return list : List of Roles
	 * @throws ApplicationException
	 */
	public List list() throws ApplicationException;

	/**
	 * Get List of Roles with pagination
	 * 
	 * @return list : List of Roles
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;
}
