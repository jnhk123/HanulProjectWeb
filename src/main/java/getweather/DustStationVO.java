package getweather;

import java.io.Serializable;

public class DustStationVO implements Serializable {
    String addr;
    String stationName;
    double tm;

    public DustStationVO(String addr, String stationName, double tm) {
        this.addr = addr;
        this.stationName = stationName;
        this.tm = tm;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getTm() {
        return tm;
    }

    public void setTm(double tm) {
        this.tm = tm;
    }

}
