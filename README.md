# myspring-data-foundationdb
a try of a spring data foundationdb community project

## install jar
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