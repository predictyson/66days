CONTAINER_NAME=spring-server

if [ $( docker ps -a | grep ${CONTAINER_NAME} | wc -l ) -gt 0 ]; then
  docker stop ${CONTAINER_NAME}
  docker rm ${CONTAINER_NAME}
fi

docker build -t ${CONTAINER_NAME} .
docker run -d -v /home/ubuntu/image:/home/ubuntu/image --name ${CONTAINER_NAME} -p 8083:8080 ${CONTAINER_NAME}
