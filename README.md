# ITÉRATION 1
# Introduction à Spring Boot

---

## Modalités
- Travail individuel en autonomie
- 1 jour max en présentiel

## Livrables

Un projet Maven contenant une application Spring Boot qui expose (au moins) un endpoint.

## Objectifs

Le premier objectif de cette itération est la découverte de Spring Boot et des principales
caractéristiques de ce framework, notamment le mécanisme d’injection de dépendances ainsi que le principe de « convention over configuration ».

Le second objectif est la mise en place d’un projet Maven, qui servira de base aux itérations
suivantes de ce module

## Compétences

- https://fr.wikipedia.org/wiki/Injection_de_d%C3%A9pendances
- https://fr.wikipedia.org/wiki/Convention_plut%C3%B4t_que_configuration

---

## Généralités J2EE, JEE, Spring, Spring Boot

Java désigne à la fois un langage et un ensemble de classes prévues pour la programmation
générale. Très rapidement, il a fallu mettre en place des extensions permettant de programmer
plus rapidement dans certains domaines, en particulier ce qui était vu à l’époque comme les
domaines professionnels (en simplifiant) :

- connexions aisées avec des bases/sources de donnée ;
- facilitation d’architecture MVC pour le Web avec les Servlets (Contrôleurs) et JSP (templates pour les vues) ;
- divers aspects d’architecture et outils (inversion de, XML, convention “Beans”, connecteur mail, etc).

Cela a donné lieu à Java 2 Entreprise Edition (J2EE), qui sera renommé par la suite en Java
Entreprise Edition, puis Jarkarta EE. Les premières versions de J2EE/JEE étaient considérées à la
fois très intéressantes et très difficiles à appréhender et à mettre en œuvre. Des efforts
concurrents multiples ont été menés et un en particulier s’est fortement inspiré de J2EE pour
chercher à le simplifier tout en gardant l’esprit : Spring.

L’idée principale est de pouvoir assembler de façon dynamique différents composants (appelés
beans) pour réaliser cette application. Par exemple, tel composant de sources de données avec tel
composant d’accès à un service externe, etc.

Ces composants sont définis par l’interface qu’ils implémentent (contrat) et l’instance effective des
composants découle de la configuration de l’application. Cela permet de facilement changer les
composants d’une application (découplage) pour, par exemple, avoir une version avec stockage
local, une version avec un stockage dans le Web dans une base MongoDB... ou replacer un
composant qui sera disponible en production par un composant “mimant” son fonctionnement,
permettant de tester le reste de l’application (principe des “mock/stub” ou “simulacre/bouchon”)

Durant ce module, nous utiliserons le framework Spring Boot qui simplifie (certes en limitant)
l’utilisation de Spring.

**RESSOURCES**
- https://www.test-recette.fr/tests-techniques/deployer-tests-unitaires/simulacres-bouchons
- https://fr.wikipedia.org/wiki/Spring_(framework)
- https://en.wikipedia.org/wiki/Jakarta_EE#History


### 1.1 — Découverte Spring Boot
Lisez et/ou regardez des tutoriaux sur Spring Boot afin de vous familiariser avec le vocabulaire et
les principes de ce framework. Ne cherchez pas à avoir une vue d’ensemble de tout ce que Spring
Boot peut offrir : c’est beaucoup trop vaste !

**RESSOURCES**
- https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices (Partie 1 - chapitres 2 & 3)
- https://gayerie.dev/docs/spring/spring/introduction.html (en français, un peu théorique)
- https://devstory.net/11267/tutoriel-spring-boot-pour-debutant (en français, mais date un peu)
- https://www.youtube.com/watch?v=Zzjn_Jz-A2E (vidéo couvrant toute l’architecture d’une application Spring Boot, en français... durant ce module, nous n’allons pas faire les choses dans le même ordre !)

### 1.2 — Exposition d’un premier endpoint

Durant cette étape, nous allons créer une application Spring Boot qui expose un endpoint HTTP
`GET /heartbeat` qui renvoie une valeur aléatoire comprise entre 40 et 230.

Nous utiliserons l’outil de build _Maven_ pour la gestion (dépendances, compilation, etc.) de notre projet. Une des conséquences est la standardisation de la structure des fichiers qui composent le projet. Nous aurons ainsi un projet « de type Maven », indépendant de l’environnement de développement (IDE) que vous choisirez d’utiliser. Nous vous recommandons soit JetBrains IntelliJ IDEA, soit Microsoft Visual Studio Code.

