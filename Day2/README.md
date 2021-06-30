### Listing docker images
```
docker images
```

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

```
curl http://172.17.0.2:80
curl http://172.17.0.3
curl http://172.17.0.4
```

### Cleanup the existing container
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
You may also replace the localhost with the IP Address of your lab machine and let your colleague access your web page.

### 
