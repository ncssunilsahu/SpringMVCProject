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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
