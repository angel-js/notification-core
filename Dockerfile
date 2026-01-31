# =========================
# STAGE 1: Build
# =========================
FROM gradle:8.6-jdk21 AS builder

# Directorio de trabajo
WORKDIR /app

# Copiamos solo los archivos necesarios primero (cache eficiente)
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Descargar dependencias (layer cache)
RUN ./gradlew dependencies --no-daemon

# Copiamos el código
COPY src ./src

# Compilamos la librería
RUN ./gradlew clean build --no-daemon

# =========================
# STAGE 2: Artifact only
# =========================
FROM eclipse-temurin:17-jre-jammy

# Directorio donde dejaremos el JAR
WORKDIR /artifact

# Copiamos solo el JAR generado
COPY --from=builder /app/build/libs/*.jar ./notification-lib.jar

# Este contenedor NO se ejecuta
CMD ["echo", "Library built successfully. Copy the JAR from this container."]
