# QuickStart
In this folder, there is the GasMetSuite.zip file that contains the GasMet.jar file and the GasMetSuite.war file.
## How to use the standalone version

You need at least Java 8 to be able to compile and run this tool.
Download the GasMet.jar file.
Then, just run:

    java -jar GasMet.jar
without command-line arguments:

![commandLine](https://github.com/paperSubmition2020/GasmetReplicationPackage/blob/master/GasMetSuite/image/commandLine.png)

Gasmet scans a .sol file as input file and performs analysis to derive various metrics able to capture information related to each smell defined in the study.

## How to deploy the war file

The GasMetSuite.war file contains all the contents of the web application.
There are two ways to deploy the war file:

	   1. by server console panel;
	   2. by manually having the war file in a specific folder of the server.
	   
In our experiment, we use [wildfly-21.0.0.Final server](https://www.wildfly.org/downloads/). 
If you want to deploy the war file in  **wildfly**  server manually, move or copy the `GasMetSuite.war` file to the `standalone\deployments` folder in the WildFly distribution.
Now, you are able to access the web project through the browser.

## GUI: How it works

 1. Upload your project and submit it for the scan.  
 
     ![home](https://github.com/paperSubmition2020/GasmetReplicationPackage/blob/master/GasMetSuite/image/Immagine1.png)
 2. Wait for a while until Gasmet checks your file to calculate the metrics.
 3. See the results of the analysis with indicated lines of code, metric, and cost smell description:
 
    ![Result](https://github.com/paperSubmition2020/GasmetReplicationPackage/blob/master/GasMetSuite/image/Immagine2.png)
