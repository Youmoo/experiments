http://www.javaspecialists.eu/archive/Issue167.html
---------------------------------------------------

执行方式:

```
javac -classpath .:target/lang.jar -processor NoArgsConstructorProcessor  src/main/java/ast/*.java

javac -cp ".:target/lang.jar" src/main/java/ast/*.java
```