FROM openjdk:19

WORKDIR /app

# Copia todos los archivos del directorio "target" al directorio "/app" en la imagen
COPY target/ /app/

# Establece el comando de entrada
ENTRYPOINT ["java", "-jar", "/app/inmogestion-0.0.1.jar"]