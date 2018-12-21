package db;
//定义一个用户类
public class PersonInfo {
	private String sid ;
	private String sname ;
	private String sex;
	private String company;
	private String zhiwei ;
	private String phone ;
	private String pQ ;
	private String weixin;
	
	
	
	
	@Override
	public String toString() {
		return sname;
	}

	public PersonInfo(String sid, String sname, String sex, String company, String zhiwei, String phone, String pQ,
			String weixin) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sex = sex;
		this.company = company;
		this.zhiwei = zhiwei;
		this.phone = phone;
		this.pQ = pQ;
		this.weixin = weixin;
	}

	public PersonInfo() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getZhiwei() {
		return zhiwei;
	}

	public void setZhiwei(String zhiwei) {
		this.zhiwei = zhiwei;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getpQ() {
		return pQ;
	}

	public void setpQ(String pQ) {
		this.pQ = pQ;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	

}