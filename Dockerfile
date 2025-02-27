# Используем официальный образ OpenJDK 21
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл сборки в контейнер
COPY target/currency-track-bot.jar app.jar

# Указываем переменную окружения для токена бота
ENV BOT_TOKEN=""

# Открываем порт (необязательно, если бот не работает через вебхук)
EXPOSE 8080

# Запускаем бота
CMD ["java", "-jar", "app.jar"]
