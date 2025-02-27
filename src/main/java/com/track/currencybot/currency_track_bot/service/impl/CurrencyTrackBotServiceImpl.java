package com.track.currencybot.currency_track_bot.service.impl;

import com.track.currencybot.currency_track_bot.client.Client;
import com.track.currencybot.currency_track_bot.exception.ServiceException;
import com.track.currencybot.currency_track_bot.service.CurrencyTrackBotService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyTrackBotServiceImpl implements CurrencyTrackBotService {
    private static final String BTC_URL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd";
    private static final String ETH_URL = "https://api.coingecko.com/api/v3/simple/price?ids=ethereum&vs_currencies=usd";

    @Autowired
    private Client client;

    @Override
    public String getBTCCurrencyTrack() throws ServiceException {
        String jsonResponse = client.getCurrencyTrack(BTC_URL)
                .orElseThrow(() -> new ServiceException("Не удалось получить данные по Bitcoin"));
        return getCurrencyFromJSON(jsonResponse, "bitcoin");
    }

    @Override
    public String getETHCurrencyTrack() throws ServiceException {
        String jsonResponse = client.getCurrencyTrack(ETH_URL)
                .orElseThrow(() -> new ServiceException("Не удалось получить данные по Ethereum"));
        return getCurrencyFromJSON(jsonResponse, "ethereum");
    }

    private String getCurrencyFromJSON(String json, String currency) throws ServiceException {
        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getJSONObject(currency).get("usd").toString();
        } catch (Exception e) {
            throw new ServiceException("Ошибка при разборе JSON", e);
        }
    }
}
