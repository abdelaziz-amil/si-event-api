# Étape de construction utilisant une image Node.js
FROM node:lts-alpine as build-stage
WORKDIR /app

# Copiez les fichiers package.json et package-lock.json
COPY si-uboeventflow-client/package*.json ./

# Copiez le reste des fichiers de l'application Vue.js dans le conteneur
COPY si-uboeventflow-client/ .

# Installez les dépendances
RUN npm install

# Construisez l'application pour la production
RUN npm run build

# Étape de production utilisant une image Apache
FROM httpd:2.4 as production-stage
COPY --from=build-stage /app/dist/ /usr/local/apache2/htdocs/

# Exposez le port 80
EXPOSE 80
