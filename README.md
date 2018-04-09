# Projet

Application spring boot basé sur une application permettant de gerer des affaires d'une equipe de police

La base de donnée Personne est commune aux bases Suspect et Agent
Elles possedent les memes attributs (nom, prenom,adresse, ville) et sont liées par leurs clefs primaires.
un supect est lié a des documents et des condamnations
chaque agent possede obligatoirement un profil ( admin ou utilisateur)


## Installations logiciels et serveurs requis

- Avoir au prealable installer Eclipse JEE ainsi que maven (disponible sur site officiel)

- Installer un serveur Mysql (https://www.mysql.com/fr/ dans la rubrique download ou de preference phpMyAdmin )
- lors de l'installation, laissez le port d'origine (3306)
- utiliser les identifiants de votre choix
- Une fois installé, verfier que le serveur Mysql est bien demarré
- se connecter a la base en utilisant :
        ```
        -hote : localhost
        -utilisateur et mot de passe : ceux renseignés lors de l'installation du logiciel
        -port : 3306
        ```
- importer le fichier mist.sql dans le Systeme de gestion de BDD
- cela importera toutes les données utiles pour le bon fonctionnement de spring boot
- pour l'instant un seul role ayant tous les privileges a été crée
        ```
        - utilisateur : directeur
        - mot de passe : miamicsi
        ```
- ce profil est celui qui a un controle total a la base de donnée mist

- Installer postman afin de pouvoir effectuer des requetes pour obtenir les données (sur le localhost qui ecoute sur le port 8080)


### Presentation api et contribution personnelle

**Table Agent basée sur un repository utilisatnt Datasource et Le driver JDBC:**

* **GET** /api/agent : liste les information de l'ensemble des instances de agent.
 * **GET** /api/agent/[id] : renvoie les informations d'une instance d'objet agent avec son id.
 * **POST** /api/agent : crée une nouvelle instance de agent avec les informations fournies.
 * **PUT** /api/agent/[id] : met à jour l'instance de agent correspondant à l'id fourni avec les informations fournies.
 * **DELETE** /api/agent/[id] : supprime l'instance de agent correspondant à l'id fourni.

 **Table Suspect basée sur des requetes hibernate :**

* **GET** /api/suspect : liste les information de l'ensemble des instances de suspect.
 * **GET** /api/suspect/[id] : renvoie les informations d'une instance d'objet suspect avec son id.
 * **DELETE** /api/suspect/[id] : supprime l'instance de suspect correspondant à l'id fourni.
 * **PUT** /api/suspect/[id] : met à jour l'instance de suspect correspondant à l'id fourni avec les informations fournies.
 * **DELETE** /api/suspect/[id] : supprime l'instance de suspect correspondant à l'id fourni.

**Table Profil basée sur des requetes hibernate :**
* **GET** /api/profil : liste les information de l'ensemble des instances de profil.
* **GET** /api/agent/[libelle] : renvoie les informations d'une instance d'objet profil avec son libelle.


 **Table documents et Condamnations : Requetes a venir**

### Installation du projet

Cloner et recuperer le projet

importer le projet en tant que "projet maven existant"

ouvrir une console de ligne de commande

se deplacer a la racine du projet

**Compilation**
```
mvn clean install
```

Une fois la compilation effectuée et build avec succes :

-retourner dans eclipse

- inutile de modifier les données dans ' application.properties' car les données  (lors installation mysql) au debut sont les meme que celles dans ce fichier

-lancer le projet (run as spring boot application)

- Executer les differentes requetes grace au logiciel "Postman"


## Auteur

* **Nourry Jean-Luc** 
(https://github.com/jln06140)



