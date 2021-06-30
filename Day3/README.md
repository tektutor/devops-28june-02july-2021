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

#### Create two containers using custom image
```
docker run -d --name ubuntu1 --hostname ubuntu1 -p 2001:22 tektutor/ansible-node-ubuntu:latest 
docker run -d --name ubuntu2 --hostname ubuntu2 -p 2002:22 tektutor/ansible-node-ubuntu:latest 
```

#### Verify if you can login without password
```
ssh -p 2001 root@localhost
ssh -p 2002 root@localhost
```
When it prompts with question "Are you sure you want to continue connecting (yes/no): ?" You type yes

If you are able to perform ssh the above demonstrated way, you are all set!