Pour commencer, créez un repository (vide) sur GitHub et créez-en un clone en local. Ce
repository va contenir à terme le code de nos API pour les jeux de plateau « Square Games »,
choisissez un nom approprié.

Pour initialiser le projet, vous pouvez utiliser la fonction de l’IDE de votre choix, soit, l’outil en ligne fourni par Spring : https://start.spring.io (choisissez bien un projet
Maven Java 21 avec Spring Boot 3.2.2). Ajoutez la dépendance “Spring Web”.
Une fois le projet créé, vérifiez que le code compile et que l’application s’exécute, puis faites un
commit de cette version initiale.
Pour mettre en œuvre quelques mécanismes de Spring, nous allons maintenant exposer un
endpoint http GET /heartbeat qui renvoie une valeur fournie par... un composant dont c’est le
rôle !

1. Qui dit rôle, dit contrat : nous créons donc une interface **HeartbeatSensor** qui contient
   une unique méthode (`get() : int`, par exemple). Pour rappel, une interface définit un
   contrat, mais ne le « remplit » pas. Ce sont les implémentations des interfaces
   (généralement des classes) qui « remplissent » le contrat.
2. Pour exposer un endpoint http, nous ajoutons maintenant une nouvelle classe
   **HeartbeatController** qui a un champ privé de type **HeartbeatSensor** et décoré par
   l’annotation **@Autowired** (ne pas initialiser ce champ). La classe HeartbeatController
   doit être décorée de l’annotation **@RestController**.
3. Démarrez l’application. Que se passe-t-il ?
4. Ajoutons maintenant une classe RandomHeartbeat, annotée par @Service et qui
   implémente l’interface HeartbeatSensor.
5. Démarrez à nouveau l’application. Que se passe-t-il maintenant ?
6. Ajoutons maintenant à classe **HeartbeatController** une méthode publique annotée
   **@GetMapping("/heartbeat")** avec la signature suivante : **getHeartbeat() : int**.
   Implémentez cette méthode et relancez l’application.
7. Ce lien devrait maintenant fonctionner : http://localhost:8080/heartbeat
   
Trouvez dans les ressources des explications sur les problèmes rencontrés et les annotations utilisées.
   
**RESSOURCES**
- https://code.visualstudio.com
- https://www.jetbrains.com/idea/download/
- Le site officiel Spring : https://spring.io/guides/gs/spring-boot/
- https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices (Partie 1 -
  chapitre 4)
- https://www.baeldung.com/spring-autowire
- https://www.baeldung.com/spring-boot (longue liste de tutoriels, ne surtout pas tout regarder !)
- https://www.baeldung.com/spring-boot-starters
- https://www.baeldung.com/spring-boot-start
- https://www.jrebel.com/blog/spring-annotations-cheat-sheet

### 1.3 — Test du endpoint
Pour tester votre endpoint, utilisez :

- Votre navigateur : c’est suffisant pour des requêtes GET sans paramètres
- Votre environnement de développement : il intègre un éditeur de requêtes HTTP, mais c’est
  un peu spartiate. Dans IntelliJ IDEA, vous pouvez ajouter un fichier de type “HTTP
  Request” (extension http) qui vous permet de créer des requêtes HTTP brutes. Toujours
  dans IntelliJ IDEA, vous pouvez aussi lister et tester votre application en utilisant la toolbar
  “Endpoints”; pour des requêtes complexes, cela reste assez laborieux...
- En pratique, il vaut mieux utiliser un outil de test un peu plus pratique, comme Bruno
  (https://usebruno.com) ou Postman (https://www.postman.com/). Il en existe d’autres, tel
  que JMeter (https://jmeter.apache.org/), mais ce dernier est plus compliqué à prendre en
  main.

Installez Postman, et écrivez un test pour votre endpoint.

  **RESSOURCES**

- https://www.baeldung.com/spring-boot-testing
- https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices (Partie 1 -
  chapitre 5)

    
    Livrables :

    À l’issue de cette courte introduction, vous devez maintenant avoir dans un repository
    GitHub un projet Maven :
    
    ➔ Permettant de compiler et exécuter une application Spring Boot
    
    ➔ Cette application démarre un serveur HTTP exposant un endpoint
    /heartbeat supportant la méthode GET.