package in.co.sunrays.proj1.service;

import in.co.sunrays.proj1.dao.UserDAOInt;
import in.co.sunrays.proj1.dto.RoleDTO;
import in.co.sunrays.proj1.dto.UserDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DatabaseException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import in.co.sunrays.proj1.exception.RecordNotFoundException;
import in.co.sunrays.proj1.util.EmailBuilder;
import in.co.sunrays.proj1.util.EmailMessage;
import in.co.sunrays.proj1.util.EmailUtility;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * JavaBean Implementation of User Service
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Service("userService")
public class UserServiceSpringImpl implements UserServiceInt {

	@Autowired
	private UserDAOInt dao;

	/**
	 * Add a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(UserDTO dto) throws ApplicationException,
			DuplicateRecordException {
		System.out.println("Service add Started");
		long pk = 0;
		try {
			UserDTO dtoExist = dao.findByLogin(dto.getLogin());
			if (dtoExist != null) {
				throw new DuplicateRecordException("Login Name already exists");
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
	 * Update a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when updated User is already exists
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void update(UserDTO dto) throws ApplicationException,
			DuplicateRecordException {
		System.out.println("Service update Started");
		try {
			UserDTO dtoExist = dao.findByLogin(dto.getLogin());
			// Check if updated User is already exists
			if (dtoExist != null && dtoExist.getId() != dto.getId()) {
				throw new DuplicateRecordException("Duplicate Login Id");
			}
			dao.update(dto);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service update End");
	}

	/**
	 * Delete a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(UserDTO dto) throws ApplicationException {
		System.out.println("Service delete Started");
		try {
			UserDTO dtoExist = findByPK(dto.getId());
			if (dtoExist == null) {
				throw new ApplicationException("User does not exist");
			}
			dao.delete(dto);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service delete End");
	}

	/**
	 * Find User by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserDTO findByLogin(String login) throws ApplicationException {

		UserDTO dto = null;
		try {
			dto = dao.findByLogin(login);
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return dto;
	}

	/**
	 * Get List of Users
	 * 
	 * @return list : List of Users
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
	 * Get List of Users with pagination
	 * 
	 * @return list : List of Users
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

	/**
	 * Search Users
	 * 
	 * @return list : List of Users
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List search(UserDTO dto) throws ApplicationException {
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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserDTO findByPK(long pk) throws ApplicationException {
		System.out.println("Service findById Started");
		UserDTO dto;
		try {
			dto = dao.findByPK(pk);
		} catch (DatabaseException e) {
			System.out.println("Application Exception.." + e);
			throw new ApplicationException("Database Exception");
		}
		System.out.println("Service findById End");
		return dto;
	}

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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List search(UserDTO dto, int pageNo, int pageSize)
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
	 * User Authentication
	 * 
	 * @return dto : Contains User's information
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserDTO authenticate(UserDTO dto) throws ApplicationException {
		try {
			UserDTO dtoExist = dao.findByLogin(dto.getLogin());
			if (dtoExist != null
					&& dtoExist.getPassword().equals(dto.getPassword())) {
				dtoExist.setLastLogin(new Timestamp(new Date().getTime()));
				dtoExist.setLastLoginIP(dto.getLastLoginIP());
				dao.update(dtoExist);
				return dtoExist;
			} else {
				throw new ApplicationException("Invalid Username or Password");
			}
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
	}

	/**
	 * Register a user
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when Login is already exists
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long registerUser(UserDTO dto) throws ApplicationException,
			DuplicateRecordException {

		long pk = 0;
		try {
			UserDTO dtoExist = dao.findByLogin(dto.getLogin());
			if (dtoExist != null) {
				throw new DuplicateRecordException("Login is already exist.");
			}
			pk = dao.add(dto);

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("login", dto.getLogin());
			map.put("password", dto.getPassword());

			String message = EmailBuilder.getUserRegistrationMessage(map);

			EmailMessage msg = new EmailMessage();
			msg.setTo(dto.getLogin());
			msg.setSubject("Registration is successful for ORS Project SUNRAYS Technologies.");
			msg.setMessage(message);
			msg.setMessageType(EmailMessage.HTML_MSG);
			EmailUtility.sendMail(msg);

		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return pk;
	}

	/**
	 * Change Password By pk
	 * 
	 * @param pk
	 *            ,oldPassword,newPassword : get parameter
	 * @return dto
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean changePassword(Long id, String oldPassword,
			String newPassword) throws ApplicationException,
			RecordNotFoundException {
		boolean flag = false;
		UserDTO dtoExist = null;
		try {
			dtoExist = dao.findByPK(id);
			if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
				dtoExist.setPassword(newPassword);
				dao.update(dtoExist);
				flag = true;

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("firstName", dtoExist.getFirstName());
				map.put("lastName", dtoExist.getLastName());
				map.put("login", dtoExist.getLogin());
				map.put("password", dtoExist.getPassword());

				String message = EmailBuilder.getForgetPasswordMessage(map);
				EmailMessage msg = new EmailMessage();

				msg.setTo(dtoExist.getLogin());
				msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
				msg.setMessage(message);
				msg.setMessageType(EmailMessage.HTML_MSG);

				EmailUtility.sendMail(msg);
			} else {
				throw new RecordNotFoundException("Old Password not match.");
			}
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return flag;
	}

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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean lock(String login) throws ApplicationException {

		boolean flag = false;
		UserDTO dtoExist = null;
		try {
			dtoExist = findByLogin(login);
			if (dtoExist != null) {
				dtoExist.setLock(UserDTO.ACTIVE);
				dao.update(dtoExist);
				flag = true;
			} else {
				throw new ApplicationException("LoginId not exist");
			}
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return flag;
	}

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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean resetPassword(String login) throws ApplicationException,
			RecordNotFoundException {

		boolean flag = false;
		UserDTO dtoExist = null;
		try {
			dtoExist = dao.findByLogin(login);
			if (dtoExist != null) {
				String newPassword = String.valueOf(new Date().getTime())
						.substring(0, 4);
				dtoExist.setPassword(newPassword);
				dao.update(dtoExist);

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("login", dtoExist.getLogin());
				map.put("password", dtoExist.getPassword());
				map.put("firstName", dtoExist.getFirstName());
				map.put("lastName", dtoExist.getLastName());
				String message = EmailBuilder.getForgetPasswordMessage(map);

				EmailMessage msg = new EmailMessage();
				msg.setTo(dtoExist.getLogin());
				msg.setSubject("Password has been reset.");
				msg.setMessage(message);
				msg.setMessageType(EmailMessage.HTML_MSG);
				EmailUtility.sendMail(msg);

				flag = true;
			} else {
				throw new RecordNotFoundException("LoginId not exist");
			}
		} catch (DatabaseException e) {

			throw new ApplicationException("Database Exception");
		}

		return flag;
	}

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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean forgetPassword(String login) throws ApplicationException,
			RecordNotFoundException {

		boolean flag = false;
		UserDTO dtoExist = null;
		try {
			dtoExist = dao.findByLogin(login);
			if (dtoExist != null) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("firstName", dtoExist.getFirstName());
				map.put("lastName", dtoExist.getLastName());
				map.put("login", dtoExist.getLogin());
				map.put("password", dtoExist.getPassword());

				String message = EmailBuilder.getForgetPasswordMessage(map);

				EmailMessage msg = new EmailMessage();

				msg.setTo(dtoExist.getLogin());
				msg.setSubject("SUNARYS ORS Password reset.");
				msg.setMessage(message);
				msg.setMessageType(EmailMessage.HTML_MSG);

				EmailUtility.sendMail(msg);

				flag = true;
			} else {
				throw new RecordNotFoundException("LoginId not exist");
			}
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return flag;
	}

	/**
	 * Get User Roles
	 * 
	 * @return RoleDTO : User Role
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public RoleDTO getRole(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Update User access
	 * 
	 * @return dto
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserDTO updateAccess(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
