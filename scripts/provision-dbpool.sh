#! /bin/sh

. /vagrant/scripts/settings.sh
. /vagrant/scripts/exports.sh

#echo "deb http://apt.postgresql.org/pub/repos/apt/ squeeze-pgdg main" | tee -a /etc/apt/sources.list
#
#wget --quiet -O - http://apt.postgresql.org/pub/repos/apt/ACCC4CF8.asc | sudo apt-key add -
#
#apt-get update
#
#apt-get install pgpool2=3.3.2-1ubuntu1 libpgpool0=3.3.2-1ubuntu1
#
#PGPOOLCFG="/etc/pgpool2/pool_hba.conf"
#
#cat >> ${PGPOOLCFG} <<HOSTCONF
#host    all             all             10.100.10.0/22           md5
#host    all             all             ${DEFAULT_DB_HOST_NET}/24    md5
#HOSTCONF
#
#service pgpool2 restart

