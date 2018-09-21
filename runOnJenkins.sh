docker stop gateway-m
docker rm gateway-m
docker image rm gateway-m:0.0.1
docker build . -t gateway-m:0.0.1
docker run -it -d -p 5000:5000 --name gateway-m --network crm-network --network-alias alias-gateway-m --link redis gateway-m:0.0.1
