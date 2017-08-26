package cn.ssmc.entity;

import java.util.List;

public class PageBean {

	private int pageSize = 5;
	private int cPage = 1;
	private long maxSize = 0;
	private int maxPage = 0;
	
	

	private List<?> list;
	
	
	
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public PageBean(int cPage) {
		super();
		this.cPage = cPage;
	}

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getcPage() {
		return cPage;
	}

	public void setcPage(int cPage) {
		this.cPage = cPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	

}
