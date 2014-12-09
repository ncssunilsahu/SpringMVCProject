package in.co.sunrays.proj1.form;

/**
 * MarkSheet Form class of project. (1)It encapsulate MarkSheet attribute and
 * populate data between Form and Controller
 * 
 * (2)Apply Validation at MarkSheet Form
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */

public class MarksheetForm extends BaseForm {

	/**
	 * Rollno of Student
	 */
	private String rollNo;
	/**
	 * ID of Student
	 */
	private long studentId;
	/**
	 * Name of Student
	 */
	private String name;
	/**
	 * Physics marks of Student
	 */
	private Integer physics;
	/**
	 * Chemistry marks of Student
	 */
	private Integer chemistry;
	/**
	 * Mathematics marks of Student
	 */
	private Integer maths;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
