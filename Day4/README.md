### Building custom Docker image with regular user(non-admin)
```
cd Day4/ubuntu-ansible
cp /home/rps/.ssh/id_rsa.pub authorized_keys
docker build -t tektutor/ansible-ubuntu-sudo:latest .
```

### Verify if the new image built is listed
```
docker images
```

### Create a test container using the custom image
```
docker run -d --name test --hostname test -p 2005:22 tektutor/ansible-ubuntu-sudo:latest 
```

### See if the container is running
```
docker ps
```

### Test if you can perform ssh to the container
```
ssh -p tektutor@localhost
```

### Running the playbook
```
ansible-playbook install-playbook.yml
```
