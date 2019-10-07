package com.example.myapplication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class DataCtrl {
    private String mTime;
    private String mTemperature;
    private String mB1;
    private String mB2;
    private String mB3;
    private String cond_code;
    private String mini;

    private int mCondition;
    public static DataCtrl fromJson(JSONObject response1,int state) {

        // JSON parsing is risky business. Need to surround the parsing code with a try-catch block.
        try {

            DataCtrl weatherData=new DataCtrl();
            if(state==1){
                JSONArray array=response1.getJSONArray("HeWeather6");
                JSONObject array2=array.getJSONObject(0);
                JSONObject basic=array2.getJSONObject("basic");
                JSONObject now=array2.getJSONObject("now");
                JSONObject update=array2.getJSONObject("update");

                weatherData.cond_code="i"+updateWeatherIcon(now.getInt("cond_code"));
                Log.d("cond_code","Success(in)! code: " + weatherData.cond_code);
                weatherData.mTemperature = now.getString("tmp");
                weatherData.mTime="     "+basic.getString("location")+"     "+update.getString("loc");
                Log.d("mTime","Success(in)! time: " + weatherData.mTime);

                weatherData.mini= now.getString("cond_txt")+"(实时)";
                weatherData.mB3 = now.getString("wind_dir")+now.getString("wind_sc")+"级";
            }

            if(state==2){
                JSONArray array=response1.getJSONArray("HeWeather6");
                JSONObject array2=array.getJSONObject(0);
                JSONArray daily_forecast=array2.getJSONArray("daily_forecast");
                JSONObject array3=daily_forecast.getJSONObject(0);
                weatherData.mB2 = array3.getString("cond_txt_d");
                weatherData.mB1=array3.getString("tmp_min")+"~"+array3.getString("tmp_max")+"℃";
            }



            return weatherData;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String updateWeatherIcon( int cond_code) {
        if(cond_code==100) return "100";
        if(cond_code==101) return "101";
        if(cond_code==102) return "102";
        if(cond_code==103) return "103";
        if(cond_code==104) return "104";
        if(200<=cond_code&&cond_code<300) return "203";
        if(300<=cond_code&&cond_code<400) return "301";
        if(400<=cond_code&&cond_code<500) return "402";
        if(500<=cond_code&&cond_code<600) return "507";
        return "i999";
    }




    public String getTemperature() {
        return mTemperature;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmB1() {
        return mB1;
    }
    public String getmB2() {
        return mB2;
    }

    public String getmB3() {
        return mB3;
    }

    public String getmIconName() {
        return cond_code;
    }

    public String getMini() {
        return mini;
    }



}
