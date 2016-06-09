#Echo service
Simple example of Java RMI that was created using Maven

#Project main info
There are three parts (subprojects) in the project:
- echo-service-client - creates client for remote interface service. It connects to the Registry on the server (using *host* and *port*) and gets remote interface for reversing string;
- echo-service-interface - it`s properly remote interface;
- echo-service-server - creates remote interface implementation. Exports this to the Java RMI Registry on the machine, where this server is executed

#Setting configuration for your server and client 
If you want use this program only on your machine go to the next paragraph. Otherwise, you must change some configuration for connection.For this you need to open file `RemoteEchoServiceParameter` in the `echo-service-interface` and only change `DEFAULT_SERVER_PORT` and `DEFAULT_SERVER_HOST` to the yours. For example, set 'host' with ip-address of your machine and 'port' with number of port which will be listening requests.
```java
DEFAULT_SERVER_PORT = 8081; // this is number of your port
DEFAULT_SERVER_HOST = "127.0.0.1"; //there set ip-address of you machine where will be your server
```

#Running the project
First of all you need to create `jar` files of your client and server. For this make command `mvn package` only in `echo-servise` project. After this you will have two jar files: *rmi-client-runnable* and *rmi-server-runnable*. Then you can run this program. It includes a few simple phases:
- Before using client start your server. For this type next command `java  -jar/path\to\created\jar/rmi-server-runnable.jar`
- Then start your client: `java -jar /path\to\created\jar/rmi-client-runnable.jar`
