# Currency Track Bot

Currency Track Bot is a Telegram bot that allows users to track real-time cryptocurrency exchange rates (Bitcoin and Ethereum).

## 📌 Features
- `/start` — Start working with the bot and view available features.
- `/btc` — Get the current Bitcoin (BTC) exchange rate.
- `/eth` — Get the current Ethereum (ETH) exchange rate.
- `/help` — Display help and available commands.

## 🛠️ Technologies
- **Programming Language**: Java 21
- **Framework**: Spring Boot
- **API**: Telegram Bots API
- **Build Tool**: Maven
- **Logging**: SLF4J + Logback
- **Containerization & Deployment**: Docker

## 📂 Project Structure
```
.
├── src
│   ├── main
│   │   ├── java/com/track/currencybot
│   │   │   ├── bot          # Command processing logic
│   │   │   ├── service      # Data retrieval services
│   │   │   ├── exception    # Error handling
│   │   ├── resources
│   │   │   ├── application.properties # Configuration files
├── Dockerfile               # Docker build file
├── pom.xml                  # Maven configuration
└── README.md
```

## 🚀 Installation & Running
### 1. Clone the Repository
```sh
git clone https://github.com/your-repo/currency-track-bot.git  
cd currency-track-bot  
```

### 2. Set Up Environment Variables
Create a `.env` file and specify your bot token:
```env
BOT_TOKEN=your_telegram_bot_token  
```

### 3. Build & Run Locally
#### Build and Run with Maven
```sh
./mvnw clean package  
java -jar target/currency-track-bot.jar  
```

### 4. Run in Docker
#### Build Docker Image
```sh
docker build -t currency-track-bot .  
```
#### Run the Container
```sh
docker run -d --name currency-bot --env BOT_TOKEN=your_telegram_bot_token currency-track-bot  
```

## 🛠 Development
### Run in Development Mode
```sh
./mvnw spring-boot:run  
```

### Logging
Logs can be found in the console or in `logs/app.log` if configured in `application.properties`.

## 📜 License
This project is licensed under the MIT License.

---

💡 **Note:** Make sure your bot is registered with [@BotFather](https://t.me/BotFather) and has a valid token to operate.