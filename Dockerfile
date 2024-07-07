# Usar la imagen oficial de OpenJDK como base
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos del proyecto al contenedor
COPY . .

# Dar permisos de ejecución al script mvnw
RUN chmod +x ./mvnw

# Construir la aplicación
RUN ./mvnw clean install

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Ejecutar la aplicación
CMD ["./mvnw", "spring-boot:run"]
