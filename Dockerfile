FROM jenkins/jenkins:alpine

EXPOSE 8080

COPY --chown=jenkins:jenkins ./plugins.txt .

RUN jenkins-plugin-cli --plugin-file ./plugins.txt --verbose

ADD --chown=jenkins:jenkins init.groovy.d /var/jenkins_home/init.groovy.d

RUN ls -lart && pwd

