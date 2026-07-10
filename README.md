# spring-cloud-config-server

Ce microservice fait office de **Serveur de Configuration Centralisé** pour l'ensemble du système de microservices. Il permet de stocker et de servir les configurations applicatives (fichiers `.properties` ou `.yml`) de manière externe, évitant ainsi d'avoir à recompiler les microservices lors d'une modification de configuration.

## ⚙️ Rôle et Fonctionnalités

- **Centralisation** : Tous les microservices de l'écosystème récupèrent leur configuration auprès de ce serveur lors de leur démarrage.
- **Support Git** : Les fichiers de configuration sont versionnés dans un dépôt Git (local ou distant).
- **Profils d'environnement** : Gestion transparente des environnements (`dev`, `test`, `prod`).

---

## 🔌 Configuration du Service

- **Port par défaut** : `8888`
- **Technologie** : Spring Boot, Spring Cloud Config Server
- **Dépôt Git ciblé** : `F:\\git-repository\\spring-cloud-microservices\\repository-memoire` (Défini dans le fichier `application.properties`).

---

## 🚀 Démarrage

### Mode Local (développement)
Assurez-vous que le chemin vers votre dépôt Git local dans le fichier `src/main/resources/application.properties` est correct. Lancez ensuite le service avec Maven :
```bash
mvn spring-boot:run
```

### Mode Docker
Le service est lancé automatiquement par le Docker Compose global du backend. Il expose le port `8888` et monte le répertoire de configuration local dans le conteneur.

---

## 🔍 Tester le Serveur de Configuration

Vous pouvez vérifier que le serveur lit correctement les configurations depuis le dépôt Git en effectuant une requête GET directement depuis votre navigateur ou via `curl` :

```bash
# Récupérer la configuration de développement du service d'authentification
curl http://localhost:8888/authentication-service/dev

# Récupérer la configuration de production du service utilisateur
curl http://localhost:8888/user-service/prod
```

Le serveur répondra avec un objet JSON contenant les propriétés du fichier correspondant (ex: `authentication-service-dev.properties`).
