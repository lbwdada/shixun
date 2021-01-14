package sdyu.sx.entity;

public class User {
	private int id; // 对象的表示
	private String name;//用户姓名
	private int sex;//性别
	private String phone_number;//手机号码
	private String area;//所在地址
	private int manager;// 是否是经理  默认0 员工  1 经理
	private String username;//用户名
	private String password;//密码
	private String photo;//头像
	private String create_time;//注册日期
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public User(int id, String name, int sex, String phone_number, String area, int manager, String username,
			String password, String photo, String create_time) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phone_number = phone_number;
		this.area = area;
		this.manager = manager;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.create_time = create_time;
	}
	public User() {
		super();
	}
}
