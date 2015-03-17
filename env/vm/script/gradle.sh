#!/bin/bash -eux

echo "==> Installing Java"
JDK_VERSION=8u40
JDK_BUILD_VERSION=b25
curl -LO "http://download.oracle.com/otn-pub/java/jdk/$JDK_VERSION-$JDK_BUILD_VERSION/jdk-$JDK_VERSION-linux-x64.rpm" -H 'Cookie: oraclelicense=accept-securebackup-cookie' && rpm -i jdk-$JDK_VERSION-linux-x64.rpm && rm -f jdk-$JDK_VERSION-linux-x64.rpm
ENV JAVA_HOME /usr/java/default

echo "==> Installing GVM"
yum -y install unzip git
yum clean all

echo "==> Pulling Docker images"
docker pull webdizz/sonarqube-plugins:5.0.1

docker pull mattgruter/artifactory

docker pull mysql:5.6

echo "==> Installing Gradle"
curl -s get.gvmtool.net | bash

source "${SSH_USER_HOME}/.gvm/bin/gvm-init.sh"
gvm install gradle 2.2
gvm d gradle 2.2
mv /root/.gvm /home/vagrant/

echo "gvm_auto_answer=true" >> /home/vagrant/.gvm/etc/config
echo "source ~/.gvm/bin/gvm-init.sh" >> /home/vagrant/.bash_profile

chown -R vagrant:vagrant /home/vagrant/.gvm
echo "Looks like everything was done, let's clean everything up"