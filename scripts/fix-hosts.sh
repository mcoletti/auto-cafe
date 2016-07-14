#!/usr/bin/env bash

# modify /etc/hosts file
sed -e 's/127\.0\.1\.1.*$//g' -i.bak /etc/hosts
sed -e "s/${IPADDR}.*$//g" -i.bak /etc/hosts
echo "${IPADDR}\t${FQDN}  ${HOSTNAME}" >> /etc/hosts