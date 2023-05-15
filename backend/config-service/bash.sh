CONTAINER_NAME=config-server

if [ $( docker ps -a | grep ${CONTAINER_NAME} | wc -l ) -gt 0 ]; then
  docker stop ${CONTAINER_NAME}
  docker rm ${CONTAINER_NAME}
fi

docker build -t ${CONTAINER_NAME} .
docker run -d --name ${CONTAINER_NAME} -p 8888:8888 ${CONTAINER_NAME}