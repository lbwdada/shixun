package sdyu.sx.entity;
import java.util.List;

/*
 * 分页工具类
 */
public class PageBean<T> {
	
	private List<T> list;//用户想看的数据 
	
	
	private int currentPage;//当前页
	
	
	private int pageSize;//每页显示条数
	
	
	private int totalCount;//总记录数
	
	
	private int totalPage;//总页数


	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public PageBean(List<T> list, int currentPage, int pageSize, int totalCount, int totalPage) {
		super();
		this.list = list;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
	}


	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}



	
	
	
	
}
