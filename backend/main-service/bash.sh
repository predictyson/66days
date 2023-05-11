CONTAINER_NAME=spring-server

if [ $( docker ps -a | grep ${CONTAINER_NAME} | wc -l ) -gt 0 ]; then
  docker stop ${CONTAINER_NAME}
  docker rm ${CONTAINER_NAME}
fi

docker build -t spring-server .
docker run -d --name ${CONTAINER_NAME} -p 8081:8080 spring-server