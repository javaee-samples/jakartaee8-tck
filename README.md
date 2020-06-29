# jakartaee8-tck

This project contains samples taken from the Jakarta EE TCK and refactored where needed to fit into a standard junit / Arquillian / Maven project.

The TCK itself can be found at: https://github.com/eclipse-ee4j/jakartaee-tck


## Running the TCK samples

```
mvn clean install
```

## Default runtime

The default runtime to execute tests against is Piranha. Other servers, can be selected using profiles. E.g.

```
mvn clean install -Ppayara-remote
```

## Running individual samples

Running individual samples can be done using the `-Dtest` parameter, in combination with the name of the test class, and optionally the test method. E.g.

```
mvn clean install -Dtest=ServletRequestTest#getAttributeNamesTest -DfailIfNoTests=false
```

## Debugging individual samples

To debug samples for embedded runtimes (running in the same process as the TCK samples), e.g:

```
mvn clean install -Dtest=ServletRequestTest#getAttributeNamesTest -DfailIfNoTests=false -Dmaven.surefire.debug
```

To debug samples for remote runtimes (running in another process as the TCK samples), e.g:

Start the remote server in debug mode, then:

```
mvn clean install -Dtest=ServletRequestTest#getAttributeNamesTest -DfailIfNoTests=false -Ppayara-remote
```
