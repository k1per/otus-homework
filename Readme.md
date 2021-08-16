Должен быть устанвленный minikube/kubectl/helm/istio

запускаем minikube
minikube start \
--cpus=4 --memory=8g \
--cni=flannel \
--kubernetes-version="v1.19.0" \
--extra-config=apiserver.enable-admission-plugins=NamespaceLifecycle,LimitRanger,ServiceAccount,DefaultStorageClass,\
DefaultTolerationSeconds,NodeRestriction,MutatingAdmissionWebhook,ValidatingAdmissionWebhook,ResourceQuota,PodPreset \
--extra-config=apiserver.authorization-mode=Node,RBAC

создаем namespaces под операторы
kubectl apply -f ./k8s-istio/namespaces.yaml

Устанавливаем Jaeger (решение для трассировки. Сюда будет отправлять инфорация по нашим запросам которые выходят из пода)
Добавляем репу/обновляем/устанавливаем оператор разворачивающим Jaeger/разворачиваем jaeger
helm repo add jaegertracing https://jaegertracing.github.io/helm-charts
helm install --version "2.19.0" -n jaeger-operator -f ./helm/jaeger/operator-values.yaml \
jaeger-operator jaegertracing/jaeger-operator
kubectl apply -f ./k8s-istio/jaeger.yaml

Проверяем состояние/открываем web-interface
kubectl get po -n jaeger -l app.kubernetes.io/instance=jaeger
minikube service -n jaeger jaeger-query-nodeport

Разворачиваем Prometheus
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add stable https://kubernetes-charts.storage.googleapis.com/
helm repo update
helm install --version "13.7.2" -n monitoring -f ./helm/prometheus/prometheus.yaml prometheus \
prometheus-community/kube-prometheus-stack

Проверяем состояние
kubectl get po -n monitoring
Добавляем прямой доступ
kubectl apply -f ./k8s-istio/prometheus-nodeport.yaml

Открываем web-интерфейс grafana / prometheus
minikube service -n monitoring prometheus-grafana-nodeport
minikube service -n monitoring prom-prometheus-nodeport

Разворачиваем Istio
Устанавливаем оператор разворачивающий istio
istioctl operator init --watchedNamespaces istio-system --operatorNamespace istio-operator
Разворачиваем Istio
kubectl apply -f ./k8s-istio/istio.yaml
Проверяем состояние istio
kubectl get all -n istio-system -l istio.io/rev=default

Устанавливаем Kiali(доска управление service-mesh) добавляем репу/обновляем/устанавливаем оператор/устанавливаем kiali
helm repo add kiali https://kiali.org/helm-charts
helm repo update
helm install --version "1.33.1" -n kiali-operator kiali-operator kiali/kiali-operator
kubectl apply -f ./k8s-istio/kiali.yaml

Проверяем состояние и открываем веб интерфейс
kubectl get po -n kiali -l app.kubernetes.io/name=kiali
minikube service -n kiali kiali-nodeport

Устанвливаем все две версии приложения
kubectl apply -f ./k8s

устанвливаем gateway + rules + virtual-service
kubectl apply -f ./k8s-istio/gateway.yaml
kubectl apply -f ./k8s-istio/rules.yaml
kubectl apply -f ./k8s-istio/virtual-service.yaml

ssh minikube
curl {SERVICE-NODE-PORT}/actuator/health


