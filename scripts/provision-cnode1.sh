#! /bin/sh

. /vagrant/scripts/settings.sh
. /vagrant/scripts/exports.sh

sh /vagrant/scripts/fix-hosts.sh


# Install Java 8
sudo add-apt-repository ppa:webupd8team/java -y
sudo apt-get -y -q update
sudo echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
sudo apt-get -y -q install oracle-java8-installer
sudo update-java-alternatives -s java-8-oracle

# Install Cassandra
echo "deb http://debian.datastax.com/community stable main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
sudo apt-get update
sudo apt-get install dsc22 cassandra=2.2.6 -y --force-yes
#apt-get -y -q install cassandra-tools

sudo ufw disable