
package com.example.android.theweatherapp3;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Raushan on 10/24/2016.
 */

public class DownloadTask extends AsyncTask<String,Void,String>{



    @Override
    protected String doInBackground(String... urls) {


        String result ="";
        URL url;
        HttpURLConnection urlConnection =null;


        try {
            url =new URL(urls[0]);

            urlConnection =(HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data !=-1){

                char current = (char)data;
                result += current;
                data = reader.read();
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject jsonObject =new JSONObject(result);
            String weatherInfo = jsonObject.getString("weather");
            JSONObject weatherData =new JSONObject((String) jsonObject.get("main"));

            Double temperature =Double.parseDouble(weatherData.getString("temp"));

            int temperatureInteger =(int) (temperature*1.8-459.67);
            String placeName = jsonObject.getString("name");

            MainActivity.temperatureTextView.setText(String.valueOf(temperature));

            MainActivity.placceTextView.setText(placeName);





        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
