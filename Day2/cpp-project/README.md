### Create soft link for python2 to make python2 as default
```
sudo ln -s -f /usr/bin/python2 /usr/bin/python
```

### Install g++ compiler
```
sudo yum group install "Development Tools"
```

### Compiling the c++ application via Maven
```
cd Day2/cpp-project
mvn compile
```

### Performing a clean build
```
cd Day2/cpp-project
mvn clean compile
```

### Perform a clean build and executing the application
```
cd Day2/cpp-project
mvn test
```
