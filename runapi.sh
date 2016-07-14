#!/usr/bin/env bash

# Run the API Container
CID=$(docker run -p 192.168.99.100:80:80 --name autocafeapi -d -t -i autocafeapi);
echo "Container Id is:${CID}"
docker inspect --format '{{ .NetworkSettings.IPAddress }}' ${CID}