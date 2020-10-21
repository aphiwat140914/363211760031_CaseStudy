import org.json.JSONException;
import org.json.JSONObject;
import sun.java2d.pipe.BufferedRenderPipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExchangeAPI {
    //class attributes
    private String result;
    private int time_last_update_unix;
    private String time_last_update_utc;
    private int time_next_Update_unix;
    private String time_next_update_utc;
    private String base_code;
    private JSONObject eachrate;

    private static String url_API="https://v6.exchangerate-api.com/v6/6997101b86739a7d69e7dc98/latest/";
    private static  JSONObject jsonObject;

    public String getResult() {
        return result;
    }

    public String getBase_code() {
        return base_code;
    }

    public JSONObject getEachrate() {
        return eachrate;
    }

    ///connect to sever
    public boolean getConnection(String base_code){
        String json = "";
        URL url = null;
        HttpURLConnection request = null;

        try {
            url = new URL(url_API+base_code);
            request = (HttpURLConnection) url.openConnection();
            //connect sever
            request.connect();
            //read data frome sver
            BufferedReader reader =new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line.length()>0){
                json += line;
            }

            //json to jsonobject
            jsonObject = new JSONObject(json);
            if (jsonObject == null){
                return false ;
            }
            this.result = jsonObject.getString("result");
            this.base_code = jsonObject.getString("base_code");
            this.eachrate = jsonObject.getJSONObject("conversion_rates");



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;

    }//getConnection

    public String getresult() {
        return result;
    }

    public String getbase_code() {
        return base_code;
    }
    public JSONObject getEachRate() {
        return eachrate;
    }

    public double getEachRate(String newCurrency) {
        double eachRate = 0.0;
        try {
            eachRate = this.eachrate.getDouble(newCurrency);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eachRate;
    }



//class
}
