FROM java:8
VOLUME /tmp
ADD *.jar web-client.jar
RUN bash -c 'touch /web-client.jar'
ENTRYPOINT ["java","-jar","/web-client.jar"]
