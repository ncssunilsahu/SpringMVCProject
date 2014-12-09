package in.co.sunrays.proj1.service;

import in.co.sunrays.proj1.dao.RoleDAOInt;
import in.co.sunrays.proj1.dto.RoleDTO;
import in.co.sunrays.proj1.exception.ApplicationException;
import in.co.sunrays.proj1.exception.DatabaseException;
import in.co.sunrays.proj1.exception.DuplicateRecordException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * JavaBean Implementation of Role Service
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Service("roleService")
public class RoleServiceSpringImpl implements RoleServiceInt {

	@Autowired
	private RoleDAOInt dao;

	/**
	 * Add a Role
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(RoleDTO dto) throws ApplicationException,
			DuplicateRecordException {

		System.out.println("Service add Started");
		long pk = 0;
		try {
			RoleDTO dtoExist = dao.findByName(dto.getName());
			if (dtoExist != null) {
				throw new DuplicateRecordException("Role Name already exists");
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
	 * Update a Role
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when updated Role is already exist
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void update(RoleDTO dto) throws ApplicationException,
			DuplicateRecordException {
		try {
			RoleDTO dtoExist = dao.findByName(dto.getName());
			// Check if updated Role is already exists
			if (dtoExist != null && dtoExist.getId() != dto.getId()) {
				throw new DuplicateRecordException("Duplicate Name");
			}
			dao.update(dto);
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
	}

	/**
	 * Delete a Role
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(RoleDTO dto) throws ApplicationException {

		try {
			RoleDTO dtoExist = findByPK(dto.getId());
			if (dtoExist == null) {
				throw new ApplicationException("Role does not exist");
			}
			dao.delete(dto);
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}

	}

	/**
	 * Find Role by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public RoleDTO findByName(String name) throws ApplicationException {

		RoleDTO dto;
		try {
			dto = dao.findByName(name);
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return dto;
	}

	/**
	 * Find Role by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public RoleDTO findByPK(long pk) throws ApplicationException {
		System.out.println("in findByPk service");
		RoleDTO dto;
		try {
			dto = dao.findByPK(pk);
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return dto;
	}

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
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public List search(RoleDTO dto, int pageNo, int pageSize)
			throws ApplicationException {
		List list;
		try {
			list = dao.search(dto, pageNo, pageSize);
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return list;
	}

	/**
	 * Search Roles
	 * 
	 * @return list : List of Roles
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public List search(RoleDTO dto) throws ApplicationException {
		List list;
		try {
			list = dao.search(dto);
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return list;
	}

	/**
	 * Get List of Roles
	 * 
	 * @return list : List of Roles
	 * @throws ApplicationException
	 */
	public List list() throws ApplicationException {
		List list;
		try {
			list = dao.list();
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return list;
	}

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
	public List list(int pageNo, int pageSize) throws ApplicationException {

		List list;
		try {
			list = dao.list(pageNo, pageSize);
		} catch (DatabaseException e) {
			throw new ApplicationException("Database Exception");
		}
		return list;
	}

}
