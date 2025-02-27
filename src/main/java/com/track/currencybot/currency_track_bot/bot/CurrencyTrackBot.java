package com.track.currencybot.currency_track_bot.bot;

import com.track.currencybot.currency_track_bot.exception.ServiceException;
import com.track.currencybot.currency_track_bot.service.CurrencyTrackBotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class CurrencyTrackBot extends TelegramLongPollingBot {

    public static final Logger LOG = LoggerFactory.getLogger(CurrencyTrackBot.class);

    public static final String START = "/start";
    public static final String BTC = "/btc";
    public static final String ETH = "/eth";
    public static final String HELP = "/help";

    @Autowired
    private CurrencyTrackBotService currencyTrackBotService;

    public CurrencyTrackBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        switch (message) {
            case START -> startCommand(chatId);
            case BTC -> btcCommand(chatId);
            case ETH -> ethCommand(chatId);
            case HELP -> helpCommand(chatId);
            default -> unknownCommand(chatId);
        }
    }

    @Override
    public String getBotUsername() {
        return "currency_track_bot";
    }

    private void startCommand(Long chatId) {
        String message = """
        ✨ Приветствую тебя, крипто-искатель! 🚀

        Я — бот, который поможет тебе следить за курсами криптовалют в реальном времени! 📊💰
        
        Вот что я умею:  
        🔹 /btc — Узнать текущий курс Bitcoin  
        🔹 /eth — Узнать текущий курс Ethereum  
        🔹 /help — Получить справку по использованию бота

        ⚡ Просто отправь команду, и я мгновенно покажу тебе актуальные данные!  

        🔔 Совет: Добавь меня в избранное, чтобы всегда быть в курсе изменений рынка! 
        """;
        sendMessage(chatId, message);
    }

    private void btcCommand(Long chatId) {
        try {
            String btc = currencyTrackBotService.getBTCCurrencyTrack();
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

            String message = """
            🟠 Bitcoin (BTC)  
            📅 Дата: %s  
            💰 Текущий курс: %s USD  
            
            🔍 Данные получены в реальном времени!
        """.formatted(date, btc);

            sendMessage(chatId, message);
        } catch (ServiceException e) {
            LOG.error("Ошибка получения курса BTC", e);
            sendMessage(chatId, "❌ Ошибка при получении курса Bitcoin (BTC). Попробуйте позже.");
        }
    }

    private void ethCommand(Long chatId) {
        try {
            String eth = currencyTrackBotService.getETHCurrencyTrack();
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

            String message = """
            🔷 Ethereum (ETH)  
            📅 Дата: %s  
            💰 Текущий курс: %s USD  
            
            🔍 Данные получены в реальном времени!
        """.formatted(date, eth);

            sendMessage(chatId, message);
        } catch (ServiceException e) {
            LOG.error("Ошибка получения курса ETH", e);
            sendMessage(chatId, "❌ Ошибка при получении курса Ethereum (ETH). Попробуйте позже.");
        }
    }

    private void helpCommand(Long chatId) {
        String message = """
        ℹ️ Помощь по командам ℹ️  

        ✨ Вот список доступных команд:  
        🔹 /start — Начало работы с ботом 
        🔹 /btc — Узнать курс Bitcoin (BTC)
        🔹 /eth — Узнать курс Ethereum (ETH)  
        🔹 /help — Показать справку 

        🔔 Совет: Добавьте меня в избранные чаты, чтобы всегда быть в курсе изменений курсов криптовалют!  
        """;

        sendMessage(chatId, message);
    }

    private void unknownCommand(Long chatId) {
        String message = """
        ❌ Неизвестная команда ❌  

        Я не знаю такой команды. 😔  
        Используйте /help, чтобы посмотреть список доступных команд. ℹ️  
        """;

        sendMessage(chatId, message);
    }

    private void sendMessage(Long chatId, String message) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }

}
