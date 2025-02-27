package com.track.currencybot.currency_track_bot.configuration;

import com.track.currencybot.currency_track_bot.bot.CurrencyTrackBot;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class CurrencyTrackBotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(CurrencyTrackBot currencyTrackBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(currencyTrackBot);
        return api;
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
