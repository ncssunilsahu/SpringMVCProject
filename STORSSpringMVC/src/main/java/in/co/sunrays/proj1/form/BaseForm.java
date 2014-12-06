package in.co.sunrays.proj1.form;

import java.util.List;

/**
 * Base Form class of project. It contain (1) Generic operations
 * 
 * @version 1.0
 * @since 16 Nov 2014
 * @author SUNRAYS Developer
 * @Copyright (c) sunRays Technologies. All rights reserved.
 * @URL www.sunrays.co.in
 */
public class BaseForm {

	/**
	 * Non business primary key
	 */
	protected long id = 0;
	/**
	 * for get dtoList
	 */
	private List dtoList;
	/**
	 * 
	 */
	private long[] ids;
	/**
	 * Initial page number for pagging
	 */
	private int pageNo = 1;
	/**
	 * Maximum page size for pagging
	 */
	private int pageSize = 5;

	/**
	 * UI operation value
	 */
	private String operation;

	/**
	 * UI Message
	 */
	private String message;

	/**
	 * accessor
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List getDtoList() {
		return dtoList;
	}

	public void setDtoList(List dtoList) {
		this.dtoList = dtoList;
	}

	public long[] getIds() {
		return ids;
	}

	public void setIds(long[] ids) {
		this.ids = ids;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
