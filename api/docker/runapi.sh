#!/usr/bin/env bash

# Run the API Container
docker run -p 8080:8080 --name autocafeapi -d -t -i autocafeapi
#CID=$(docker run -p 192.168.99.100:80:80 --name autocafeapi -d -t -i autocafeapi);
#CID=$(docker run -p 8080:8080 --name autocafeapi -d -t -i autocafeapi);
#echo "Container Id is:${CID}"
#docker inspect --format '{{ .NetworkSettings.IPAddress }}' ${CID}