package getweather;

public class TranslateXY {
    // LCC DFS 좌표변환을 위한 기초 자료
    //
    double RE = 6371.00877; // 지구 반경(km)
    double GRID = 5.0;      // 격자 간격(km)
    double SLAT1 = 30.0;    // 투영 위도1(degree)
    double SLAT2 = 60.0;    // 투영 위도2(degree)
    double OLON = 126.0;    // 기준점 경도(degree)
    double OLAT = 38.0;     // 기준점 위도(degree)
    double XO = 43;         // 기준점 X좌표(GRID)
    double YO = 136;        // 기1준점 Y좌표(GRID)
    TranslatexyVO tvo = new TranslatexyVO();
    //
    // LCC DFS 좌표변환 ( code : "toXY"(위경도->좌표, v1:위도, v2:경도), "toLL"(좌표->위경도,v1:x, v2:y) )
    //
    public TranslatexyVO getTransXY (double v1, double v2){
        double DEGRAD = Math.PI / 180.0;
        double RADDEG = 180.0 / Math.PI;
        String code = "toXY";
        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon  = OLON  * DEGRAD;
        double olat  = OLAT  * DEGRAD;

        double sn = Math.tan( Math.PI*0.25 + slat2*0.5 ) / Math.tan( Math.PI*0.25 + slat1*0.5 );
        sn = Math.log( Math.cos(slat1) / Math.cos(slat2) ) / Math.log(sn);
        double sf = Math.tan( Math.PI*0.25 + slat1*0.5 );
        sf = Math.pow(sf,sn) * Math.cos(slat1) / sn;
        double ro = Math.tan( Math.PI*0.25 + olat*0.5 );
        ro = re * sf / Math.pow(ro,sn);
        if (code == "toXY")
        {
            tvo.setLat(v1);
            tvo.setLng(v2);
            double ra = Math.tan( Math.PI*0.25 + (v1)*DEGRAD*0.5 );
            ra = re * sf / Math.pow(ra,sn);
            double theta = v2 * DEGRAD - olon;
            if (theta >  Math.PI) theta -= 2.0 * Math.PI;
            if (theta < -Math.PI) theta += 2.0 * Math.PI;
            theta *= sn;
            tvo.setX((int) Math.floor( ra*Math.sin(theta) + XO + 0.5 ));;
            tvo.setY((int) Math.floor( ro - ra*Math.cos(theta) + YO + 0.5 ));
        }

        return tvo;
    }
    //-->
}
