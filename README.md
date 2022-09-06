# jenkins-k8s
Jenkins Deployment on a Kubernetes cluster running on minikube.

## Features
- Custom Jenkins docker image built with selected plugins
- Provisioning jenkins-admin service account
- Deployment of a persistent volume mounted onto the /var/jenkins_home/jobs directory. Used to store all the Jenkins jobs
- The Jenkins master pod deployment
- Groovy script(s) in init.groovy.d used to auto-configure the Jenkins platform during startup. At this stage it configures the Kubernetes plugin to provision pods as Jenkins agents. 

## Requirements

- minikube v1.26.0 & above
- kubectl v1.24.1 & above

## Setup

1. Create the minikube node: <br>
    `minikube start`
2. Enable docker to pull images from minikube's local registry: <br>
    `eval $(minikube docker-env)`
3. Run the startup script. This will build the docker image and deploy all the kubernetes resources: <br>
    `./startup.sh`
