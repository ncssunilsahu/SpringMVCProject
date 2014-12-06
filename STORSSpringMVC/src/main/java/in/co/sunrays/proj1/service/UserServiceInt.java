package in.co.sunrays.proj1.service;

import in.co.sunrays.proj1.dto.RoleDTO;
import in.co.sunrays.proj1.dto.UserDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import in.co.sunrays.proj1.exception.RecordNotFoundException;
import java.util.List;

/**
 * Business Service Interface of User
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public interface UserServiceInt {

	/**
	 * Add a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when User is already exists
	 */
	public long add(UserDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Register a user
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when Login is already exists
	 */
	public long registerUser(UserDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Update a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when updated User is already exists
	 */
	public void update(UserDTO dto) throws ApplicationException,
			DuplicateRecordException;

	/**
	 * Delete a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(UserDTO dto) throws ApplicationException;

	/**
	 * Find user by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public UserDTO findByLogin(String login) throws ApplicationException;

	/**
	 * Find user by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public UserDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Get List of Users
	 * 
	 * @return list : List of Users
	 * @throws ApplicationException
	 */
	public List list() throws ApplicationException;

	/**
	 * Get List of Users with pagination
	 * 
	 * @return list : List of Users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Search Users
	 * 
	 * @return list : List of Users
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(UserDTO dto) throws ApplicationException;

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
	 * @throws ApplicationException
	 */
	public List search(UserDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	/**
	 * Change Password By pk
	 * 
	 * @param pk
	 *            ,oldPassword,newPassword : get parameter
	 * @return dto
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 */
	public boolean changePassword(Long id, String oldPassword,
			String newPassword) throws ApplicationException,
			RecordNotFoundException;

	/**
	 * User Authentication
	 * 
	 * @return dto : Contains User's information
	 * @param dto
	 * @throws ApplicationException
	 */
	public UserDTO authenticate(UserDTO dto) throws ApplicationException;

	/**
	 * Lock User for certain time duration
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : throws when user not found
	 */
	public boolean lock(String login) throws ApplicationException;

	/**
	 * Reset Password of User with auto generated Password
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : throws when user not found
	 */
	public boolean resetPassword(String login) throws ApplicationException,
			RecordNotFoundException;

	/**
	 * Send the password of User to his Email
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : throws when user not found
	 */
	public boolean forgetPassword(String login) throws ApplicationException,
			RecordNotFoundException;

	/**
	 * Get User Roles
	 * 
	 * @return RoleDTO : User Role
	 * @param dto
	 * @throws ApplicationException
	 */
	public RoleDTO getRole(UserDTO dto) throws ApplicationException;

	/**
	 * Update User access
	 * 
	 * @return dto
	 * @param dto
	 * @throws ApplicationException
	 */
	public UserDTO updateAccess(UserDTO dto) throws ApplicationException;

}