package in.co.sunrays.proj1.dao;

import in.co.sunrays.proj1.dto.RoleDTO;
import in.co.sunrays.proj1.exception.DatabaseException;

import java.util.List;

/**
 * Data Access Object of Role
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public interface RoleDAOInt {

	/**
	 * Add a Role
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(RoleDTO dto) throws DatabaseException;

	/**
	 * Update a Role
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void update(RoleDTO dto) throws DatabaseException;

	/**
	 * Delete a Role
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void delete(RoleDTO dto) throws DatabaseException;

	/**
	 * Find Role by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public RoleDTO findByName(String name) throws DatabaseException;

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public RoleDTO findByPK(long pk) throws DatabaseException;

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
	 * @throws DatabaseException
	 */
	public List search(RoleDTO dto, int pageNo, int pageSize)
			throws DatabaseException;

	/**
	 * Search Roles
	 * 
	 * @return list : List of Roles
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(RoleDTO dto) throws DatabaseException;

	/**
	 * Get List of Roles
	 * 
	 * @return list : List of Roles
	 * @throws DatabaseException
	 */
	public List list() throws DatabaseException;

	/**
	 * Get List of Roles with pagination
	 * 
	 * @return list : List of Roles
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws DatabaseException;
}
