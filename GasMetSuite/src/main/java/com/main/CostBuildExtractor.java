
package com.main;

import com.antlr4.grammar.solidity.SolidityLexer;
import com.antlr4.grammar.solidity.SolidityParser;
import com.visitors.ContractVisitor;
import com.visitors.ContractVisitorPragma;

import static com.main.Main.csvPrinter;
import static com.main.Main.readFile;
import static com.main.Main.record;
import static com.main.Main.writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import javax.json.Json;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CostBuildExtractor {

	public static String executeCommand(String command) {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			InputStreamReader in = new InputStreamReader(p.getInputStream());
			BufferedReader reader = new BufferedReader(in);
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			p.destroy();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter writer = null;
		ArrayList<Object> record;
		CSVPrinter csvPrinter = null;
		writer = Files.newBufferedWriter(Paths.get("csvOutput/SorgentiSol4256.csv"));
		csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ContractName", "Cost Build", "Cost Esec").withDelimiter(';'));
		File dir = new File("src/main/resources/SorgentiSol4256/");
		if (dir.isDirectory()) {
			File[] filesInDir = dir.listFiles();
			Arrays.sort(filesInDir);
			for (File f : filesInDir) {
				record = new ArrayList<Object>();

				System.out.println("Analisi del file:" + f.getName());
				String contractCode = readFile(f.getPath(), Charset.forName("UTF-8"));
				CharStream charStream = CharStreams.fromString(contractCode);
				SolidityLexer lexer = new SolidityLexer(charStream);
				TokenStream tokens = new CommonTokenStream(lexer);
				SolidityParser parser = new SolidityParser(tokens);
				ContractVisitorPragma contractVisitorP = new ContractVisitorPragma(contractCode);
				contractVisitorP.visit(parser.sourceUnit());

				String versione = contractVisitorP.getPragmaValue();
				if (versione == null) {
					versione = "0.4.24";
				}
				System.out.println("Versione: " + versione);
				String a = f.getName().replace(".", "#").split("#")[0];
				record.add(a);

				String result1 = CostBuildExtractor.executeCommand("powershell cp " + f.getAbsolutePath() + " C:\\Users\\lenovo\\Documents\\NetBeansProjects\\metriche\\truffleDir\\contracts\\");
				System.out.println(result1);

				FileWriter f1 = new FileWriter(new File("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\metriche\\truffleDir\\truffle-config.js"));
				f1.write("module.exports = {\n"
						+ "\n"
						+ "\n"
						+ "  networks: {\n"
						+ "  \n"
						+ "     development: {\n"
						+ "      host: \"127.0.0.1\",    \n"
						+ "      port: 8545,            \n"
						+ "      network_id: \"*\",       \n"
						+ "     },\n"
						+ "	 },\n"
						+ "\n"
						+ "  \n"
						+ "  mocha: {\n"
						+ "    \n"
						+ "  },\n"
						+ "\n"
						+ " \n"
						+ "  compilers: {\n"
						+ "    solc: {\n"
						+ "       version: \"" + versione + "\",\n"
						+ "	   }\n"
						+ "  }\n"
						+ "}");
				f1.flush();
				f1.close();
				FileWriter f2 = new FileWriter(new File("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\metriche\\truffleDir\\migrations\\1_initial_migration.js"));
				f2.write("const Migrations = artifacts.require(\"" + a + "\");\n"
						+ "module.exports = function(deployer) {\n"
						+ "  deployer.deploy(Migrations);\n"
						+ "};");
				f2.flush();
				f2.close();

				String result4 = CostBuildExtractor.executeCommand("powershell ./prova.bat");

				String result5 = CostBuildExtractor.executeCommand("powershell rm C:\\Users\\lenovo\\Documents\\NetBeansProjects\\metriche\\truffleDir\\contracts\\ -r");
				System.out.println(result5);
				File f6 = new File("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\metriche\\truffleDir\\contracts\\");
				f6.mkdir();
				FileReader fr = new FileReader("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\metriche\\truffleDir\\result\\result.txt");
				LineNumberReader lnr = new LineNumberReader(fr);

				String line;
				String costBuild = "", costEsec = "";

				while ((line = lnr.readLine()) != null) {
					if (lnr.getLineNumber() == 39) {
						costBuild = line;
					}
					if (lnr.getLineNumber() == 79) {
						costEsec = line;
						break;
					}

				}

				fr.close();
				try {
					String c = costBuild.split(":          ")[1];
					record.add(c);
					System.out.println("Costo build: " + c);
					String d = costEsec.split(":          ")[1];
					record.add(d);
					System.out.println("Costo esecuzione: " + d);
					csvPrinter.printRecord(record);
					csvPrinter.flush();
				} catch (Exception e) {
					record.add("ERROR");
					record.add("ERROR");
				}

			}
		}

		//command[1]= "ls";
	}
}
