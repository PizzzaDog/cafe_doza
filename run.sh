git pull
docker stop doza_back_con || true
docker rm doza_back_con || true
docker build -t doza_back .
docker run -d -p 8088:8088 --name doza_back_con doza_back