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

import java.io.BufferedWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class generatoreWrapper {

    public static void main(String[] args) {

        // List<String> solPaths = new ArrayList<>();
        File dir = new File("src/main/resources/SorgentiSolTest/");
        if (dir.isDirectory()) {
            File[] filesInDir = dir.listFiles();
            Arrays.sort(filesInDir);
            for (File f : filesInDir) {
                System.out.println("generazione wrapper " + f.getName());
                try {

                } catch (Exception e) {
                    System.err.println("errrore per file sol " + f.getName());
                }
            }
        }

    }
}
