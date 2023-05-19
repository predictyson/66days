CONTAINER_NAME=apigateway-server

if [ $( docker ps -a | grep ${CONTAINER_NAME} | wc -l ) -gt 0 ]; then
  docker stop ${CONTAINER_NAME}
  docker rm ${CONTAINER_NAME}
fi

docker build -t ${CONTAINER_NAME} .
docker run -d --name ${CONTAINER_NAME} -p 8000:8000 ${CONTAINER_NAME}