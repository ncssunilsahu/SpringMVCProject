package in.co.sunrays.spring.service;

import in.co.sunrays.spring.dto.UserDTO;

import java.util.List;

public interface UserServiceInt {

	public UserDTO get(long id);

	/**
	 * Gets User by Login ID
	 * 
	 * @param login
	 * @return
	 */
	public UserDTO get(String login);

	/**
	 * Adds a User
	 * 
	 * @param dto
	 * @return
	 */
	public long add(UserDTO dto);

	/**
	 * Updates a User
	 * 
	 * @param dto
	 * @return
	 */
	public long update(UserDTO dto);

	/**
	 * Delete a User
	 * 
	 * @param id
	 * @return
	 */

	public UserDTO delete(long id);

	/**
	 * Searches Users
	 * 
	 * @param dto
	 * @return
	 */
	public List<UserDTO> search(UserDTO dto);

	/**
	 * Paginate a search
	 * 
	 * @param dto
	 * @return
	 */
	public List<UserDTO> search(UserDTO ac, int pageNo, int pageSize);

	/**
	 * Changes password
	 * 
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public boolean changePassword(Long id, String oldPassword,
			String newPassword);

	/**
	 * Authenticates a User
	 * 
	 * @param dto
	 * @return
	 */
	public UserDTO authenticate(String login, String password);

	/**
	 * Register new user
	 * 
	 * @param dto
	 * @return
	 */
	public long registerUser(UserDTO dto);
}
