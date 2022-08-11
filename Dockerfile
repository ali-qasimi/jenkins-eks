FROM jenkins/jenkins:alpine

EXPOSE 8080

COPY --chown=jenkins:jenkins ./plugins.txt .

RUN ls -lart && id jenkins

RUN jenkins-plugin-cli --plugin-file ./plugins.txt