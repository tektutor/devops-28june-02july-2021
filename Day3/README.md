### Create ssh key-pairs for rps user
```
ssh-keygen
```
Make sure you accept all defaults by hitting enter key thrice :)


### Building ansible ubuntu custom image
```
cd Day3/ubuntu-ansible
cp /home/rps/.ssh/id_rsa.pub authorized_keys
docker build -t tektutor/ansible-node-ubuntu:latest .
```

### Check if the new image is listed in local registry
```
docker images
```

### Testing the image
In order to test the image, we need to
1. Create couple of containers using our custom image
2. See if we can perform ssh login with key-based login authentication

#### Create two containers using custom image
```
docker run -d --name ubuntu1 --hostname ubuntu1 -p 2001:22 tektutor/ansible-node-ubuntu:latest 
docker run -d --name ubuntu2 --hostname ubuntu2 -p 2002:22 tektutor/ansible-node-ubuntu:latest 
```

#### Check if the ubuntu1 and ubuntu2 containers are running
```
docker ps --filter "name=ubuntu*"
```
The expected output is shown below
<pre>
[jegan@tektutor DevOps]$ docker ps --filter "name=ubuntu*"
CONTAINER ID   IMAGE                                 COMMAND               CREATED             STATUS             PORTS                                           NAMES
3d829e1b0630   tektutor/ansible-node-ubuntu:latest   "/usr/sbin/sshd -D"   About an hour ago   Up About an hour   80/tcp, 0.0.0.0:2002->22/tcp, :::2002->22/tcp   ubuntu2
e8b6914e6322   tektutor/ansible-node-ubuntu:latest   "/usr/sbin/sshd -D"   About an hour ago   Up About an hour   80/tcp, 0.0.0.0:2001->22/tcp, :::2001->22/tcp   ubuntu1
[jegan@tektutor DevOps]$ 
</pre>

#### Verify if you can login without password
```
ssh -p 2001 root@localhost
ssh -p 2002 root@localhost
```
When it prompts with question "Are you sure you want to continue connecting (yes/no): ?" You type yes

### Building ansible centos custom image
```
cd Day3/centos-ansible
cp /home/rps/.ssh/id_rsa.pub authorized_keys
docker build -t tektutor/ansible-node-centos:latest .
```

### Check if the new image is listed in local registry
```
docker images
```
You should get a similar output as shown below
<pre>
[jegan@tektutor DevOps]$ docker images
REPOSITORY                                TAG       IMAGE ID       CREATED             SIZE
tektutor/ansible-node-centos              latest    944801bbdcd0   14 minutes ago      250MB
tektutor/ansible-node-ubuntu              latest    390b04b9ddf6   About an hour ago   220MB
tektutor/nginx-lb                         1.0       7735899f0eac   20 hours ago        133MB
nginx                                     1.20      7ca45f2d188b   7 days ago          133MB
mysql                                     latest    5c62e459e087   7 days ago          556MB
ubuntu                                    16.04     065cf14a189c   12 days ago         135MB
docker.bintray.io/jfrog/artifactory-oss   latest    b459de446b90   13 days ago         787MB
centos                                    8         300e315adb2f   6 months ago        209MB
[jegan@tektutor DevOps]$ 
</pre>

### Testing the image

#### Create two containers using custom image
```
docker run -d --name centos1 --hostname centos1 -p 2003:22 tektutor/ansible-node-centos:latest 
docker run -d --name centos2 --hostname centos2 -p 2004:22 tektutor/ansible-node-centos:latest 
```

#### Check if the centos1 and centos2 containers are running
```
docker ps --filter "name=centos*"
```
The expected output is shown below
<pre>
[jegan@tektutor DevOps]$ docker ps --filter "name=centos*"
CONTAINER ID   IMAGE                                 COMMAND               CREATED          STATUS          PORTS                                           NAMES
eeadfc6ce4dd   tektutor/ansible-node-centos:latest   "/usr/sbin/sshd -D"   19 minutes ago   Up 19 minutes   80/tcp, 0.0.0.0:2004->22/tcp, :::2004->22/tcp   centos2
a9ea5f9a1405   tektutor/ansible-node-centos:latest   "/usr/sbin/sshd -D"   19 minutes ago   Up 19 minutes   80/tcp, 0.0.0.0:2003->22/tcp, :::2003->22/tcp   centos1
[jegan@tektutor DevOps]$
</pre>

#### Verify if you can login without password
```
ssh -p 2003 root@localhost
ssh -p 2004 root@localhost
```
When it prompts with question "Are you sure you want to continue connecting (yes/no): ?" You type yes

If you are able to perform ssh the above demonstrated way, you are all set!
