# UBOEventFlow

Web app de gestion d'événements.

## Structure du projet

Le projet est divisé en 7 parties : le front-end, la racine, et les 5 apis.

Les api sont les suivantes :
- `si-event-api` : [API de gestion des événements](https://github.com/abdelaziz-amil/si-event-api)
- `si-membre-api` : [API de gestion des membres](https://github.com/abdelaziz-amil/si-membre-api)
- `si-lieu-api` : [API de gestion des lieux](https://github.com/Flintpop/si-lieu-api)
- `si-commentaire-api` : [API de gestion des commentaires (avec mongoDB)](https://github.com/Flintpop/si-commentaire-api)
- `si-core-api` : [API façade](https://github.com/Flintpop/si-core-api)

La [racine](https://github.com/AntoineT01/UBOEventFlow) contient les fichiers de configuration et les scripts de lancement du docker compose.
Elle contient les fichiers d'initialisation de la base de données, à la fois mongoDB et mariadb.
Avec docker compose, on peut lancer l'ensemble des apis et la base de données (avec les projets github correspondants, ils sont en publics).

## Lancement du projet

Pour lancer le projet, il suffit de lancer le script `start.sh` à la [racine](https://github.com/AntoineT01/UBOEventFlow) du projet.
On peut également lancer le projet et reset la base de données avec le script `startReset.sh`.

Les scripts de lancements lancent également docker desktop sur windows.
> ⚠️ Uniquement testé sur Windows.

## Utilisation du projet

Le projet est accessible à l'adresse `http://localhost:8080`.

Toutes les fonctionnalités fonctionnent.