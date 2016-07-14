#!/usr/bin/env bash

BUILD_VERSION=latest
while [ $# -gt 0 ]
do
    case "$1" in
        -v) BUILD_VERSION=$2;;
     esac
     shift
done

# Build the Docker Image
#if [ $BUILD_VERSION = "latest" ]; then
#   docker build -t autocafedb .
#else
#   docker build -t autocafedb:$BUILD_VERSION .
#fi

 docker build -t autocafedb .