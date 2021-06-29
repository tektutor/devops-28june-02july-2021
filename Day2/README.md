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
