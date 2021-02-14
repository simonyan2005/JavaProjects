import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class WeatherService {


    private static String URL = "https://api.weather.yandex.ru/v2/forecast?lat=%s&lon=%s&lang=ru-RU";
    private final String token;
    private final OkHttpClient client = new OkHttpClient();

    WeatherService(String token){
        this.token = token;
    }


    public Weather getWeather(double lat , double lon) throws IOException {


        Request request = new Request.Builder()
                .url(String.format(URL , lat , lon))
                .header("X-Yandex-API-Key" , this.token)
                .build();

        try (Response response = client.newCall(request).execute()) {

            Gson mapper = new Gson();
            return mapper.fromJson(response.body().string() , Weather.class);
        }


    }



    public static void main(String[] args) throws IOException {

            WeatherService ws = new WeatherService("b15d1f1b-10a9-4496-9925-6ddcfecc0071");
            System.out.println(ws.getWeather(40.177628, 44.512546));
        }



    @Override
    public String toString() {
        return "WeatherService{" +
                "token='" + token + '\'' + '}';
    }
}

