package com.track.currencybot.currency_track_bot.client;

import com.track.currencybot.currency_track_bot.exception.ServiceException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class Client {

    @Autowired
    private OkHttpClient client;

    public Optional<String> getCurrencyTrack(String url) throws ServiceException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new ServiceException("Ошибка при получении данных: " + response.message());
            }

            return Optional.ofNullable(response.body()).map(body -> {
                try {
                    return body.string();
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка чтения ответа", e);
                }
            });
        } catch (IOException e) {
            throw new ServiceException("Ошибка выполнения запроса", e);
        }
    }
}

