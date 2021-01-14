package sdyu.sx.entity;


public class Category {
		private int gid;//分类的id
		private String name;//分类名称
		private int state;//分类的状态码  1 启用 2停用
		private int order_number;//排序字段  数字越大分类排名在首页面展示越靠前
		private String description;//分类描述
		private String create_time; //创建时间
		public int getGid() {
			return gid;
		}
		public void setGid(int gid) {
			this.gid = gid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public int getOrder_number() {
			return order_number;
		}
		public void setOrder_number(int order_number) {
			this.order_number = order_number;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getCreate_time() {
			return create_time;
		}
		public void setCreate_time(String create_time) {
			this.create_time = create_time;
		}
		public Category(int gid, String name, int state, int order_number, String description, String create_time) {
			super();
			this.gid = gid;
			this.name = name;
			this.state = state;
			this.order_number = order_number;
			this.description = description;
			this.create_time = create_time;
		}
		public Category() {
			super();
		}
		
		
}
