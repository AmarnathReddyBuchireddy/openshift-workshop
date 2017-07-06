# Trivadis/RedHat Openshift Workshop - Part 3

A simple Spring-boot 2 CRUD application with mongoDB. In Step 4 the application will be deployed to openshift, by the help of the fabric8-maven-plugin.

## Run the application

1. oc login -u developer -p developer
2. minishift docker-env
3. start a mongodb : oc create -f kube/mongoservice.yml && oc create -f kube/mongodeployment.yml
4. mvn clean install fabric8:deploy 
5. get service URL minishift openshift service list -n myproject
6. verify service: curl $URL/index.html