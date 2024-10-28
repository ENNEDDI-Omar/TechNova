## SPRING FRAMEWORK

Cette application est un système basique de gestion des utilisateurs construit avec Spring Core, utilisant Spring MVC et Spring Data JPA. Elle permet aux utilisateurs d'effectuer des opérations CRUD (Création, Lecture, Mise à jour, Suppression) via une interface web.

## Table des matières
- [Structure du Projet](#structure-du-projet)
- [Injection de Dépendances (DI)](#injection-de-dépendances-di)
- [Inversion de Contrôle (IoC)](#inversion-de-contrôle-ioc)
- [Beans Spring](#beans-spring)
- [Portées des Beans](#portées-des-beans)
- [ApplicationContext](#applicationcontext)
- [Spring Data JPA](#spring-data-jpa)
- [Spring MVC](#spring-mvc)
- [Installation et Configuration](#installation-et-configuration)

### Structure du Projet

      src/
      ├── main/
      │   ├── java/
      │   │   └── org/technova/
      │   │       ├── controller/
      │   │       │   ├── HomeController.java
      │   │       │   └── UserController.java
      │   │       ├── model/
      │   │       │   └── User.java
      │   │       ├── repository/
      │   │       │   └── UserRepository.java
      │   │       └── service/
      │   │           ├── impl/
      │   │           │   └── UserServiceImpl.java
      │   │           └── interfaces/
      │   │               └── UserService.java
      │   ├── resources/
      │   │   ├── spring/
      │   │   │   ├── applicationContext.xml
      │   │   │   ├── spring-data.xml
      │   │   │   └── spring-mvc.xml
      │   │   └── database.properties
      │   └── webapp/
      │       ├── WEB-INF/
      │       │   ├── views/
      │       │   │   ├── home.jsp
      │       │   │   ├── userForm.jsp
      │       │   │   └── userList.jsp
      │       │   ├── jboss-web.xml
      │       │   └── web.xml
      │       └── resources/
      │           └── css/
      │               └── style.css

### Injection de Dépendances (DI)
Dans ce projet, nous avons implémenté trois types d'injection de dépendances :

1. Injection par Constructeur :
    __ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ __ _ _ _ _ _ _ _ _
    |  public UserServiceImpl(UserRepository userRepository) {  |
    |     this.userRepository = userRepository;                 |
    |  }                                                        |
    |_ _ _ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _  __ _  _ _ _ _

2. Injection par Setter :

   __ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ __ _ _ _ _ _ _ _ _
   
      |public void setUserRepository(UserRepository userRepository) {
            this.userRepository = userRepository;
        }                                                        |
      |_ _ _ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _  __ _  _ _ _ _
4. Injection par Interface :

     __ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ __ _ _ _ _ _ _ _ _
   
    |  public void setRepository(UserRepository repository) {
        this.userRepository = repository;
    }                                                        |
    |_ _ _ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _  __ _  _ _ _ _


 ### Inversion de Contrôle (IoC)
  L'IoC est implémentée via la configuration XML dans trois fichiers principaux :
  
  + applicationContext.xml : Configuration principale de l'application
  + spring-data.xml : Configuration de la base de données et JPA
  + spring-mvc.xml : Configuration MVC
  
  Exemple depuis applicationContext.xml :
             <bean id="userService" class="org.technova.service.impl.UserServiceImpl">
                <property name="repository" ref="userRepository"/>
            </bean>

### Beans Spring
  L'application définit plusieurs beans :

  1. Beans de Service :
  
  <bean id="userService" class="org.technova.service.impl.UserServiceImpl">
      <property name="repository" ref="userRepository"/>
  </bean>
  
 2.  Beans de Contrôleur :
  
  <bean id="userController" class="org.technova.controller.UserController">
      <property name="userService" ref="userService"/>
  </bean>           
### Portées des Beans
  Nous utilisons deux portées de beans dans notre application :
  
  1. Singleton (Par défaut) :
  
  <bean id="singletonUser" class="org.technova.model.User" scope="singleton">
      <property name="nom" value="Admin"/>
      <property name="prenom" value="System"/>
  </bean>
  
  2. Prototype :
  
  Copy<bean id="prototypeUser" class="org.technova.model.User" scope="prototype"/>  

### ApplicationContext
  Le contexte de l'application est configuré dans web.xml :
  <context-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>
          classpath:spring/applicationContext.xml
          classpath:spring/spring-data.xml
      </param-value>
  </context-param>

### Spring Data JPA
  La configuration JPA est gérée dans spring-data.xml :
  <bean id="entityManagerFactory"
        class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
      <property name="dataSource" ref="dataSource"/>
      <property name="packagesToScan" value="org.technova.model"/>
      
  </bean>
  Interface Repository :
  public interface UserRepository extends JpaRepository<User, Long> {
      List<User> findByNationalite(String nationalite);
      User findByPieceIdentite(String pieceIdentite);
  }  

### Spring MVC
  Configuration MVC dans spring-mvc.xml :
  <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
      <property name="prefix" value="/WEB-INF/views/"/>
      <property name="suffix" value=".jsp"/>
  </bean>
  Les contrôleurs gèrent les requêtes HTTP :
  public class UserController extends AbstractController {
      @Override
      protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) {
          // Logique de traitement des requêtes
      }
  }

### Installation et Configuration

1. Prérequis :
  
  Java 17
  Maven
  PostgreSQL
  WildFly 33.0.2.Final
  
  
2. Configuration de la Base de Données :
  
  Créer une base de données PostgreSQL nommée user_management
  Configurer database.properties avec vos identifiants
  
  
3.  Construction et Déploiement :
  bashCopymvn clean install
  mvn wildfly:deploy
  
4. Accès à l'application :
  Copyhttp://localhost:8080/user-management


L'application comprend un tableau de bord affichant les statistiques des utilisateurs et fournit les opérations CRUD pour la gestion des utilisateurs.


  
  
