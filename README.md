# Swarm JGroup Example #

Requirements

1. Java 8
2. Maven

Package the example with:

    mvn clean package

Service 1 creates a simple rest service that return a string with a miliseconds padding, service 2 consumes service 1 and returns same value with other wrapper.

## Service 1 - Produces ##

Run:

    java -Djava.net.preferIPv4Stack=true -jar swarm-service-1\target\swarm-service-1-swarm.jar

URL:

    http://localhost:8082/name/hola

Response example:

    {"name":"hola","paddingName":"hola19570608687877","valid":true}

## Service 2 - Consumes Service 1 ##

Run:

    java -Djava.net.preferIPv4Stack=true -jar swarm-service-2\target\swarm-service-2-swarm.jar

URL:

    http://localhost:8083/myname/hellou

Response example:

    {"name":"hellou","resultName":"hellou19596981459471"}


Because of this issue: **https://github.com/belaban/JGroups/wiki/FAQ** 
use: *-Djava.net.preferIPv4Stack=true*