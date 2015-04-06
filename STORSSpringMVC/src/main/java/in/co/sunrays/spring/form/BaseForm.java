package in.co.sunrays.spring.form;

import java.util.List;

public class BaseForm {

	protected long id = 0;
	private List dtoList;
	private long[] ids;
	private int pageNo = 1;
	private int pageSize = 5;
	private String operation;

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

}
