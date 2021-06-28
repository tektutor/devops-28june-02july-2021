### Maven Overview
- is an open source build tool
- developed by Apache Foundation
- is better than Ant build tool
- insists on conventions for everything
    - project folder
    - dependency
    - naming your project using maven co-ordinates
 - supports dependency management
 - uses plugins for achieving specific tasks
     - e.g maven-clean-plugin to delete the target folder where all the .class files and .jar,.war.ear are kept
 - each plugin has one or more goals
 - each plugin goal supports a specific tasks
     - e.g maven-compiler-plugin supports a goal called 'compile' to compile application source code
     - maven-compiler-plugin also supports another goal called 'test-compile' to compile test source code(JUnit/TestNg,etc.,)
 - Maven plugins
     - are used by Maven to compile your project or package your application binaries, deploy your binaries else where
 - Maven dependencies
     - are third-party libraries used by your project
          for example: JUnit, log4j, spring-boot framework, etc,, 

### Compiling the Hello project
```
mvn compile
```

### Executing the Hello application using the .class file
```
cd target/classes
java org.tektutor.Hello
cd ../..
```

### Executing the test cases
```
mvn test
````

### Creating jar file 
```
mvn package
```

### Executing the jar file
```
cd target
java -classpath tektutor-hello-app-1.0.0.jar org.tektutor.Hello
cd ..
```

### Listing default life-cycle phases
```
mvn help:describe -Dcmd=compile
```

### Listing clean life-cycle phases
```
mvn help:describe -Dcmd=clean
```

### Listing site life-cycle phases
```
mvn help:describe -Dcmd=site
```

### Listing the goals supported by a Maven plugin
```
mvn help:describe -Dplugin=org.apache.maven.plugins:maven-compiler-plugin
```

### Listing the goals supported by a Maven plugin along with configuration details
```
mvn help:describe -Dplugin=org.apache.maven.plugins:maven-compiler-plugin -Ddetail
```

### Printing effective pom
```
mvn help:effective-pom
```

### Redirecting the output of effective-pom to a file
```
mvn help:effective-pom > effective-pom.txt 2>&1
```

### Creating a hello java project using archetype plugin in batch mode(non-interactive mode)
```
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DgroupId=org.tektutor -DartifactId=hello -Dversion=1.0.0 -DinteractiveMode=false
```

### Creating a sample web application using archetype plugin in batch mode
```
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-webapp -DgroupId=org.tektutor -DartifactId=tektutor-helloweb-app -Dversion=1.0.0 -DinteractiveMode=false
```

### Creating a web application in interactive mode
```
mvn archetype:generate
```
When it prompts for type of project, type maven-archetype-webapp
When it prompts for groupId, type org.tektutor
When it prompts for version, type 1.0.0
Accept defaults by pressing Enter. If all goes well, you will have a web application created at this point!

### Executing multi-module project
```
cd Day1/CRM
mvn clean package
```

### Check how the effective pom looks from project home
```
cd Day1/CRM
mvn help:effective-pom > effective-pom.yml
cd ..
```

### Check how the effective pom looks inside frontend module
```
cd Day1/CRM/frontend
mvn help:effective-pom > effective-pom.yml
cd ..
```

### Check how the effective pom looks inside main module
```
cd Day1/CRM/main
mvn help:effective-pom > effective-pom.yml
cd ..
```

### Finding the dependency of your project
```
cd Day1/CRM
mvn dependency:tree
```

### Enabling debug mode to troubleshoot
```
cd Day1/CRM
mvn package -X > out.yml 2>&1
```

### Creating a JFrog Artifactory Docker container
```
docker run -d --name jfrog --hostname jfrog -p 8081:8081 -p 8082:8082 docker.bintray.io/jfrog/artifactory-oss:latest
```
