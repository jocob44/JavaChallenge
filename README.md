# Java Challenge

Command line Application to calculate and render the bowling scores taken from text files.

### Technologies Documentation 

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.7/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.7/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#using-boot-devtools)


### Requirement

- Java 8 or later

### Deployment

The application can be build and run using the next command in the main directory

```bash
 ./mvnw spring-boot:run
```

Also, it is possible to package the solution using the next command:

```bash
 ./mvnw clean package spring-boot:repackage
```

the result will be included in the related directory:


>/target


### Testing

If you want to check the test results run:
```bash
./mvnw test
```

### Special Instructions

The current solution take an input from resource folder.

It is possible to edit the input file editing the next **src/main/resources/application.properties** file key:

>inputPath=scores.txt


In case you use an invalid input file the related error will be shown in the **System.Out** output.
