FROM    centos:centos6

# Enable EPEL for Node.js
# The Fedora Extra Packages for Enterprise Linux (EPEL) repository provides useful software packages that are not included in the official CentOS or Red Hat Enterprise Linux repositories.
RUN     rpm -Uvh http://download.fedoraproject.org/pub/epel/6/i386/epel-release-6-8.noarch.rpm
# Install Node.js and npm
RUN     yum install -y npm

# Bundle app source
COPY . /src
# Install app dependencies
RUN cd /src; npm install http-server -g

EXPOSE  8080
WORKDIR /src
CMD ["http-server"]
