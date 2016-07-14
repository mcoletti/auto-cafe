#!/usr/bin/env bash

export HOSTNAME=`hostname`
export DOMAIN="autocafe.saat.com"
export FQDN=${HOSTNAME}.${DOMAIN}
export DEBIAN_FRONTEND=noninteractive
export IPADDR=`ifconfig eth1 | grep "inet addr" | sed -e 's/.*inet addr://g' | sed -e 's/Bcast.*//g'`
export DELAY=60
export LANG=en_US
export LC_ALL=en_US.UTF-8
export LC_CTYPE=en_US.UTF-8
export LC_MESSAGES=en_US.UTF-8
