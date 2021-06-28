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
