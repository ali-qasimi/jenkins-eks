FROM alpine:latest

EXPOSE 80
RUN curl https://get.jenkins.io/war-stable/latest/jenkins.war

