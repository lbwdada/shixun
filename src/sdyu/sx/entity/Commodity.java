package sdyu.sx.entity;

public class Commodity {
	private int cid; //商品id
	private int gid; //分类id
	private String name;//商品名称
	private String color;//商品颜色
	private String size;//商品大小
	private double price;//商品单价
	private String description;//商品描述
	private String full_description;//商品详细描述
	private String pic;//商品图片
	private int state;//商品状态  0 正常 1 热门  2新品  3为你推荐 4 小米明星单品
	private String version;//商品型号
	private String product_date;//商品的生产日期 	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFull_description() {
		return full_description;
	}
	public void setFull_description(String full_description) {
		this.full_description = full_description;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getProduct_date() {
		return product_date;
	}
	public void setProduct_date(String product_date) {
		this.product_date = product_date;
	}
	public Commodity(int cid, int gid, String name, String color, String size, double price, String description,
			String full_description, String pic, int state, String version, String product_date) {
		super();
		this.cid = cid;
		this.gid = gid;
		this.name = name;
		this.color = color;
		this.size = size;
		this.price = price;
		this.description = description;
		this.full_description = full_description;
		this.pic = pic;
		this.state = state;
		this.version = version;
		this.product_date = product_date;
	}
	public Commodity() {
		super();
	}

}
