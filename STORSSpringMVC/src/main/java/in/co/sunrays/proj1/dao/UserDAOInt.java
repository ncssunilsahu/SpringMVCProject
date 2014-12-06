package in.co.sunrays.proj1.dao;

import in.co.sunrays.proj1.dto.UserDTO;
import in.co.sunrays.proj1.exception.DatabaseException;
import java.util.List;

/**
 * Data Access Object of User
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public interface UserDAOInt {

	/**
	 * Add a User
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public long add(UserDTO dto) throws DatabaseException;

	/**
	 * Update a User
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void update(UserDTO dto) throws DatabaseException;

	/**
	 * Delete a User
	 * 
	 * @param dto
	 * @throws DatabaseException
	 */
	public void delete(UserDTO dto) throws DatabaseException;

	/**
	 * Find User by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public UserDTO findByLogin(String login) throws DatabaseException;

	/**
	 * Find User by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws DatabaseException
	 */
	public UserDTO findByPK(long pk) throws DatabaseException;

	/**
	 * Get List of Users
	 * 
	 * @return list : List of User
	 * @throws DatabaseException
	 */
	public List list() throws DatabaseException;

	/**
	 * Get List of Users with pagination
	 * 
	 * @return list : List of User
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws DatabaseException;

	/**
	 * Search Users
	 * 
	 * @return list : List of Users
	 * @param dto
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(UserDTO dto) throws DatabaseException;

	/**
	 * Search Users with pagination
	 * 
	 * @return list : List of Users
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List search(UserDTO dto, int pageNo, int pageSize)
			throws DatabaseException;

}