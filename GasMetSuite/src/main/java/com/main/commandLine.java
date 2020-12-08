package com.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import com.antlr4.grammar.solidity.SolidityLexer;
import com.antlr4.grammar.solidity.SolidityParser;
import com.visitors.ContractVisitor1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;



public class commandLine {

    final static boolean DEBUG = false;

    static String readFile(String path, Charset encoding) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

  

    static BufferedWriter writer = null;
    static ArrayList<Object> record;
    static CSVPrinter csvPrinter = null;

    public static void main(String[] args) throws IOException {
        File f;
        boolean continues=true;
    	do {
        	
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n Please enter a Path of a .sol file:");
        f = new File(bufferedReader.readLine());
          if(f.exists())  {
        
                record = new ArrayList<Object>();
                record.add(new File(f.getPath()).getName());

                System.out.println("\n-------------------------------");
                System.out.println("Input File:" + f.getName());
                String contractCode = readFile(f.getPath(), Charset.forName("UTF-8"));
                CharStream charStream = CharStreams.fromString(contractCode);
                SolidityLexer lexer = new SolidityLexer(charStream);
                TokenStream tokens = new CommonTokenStream(lexer);
                SolidityParser parser = new SolidityParser(tokens);
                ContractVisitor1 contractVisitor1 = new ContractVisitor1(contractCode);
                contractVisitor1.visit(parser.sourceUnit());
             
                Map<Integer, List<String>> contractNOI = contractVisitor1.getMap();
               
                List<String> noif = contractVisitor1.getNoif();
                HashMap<String, Integer> metric = contractVisitor1.getMetric();
                ArrayList<String> iLibrary = contractVisitor1.getListImport();
                ArrayList<String> extFunction = contractVisitor1.getListExtF();
                ArrayList<String> publicFunction = contractVisitor1.getListpublicF();
                ArrayList<String> variableStruct = contractVisitor1.getVariableStructList();
                ArrayList<String> vIList = contractVisitor1.getListVi();

                System.out.println("\n**metric: ACI = number of assignment and counters' increments within cycles");
                if (metric.get("countOperator") != null) {
                    double countOperator = metric.get("countOperator");
                    System.out.println(countOperator);
                    record.add(Double.toString(countOperator));
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("**metric: PM = number of public function");
                if (metric.get("publicF") != null && metric.get("publicF") != 0) {
                    double publicF = metric.get("publicF");
                    System.out.println(publicF);
                    record.add(Double.toString(publicF));
                    if (DEBUG) {
                        System.out.println("Name of public function: ");
                        for (int i = 0; i < publicFunction.size(); i++) {
                            System.out.println(publicFunction.get(i));
                        }
                    }
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("**metric: EF = number of external function");
                if (metric.get("extF") != null && metric.get("extF") != 0) {
                    double extF = metric.get("extF");
                    System.out.println(extF);
                    record.add(Double.toString(extF));
                    System.out.println("Name of external function: ");
                    for (int i = 0; i < extFunction.size(); i++) {
                        System.out.println(extFunction.get(i));
                    }
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("**metric: AZ = number of the assignations default value during all variable definitions");

                double countDefaulVi = metric.getOrDefault("countViDefault", 0);
                double countDefaultV = metric.getOrDefault("countVdefault", 0);
                double totalOfAssignDefault = countDefaulVi + countDefaultV;
                System.out.println(totalOfAssignDefault);
                record.add(Double.toString(totalOfAssignDefault));

                
                System.out.println("**metric: LI = number of library imports/Contract's LOC");
                if (metric.get("import") != null && metric.get("lloc") != null && metric.get("lloc") != 0) {
                    double nImp = metric.get("import");
                    double loc = metric.get("lloc");
                    double mImport = nImp / loc;
                    System.out.println(mImport);
                    record.add(Double.toString(mImport));
                    System.out.println("Name of library imports: ");
                    for (int i = 0; i < iLibrary.size(); i++) {
                        System.out.println(iLibrary.get(i));
                    }
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("\n**metric: IFF = number of internal functions/total number of functions");
                if (metric.get("ninternalF") != null && metric.get("countFunction") != null && metric.get("countFunction") != 0) {
                    double ninternalF = metric.get("ninternalF");
                    double countFunction = metric.get("countFunction");
                    double minternalF = ninternalF / countFunction;
                    System.out.println(minternalF);
                    record.add(Double.toString(minternalF));
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("\n\n**metric: UMA = number of use of memory array/ tot number of use of array");
                if (DEBUG) {
                    System.out.println("countDicArrayMemory:" + contractVisitor1.getCountDicArrayMemory());
                    System.out.println(" number of array : " + contractVisitor1.getCountDicArray());
                    System.out.println(" expression: " + contractVisitor1.getCountUseMem());
                    System.out.println(" count array use: " + contractVisitor1.getCountUseArray());
                }

                double totalArray = contractVisitor1.getCountUseMem() + contractVisitor1.getCountUseArray();
                double rap12 = contractVisitor1.getCountUseMem() > 0 ? contractVisitor1.getCountUseMem() / totalArray : 0.0;
                System.out.println(rap12);
                record.add(Double.toString(rap12));

                
                System.out.println("\n\n**metric: SB = number of strings/number of bytes1..32\"");
                if (metric.get("nString") != null && metric.get("nBytes") != null && metric.get("nBytes") != 0) {
                    double nString = metric.get("nString");
                    double nBytes = metric.get("nBytes");
                    double rapp13 = nString / nBytes;
                    System.out.print(rapp13);
                    record.add(Double.toString(rapp13));
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("\n\n**metric: RLV = number of method with return local variables/number of method\"");
                if (metric.get("countReturn") != null && metric.get("countFunction") != 0) {
                    double nReturn = metric.get("countReturn");
                    double countFunction = metric.get("countFunction");
                    double rapp15 = nReturn / countFunction;
                    System.out.println(rapp15);
                    record.add(Double.toString(rapp15));
                } else {
                    System.out.println(0.0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric: GV = number of global variables");
                if (metric.get("countVi") != null) {
                    int nStateVar = metric.get("countVi");
                    System.out.println(nStateVar);
                    record.add(Double.toString(nStateVar));
                } else {
                    System.out.println(0.0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric: NLF = number of loop ");
                if (metric.get("nCicli") != null) {
                    int nLoop = metric.get("nCicli");
                    System.out.println(nLoop);
                    record.add(nLoop);
                } else {
                    System.out.println(0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric: NU = number of not uint256/overall uint");
                if (metric.get("nUint256") != null && metric.get("nUint") != null && metric.get("nUint") != 0) {
                    double nUint256 = metric.get("nUint256");
                    double nUint = metric.get("nUint");
                    double rapp20 = (nUint - nUint256) / nUint;
                    System.out.print(rapp20);
                    record.add(Double.toString(rapp20));
                } else {
                    System.out.println(0);
                    record.add("0.0");
                }

                
                System.out.println("\n\n**metric: IP = number of indexed parameter");
                if (metric.get("nIndexed") != null) {
                    int nIndexed = metric.get("nIndexed");
                    System.out.println(nIndexed);
                    record.add(nIndexed);
                } else {
                    System.out.println(0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric: NM =  number of mapping / number of instance variables");
                if (metric.get("mapping") != null && metric.get("countVi") != null && metric.get("countVi") != 0) {
                    double countMapping = metric.get("mapping");
                    double countVi = metric.get("countVi");
                    double mmappingVi = countMapping / countVi;
                    System.out.println(mmappingVi);
                    record.add(Double.toString(mmappingVi));
                } else {
                    System.out.println(0.0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric: MA = number of mapping/number of array + mappings");
                if (metric.get("mapping") != null && metric.get("nArray") != null && metric.get("mapping") != 0) {
                    double countMapping = metric.get("mapping");
                    double nArray = metric.get("nArray");
                    double rapp25 = countMapping / (nArray + countMapping);
                    System.out.print(rapp25 + "\n");
                    record.add(Double.toString(rapp25));
                } else {
                    System.out.println(0.0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric: EC = number of invocation of external call/total number of call");
                
                int nondef = 0;
                if (contractNOI.size() != 0) {
                    if (contractNOI.entrySet().size() >= 0) {
                        for (Map.Entry<Integer, List<String>> entry : contractNOI.entrySet()) {
                            Integer key = entry.getKey();
                            List<String> values = entry.getValue();
                            
                            
                            
                            for (String s : values) {
                                for (String a : noif) {
                                    
                                    
                                    if ((a.equalsIgnoreCase(s))) {
                                        nondef++;

                                    }
                                }
                            }
                        }
                    }
                    double diff = noif.size() - nondef;
                    
                    if (noif.size() != 0) {
                        double nofTotalInvocation = noif.size();
                        double nec = nondef / nofTotalInvocation;
                        System.out.print(nec);
                        record.add(Double.toString(nec));
                    } else {
                        System.out.println(0.0);
                        record.add("0.0");
                    }
                }

                
                System.out.println("\n\n **metric: BV = number of booleans/overall variables");
                if (metric.get("nBool") != null && metric.get("nVar") != null && metric.get("nVar") != 0) {
                    double nBool = metric.get("nBool");
                    double nVar = metric.get("nVar");
                    double rapp29 = (nBool / nVar);
                    System.out.print(rapp29);
                    record.add(Double.toString(rapp29));
                } else {
                    System.out.println(0.0);
                    record.add("0.0");
                }

                
                System.out.println("\n\n**metric: NE = number of events");
                if (metric.get("nEvent") != null) {
                    double nEvent = metric.get("nEvent");
                    System.out.print(nEvent);
                    record.add(Double.toString(nEvent));
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("\n\n**metric: DF = number of defined functions/LOC");
                if (metric.get("countFunction") != null && metric.get("LLOC") != null && metric.get("LLOC") != 0) {
                    double countFunction = metric.get("countFunction");
                    double countLLOC = metric.get("LLOC");   
                    double rapp31 = (countFunction / countLLOC);
                    System.out.print(rapp31);
                    record.add(Double.toString(rapp31));
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }
                }
    	else {
    		 System.err.println("File doesn't exist.Try again.");
    		 continues=true;
    	}

    	}while(continues);
       
                
            
        }
}
    
