package in.co.sunrays.proj1.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * MarkSheet JavaBean encapsulates MarkSheet attributes
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

@Entity
@Table(name = "st_marksheet")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class MarksheetDTO {

	/**
	 * Non Business primary key
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 20)
	protected long id = 0;
	/**
	 * Contains MarkSheet ID who created this database record
	 */

	@Column(name = "CREATED_BY", length = 50)
	protected String createdBy = null;
	/**
	 * Contains MarkSheet ID who modified this database record
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
	 * Name of Student
	 */
	@Column(name = "NAME", length = 100)
	private String name = null;

	/**
	 * RollNo of Student
	 */
	@Column(name = "ROLL_NO", length = 100)
	private String rollNo = null;

	/**
	 * Physics marks of Student
	 */
	@Column(name = "PHYSICS", length = 100)
	private Integer physics;

	/**
	 * Chemistry marks of Student
	 */
	@Column(name = "CHEMISTRY", length = 100)
	private Integer chemistry;

	/**
	 * Mathematics marks of Student
	 */
	@Column(name = "MATHS", length = 100)
	private Integer maths;

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

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}
}
