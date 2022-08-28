#!/bin/bash -x

export TIMESTAMP=$(date +'%d%m%Y')
docker image build . --tag jenkinsalpine:$TIMESTAMP

if [[ $? -ne '0' ]]; then
    echo "docker image wasn't built successfully"
else
    echo "docker image build successfully"
    envsubst < jenkinsDeployment.yaml > jenkinsDeploymentCompleted.yaml 
    mv jenkinsDeploymentCompleted.yaml jenkinsDeployment.yaml
    kubectl apply -f .
fi