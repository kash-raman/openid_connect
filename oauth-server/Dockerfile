FROM java:8
VOLUME /tmp
ADD *.jar oauth-server.jar
RUN bash -c 'touch /oauth-server.jar'
ENTRYPOINT ["java","-jar","/oauth-server.jar"]
