package getweather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetDust{

    double geoX, geoY;

    public GetDust(double geoX, double geoY) {
        this.geoX = geoX;
        this.geoY = geoY;
    }

    //경위도 tm좌표 변환
    GeoPoint location = new GeoPoint(geoY, geoX);
    GeoPoint tm_pt = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, location);
    //Log.d("abc", "" + tm_pt.getX() + tm_pt.getY());
    double tmX = tm_pt.getX(), tmY = tm_pt.getY();

    BufferedReader br = null, br2= null;
    String result1 = "", result2 = "";
    ArrayList<DustStationVO> stationList = new ArrayList<DustStationVO>();
    ArrayList<DustInfoVO> last3hdustList = new ArrayList<DustInfoVO>();
    
    DustInfoVO vo = null;
    String key = "EJmGtsKFK8swoD%2FB3ayPRQswYDu25icg76t6HqcxEF1fX%2B5A4nek3ruj2fJTqImB9Ok6z3TVUcK5AVFVZjdT0Q%3D%3D";
    String key2 = "KuLofqZV%2FXoxpdyLPmuOBb7AiZSXLsQAidCeoUjMD%2FtVne8jO268jYBPdtHHW%2FJ%2Fp%2BSGQvvbORFf6EfG8S6eaw%3D%3D";
    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    //관측소에 따른 미세먼지 조회
    public ArrayList<DustInfoVO> SearchDustInfo() {
        SearchStationName();
        for(int i = 0; i < 3; i++) {
            String url_searchDust = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty" +
                    "?stationName=" + stationList.get(i).getStationName() +
                    "&dataTerm=daily&pageNo=1&numOfRows=3" +
                    "&ServiceKey=" + key +
                    "&ver=1.3&_returnType=json";

            try {
                //MultipartEntityBuild  생성
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

                //전송
                InputStream inputStream = null;
                httpPost = new HttpPost(url_searchDust);
                httpPost.setEntity(builder.build());

                httpResponse = httpClient.execute(httpPost);
                httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

                BufferedReader br2 = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = br2.readLine()) != null) {
                    result2 = result2 + line + "\n";
                }
                result2 = result2.substring(result2.indexOf("["), result2.indexOf("]") + 1).trim();

                int pm10sum = 0;
                int pm25sum = 0;
                int pm10gsum = 0;
                int pm25gsum = 0;

                int cnt = 0;
                JSONArray jsonArray = new JSONArray(result2);
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject row = jsonArray.getJSONObject(j);
                    int pm10Grade = row.getInt("pm10Grade");
                    int pm10Value = row.getInt("pm10Value");
                    int pm25Grade = row.getInt("pm25Grade");
                    int pm25Value = row.getInt("pm25Value");

                    if (pm10Grade != 0 && pm10Value != 0 && pm25Grade != 0 && pm25Value != 0){
                        pm10sum += pm10Value;
                        pm25sum += pm25Value;
                        pm10gsum += pm10Grade;
                        pm25gsum += pm25Grade;
                        cnt++;
                    }
                }

                vo = new DustInfoVO((pm10gsum/cnt), (pm10sum/cnt), (pm25gsum/cnt), (pm25sum/cnt));
                vo.setStation(stationList.get(i).getStationName());
                //Log.d("abc", "" + vo.getPm10Grade());

            } catch (Exception e) {
                e.getMessage();
            } finally {
                if (httpEntity != null) {
                    httpEntity = null;
                }
                if (httpResponse != null) {
                    httpResponse = null;
                }
                if (httpPost != null) {
                    httpPost = null;
                }
                if (httpClient != null) {
                    httpClient = null;
                }
            }
            last3hdustList.add(vo);
        }
        return last3hdustList;
    }


        //SearchStationName();
        //Log.d("abc", stationList.get(0).getStationName() );
        /*String stationName = "백령도";
        try{
//            for (int i = 0; i < 3; i++) {
                String url_searchDust = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty" +
                        "?stationName=" + stationName +
                        "&dataTerm=daily&pageNo=1&numOfRows=1" +
                        "&ServiceKey=" + key +
                        "&ver=1.3&_returnType=json";
                Log.d("url", url_searchDust);

                URL url = new URL(url_searchDust);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                br2 = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String line;
                while ((line = br2.readLine()) != null) {
                    result2 = result2 + line + "\n";
                }
                result2 = result2.substring(result2.indexOf("["), result2.indexOf("]") + 1).trim();

                DustInfoVO vo = null;

                JSONArray jsonArray = new JSONArray(result2);
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject row = jsonArray.getJSONObject(j);
                    int pm10Grade = row.getInt("pm10Grade");
                    int pm25Grade = row.getInt("pm25Grade");
                    vo = new DustInfoVO(pm10Grade, pm25Grade);
                    last3hdustList.add(vo);
                }
            //}
        } catch (Exception e){
            Log.d("Error", e.getMessage());
        }*/


    //관측소 조회
    public ArrayList<DustStationVO> SearchStationName() {
        try {
            String url_searchObserv = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList" +
                    "?tmX=" + tmX +
                    "&tmY=" + tmY +
                    "&pageNo=1&numOfRows=10" +
                    "&ServiceKey=" + key2 +
                    "&_returnType=json";
            URL url = new URL(url_searchObserv);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                result1 = result1 + line + "\n";
            }
            //리스트 제외한 나머지 버림
            result1 = result1.substring(result1.indexOf("["), result1.indexOf("]") + 1).trim();

            //Log.d("abc", result);

            DustStationVO vo = null;

            //주변 관측소 파싱
            JSONArray jsonArray = new JSONArray(result1);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                String addr = row.getString("addr");
                String stationName = row.getString("stationName");
                double tm = row.getDouble("tm");

                vo = new DustStationVO(addr, stationName, tm);
                stationList.add(vo);
            }
            //Log.d("abc", "" + stationList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stationList;
    }
}

