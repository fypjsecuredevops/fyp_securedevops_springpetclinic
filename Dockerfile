FROM tomcat:9.0
LABEL maintainer="darrenchoojuninn@gmail.com"
COPY /target/*.war /usr/local/tomcat/webapps/spring-petclinic.war
CMD ["catalina.sh", "run"]
