FROM openjdk:19

# Crea el directorio de la aplicación en la imagen
WORKDIR /app

# Copia el archivo JAR al directorio de la aplicación en la imagen
COPY target/inmogestion-0.0.1.jar /app/inmogestion-0.0.1.jar

# Establece el comando de entrada
ENTRYPOINT ["java", "-jar", "inmogestion-0.0.1.jar"]