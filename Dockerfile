FROM jenkins/jenkins:alpine

EXPOSE 8080

COPY --chown=jenkins:jenkins ./plugins.txt .

RUN jenkins-plugin-cli --plugin-file ./plugins.txt --verbose

COPY --chown=jenkins:jenkins ./init.groovy.d $JENKINS_HOME/init.groovy.d

RUN ls -lart $JENKINS_HOME && id jenkins

