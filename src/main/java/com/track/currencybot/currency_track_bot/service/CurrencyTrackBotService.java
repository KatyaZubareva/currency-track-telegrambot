package com.track.currencybot.currency_track_bot.service;

import com.track.currencybot.currency_track_bot.exception.ServiceException;

public interface CurrencyTrackBotService {

    String getBTCCurrencyTrack() throws ServiceException;

    String getETHCurrencyTrack() throws ServiceException;
}
