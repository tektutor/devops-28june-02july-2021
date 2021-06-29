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
docker restart ubuntu`
```

### Getting inside a running container
```
docker exec -it ubuntu1 bash
```
