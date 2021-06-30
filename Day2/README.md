## Managing Docker Images in Local Docker Registry

### You may download an image from Docker hub using the below command
```
docker pull hello-world:latest
docker pull ubuntu:16.04
```

### Listing docker images
```
docker images
```

### You may delete an image using the below command
In case you have some containers created out of the hello-world:latest, you need to delete it first before attempting to delete
the image.
```
docker rmi hello-world:latest
```

### Creating a custom docker image

#### Create a tmp nginx container to copy the nginx.conf file
```
docker run -d --name tmp --hostname tmp nginx:1.20
docker cp tmp:/etc/nginx/nginx.conf .
```

#### Edit the nginx.conf locally as shown below
Assuming 
<pre>
  172.17.0.2 is the IP address of nginx1 container
  172.17.0.3 is the IP address of nginx2 container
  172.17.0.4 is the IP address of nginx3 container  
</pre>

nginx.conf file contents below
```
user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}

http {
    server {
       location / {
		proxy_pass http://backend;
     }
    }

    upstream backend {
        server 172.17.0.2:80; 
        server 172.17.0.3:80;
        server 172.17.0.4:80;
    }
}
```

Create a Dockerfile with the below content

```
FROM nginx:1.20
MAINTAINER Jeganathan Swaminathan <jegan@tektutor.org>

COPY nginx.conf /etc/nginx/nginx.conf
```

Build the docker image
```
docker build -t tektutor/nginx-lb .
```

#### Check if you can see your custom image
```
docker images
```

## Managing Containers
Containers can be 
  1. Created in interactive mode or background mode
  2. Containers can be started, listed, stopped, restarted, deleted
  3. You may also rename an existing containers

### Create a container in interactive mode
```
docker run -it --name ubuntu1 --hostname ubuntu1 ubuntu:16.04 /bin/bash
````

### Listing the currently running containers( from another terminal )
```
docker ps
```

### Stopping a container
```
docker stop ubuntu1
```

### Start a stopped container
```
docker start ubuntu1
```

### Restarting a container
```
docker restart ubuntu1
```

### Getting inside a running container
```
docker exec -it ubuntu1 bash
```

### Deleting a container
```
docker stop ubuntu1
docker rm ubuntu1
```

### Deleting a container forcibly while the container is still running
```
docker rm -f ubuntu1
```

### Finding IP Address of the container
```
docker inspect | grep IPA
docker inspect -f {{.NetworkSettings.IPAddress}} ubuntu1
```

### Create an nginx web server container
```
docker run -d --name nginx1 --hostname nginx1 nginx:1.20
docker run -d --name nginx2 --hostname nginx2 nginx:1.20
docker run -d --name nginx3 --hostname nginx3 nginx:1.20
```

### Listing all nginx containers
```
docker ps --filter "name=nginx*"
```

### Find the IPAddresses of nginx containers
```
docker inspect nginx1
docker inspect nginx2 | grep IPAddress
docker inspect -f {{.NetworkSettings.IPAddress}} nginx3
```
### Accessing the nginx web pages
Assuming
<pre>
  172.17.0.2 is the IPAddress of nginx1
  172.17.0.3 is the IPAddress of nginx2
  172.17.0.4 is the IPAddress of nginx3
</pre>

You may access the respective web pages from nginx1, nginx2 and nginx3 containers as shown below
```
curl http://172.17.0.2:80
curl http://172.17.0.3
curl http://172.17.0.4
```

### Cleanup the existing nginx container
```
docker rm -f $(docker ps -aq --filter "name=nginx*")
```

### Port forwarding
```
docker run -d --name nginx1 --hostname nginx1 -p 8001:80 nginx:1.20 
docker run -d --name nginx2 --hostname nginx2 -p 8002:80 nginx:1.20 
docker run -d --name nginx3 --hostname nginx3 -p 8003:80 nginx:1.20 
```

### Test - try to access the web pages from your lab machine web browser
```
http://localhost:8001
http://localhost:8002
http://localhost:8003
```
You may also replace the localhost with the IP Address of your lab machine and let your colleagues access your web page.

## Setting up a load balancer with nginx

### Delete any existing containers
```
docker rm -f $(docker ps -aq)
```

### Create 3 or more nginx web servers
```
docker run -d --name nginx1 --hostname nginx1 nginx:1.20
docker run -d --name nginx2 --hostname nginx2 nginx:1.20
docker run -d --name nginx3 --hostname nginx3 nginx:1.20
```

### Create a load balancer container
```
docker run -d --name lb --hostname lb -p 80:80 tektutor/nginx-lb:1.0
```

### Update the index.html web page in nginx1, nginx2 and nginx3 containers
```
docker exec -it nginx1 bash
echo "Server 1" > /usr/share/nginx/html/index.html
exit

docker exec -it nginx2 bash
echo "Server 2" > /usr/share/nginx/html/index.html
exit

docker exec -it nginx3 bash
echo "Server 3" > /usr/share/nginx/html/index.html
exit
```

### Test load balancer setup now
```
curl http://localhost
curl http://localhost
curl http://localhost
```
Each time you try the above url, you are supposed to get outputs similar to in round robin fashion. 
<pre>
  Server 1
  Server 2 
  Server 3
</pre>
