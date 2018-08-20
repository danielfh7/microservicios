export DATABASE_HOST=localhost
export SECURITY_BASEURL=http://localhost:8090/
export CLIENTES_BASEURL=http://localhost:8080/
export ORDENES_BASEURL=http://localhost:9000/
java -jar authenticator/build/libs/service.jar db tag ./authenticator/src/main/resources/config.yaml 2018_08_18
java -jar authenticator/build/libs/service.jar db migrate ./authenticator/src/main/resources/config.yaml
java -jar customers/build/libs/service.jar db tag ./customers/src/main/resources/config.yaml 2018_08_18
java -jar customers/build/libs/service.jar db migrate ./customers/src/main/resources/config.yaml
java -jar orders/build/libs/service.jar db tag ./orders/src/main/resources/config.yaml 2018_08_18
java -jar orders/build/libs/service.jar db migrate ./orders/src/main/resources/config.yaml

