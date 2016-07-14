#! /bin/sh

. /vagrant/scripts/settings.sh
. /vagrant/scripts/exports.sh

sh /vagrant/scripts/fix-hosts.sh


apt-get update -y

# MySQL
echo "Preparing MySQL"

echo "Installing MySQL"
apt-get install -y --no-install-recommends mysql-server

# Configure server.
mysql -e "GRANT ALL PRIVILEGES ON *.* to 'root'@'%' identified by 'pa55word!' WITH GRANT OPTION;"
mysql -e "CREATE SCHEMA autocafedb DEFAULT CHARACTER SET utf8 ;"
#mysql -e "FLUSH PRIVILEGES;"

cp /vagrant/tools/configuration/mysql/custom.cnf /etc/mysql/conf.d/z-custom.cnf

service mysql restart

echo "Finished provisioning."


#sudo apt-key adv --recv-keys --keyserver hkp://keyserver.ubuntu.com:80 0xcbcb082a1bb943db
#sudo add-apt-repository 'deb [arch=amd64,i386,ppc64el] http://mirrors.accretive-networks.net/mariadb/repo/10.0/ubuntu trusty main'
#sudo apt-get update -y
#
#sudo DEBIAN_FRONTEND=noninteractive apt-get install mariadb-server-10.0 -y
#
## Configure server.
#mysql -e "GRANT ALL PRIVILEGES ON *.* to 'root'@'%' identified by 'pa55word!' WITH GRANT OPTION;"
#mysql -e "CREATE SCHEMA ${DEFAULT_DB_NAME} DEFAULT CHARACTER SET utf8 ;"
##mysql -e "FLUSH PRIVILEGES;"
#
#cp /vagrant/scripts/configuration/mysql/custom.cnf /etc/mysql/conf.d/z-custom.cnf
#
#sudo service mysql restart



## determine version of ubuntu
#OS_TYPE=`cat /etc/issue | cut -d ' ' -f 1`
#OS_VER=`cat /etc/issue | cut -d ' ' -f 2 | cut -d '.' -f 1`
#
#case ${OS_VER} in
#    12)
#        PG_VERSION=9.1
#        ;;
#    14)
#        PG_VERSION=9.3
#        ;;
#esac
##PG_VERSION=9.3
#PGCFG="/etc/postgresql/${PG_VERSION}/main/postgresql.conf"
#PGHBA="/etc/postgresql/${PG_VERSION}/main/pg_hba.conf"
#PGPOOLCFG="/etc/pgpool2/pool_hba.conf"
#
#sh /vagrant/tools/fix-hosts.sh
#
#apt-get update -y
#apt-get install -y postgresql-${PG_VERSION}
#
#sed -e "s/^#listen_addresses/listen_addresses = '*'\n&/g" -i.bak ${PGCFG}
#cat >> ${PGHBA} <<HOSTCONF
#host    all             all             10.100.10.0/22           md5
#host    all             all             ${DEFAULT_DB_HOST}/24    md5
#HOSTCONF
#
#
#
#service postgresql restart
#
#su - postgres -c "echo \"alter user postgres with password 'postgres'\" | psql"
#su - postgres -c "echo \"create user ${DEFAULT_DB_USER} with encrypted password '${DEFAULT_DB_PASS}' createdb\" | psql"
#su - postgres -c "echo \"create database ${DEFAULT_DB_NAME} with owner ${DEFAULT_DB_USER} encoding 'UTF-8'\" | psql"
#
#cat /vagrant/scripts/exports.sh >> ~vagrant/.bashrc
#
#for PASSFILE in ~/.pgpass ~vagrant/.pgpass
#do
#    echo "localhost:${DEFAULT_DB_PORT}:*:postgres:postgres" > ${PASSFILE}
#    echo "localhost:${DEFAULT_DB_PORT}:*:${DEFAULT_DB_USER}:${DEFAULT_DB_PASS}" >> ${PASSFILE}
#    echo "${DEFAULT_DB_HOST}:${DEFAULT_DB_PORT}:*:${DEFAULT_DB_USER}:${DEFAULT_DB_PASS}" >> ${PASSFILE}
#    chmod 600 ${PASSFILE}
#    chown vagrant:vagrant ${PASSFILE}
#done