# myspring-data-foundationdb
a try of a spring data foundationdb community project

[![Build Status](https://travis-ci.org/martinjunker/myspring-data-foundationdb.svg?branch=master)](https://travis-ci.org/martinjunker/myspring-data-foundationdb)

## Quick Start


### Maven configuration

Add the Maven dependency:

```xml
<dependency>
    <groupId>de.h2cl.spring.data</groupId>
    <artifactId>myspring-data-foundationdb</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```



### install foundation DB client jar
since the java driver is not present in maven central you should download it from [https://www.foundationdb.org](https://www.foundationdb.org/downloads/5.1.5/bindings/java/fdb-java-5.1.5.jar) and add it manually to your maven repository:

```
mvn install:install-file
  -Dfile=<path-to-file> 
  -DgroupId=com.apple.cie.foundationdb
  -DartifactId=fdb-java 
  -Dversion=<version> 
  -Dpackaging=jar 
  -DgeneratePom=true
```
### FoundationDbRepository
A default implementation of FoundationDbRepository, aligning to the generic Repository Interfaces, is provided. Spring can do the Repository implementation for you depending on method names in the interface definition.

The FoundationDbRepository extends PagingAndSortingRepository

```java
    public interface FoundationDbRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
    }
```
## ToDo
* Entity Metadata
* ObjectMapping ([Look at Spring Data Commons](https://github.com/spring-projects/spring-data-commons/wiki/Developer-guide#mapping-and-conversion-system))
* connect to maven central