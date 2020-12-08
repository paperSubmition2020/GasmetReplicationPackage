package com.main;

import java.io.File;
import java.io.IOException;
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
import com.visitors.ContractVisitor;
import com.visitors.ContractVisitor1;

import java.io.BufferedWriter;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Main {
	// The metric VS is not considered in the study
    final static boolean DEBUG = false;

    static String readFile(String path, Charset encoding) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static Map<String, List> getMetric(String filename, String contractCod) throws IOException {
        String contractCode = contractCod;
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
        HashMap<String, List> metriche = new HashMap<String, List>();
        ArrayList<Integer> lineVi = contractVisitor1.getLineVi();
        ArrayList<Integer> lineVinS = contractVisitor1.getLineVinS();
        ArrayList<Integer> linePublicF = contractVisitor1.getLinePublicF();
        ArrayList<Integer> lineExtF = contractVisitor1.getLineExtF();
        ArrayList<Integer> lineCountVdefault = contractVisitor1.getLineCountVdefault();
        ArrayList<Integer> lineCountVidefault = contractVisitor1.getLineCountVidefault();
        ArrayList<Integer> lineImport = contractVisitor1.getLineImport();
        ArrayList<Integer> lineFunction = contractVisitor1.getLineFunction();
        ArrayList<Integer> lineInternalF = contractVisitor1.getLineInternalF();
        ArrayList<Integer> lineCicli = contractVisitor1.getLineCicli();
        ArrayList<Integer> lineNEvent = contractVisitor1.getLineEvent();
        ArrayList<Integer> lineNIndexed = contractVisitor1.getLineNIndexed();
        ArrayList<Integer> lineNBool = contractVisitor1.getLineNBool();
        ArrayList<Integer> lineNMapping = contractVisitor1.getLineNMapping();
        ArrayList<Integer> lineNUint256 = contractVisitor1.getLineNUint256();
        ArrayList<Integer> lineCountOperator = contractVisitor1.getLineCountOperator();
        ArrayList<Integer> lineMTArray = contractVisitor1.getLineMTArray();
        ArrayList<Integer> linenString = contractVisitor1.getLinenString();
        ArrayList<Integer> linenBytes = contractVisitor1.getLinenBytes();
        ArrayList<Integer> linenReturn = contractVisitor1.getLineReturn();
        ArrayList<Integer> lineExCall = contractVisitor1.getLineExCall();
        List lineVS = new ArrayList();
        List linePublic = new ArrayList();
        List lineExt = new ArrayList();
        List lineCountDefault = new ArrayList();
        List lineImportM = new ArrayList();
        List lineInternal = new ArrayList();
        List lineCountFunction = new ArrayList();
        List lineGV = new ArrayList();
        List lineNCicli = new ArrayList();
        List lineEvent = new ArrayList();
        List lineIndexed = new ArrayList();
        List lineBool = new ArrayList();
        List lineMapping = new ArrayList();
        List lineUint256 = new ArrayList();
        List lineCountOp = new ArrayList();
        List lineMarray = new ArrayList();
        List lineString = new ArrayList();
        List lineReturn = new ArrayList();
        List lineExtCall = new ArrayList();
        List<String> listExtF = new ArrayList();
        List lineTMapping = new ArrayList();

        
        
        if (metric.get("vInS") != null && (metric.get("countVi") != null) && (metric.get("countVi") != 0)) {
            double countVi = metric.get("countVi");
            double vInS = metric.get("vInS");
            double mVi = vInS / (vInS + countVi);
            lineVS.add(mVi);
            lineVS.addAll(lineVi);
            lineVS.addAll(lineVinS);
            
        } else {
            lineVS.add(0.0);
            
        }

        
        if (metric.get("countOperator") != null) {
            double countOperator = metric.get("countOperator");
            lineCountOp.add(countOperator);
            lineCountOp.addAll(lineCountOperator);
            metriche.put("ACI = number of assignment and counters' increments within cycles", lineCountOp);
        } else {
            lineCountOp.add(0.0);
            metriche.put("ACI =  number of assignment and counters' increments within cycles", lineCountOp);
        }

        
        if (metric.get("publicF") != null) {
            double publicF = metric.get("publicF");
            linePublic.add(publicF);
            linePublic.addAll(linePublicF);
            metriche.put("PM = number of public function", linePublic);
        } else {
            linePublic.add(0.0);
            metriche.put("PM = number of public function", linePublic);
        }

        
        if (metric.get("extF") != null) {
            double extF = metric.get("extF");
            lineExt.add(extF);
            lineExt.addAll(lineExtF);
            metriche.put("EF = number of external function", lineExtF);
            for (int i = 0; i < extFunction.size(); i++) {
            }
        } else {
            lineExt.add(0.0);
            metriche.put("EF = number of external function", lineExt);
        }

        
        double countDefaulVi = metric.getOrDefault("countViDefault", 0);
        double countDefaultV = metric.getOrDefault("countVdefault", 0);
        double totalOfAssignDefault = countDefaulVi + countDefaultV;
        lineCountDefault.add(totalOfAssignDefault);
        lineCountDefault.addAll(lineCountVidefault);
        lineCountDefault.addAll(lineCountVdefault);
        metriche.put("AZ = number of the assignations default value during all variable definitions ", lineCountDefault);

        
        if (metric.get("import") != null && metric.get("lloc") != null && metric.get("lloc") != 0) {
            double nImp = metric.get("import");
            double loc = metric.get("lloc");
            double mImport = nImp / loc;
            lineImportM.add(mImport);
            lineImportM.addAll(lineImport);
            metriche.put("LI = number of library imports/Contract's LOC", lineImportM);
        } else {
            lineImportM.add(0.0);
            metriche.put("LI = number of library imports/Contract's LOC", lineImportM);
        }

        
        if (metric.get("ninternalF") != null && metric.get("countFunction") != null && metric.get("countFunction") != 0) {
            double ninternalF = metric.get("ninternalF");
            double countFunction = metric.get("countFunction");
            double minternalF = ninternalF / countFunction;
            lineInternal.add(minternalF);
            lineInternal.addAll(lineInternalF);
            metriche.put("IFF = number of internal functions/total number of functions ", lineInternal);

        } else {
            lineInternal.add(0.0);
            metriche.put("IFF = number of internal functions/total number of functions ", lineInternal);
        }

        
        double totalArray = contractVisitor1.getCountUseMem() + contractVisitor1.getCountUseArray();
        if (totalArray != 0) {
            double rap12 = contractVisitor1.getCountUseMem() / totalArray;
            lineMarray.add(rap12);
            lineMarray.addAll(lineMTArray);
            metriche.put("UMA = number of use of memory array/ tot number of use of array ", lineMarray);
        } else {
            lineMarray.add(0.0);
            metriche.put("UMA = number of use of memory array/ tot number of use of array ", lineMarray);
        }

        
        if (metric.get("nString") != null && metric.get("nBytes") != null && metric.get("nBytes") != 0) {
            double nString = metric.get("nString");
            double nBytes = metric.get("nBytes");
            double rapp13 = nString / nBytes;
            lineString.add(rapp13);
            lineString.addAll(linenString);
            lineString.addAll(linenBytes);
            metriche.put("SB = number of strings/number of bytes1..32", lineString);
        } else {
            lineString.add(0.0);
            metriche.put("SB = number of strings/number of bytes1..32", lineString);
        }

        
        if (metric.get("countReturn") != null && metric.get("countFunction") != 0) {
            double nReturn = metric.get("countReturn");
            double countFunction = metric.get("countFunction");
            double rapp15 = nReturn / countFunction;
            lineReturn.add(rapp15);
            lineReturn.addAll(linenReturn);
            metriche.put("RLV = number of method with return local variables/number of method", lineReturn);
        } else {
            lineReturn.add(0.0);
            metriche.put("RLV = number of method with return local variables/number of method", lineReturn);
        }

        
        if (metric.get("countVi") != null) {
            double nStateVar = metric.get("countVi");
            lineGV.add(nStateVar);
            lineGV.addAll(lineVi);
            metriche.put("GV = number of global variables", lineGV);
        } else {
            lineGV.add(0.0);
            metriche.put("GV = number of global variables", lineGV);
        }

        
        if (metric.get("nCicli") != null) {
            double nLoop = metric.get("nCicli");
            lineNCicli.add(nLoop);
            lineNCicli.addAll(lineCicli);
            metriche.put("NLF = number of loop", lineNCicli);
        } else {
            lineNCicli.add(0.0);
            metriche.put("NLF = number of loop", lineNCicli);
        }

        
        if (metric.get("nUint256") != null && metric.get("nUint") != null && metric.get("nUint") != 0) {
            double nUint256 = metric.get("nUint256");
            double nUint = metric.get("nUint");
            double rapp20 = (nUint - nUint256) / nUint;
            lineUint256.add(rapp20);
            lineUint256.addAll(lineNUint256);
            metriche.put("NU = number of not uint256/overall uint", lineUint256);
        } else {
            lineUint256.add(0.0);
            metriche.put("NU = number of not uint256/overall uint", lineUint256);
        }

        
        if (metric.get("nIndexed") != null) {
            double nIndexed = metric.get("nIndexed");
            lineIndexed.add(nIndexed);
            lineIndexed.addAll(lineNIndexed);
            metriche.put("IP = number of indexed parameter", lineIndexed);
        } else {
            lineIndexed.add(0.0);
            metriche.put("IP = number of indexed parameter", lineIndexed);
        }

        
        if (metric.get("mapping") != null && metric.get("countVi") != null && metric.get("countVi") != 0) {
            double countMapping = metric.get("mapping");
            double countVi = metric.get("countVi");
            double mmappingVi = countMapping / countVi;
            lineMapping.add(mmappingVi);
            lineMapping.addAll(lineNMapping);
            metriche.put("NM =  number of mapping / number of instance variables", lineMapping);
        } else {
            lineMapping.add(0.0);
            metriche.put("NM = number of mapping / number of instance variables", lineMapping);
        }

        
        if (metric.get("mapping") != null && metric.get("nArray") != null) {
            double countMapping = metric.get("mapping");
            double nArray = metric.get("nArray");
            double rapp25 = countMapping / (nArray + countMapping);
            lineTMapping.add(rapp25);
            lineTMapping.addAll(lineNMapping);
            metriche.put("MA = number of mapping/number of array + mappings", lineTMapping);
        } else {
            lineTMapping.add(0.0);
            metriche.put("MA = number of mapping/number of array + mappings", lineTMapping);
        }

        
        
        int nondef = 0;
        int ext = 0;
        if (contractNOI.size() != 0) {
            if (contractNOI.entrySet().size() >= 0) {
                for (Map.Entry<Integer, List<String>> entry : contractNOI.entrySet()) {
                    Integer key = entry.getKey();
                    List<String> values = entry.getValue();
                    for (String v : values) {
                        for (String n : noif) {
                            if (!(n.equalsIgnoreCase(v))) {
                                listExtF.add(n);
                            }
                        }
                    }
                }
            }

            Set<String> set = new TreeSet<>(listExtF);
            List<String> listWithoutDuplicates = new ArrayList<>(set);
            if (noif.size() != 0) {
                Map<String, List> map = contractVisitor1.getLineNoif();
                for (Map.Entry<String, List> entry : map.entrySet()) {
                    String key = entry.getKey();
                    List<Integer> values = entry.getValue();
                    for (String a : listWithoutDuplicates) {
                        if ((key.equalsIgnoreCase(a))) {
                            lineExCall.addAll(map.get(key));
                            ext++;
                        }
                    }
                }
                double nofTotalInvocation = noif.size();
                double nec = ext / nofTotalInvocation;
                lineExtCall.add(nec);
                lineExtCall.addAll(lineExCall);
                metriche.put("EC = number of invocation of external call/total number of call", lineExtCall);
            } else {
                lineExtCall.add(0.0);
                metriche.put("EC = number of invocation of external call/total number of call", lineExtCall);
            }
        }

        
        if (metric.get("nBool") != null && metric.get("nVar") != null && metric.get("nVar") != 0) {
            double nBool = metric.get("nBool");
            double nVar = metric.get("nVar");
            double rapp29 = (nBool / nVar);
            lineBool.add(rapp29);
            lineBool.addAll(lineNBool);
            metriche.put("BV = number of booleans/overall variables", lineBool);
        } else {
            lineBool.add(0.0);
            metriche.put("BV = number of booleans/overall variables", lineBool);
        }

        
        if (metric.get("nEvent") != null) {
            double nEvent = metric.get("nEvent");
            lineEvent.add(nEvent);
            lineEvent.addAll(lineNEvent);
            metriche.put("NE = number of events", lineEvent);
        } else {
            lineEvent.add(0.0);
            metriche.put("NE = number of events", lineEvent);
        }

        
        if (metric.get("countFunction") != null && metric.get("LLOC") != null && metric.get("LLOC") != 0) {
            double countFunction = metric.get("countFunction");
            double countLLOC = metric.get("LLOC");
            double rapp31 = (countFunction / countLLOC);
            lineCountFunction.add(rapp31);
            lineCountFunction.addAll(lineFunction);
            metriche.put("DF = number of defined functions/LOC", lineCountFunction);
        } else {
            lineCountFunction.add(0.0);
            metriche.put("DF = number of defined functions/LOC", lineCountFunction);
        }
        for (Map.Entry<String, List> entry : metriche.entrySet()) {
            String key = entry.getKey();
            List values = entry.getValue();
            System.out.println("Key = " + key);
            System.out.println("Values = " + values);
        }
        return metriche;
    }

    static BufferedWriter writer = null;
    static ArrayList<Object> record;
    static CSVPrinter csvPrinter = null;

    public static void main(String[] args) throws IOException {
        
        File dir = new File("sorgenti/");

        if (dir.isDirectory()) {
            File[] filesInDir = dir.listFiles();
            Arrays.sort(filesInDir);
            for (File f : filesInDir) {

                record = new ArrayList<Object>();
                record.add(new File(f.getPath()).getName());

                System.out.println("\n-------------------------------");
                System.out.println("File input:" + f.getName());
                String contractCode = readFile(f.getPath(), Charset.forName("UTF-8"));
                getMetric(f.getName(), contractCode);
                CharStream charStream = CharStreams.fromString(contractCode);
                SolidityLexer lexer = new SolidityLexer(charStream);
                TokenStream tokens = new CommonTokenStream(lexer);
                SolidityParser parser = new SolidityParser(tokens);
                ContractVisitor contractVisitor1 = new ContractVisitor(contractCode);
                contractVisitor1.visit(parser.sourceUnit());
                Map<Integer, List<String>> contractNOI = contractVisitor1.getMap();
                record.add(contractVisitor1.getContractName());
                List<String> noif = contractVisitor1.getNoif();
                HashMap<String, Integer> metric = contractVisitor1.getMetric();
                ArrayList<String> iLibrary = contractVisitor1.getListImport();
                ArrayList<String> extFunction = contractVisitor1.getListExtF();
                ArrayList<String> publicFunction = contractVisitor1.getListpublicF();
                ArrayList<String> variableStruct = contractVisitor1.getVariableStructList();
                ArrayList<String> vIList = contractVisitor1.getListVi();

                if (DEBUG) {
                    Iterator it = metric.entrySet().iterator();

                    while (it.hasNext()) {                    
                        Map.Entry entry = (Map.Entry) it.next();
                        System.out.println("Numero di: " + entry.getKey());
                        System.out.println("Value = " + entry.getValue());
                    }
                }

                
                System.out.println("**metric #1: number of variable contain in a struct/(number of variable contain in a struct + number of instance variable ) :");
                if (metric.get("vInS") != null && (metric.get("countVi") != null) && (metric.get("countVi") != 0)) {
                    
                    double countVi = metric.get("countVi");
                    System.out.println("number of instance variable " + countVi);
                    

                    double vInS = metric.get("vInS");
                    System.out.println("number of variable contain in a struct+ number of instance variable " + vInS + " ");
                    double mVi = vInS / (vInS + countVi);
                    System.out.print("metric 1:  " + mVi + "\n");
                    record.add(Double.toString(mVi)); 
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("\n**metric #2: number of assignment and counters' increments within cycles: ");
                if (metric.get("countOperator") != null) {
                    double countOperator = metric.get("countOperator");
                    System.out.println(countOperator);
                    record.add(Double.toString(countOperator));
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("**metric #3: number of public function");
                if (metric.get("publicF") != null && metric.get("publicF") != 0) {
                    double publicF = metric.get("publicF");
                    System.out.println(publicF);
                    record.add(Double.toString(publicF));
                    if (DEBUG) {
                        System.out.println("Le funzioni dichiarate public sono: ");
                        for (int i = 0; i < publicFunction.size(); i++) {
                            System.out.println(publicFunction.get(i));
                        }
                    }
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("**metric #5 external: number of external function");
                if (metric.get("extF") != null && metric.get("extF") != 0) {
                    double extF = metric.get("extF");
                    System.out.println(extF);
                    record.add(Double.toString(extF));
                    System.out.println("Le funzioni dichiarate external sono: ");
                    for (int i = 0; i < extFunction.size(); i++) {
                        System.out.println(extFunction.get(i));
                    }
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("**metric #6:number of the assignations default value during all variable definitions :");

                double countDefaulVi = metric.getOrDefault("countViDefault", 0);
                double countDefaultV = metric.getOrDefault("countVdefault", 0);
                double totalOfAssignDefault = countDefaulVi + countDefaultV;
                System.out.println(totalOfAssignDefault);
                record.add(Double.toString(totalOfAssignDefault));

                
                System.out.println("**metric #7 import: number of library imports/Contract's LOC: ");
                if (metric.get("import") != null && metric.get("lloc") != null && metric.get("lloc") != 0) {
                    double nImp = metric.get("import");
                    double loc = metric.get("lloc");
                    double mImport = nImp / loc;
                    System.out.println(mImport);
                    record.add(Double.toString(mImport));
                    System.out.println("Le librerie importate sono: ");
                    for (int i = 0; i < iLibrary.size(); i++) {
                        System.out.println(iLibrary.get(i));
                    }
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("\n**metric #11: Number of internal functions/total number of functions :");
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

                
                System.out.println("\n\n**metric 12) number of use of memory array/ tot number of use of array");
                if (DEBUG) {
                    System.out.println("countDicArrayMemory:" + contractVisitor1.getCountDicArrayMemory());
                    System.out.println(" number of array : " + contractVisitor1.getCountDicArray());
                    System.out.println(" expression: " + contractVisitor1.getCountUseMem());
                    System.out.println(" count array use: " + contractVisitor1.getCountUseArray());
                }

                double totalArray = contractVisitor1.getCountUseMem() + contractVisitor1.getCountUseArray();
                double rap12 = contractVisitor1.getCountUseMem() > 0 ? contractVisitor1.getCountUseMem() / totalArray : 0.0;
                System.out.println("rap12:" + rap12);
                record.add(Double.toString(rap12));

                
                System.out.println("\n\n**metric #13: number of strings/number of bytes1..32 :");
                if (metric.get("nString") != null && metric.get("nBytes") != null && metric.get("nBytes") != 0) {
                    double nString = metric.get("nString");
                    System.out.print(" nString: " + nString);
                    double nBytes = metric.get("nBytes");
                    System.out.print("    nBytes: " + nBytes);
                    double rapp13 = nString / nBytes;
                    System.out.print("    rapp: " + rapp13);
                    record.add(Double.toString(rapp13));
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("\n\n**metric #15: number of method with return local variables/number of method ");
                if (metric.get("countReturn") != null && metric.get("countFunction") != 0) {
                    double nReturn = metric.get("countReturn");
                    System.out.print("number of method with return local variables: " + nReturn);
                    double countFunction = metric.get("countFunction");
                    System.out.print("    number of method: " + countFunction);
                    double rapp15 = nReturn / countFunction;
                    System.out.println("\nn. of method with return/n. of method: " + rapp15);
                    record.add(Double.toString(rapp15));
                } else {
                    System.out.println(0.0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric #16: number of global variables: ");
                if (metric.get("countVi") != null) {
                    int nStateVar = metric.get("countVi");
                    System.out.println(nStateVar);
                    record.add(Double.toString(nStateVar));
                } else {
                    System.out.println(0.0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric #19: Number of loop: ");
                if (metric.get("nCicli") != null) {
                    int nLoop = metric.get("nCicli");
                    System.out.println(nLoop);
                    record.add(nLoop);
                } else {
                    System.out.println(0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric #20: number of not uint256/overall uint");
                if (metric.get("nUint256") != null && metric.get("nUint") != null && metric.get("nUint") != 0) {
                    double nUint256 = metric.get("nUint256");
                    System.out.print("   nUint256: " + nUint256);
                    double nUint = metric.get("nUint");
                    System.out.print("   nUint: " + nUint);
                    double rapp20 = (nUint - nUint256) / nUint;
                    System.out.print("   num not uint256/num uint: " + rapp20);
                    record.add(Double.toString(rapp20));
                } else {
                    System.out.println(0);
                    record.add("0.0");
                }

                
                System.out.println("\n\n**metric #21: number of indexed parameter");
                if (metric.get("nIndexed") != null) {
                    int nIndexed = metric.get("nIndexed");
                    System.out.println("n. of parameter indexed:" + nIndexed);
                    record.add(nIndexed);
                } else {
                    System.out.println(0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric #22:number of mapping/ number of instance variables :");
                if (metric.get("mapping") != null && metric.get("countVi") != null && metric.get("countVi") != 0) {
                    double countMapping = metric.get("mapping");
                    double countVi = metric.get("countVi");
                    double mmappingVi = countMapping / countVi;
                    System.out.println("n. mapping: " + countMapping + "  n.variables: " + countVi + "  rapp: " + mmappingVi);
                    record.add(Double.toString(mmappingVi));
                } else {
                    System.out.println(0.0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric #25: number of mapping/number of array + mappings");
                if (metric.get("mapping") != null && metric.get("nArray") != null && metric.get("mapping") != 0) {
                    double countMapping = metric.get("mapping");
                    System.out.print(" nMapping: " + countMapping);
                    double nArray = metric.get("nArray");
                    System.out.print("    nArray: " + nArray);
                    double rapp25 = countMapping / (nArray + countMapping);
                    System.out.print("    rapp: " + rapp25 + "\n");
                    record.add(Double.toString(rapp25));
                } else {
                    System.out.println(0.0);
                    record.add("0.0");
                }

                
                System.out.println("\n**metric #26: number of external call/total number of call");
                
                
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
                        System.out.print(" Number of external call/total number of call: " + nec);
                        record.add(Double.toString(nec));
                    } else {
                        System.out.println(0.0);
                        record.add("0.0");
                    }
                }

                
                System.out.println("\n\n **metric #29:number of booleans/overall variables");
                if (metric.get("nBool") != null && metric.get("nVar") != null && metric.get("nVar") != 0) {
                    double nBool = metric.get("nBool");
                    System.out.print("   nBool: " + nBool);
                    double nVar = metric.get("nVar");
                    System.out.print("   nVar: " + nVar);
                    double rapp29 = (nBool / nVar);
                    System.out.print(" Number of booleans/overall variables: " + rapp29);
                    record.add(Double.toString(rapp29));
                } else {
                    System.out.println(0.0);
                    record.add("0.0");
                }

                
                System.out.println("\n\n**metric #30:number of events");
                if (metric.get("nEvent") != null) {
                    double nEvent = metric.get("nEvent");
                    System.out.print(" nEvent: " + nEvent);
                    record.add(Double.toString(nEvent));
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                System.out.println("\n\n**metric #31:number of defined functions/LOC");
                if (metric.get("countFunction") != null && metric.get("LLOC") != null && metric.get("LLOC") != 0) {
                    double countFunction = metric.get("countFunction");
                    System.out.print(" Function: " + countFunction);
                    double countLLOC = metric.get("LLOC");
                    System.out.print(" LLOC: " + countLLOC);
                    double rapp31 = (countFunction / countLLOC);
                    System.out.print(" number of defined functions/LOC: " + rapp31);
                    record.add(Double.toString(rapp31));
                } else {
                    System.out.println("0.0");
                    record.add("0.0");
                }

                
                
            }
        }

    }
}
