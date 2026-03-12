# ==========================================
# Etapa 1: Construcción (Build)
# ==========================================
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copiamos el wrapper de Maven y el archivo de configuración pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Le damos permisos de ejecución al wrapper (muy importante en entornos Linux)
RUN chmod +x ./mvnw

# Descargamos las dependencias de Maven (esto ayuda a que los despliegues futuros sean más rápidos)
RUN ./mvnw dependency:go-offline

# Copiamos todo el código fuente de tu proyecto
COPY src ./src

# Compilamos el proyecto y generamos el archivo .jar (omitiendo los tests para mayor velocidad)
RUN ./mvnw clean package -DskipTests

# ==========================================
# Etapa 2: Ejecución (Run)
# ==========================================
# Usamos una imagen JRE (solo ejecución) que es mucho más ligera
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copiamos únicamente el archivo .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto estándar de Spring Boot
EXPOSE 8081

# Comando que ejecuta tu aplicación web
ENTRYPOINT ["java", "-jar", "app.jar"]