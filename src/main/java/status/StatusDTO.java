package status;

public class StatusDTO {
	String cityName, pm10, pm25;

	public StatusDTO() {}
	
	
	public StatusDTO(String cityName, String pm10, String pm25) {
		super();
		this.cityName = cityName;
		this.pm10 = pm10;
		this.pm25 = pm25;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPm10() {
		return pm10;
	}

	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}

	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	
	
	
}
