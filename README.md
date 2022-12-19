Struts6 Spring JPA
===============
Projects based on [struts2-spring-jpa](https://github.com/xiaobin80/struts2-spring-jpa).    
  with JTA(Atomikos-Hibernat4) + JPA


## JPA

if hibernate-core version >= 6 then "must be" [JDK11](https://docs.jboss.org/hibernate/orm/6.0/migration-guide/migration-guide.html#_java_11)

[A Java 17 and Jakarta EE 9 baseline for Spring Framework 6](https://spring.io/blog/2021/09/02/a-java-17-and-jakarta-ee-9-baseline-for-spring-framework-6)
As announced at SpringOne yesterday, Spring Framework 6 and Spring Boot 3 are planned towards a high-end baseline for their general availability in Q4 2022:

### javax.persistence
So no it is not a bug it is intended as there is no Java Persistence API after version 2.2,    
as the development/maintenance of the EE APIs has been moved from Oracle to a foundation.    
With the new releases the packages have also changed (due to legal implication) and those are now in 
```java
jakarta.persistence
```
instead of 
```java
javax.persistence.
```

### Hibernat5.2
You can also put :
```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```
And let the DateBase manage the incrementation of the primary key:
```sql
AUTO_INCREMENT PRIMARY KEY
```

### javax.transaction
```java
jakarta.transaction
```
instead of
```java
javax.transaction
```


# Deploy
War:    
```
mvn clean
```
```
mvn compile
```
```
mvn package -DskipTests
```

## org.apache.maven.plugins:maven-war-plugin:2.2
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-war-plugin:2.2:war 
(default-war) on project struts6-spring-jpa: Execution default-war of goal 
org.apache.maven.plugins:maven-war-plugin:2.2:war failed: Unable to load the mojo 'war' in the plugin 
'org.apache.maven.plugins:maven-war-plugin:2.2' due to an API incompatibility: 
org.codehaus.plexus.component.repository.exception.ComponentLookupException: Cannot access defaults field of Properties
```
add pom.xml:
```xml
      <plugin>
        <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-war-plugin -->
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.3.2</version>
      </plugin>
```

## Jetty
Set JETTY_HOME in you system path:

if JDK17:
```
D:\progFiles\jetty-home-11.0.x
```

if JDK8:
```
D:\progFiles\jetty-9.4.x
```

### Init
> JDK11+

```
cd %JETTY_HOME%\jetty-base
java -jar %JETTY_HOME%\start.jar --add-module=server,http,deploy,annotations
```

webapps:
application deploy directory.


### Run
```
java -jar %JETTY_HOME%\start.jar
```

## Web
* http://localhost:8080/struts6-spring-jpa-0.1.0-SNAPSHOT/index    
* http://localhost:8080/struts6-spring-jpa-0.1.0-SNAPSHOT/home    
* http://localhost:8080/struts6-spring-jpa-0.1.0-SNAPSHOT/userSingle?userId={id}    
* [userList - struts6_tags](http://localhost:8080/struts6-spring-jpa-0.1.0-SNAPSHOT/userList)    
* http://localhost:8080/struts6-spring-jpa-0.1.0-SNAPSHOT/userDel?userId={id}    
* http://localhost:8080/struts6-spring-jpa-0.1.0-SNAPSHOT/userInsert    
* http://localhost:8080/struts6-spring-jpa-0.1.0-SNAPSHOT/userUpdate?userName={value}&userId={id}    
* http://localhost:8080/struts6-spring-jpa-0.1.0-SNAPSHOT/trainList?month={number}&trainNum={number}    



# Ref
- [安装memcached](https://tdtc-hrb.github.io/csdn/post/ops_memcached_ubuntu/)
- [Jakarta Persistence](https://docs.jboss.org/hibernate/orm/6.0/migration-guide/migration-guide.html#_jakarta_persistence)
- [Jetty Setup](https://happycoding.io/tutorials/java-server/jetty-setup)
- [why is dependency to javax.persistence-api removed in hibernate-core 6.0.2](https://stackoverflow.com/questions/72475572/why-is-dependency-to-javax-persistence-api-removed-in-hibernate-core-6-0-2)
- [Table 'test.hibernate_sequence' doesn't exist](https://stackoverflow.com/a/36086793)
