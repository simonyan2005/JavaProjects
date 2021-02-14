import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Location;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import java.io.IOException;
import java.util.List;

public class Bot {

    private static final String TELEGRAM_TOKEN = "b15d1f1b-10a9-4496-9925-6ddcfecc0071";
    private static final String YANDEX_TOKEN = "1507512620:AAEY0q_yrWuiJElvxQvfsre5t2sFM8EdQAo";
    private static int offset = 0;
    private static String answer = "";

    public static void main(String[] args) throws IOException {
        TelegramBot bot = new TelegramBot(YANDEX_TOKEN);
        WeatherService weatherService = new WeatherService(TELEGRAM_TOKEN);


    while(true){

        GetUpdates getUpdates = new GetUpdates().limit(1).offset(offset).timeout(0);//offset(0 or 1) read or not
        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();

        if (!updates.isEmpty()) {


        Update update = updates.get(0);
        Message message = update.message();


        if ("/start".equals(message.text())) {
            answer = "Здраствуй , я БОТ который рад тебе помочь знать прогноз погоды . ☀ \n Отправь мне свою геолокацию !";

        }
        else if(message.location() != null){
            Location location = message.location();
            Weather weather = weatherService.getWeather(location.latitude() , location.longitude());
            answer = "Погода на сегодня: \n" +
                     "Температура: " + weather.getFact().getTemp() + " \uD83C\uDF21 \n" +
                     "Осущается как: " + weather.getFact().getFeels_like() + " ☁ \n" +
                     "Скорость ветра: " + weather.getFact().getWind_speed() + " \uD83C\uDF2B \n" +
                     "Давление: " + weather.getFact().getPressure_mn() + "\n" +
                     "Подробнее: " + weather.getInfo().getUrl();
        }
        else {
            answer = "unknown command!";
        }

        SendMessage sendMessage = new SendMessage(message.chat().id(), answer);
        bot.execute(sendMessage);
        offset = update.updateId() + 1; //read


    }}
            }
        }

