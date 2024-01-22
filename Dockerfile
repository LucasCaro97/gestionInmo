FROM openjdk:19
ADD target/inmogestion-0.0.1.jar inmogestion-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/inmogestion-0.0.1"]