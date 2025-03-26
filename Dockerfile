
# Используем официальный образ OpenJDK 21
FROM openjdk:21-jdk-slim

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR-файл в контейнер (замени kv.jar на своё имя, если нужно)
COPY target/kv-0.0.1-SNAPSHOT.jar app.jar

# Устанавливаем переменные среды (пароли можно убрать и передавать через docker-compose)
ENV ENV SPRING_APPLICATION_NAME=kv
    ENV SPRING_DATASOURCE_URL="jdbc:mysql://db:3306/bdKv?useSSL=false&allowPublicKeyRetrieval=true"
    ENV SPRING_DATASOURCE_USERNAME=root
    ENV SPRING_DATASOURCE_PASSWORD=MyROOTpassword
    ENV SERVER_PORT=8081


# Запускаем приложение
CMD ["java", "-jar", "app.jar"]
