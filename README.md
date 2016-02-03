Because of this issue: https://github.com/belaban/JGroups/wiki/FAQ use:
-Djava.net.preferIPv4Stack=true

Service 1 - Produces
java -Djava.net.preferIPv4Stack=true -jar swarm-service-1\target\swarm-service-1-swarm.jar
URL:
http://localhost:8082/name/hola
Response example:
{"name":"hola","paddingName":"hola19570608687877","valid":true}

Service 2 - Consumes produces
java -Djava.net.preferIPv4Stack=true -jar swarm-service-2\target\swarm-service-2-swarm.jar
URL:
http://localhost:8083/myname/hellou
Response example:
{"name":"hellou","resultName":"hellou19596981459471"}