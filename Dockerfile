FROM tomcat:9.0
LABEL maintainer="darrenchoojuninn@gmail.com"
COPY /target/*.jar /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
