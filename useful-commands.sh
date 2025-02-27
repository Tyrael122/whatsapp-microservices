
kubectl port-forward --address 0.0.0.0 services/rabbitmq 8083:5672
kubectl port-forward --address 0.0.0.0 services/chat-service 8082:8080
kubectl port-forward --address 0.0.0.0 services/user-service 8081:8080

minikube service rabbitmq --url

kubectl rollout restart deployment user-service
