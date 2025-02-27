# Currency Track Bot

Currency Track Bot — это Telegram-бот, который позволяет отслеживать актуальные курсы криптовалют (Bitcoin и Ethereum) в реальном времени.

## 📌 Функциональность
- `/start` — Начало работы с ботом, описание возможностей.
- `/btc` — Узнать текущий курс Bitcoin (BTC).
- `/eth` — Узнать текущий курс Ethereum (ETH).
- `/help` — Справка по командам бота.

## 🛠️ Технологии
- **Язык программирования**: Java 21
- **Фреймворк**: Spring Boot
- **API**: Telegram Bots API
- **Сборщик проекта**: Maven
- **Логирование**: SLF4J + Logback
- **Упаковка и развертывание**: Docker

## 📂 Структура проекта
```
.
├── src
│   ├── main
│   │   ├── java/com/track/currencybot
│   │   │   ├── bot          # Логика обработки команд
│   │   │   ├── service      # Сервисы получения данных
│   │   │   ├── exception    # Обработка ошибок
│   │   ├── resources
│   │   │   ├── application.properties # Конфигурационные файлы
├── Dockerfile               # Файл для сборки Docker-образа
├── pom.xml                  # Конфигурация Maven
└── README.md
```

## 🚀 Установка и запуск
### 1. Клонирование репозитория
```sh
git clone https://github.com/your-repo/currency-track-bot.git
cd currency-track-bot
```

### 2. Настройка переменных окружения
Создайте `.env` файл и укажите токен вашего бота:
```env
BOT_TOKEN=your_telegram_bot_token
```

### 3. Сборка и запуск локально
#### Сборка и запуск через Maven
```sh
./mvnw clean package
java -jar target/currency-track-bot.jar
```

### 4. Запуск в Docker
#### Сборка Docker-образа
```sh
docker build -t currency-track-bot .
```
#### Запуск контейнера
```sh
docker run -d --name currency-bot --env BOT_TOKEN=your_telegram_bot_token currency-track-bot
```

## 🛠 Разработка
### Запуск в режиме разработки
```sh
./mvnw spring-boot:run
```

### Логирование
Логи можно найти в консоли или файле `logs/app.log`, если он настроен в `application.properties`.

## 📜 Лицензия
Этот проект распространяется под лицензией MIT.

---

💡 **Примечание:** Убедитесь, что ваш бот зарегистрирован в [@BotFather](https://t.me/BotFather) и у него есть токен для работы.

