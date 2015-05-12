package in.co.sunrays.spring.service;

import in.co.sunrays.spring.dao.UserDAOInt;
import in.co.sunrays.spring.dto.UserDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "userService")
public class UserServiceImpl implements UserServiceInt {

	@Autowired
	private UserDAOInt dao = null;

	public void setDao(UserDAOInt dao) {
		this.dao = dao;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserDTO get(long id) {
		return dao.findByPk(id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(UserDTO dto) {

		long pk = dao.add(dto);

		/**
		 * In order to see the transactional behavior of method, make if
		 * condition true. It will raise and propagate unchecked
		 * RuntimeException exception. That will cause transaction roll back and
		 * User will not be added.
		 */
		if (false) {
			throw new RuntimeException("Test Transaction Rollback");
		}

		// Test Transaction Rollback by making if condition true
		if (false) {
			throw new RuntimeException("Rolback Trn");
		}
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long update(UserDTO dto) {
		return dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public UserDTO delete(long id) {
		return dao.delete(id);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<UserDTO> search(UserDTO dto) {
		return dao.search(dto);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

	/**
	 * Gets User by Login ID
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserDTO get(String login) {
		UserDTO dto = new UserDTO();
		dto.setLogin(login);

		List<UserDTO> list = search(dto);
		if (list.size() == 1) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public boolean changePassword(Long id, String oldPassword,
			String newPassword) {
		UserDTO dto = get(id);
		if (oldPassword.equals(dto.getPassword())) {
			dto.setPassword(newPassword);
			dao.update(dto);
			return true;
		} else {
			return false;
		}
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserDTO authenticate(String login, String password) {

		UserDTO dto = new UserDTO();
		dto.setLogin(login);
		dto.setPassword(password);

		List<UserDTO> list = search(dto);
		if (list.size() == 1) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Register New User
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long registerUser(UserDTO dto) {
		long id = add(dto);
		// Send Email after successful User creation
		return id;
	}

}
