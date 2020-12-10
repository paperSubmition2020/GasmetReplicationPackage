# Replication Package for the paper entitled "Profiling Gas Leaks in Solidity Smart Contracts".

Description of the content:

 1. "*GasMetSuite*": the folder contains the prototype Java tool able to parse Solidity smart contracts and automatically compute
 the metrics in the GasMet suite, generate the Ethereum development environment and collect data regarding gas consumption. We provide a user interface that automatically checks Smart Contracts for code smell and bad practices, it highlights them in the code and gives a detailed explanation of the metric corresponding to the cost smell.
 We use [Maven](https://maven.apache.org/) to manage dependencies and compile the project;
 2. "*csvOutput*": contains all the data we obtained by running the Java code of GasMet;
 3. *"survey"*: contains the collected responses from the survey targeting experts in the development of Solidity smart contracts and blockchainapplications;
 4. *"Dataset.zip"*: the dataset of 2,186 Solidity smart contracts, randomly selected from a repository available at 
 (thec00n.2018.etherscan_verified_contracts. https://github.com/thec00n/etherscan_verified_contracts. Online; accessed 07 December 2020). In particular, all the smart contracts present in such a repository are actually deployed on the Ethereum blockchain and their source code is validated by Etherscan.
