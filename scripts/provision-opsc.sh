#! /bin/sh

. /vagrant/scripts/settings.sh
. /vagrant/scripts/exports.sh

sh /vagrant/scripts/fix-hosts.sh


sudo echo "deb http://debian.datastax.com/community stable main" | sudo tee -a /etc/apt/sources.list.d/datastax.community.list
sudo wget -q -O - http://debian.datastax.com/debian/repo_key | sudo apt-key add -

sudo apt-get update

# install OpsCenter and a few base packages
sudo apt-get install vim curl zip unzip git python-pip opscenter -y --force-yes

# start OpsCenter
sudo service opscenterd start

echo "Vagrant provisioning complete and OpsCenter started"
