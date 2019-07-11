Prerequisite:
 docker
 java 
 maven

Step 1: Start DB 


docker pull postgres:11

docker run --rm --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/Documents/docker_support/postgres:/var/lib/postgresql/data postgres:11


Step 2: Start auth server
cd oauth-server
mvn spring-boot:run 


Step 3: Start webClient

cd web-client 
mvn spring-boot:run 



Additional Info:

Docker images <br/>
krangan/oauth-server<br/>
krangan/web-client<br/>
