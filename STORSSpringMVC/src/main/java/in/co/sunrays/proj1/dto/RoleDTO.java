package in.co.sunrays.proj1.dto;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Role JavaBean encapsulates Role attributes
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */
@Entity
@Table(name = "st_role")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class RoleDTO {

	/**
	 * Non Business primary key
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 20)
	protected long id = 0;
	/**
	 * Contains Role who created this database record
	 */

	@Column(name = "CREATED_BY", length = 50)
	protected String createdBy = null;
	/**
	 * Contains Role who modified this database record
	 */

	@Column(name = "MODIFIED_BY", length = 50)
	protected String modifiedBy = null;
	/**
	 * Contains Created Timestamp of database record
	 */

	@Column(name = "CREATED_DATETIME")
	protected Timestamp createdDatetime;
	/**
	 * Contains Modified Timestamp of database record
	 */

	@Column(name = "MODIFIED_DATETIME")
	protected Timestamp modifiedDatetime;

	/**
	 * Name of Role
	 */
	@Column(name = "NAME", length = 100)
	private String name = null;

	/**
	 * Address of College
	 */
	@Column(name = "DESCRIPTION", length = 255)
	private String description = null;
}
