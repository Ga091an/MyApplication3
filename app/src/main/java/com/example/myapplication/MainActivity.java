package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView mTime;
    TextView mTemp;
    TextView mB2;
    TextView mB3;
    TextView mB1;
    TextView mini;
    ImageView mWeatherImage;

    final String WEATHER_URL = "https://free-api.heweather.net/s6/weather/now?location=beijing&key=b5d43eaf1d544c4bb7fd3b316689413e";
    final String WEATHER_URL2 = "https://free-api.heweather.net/s6/weather/forecast?location=beijing&key=b5d43eaf1d544c4bb7fd3b316689413e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTime = findViewById(R.id.time);
        mTemp = findViewById(R.id.tempL);
        mB1 = findViewById(R.id.tempB1);
        mB2 = findViewById(R.id.tempB2);
        mB3 = findViewById(R.id.tempB3);
        mini = findViewById(R.id.tempMini);
        mWeatherImage=findViewById((R.id.weatherSymbolIV));

        letsDoSomeNetworking();

    }
    private void getWeatherForNewCity(String city) {
        Log.d("Clima", "Getting weather for new city");
        letsDoSomeNetworking();
    }
    public void letsDoSomeNetworking() {

        // AsyncHttpClient belongs to the loopj dependency.
        AsyncHttpClient client = new AsyncHttpClient();
        //final DataCtrl weatherData = new DataCtrl();


        // Making an HTTP GET request by providing a URL and the parameters.
        /*RequestParams params = new RequestParams();
        params.put("appid","52162865");
        params.put("appsecret","3j2T82kz");
        params.put("version","52162865");
         */


        client.get(WEATHER_URL,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("response1","Success(in)! JSON: " + response.toString());

               DataCtrl mData=DataCtrl.fromJson(response,1);
               updateUI(mData,1);

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {

                Log.e("climate", "Fail " + e.toString());
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();

                Log.d("climate", "Status code " + statusCode);
                Log.d("climate", "Here's what we got instead " + response.toString());
            }

        });
        AsyncHttpClient client2 = new AsyncHttpClient();
        client2.get(WEATHER_URL2,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("response2","Success(in)! JSON: " + response.toString());
                DataCtrl mData=DataCtrl.fromJson(response,2);
                updateUI(mData,2);

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {

                //Log.e("climate", "Fail " + e.toString());
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();

                Log.d("climate", "Status code " + statusCode);
                Log.d("climate", "Here's what we got instead " + response.toString());
            }

        });
        //System.out.println(response1.toString());
        //Log.e("response2","Success(in)! JSON: " + response2.toString());
        //DataCtrl mData=DataCtrl.fromJson(response1,response2);
        //updateUI(mData);
        //Intent intent= new Intent(this,autoUpdate.class);
        //startService(intent);



    }
    private void updateUI(DataCtrl weather,int state) {
        if(state==1){
            mTime.setText(weather.getmTime());
            mTemp.setText(weather.getTemperature());
            mini.setText(weather.getMini());
            mB3.setText(weather.getmB3());
            int resourceID = getResources().getIdentifier(weather.getmIconName(), "drawable", getPackageName());
            mWeatherImage.setImageResource(resourceID);
        }
        if(state==2){
            mB1.setText(weather.getmB1());
            mB2.setText(weather.getmB2());

        }

    }
    /*private void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                }
            }
        })
    }

     */


}
