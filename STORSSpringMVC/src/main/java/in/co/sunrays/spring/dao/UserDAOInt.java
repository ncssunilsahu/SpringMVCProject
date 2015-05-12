package in.co.sunrays.spring.dao;

import in.co.sunrays.spring.dto.UserDTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * User DAO Interface provides abstract methods of CRUD operations.
 * Implementation will be done by JDBC, Hibrenate or JPA.
 * 
 * All methods propagate unchecked DataAccessException. It is a generic
 * exception handling provided by Spring-DAO.
 * 
 * If DataAccessException is propagated from a method then declarative
 * transaction is rolled back by Spring AOP.
 * 
 * @version 1.0
 * @since 1 Jan 2015
 * @author Sunil Sahu
 * @Copyright (c) Sunil Sahu
 * @url www.sunilbooks.com
 */

public interface UserDAOInt {

	/**
	 * Adds an User
	 */
	public long add(UserDTO dto) throws DataAccessException;

	/**
	 * Updates an User
	 */
	public long update(UserDTO dto) throws DataAccessException;

	/**
	 * Deletes an User
	 */
	public UserDTO delete(long id) throws DataAccessException;

	/**
	 * Finds a User by User id.
	 */
	public UserDTO findByPk(long id) throws DataAccessException;

	/**
	 * Searches Users by given User criteria.
	 */
	public List<UserDTO> search(UserDTO dto) throws DataAccessException;

	/**
	 * Searches Users by given User criteria. It applies pagination
	 */
	public List<UserDTO> search(UserDTO ac, int pageNo, int pageSize)
			throws DataAccessException;

}
