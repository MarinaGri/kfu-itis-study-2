package ru.itis.weather_hw_14;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class WeatherForecastGetter {

    public String getWeather(String str) throws  IllegalArgumentException, UnknownHostException, IOException{
        URLConnection conn = new URL("https://www.metaweather.com/api/location/search/?query=" + str).openConnection();
        BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        Gson gson = new Gson();
        JsonArray cityData = gson.fromJson(r, JsonArray.class);
        int numOfCity = 0;

        StringBuilder sb = new StringBuilder();
        for(JsonElement cityItem : cityData) {
            JsonObject cityItemObj = cityItem.getAsJsonObject();
            String id  = cityItemObj.get("woeid").getAsString();
            URLConnection conn2 = new URL("https://www.metaweather.com/api/location/" + id + "/").openConnection();
            BufferedReader r2 = new BufferedReader(new InputStreamReader(conn2.getInputStream(), "UTF-8"));
            JsonObject data = gson.fromJson(r2, JsonObject.class);
            JsonArray weatherData = data.getAsJsonArray("consolidated_weather");
            String cityName = cityItemObj.get("title").getAsString().replace('"', ' ');
            createTitle(sb, cityName);
            numOfCity = createWeatherText(sb, weatherData);
        }
        if(numOfCity == 0){
            throw new IllegalArgumentException("City not found");
        }
        return sb.toString();
    }

    private void createTitle(StringBuilder sb, String name){
        for(int i = 0; i < (46-name.length())/2; i++) {
            sb.append("-");
        }
        sb.append(name);
        for(int i = 0; i < (46-name.length())/2; i++) {
            sb.append("-");
        }
        sb.append("\n");
    }

    private int createWeatherText(StringBuilder sb, JsonArray weatherData){
        int counter = 0;
        for(JsonElement weatherItem : weatherData) {
            JsonObject weatherItemObj = weatherItem.getAsJsonObject();
            sb.append("Date: ").append(weatherItemObj.get("applicable_date").getAsString()).append("\n");

            double minTemp = Math.floor(weatherItemObj.get("min_temp").getAsDouble()*100)/100.0;
            double maxTemp = Math.floor(weatherItemObj.get("max_temp").getAsDouble()*100)/100.0;

            sb.append("Temp: ").append(minTemp).append(" - ").append(maxTemp).append("\n");
            sb.append(weatherItemObj.get("weather_state_name")).append("\n\n");
            counter++;
        }
        return counter;
    }
}
