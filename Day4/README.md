### Building custom Docker image with regular user(non-admin)
```
cd Day4/ubuntu-ansible
cp /home/rps/.ssh/id_rsa.pub authorized_keys
docker build -t tektutor/ansible-ubuntu-sudo:latest .
```

### Verify if the new image built is listed
```
docker images --filter "reference=tektutor/ansible-ubuntu-sudo*"
```
The expected output is
<pre>
jegan@tektutor Day4]$ docker images --filter "reference=tektutor/ansible-ubuntu-sudo*"
REPOSITORY                     TAG       IMAGE ID       CREATED          SIZE
tektutor/ansible-ubuntu-sudo   latest    e3dc1e5dcd4f   18 minutes ago   222MB
</pre>

### Create a test container using the custom image
```
docker run -d --name test --hostname test -p 2005:22 tektutor/ansible-ubuntu-sudo:latest 
```

### See if the container is running
```
docker ps
```
The expected output is
<pre>
jegan@tektutor Day4]$ docker ps --filter "name=test"
CONTAINER ID   IMAGE                                 COMMAND               CREATED          STATUS          PORTS                                           NAMES
5ef865cb2a68   tektutor/ansible-ubuntu-sudo:latest   "/usr/sbin/sshd -D"   11 minutes ago   Up 11 minutes   80/tcp, 0.0.0.0:2005->22/tcp, :::2005->22/tcp   test
[jegan@tektutor Day4]$ ssh -p 2005 tektutor@localhost
</pre>

### Test if you can perform ssh to the container
```
ssh -p 2005 tektutor@localhost
```
The expected output is
<pre>
[jegan@tektutor Day4]$ docker ps --filter "name=test"
CONTAINER ID   IMAGE                                 COMMAND               CREATED          STATUS          PORTS                                           NAMES
5ef865cb2a68   tektutor/ansible-ubuntu-sudo:latest   "/usr/sbin/sshd -D"   11 minutes ago   Up 11 minutes   80/tcp, 0.0.0.0:2005->22/tcp, :::2005->22/tcp   test
[jegan@tektutor Day4]$ ssh -p 2005 tektutor@localhost
Welcome to Ubuntu 16.04.7 LTS (GNU/Linux 4.18.0-240.el8.x86_64 x86_64)

 * Documentation:  https://help.ubuntu.com
 * Management:     https://landscape.canonical.com
 * Support:        https://ubuntu.com/advantage
Last login: Thu Jul  1 05:31:54 2021 from 172.17.0.1
tektutor@test:~$ 
</pre>

### Running the playbook that install vim onto the image with tektutor user
```
ansible-playbook install-playbook.yml
```

### Running the install nginx playbook on dev group(ubuntu machines)
```
ansible-playbook install-nginx-playbook.yml
```
