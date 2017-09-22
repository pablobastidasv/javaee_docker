FROM jboss/wildfly
ADD target/javaee_docker.war /opt/jboss/wildfly/standalone/deployments/