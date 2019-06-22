package status;

public class StatusVO {
	private String id, weather;
	private String light, secure, window, gas, boiler, door, autoWindow;
	private int water, temper, dust;
	private String addr;
	
	public String getAutoWindow() {
		return autoWindow;
	}
	public void setAutoWindow(String autoWindow) {
		this.autoWindow = autoWindow;
	}
	public String getDoor() {
		return door;
	}
	public void setDoor(String door) {
		this.door = door;
	}
	public String getGas() {
		return gas;
	}
	public void setGas(String gas) {
		this.gas = gas;
	}
	public String getBoiler() {
		return boiler;
	}
	public void setBoiler(String boiler) {
		this.boiler = boiler;
	}
	public String getWindow() {
		return window;
	}
	public void setWindow(String window) {
		this.window = window;
	}

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLight() {
		return light;
	}
	public void setLight(String light) {
		this.light = light;
	}
	public String getSecure() {
		return secure;
	}
	public void setSecure(String secure) {
		this.secure = secure;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public int getWater() {
		return water;
	}
	public void setWater(int water) {
		this.water = water;
	}
	public int getTemper() {
		return temper;
	}
	public void setTemper(int temper) {
		this.temper = temper;
	}
	public int getDust() {
		return dust;
	}
	public void setDust(int dust) {
		this.dust = dust;
	}
	
}
