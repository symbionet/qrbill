#!/bin/sh
version=0.9.2
mvn package
java -classpath target/maven-example-1.0-SNAPSHOT.jar:/Users/bleichenbacher/.m2/repository/net/codecrete/qrbill/qrbill-generator/$version/qrbill-generator-$version.jar net.codecrete.qrbill.examples.QRBillExample