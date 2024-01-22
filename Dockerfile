FROM openjdk:19

WORKDIR /app

# Copia el archivo JAR al directorio "/app" en la imagen
COPY target/inmogestion-0.0.1.jar /app/

# Establece el comando de entrada
ENTRYPOINT ["java", "-jar", "/app/inmogestion-0.0.1.jar"]
