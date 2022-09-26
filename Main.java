package MySixthWork;

/*    Created by: Maxim.G, GU_QA_1883

   1. С помощью http запроса получить в виде json строки погоду в Санкт-Петербурге на период времени,
      пересекающийся со следующим занятием (например, выборка погода на следующие 5 дней - подойдет)
   2. Подобрать источник самостоятельно. Можно использовать api accuweather, порядок следующий: зарегистрироваться,
      зарегистрировать тестовое приложение для получения api ключа, найдите нужный endpoint и изучите документацию.
      Бесплатный тарифный план предполагает получение погоды не более чем на 5 дней вперед.
*/

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        // Экземпляр класса OkHttpClient выполняет всю работу по созданию и отправке запросов
        OkHttpClient okHttpClient = new OkHttpClient();

        // Собрал URL из множества сегментов с помощью класса HttpUrl.
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment("295212")
                .addQueryParameter("apikey", "zQvgpY3m37GPLzaFlGozx0va5p67tPHh")
                .addQueryParameter("language", "en")
                .addQueryParameter("details", "true")
                .addQueryParameter("metric", "true")
                .build();

        // Экземпляр класса Request создается через Builder (см. паттерн проектирования "Строитель")
        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("Content-Type", "application/json")
                .build();

        // Получение объекта ответа от сервера
        Response response = okHttpClient.newCall(request).execute();

        System.out.println(response.isSuccessful());
        System.out.println(response.code());
        System.out.println(response.headers());
        assert response.body() != null;
        System.out.println(response.body().string());
    }
}