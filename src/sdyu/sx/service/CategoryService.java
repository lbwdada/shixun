package sdyu.sx.service;

import java.util.List;

import sdyu.sx.dao.CategoryDao;
import sdyu.sx.entity.Category;
import sdyu.sx.entity.PageBean;
/*
 * 分类的业务层
 */
public class CategoryService {
	private CategoryDao dao = new CategoryDao();

	/*
	 * 分页查询分类业务
	 */
	public PageBean<Category> findAllByPage(String currentPage, String pageSize) {
		//设置默认值
		if(currentPage == null || currentPage.trim().equals("")) {
			currentPage = "1";
		}
		if(pageSize == null || pageSize.trim().equals("")) {
			pageSize = "3";
		}
		
		PageBean<Category> pb = new PageBean<Category>();
		
		//将获取到的值转换为int,并添加到pagebean中
		int cpage = Integer.parseInt(currentPage);
		int size = Integer.parseInt(pageSize);
		pb.setCurrentPage(cpage);
		pb.setPageSize(size);
		
		//根据用户想看的页码 和size 查询信息
		List<Category> list = dao.findCategoryByLimit(cpage,size);
		pb.setList(list);
		
		//查询总条数
		int count=	dao.findCategoryByCount();
				
		pb.setTotalCount(count);
		
		//总页数=总条数%每页显示条数==0？总条数/每页显示条数:总条数/每页显示条数+1;
		
		int totalPage = count%size==0?count/size:count/size+1;
				
		pb.setTotalPage(totalPage);
				
				
		return pb;
	}

	/*
	 * 添加分类业务
	 */
	public int addCategory(Category category) {
		return dao.addCategory(category);
	}

	/*
	 * 批量删除业务
	 */
	public int deleteAllSelected(String ids) {
		return dao.deleteAllSelected(ids);
	}

	/*
	 * 根据id查询分类信息业务
	 */
	public Category findCategoryByGid(String gid) {
		return dao.findCategoryByGid(gid);
	}

	/*
	 * 更新分类业务
	 */
	public int updateCategory(Category category) {
		return dao.updateCategory(category);
	}

	/*
	 * 查询所有分类
	 */
	public List<Category> findAllCategory() {
		return dao.findAllCategory();
	}

	/*
	 * 获取首页分类
	 */
	public List<Category> getIndexCategory() {
		return dao.getIndexCategory();
	}

}
