# Infrastructure

## base install on docker

### postgresql

```bash
sudo docker pull postgres
sudo docker volume create pg-data
sudo docker run --restart=always --name [db_name] -e POSTGRES_PASSWORD=[user_pwd] -e POSTGRES_USER=[user_name] -v pg-data:/var/lib/postgresql/data -d -p 5432:5432 postgres
```

### redis

```bash
sudo docker pull redis
sudo docker volume create redis-data
sudo docker run --restart=always --name [redis_name] -v redis-data:/data -d -p 6379:6379 redis redis-server --appendonly yes
```

### mongodb

```bash
sudo docker pull mongo
sudo docker volume create mongo-data
sudo docker run --restart=always --name [mongo_name] -p 27017:27017 -v mongo-data:/data/db -d mongo
```

### jenkins

```bash
sudo docker pull jenkins
sudo docker volume create jenkins-data
sudo docker run --restart=always -d --name [jenkins_name] -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home jenkins
```