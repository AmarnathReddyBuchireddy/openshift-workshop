# Hello 

Simple Spring Boot Standalone app to play around with different Kubernetes functionalities.
It provides the following endpoints:

- / just retuning a hello String
- /host returning the current hostname
- /health exposes the current actuator health state
- /health-unstable randomly creates errors
- /shutdown exits the JVM after called 4 times (Kubernetes should then restart the container)

## Install Application Image into a local Minishift Registry

- Start minishift (for docker-machine users: `minishift start --vm-driver=virtualbox`),
  optionally tweak memory with `--memory 4096` (default is 2048 MB and 2 CPUs).
- Login to Openshift: `oc login -u developer -p developer`
- Set the local docker environment:
  - Linux: `eval $(minishift docker-env)`
  - Windows `@FOR /f "tokens=*" %i IN ('minishift docker-env') DO @call %i`
- Login to the docker registry:
  - Linux: `docker login -u developer -p $(oc whoami -t) $(minishift openshift registry)`
  - Windows: evaluate the expression `oc whoami -t & minishift openshift registry` and replace them in the command
- Build the image with `docker build -t demo .`
- Tag the built docker image:
  - Linux: `docker tag demo $(minishift openshift registry)/myproject/demo
  - Windows: first execute `minishift openshift registry` and put the $OUTPUT to `docker tag demo $OUTPUT/myproject/demo`
- Push the built image to the openshift registry: `docker push $(minishift openshift registry)/myproject/demo`
- Ensure the _deployment.xml_ "image" entry matches the pushed name (`172.30.1.1:5000/myproject/demo` ,
  check the prefix by executing `minishift openshift registry`)

## Install Application Image into DockerHub

- Start minishift (for docker-machine users: `minishift start --vm-driver=virtualbox`),
  optionally tweak memory with `--memory 4096` (default is 2048 MB and 2 CPUs).
- Login to Openshift: `oc login -u developer -p developer`
- Set the local docker environment:
  - Linux: `eval $(minishift docker-env)`
  - Windows `@FOR /f "tokens=*" %i IN ('minishift docker-env') DO @call %i`

- Login to the docker registry: `docker login -u myuser`
- Build the image with `docker build -t demo .`
- Tag the built docker image: `docker tag demo myuser/demo`
- Push the built image to the openshift registry: `docker push myuser/demo`
- Ensure the _deployment.xml_ "image" entry matches the pushed name (`myuser/demo`)
