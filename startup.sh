#!/bin/bash -x

docker image build .

if [[ $? -ne '0' ]]; then
    echo "docker image wasn't built successfully"
else
    echo "docker image build successfully"
    kubectl apply -f .
fi