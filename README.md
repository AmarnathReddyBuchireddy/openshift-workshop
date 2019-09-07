# Trivadis/RedHat Openshift Workshop

This workshop gives you a brief introduction of how to deploy a Java application step by step to openshift.  

## Pre-requirements:
- install Docker (if you run on Windows be aware if you are using docker on Hyper-v or with docker-machine/Virtualbox. Will weii provide some hints for docker-machine useres in the readmes) 
- powershell if you are running on windows
- install openshift client tools: https://developers.openshift.com/managing-your-applications/client-tools.html
- install minishift: https://docs.openshift.org/latest/minishift/getting-started/installing.html
- check versions: 
  - *minishift version* == v1.4.1
  - *minishift openshift version*
  - openshift v3.6.0+
  - kubernetes v1.6.1
  - etcd 3.2.1
    
-------------------------------

- _Step 1_, is a simple Spring-boot CRUD application running locally, using a local mongodb instance
- In _Step 2_, we start the application as a Docker container, together with a mongodb container, locally
- In _Step 3_, we push the created Docker image to the openshift repository and deploy the mongodb and the application itself in openshift
- In _Step 4_, we automate the deployment to openshift by using the fabric8 maven plugin. Additionally we customize the service & deployment descriptors
- _Step 5_, demonstrates the usage of a custom Dockerfile together with the Fabric8 plugin
- In _Step 6_ we split the application into a frontend a write and a read service
- In _Step 7_ we add a second frontend and use a different approach for service discovery
- In _Step 8_ and _Step 9_ we will look at config maps as a mechanism to externalize configuration.
- In _Step 10_ we will use secrets to store sensitive data.
- In _Step 11_ we will creat and use a template.
- In _Step 12_ we will buildour own S2I builder image.

- _demo_ contains a simple overall demo presenting the most important aspects such as pods, replicasets,
  deployments, services and routes. _spring-boot-demo_ contains a possible Spring application, which contains
  additional instable health access points to demonstrate monitoring and restarting facilities.
- _s2i_ contains a small example for building a custom s2i builder image.
. _templates_ contains a s,aöö example for creating a kubnernetes template.
- _config,aüs-1/2_ contains example for creating configmaps including propagation of an image through stages
  qith different configs.
- _secrets_ contains an exercise for creating a secret and printing it's content in a container.


## Reset your installation:

Resetting your Openshift is easy. In most cases it is sufficient to run the following commands (ensure you are logged in as developer user, not as admin ;-) ):
- *oc delete route --all*
- *oc delete service --all*
- *oc delete deployment --all*
- *oc delete pods --all*
- *oc delete templates --all*
- *oc delete cm --all*

Dont use *oc delete secrets --all* since you might also delete secrets used by some system components
in your project.

Given Openshift a few seconds to perform all changes (the CLI will immedeately return, but the deletion process might still be ongoing).

If nevertheless things still fail (rarely happening) you can recreate the Openshift VM completely:

- *minishift delete*
- *minishift start --memory 4096 --cpus 2*

