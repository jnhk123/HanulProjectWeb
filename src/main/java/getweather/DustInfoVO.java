package getweather;

public class DustInfoVO {
    int pm10Grade,pm10Value, pm25Grade, pm25Value;
    String station;

    public DustInfoVO(int pm10Grade, int pm25Grade) {
        this.pm10Grade = pm10Grade;
        this.pm25Grade = pm25Grade;
    }

    public DustInfoVO(int pm10Grade, int pm10Value, int pm25Grade, int pm25Value) {
        this.pm10Grade = pm10Grade;
        this.pm10Value = pm10Value;
        this.pm25Grade = pm25Grade;
        this.pm25Value = pm25Value;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getPm10Grade() {
        return pm10Grade;
    }

    public void setPm10Grade(int pm10Grade) {
        this.pm10Grade = pm10Grade;
    }

    public int getPm10Value() {
        return pm10Value;
    }

    public void setPm10Value(int pm10Value) {
        this.pm10Value = pm10Value;
    }

    public int getPm25Grade() {
        return pm25Grade;
    }

    public void setPm25Grade(int pm25Grade) {
        this.pm25Grade = pm25Grade;
    }

    public int getPm25Value() {
        return pm25Value;
    }

    public void setPm25Value(int pm25Value) {
        this.pm25Value = pm25Value;
    }
}
