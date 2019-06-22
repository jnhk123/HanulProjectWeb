package status;

public class AddrVO {
	String id;
	String addr[], addrs;
	String nx, ny;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getAddr() {
		return addr;
	}
	public void setAddr(String[] addr) {
		this.addr = addr;
		this.addrs = addr[0] + (addr[1].isEmpty()? addr[1]: "&nbsp;" + addr[1]) ;
	}
	
	public String getAddrs() {
		return addrs;
	}
	public void setAddrs(String addrs) {
		this.addrs = addrs;	
		addr = addrs.split("&nbsp;");
	}
	public String getNx() {
		return nx;
	}
	public void setNx(String nx) {
		this.nx = nx;
	}
	public String getNy() {
		return ny;
	}
	public void setNy(String ny) {
		this.ny = ny;
	}
	

}
