package status;

public class GetUserVO {
	private String id, pw, name, addr, phone;
	private char admin, sell, isdel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public char getAdmin() {
		return admin;
	}
	public void setAdmin(char admin) {
		this.admin = admin;
	}
	public char getSell() {
		return sell;
	}
	public void setSell(char sell) {
		this.sell = sell;
	}
	public char getIsdel() {
		return isdel;
	}
	public void setIsdel(char isdel) {
		this.isdel = isdel;
	}
	
	
}
