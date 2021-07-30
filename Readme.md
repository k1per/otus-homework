Устанавливаем базу из helm-а
helm install pg bitnami/postgresql -f ./helm/postgres-db-helm-values.yaml

Первоначальная миграция не нужна. Будет произведена с помощью Flyway при старте приложения в контейнере
(скрипт первоначальной миграции src/main/resources/db/migration/V1__create.sql)

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


