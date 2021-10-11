# QuickStart
In this folder, there is the GasMetSuite.zip file that contains the GasMet.jar file and the GasMetSuite.war file.

## How to use the standalone version

You need at least Java 8 to be able to compile and run this tool.
Download the GasMet.jar file.
### 1) Using via GUI
Double click on 'GasMet.jar'. When  launching  the  GUI,  a  home  screen  containing two buttons is visualized (as shown in next figure).

![Start2](https://user-images.githubusercontent.com/19287382/121712524-1fcbd500-cadc-11eb-8310-898d8c064bc2.PNG)

The button 'Upload smart contract' allows users to select a smart contract (or a set of smart  contracts) and compute the readability metrics in each function(s)  comprised  in  the selected smart contract(s).

![image](https://user-images.githubusercontent.com/19287382/121713357-1000c080-cadd-11eb-832f-c198c25657dc.png)

When click on button 'Open', the tool calculate redability metric and show result into table (as shown in next figure).

![image](https://user-images.githubusercontent.com/19287382/121713923-a9c86d80-cadd-11eb-91ec-ea0ac3479019.png)

When you click the 'Export csv' button, the results are exported to a CSV file.

### 2) Using via command line

The built jar requires two parameters:
 1) Path directory smart contracts or path single smart contract;
 2) Name of result file csv.
 
```
java -jar GasMet.jar -p <path directory smart contracts or path single smart contract> -o <choose a name of result file csv>
```

![commandLine](https://github.com/paperSubmition2020/GasmetReplicationPackage/blob/master/GasMetToolSuite/image/commandLine.png)


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
 
     ![home](https://github.com/paperSubmition2020/GasmetReplicationPackage/blob/master/GasMetToolSuite/image/Immagine1.png)
 2. Wait for a while until Gasmet checks your file to calculate the metrics.
 3. See the results of the analysis with indicated lines of code, metric, and cost smell description:
 
    ![Result](https://github.com/paperSubmition2020/GasmetReplicationPackage/blob/master/GasMetToolSuite/image/Immagine2.png)
