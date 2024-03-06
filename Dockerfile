# Utiliser une image de base avec Java (précisez la version de Java selon vos besoins)
FROM openjdk:17-jdk

# Ajouter un label pour maintenir votre image
LABEL maintainer="nom@example.com"

# Ajouter un volume pour stocker les logs de l'application
VOLUME /tmp

# Exposer le port sur lequel votre application s'exécute
EXPOSE 8080

# Ajouter le fichier .jar de votre application dans l'image
ARG JAR_FILE=build/libs/si-event-api-1.0.jar
ADD ${JAR_FILE} app.jar

# Commande pour exécuter l'application
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
