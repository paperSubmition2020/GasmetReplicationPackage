# QuickStart
In thi folder, there are GasMetSuite.zip that contains GasMet.jar file and GasMetSuite.war file.
## How to use the standalone version

You need at least Java 8 to be able to compile and run this tool.
Download GasMet.jar file.
Then, just run:

    java -jar GasMet.jar
Gasmet scans a .sol file as input file and performs analysis to derive various metrics able to capture information related to each smell defined in the study.

## How to deploy the war file

In this folder, there is the GasMetSuite.war file, that contains all the contents of the web application.
There are two ways to deploy the war file:

	   1. by server console panel;
	   2. by manually having the war file in specific folder of server.
	   
In our sperimentation, we use [wildfly-21.0.0.Final server](https://www.wildfly.org/downloads/). 
If you want to deploy the war file in  **wildfly**  server manually, move or copy the `GasMetSuite.war` file to the `standalone\deployments` folder in the WildFly distribution.
Now, you are able to access the web project through browser.

## GUI: How it works

 1. Upload your project                                 
and submit it for scan.  
     ![home](https://github.com/paperSubmition2020/GasmetReplicationPackage/blob/master/GasMetSuite/image/Immagine1.png)
 2. Wait for a while until Gasmet checks your file for calculate metric.
 3. See the results of analysis with indicated lines of code and metric description:
    ![Result](https://github.com/paperSubmition2020/GasmetReplicationPackage/blob/master/GasMetSuite/image/Immagine2.png)
