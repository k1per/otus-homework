Должен быть устанвленный minikube/kubectl/helm
Устанавливаем базу из helm-а
helm install pg bitnami/postgresql -f ./helm/postgres-db-helm-values.yaml

Первоначальная миграция не нужна. Будет произведена с помощью Flyway при старте приложения в контейнере
(скрипт первоначальной миграции src/main/resources/db/migration/V1__create.sql)

Добавляем репозиторий prometheus community и устанавливаем
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add stable https://charts.helm.sh/stable
helm repo update
helm install prom prometheus-community/kube-prometheus-stack -f ./helm/prometheus/prometheus.yaml --atomic

Устанавливаем helm repo add ingress-nginx
minikube addons disable ingress(непонятно правда зачем)
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm install nginx ingress-nginx/ingress-nginx -f ./helm/nginx-ingress.yaml --atomic

Делаем доступную графану извне кластера(то есть локалхост)
kubectl port-forward service/prom-grafana 9000:80
проверяем :) 
localhost:9000 
Пароль -> admin / prom-operator или тут глянуть можно kubectl get secret prom-grafana -o jsonpath={.data}

Также доступ к prometheus
kubectl port-forward service/prom-kube-prometheus-stack-prometheus 9090
localhost:9090 

Устанвливаем все
kubectl apply -f ./k8s

проверяем
curl http://arch.homework/actuator/health
swagger-ui тут http://arch.homework/swagger-ui/#/user

postman коллекция находится в папке postman либо
Получить пользователя
curl --location --request GET 'arch.homework/api/v1/user/1'

Обновить пользователя
curl --location --request PUT 'arch.homework/api/v1/user/1' \
--header 'Content-Type: application/json' \
--data-raw '{
"email": "lost",
"firstName": "Antonious",
"id": 1,
"lastName": "Korenevius",
"phone": "+42",
"username": "k1p3r2177"
}'

Смотрим изменения
curl --location --request GET 'arch.homework/api/v1/user/1'

Создать пользователя
curl --location --request POST 'arch.homework/api/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{
"email": "none",
"firstName": "Tyrion",
"lastName": "Lanisster",
"phone": "send the raven",
"username": "devil"
}'

Смотрим созданного пользователя
curl --location --request GET 'arch.homework/api/v1/user/2'

Удаляем
curl --location --request DELETE 'arch.homework/api/v1/user/2'


