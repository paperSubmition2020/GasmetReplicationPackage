package com.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.antlr.v4.runtime.misc.NotNull;

import com.antlr4.grammar.solidity.SolidityBaseVisitor;
import com.antlr4.grammar.solidity.SolidityParser.BlockContext;
import com.antlr4.grammar.solidity.SolidityParser.ContractDefinitionContext;
import com.antlr4.grammar.solidity.SolidityParser.ElementaryTypeNameContext;
import com.antlr4.grammar.solidity.SolidityParser.EventDefinitionContext;
import com.antlr4.grammar.solidity.SolidityParser.EventParameterContext;
import com.antlr4.grammar.solidity.SolidityParser.ExpressionContext;
import com.antlr4.grammar.solidity.SolidityParser.ForStatementContext;
import com.antlr4.grammar.solidity.SolidityParser.FunctionCallArgumentsContext;
import com.antlr4.grammar.solidity.SolidityParser.FunctionDefinitionContext;
import com.antlr4.grammar.solidity.SolidityParser.IfStatementContext;
import com.antlr4.grammar.solidity.SolidityParser.ImportDirectiveContext;
import com.antlr4.grammar.solidity.SolidityParser.InheritanceSpecifierContext;
import com.antlr4.grammar.solidity.SolidityParser.MappingContext;
import com.antlr4.grammar.solidity.SolidityParser.SimpleStatementContext;
import com.antlr4.grammar.solidity.SolidityParser.StateVariableDeclarationContext;
import com.antlr4.grammar.solidity.SolidityParser.StatementContext;
import com.antlr4.grammar.solidity.SolidityParser.StructDefinitionContext;
import com.antlr4.grammar.solidity.SolidityParser.TypeNameContext;
import com.antlr4.grammar.solidity.SolidityParser.VariableDeclarationContext;
import com.antlr4.grammar.solidity.SolidityParser.VariableDeclarationStatementContext;
import com.antlr4.grammar.solidity.SolidityParser.WhileStatementContext;
import com.helper.LOCCalculator;
//The metric VS is not considered in the study
@SuppressWarnings("deprecation")
public class ContractVisitor extends SolidityBaseVisitor<Void> {

    private int count;
    private int countImport;
    private int countExtFunc;
    private int countinternalF;
    private int countFunction;
    private int countReturn;
    private int countPublicFunc;
    private int countVi;
    private int countS;
    private int countMapping;
    private int countVdefault;
    private int countViDefault;
    private List<String> set = new ArrayList<>();
    ;
	private Map<String, Integer> contractDefCounts = new HashMap<>();
    private ArrayList<String> iLibrary = new ArrayList<String>();
    private ArrayList<String> extFunction = new ArrayList<String>();
    private ArrayList<String> intFunction = new ArrayList<String>();
    private ArrayList<String> publicFunction = new ArrayList<String>();
    private ArrayList<String> variableStruct = new ArrayList<String>();
    private ArrayList<String> vI = new ArrayList<String>();
    private ContractDefinitionContext currentContract;
    // sloc, lloc, cloc, fcount, wmc, tnl, tnle, tnumpar, tnos, dit, tnoa, tnod,
    // cbo, tna, tnoi
    private Map<ContractDefinitionContext, Integer[]> contractMetrics = new HashMap<>();
    private Map<String, Integer> contractDIT = new HashMap<>();
    private Map<String, Set<String>> contractNOA = new HashMap<>();
    private Map<Integer, List<String>> contractNOI = new HashMap<>();
    private CountOperatorVisitor cOperator = new CountOperatorVisitor();
    private String sourceText;
    private HashMap<String, Integer> metric = new HashMap<String, Integer>();
    private int nString;
    private int nBytes;
    private int nUint;
    private int nUint256;
    private int nEvent;
    private int nIndexed;
    private int nBool;
    private int nCicli;
    private int nArray;
    private String indexed;
    private String contractName = "";
    private int nMemory;
    private int nMappingArray;
    private int nVar;
    private int Expression;
    int countUseMem = 0;
    int countUseArray = 0;

    ArrayList<String> nome;
    ArrayList<String> nomeArray;
    int countDicArrayMemory = 0;
    int countDicArray = 0;

    private List<String> noif = new ArrayList<String>();

    /**
     * *************************************************
     */
    ArrayList<Integer> lineVi = new ArrayList<Integer>();
    ArrayList<Integer> lineVinS = new ArrayList<Integer>();
    ArrayList<Integer> linePublicF = new ArrayList<Integer>();
    ArrayList<Integer> lineExtF = new ArrayList<Integer>();
    ArrayList<Integer> lineCountVdefault = new ArrayList<Integer>();
    ArrayList<Integer> lineCountVidefault = new ArrayList<Integer>();
    ArrayList<Integer> lineImport = new ArrayList<Integer>();
    ArrayList<Integer> lineFunction = new ArrayList<Integer>();
    ArrayList<Integer> lineInternal = new ArrayList<Integer>();
    ArrayList<Integer> lineCicli = new ArrayList<Integer>();
    ArrayList<Integer> lineEvent = new ArrayList<Integer>();
    ArrayList<Integer> lineNIndexed = new ArrayList<Integer>();
    ArrayList<Integer> lineNBool = new ArrayList<Integer>();
    ArrayList<Integer> lineNMapping = new ArrayList<Integer>();
    ArrayList<Integer> lineNUint256 = new ArrayList<Integer>();
    ArrayList<Integer> lineCountOperator = new ArrayList<Integer>();
    ArrayList<Integer> lineMTArray = new ArrayList<Integer>();

    public ContractVisitor(String sourceText) {
        this.sourceText = sourceText;
    }

    public ContractVisitor() {
    }

    public String getContractName() {
        return contractName;
    }

    @Override
    public Void visitContractDefinition(@NotNull ContractDefinitionContext ctx) {
        contractName = ctx.getChild(1).getText();
        count++;
        int sloc = ctx.getStop().getLine() - ctx.getStart().getLine() + 1;
        LOCCalculator locCalc = new LOCCalculator("\n", ctx.getStart().getLine() - 1, ctx.getStop().getLine() - 1);
        locCalc.calculateLOCMetrics(sourceText);
        int lloc = locCalc.getLLOC();
        int cloc = locCalc.getCLOC();
        metric.put("LLOC", lloc);

        contractDefCounts.put(ctx.getStart().getText(),
                contractDefCounts.getOrDefault(ctx.getStart().getText(), 0) + 1);

        Integer[] mets = new Integer[15];
        mets[0] = sloc;
        mets[1] = lloc;
        mets[2] = cloc;
        mets[3] = 0;
        mets[4] = 0;
        mets[5] = 0;
        mets[6] = 0;
        mets[7] = 0;
        mets[8] = 0;
        mets[9] = calculateDIT(ctx);
        mets[13] = 0;
        mets[14] = 0;
        currentContract = ctx;
        metric.put("lloc", lloc);
        metric.put("cloc", cloc);
        contractMetrics.put(ctx, mets);
        super.visitContractDefinition(ctx);
        currentContract = null;
        return null;
    }

    @Override
    public Void visitImportDirective(@NotNull ImportDirectiveContext ctx) {
        //countImport++;

        if (ctx.importDeclaration() != null) {
            countImport++;
            lineImport.add(ctx.start.getLine());
            metric.put("import", countImport);
           
            if ((ctx.getChild(1).toString()).equals("{")) {
                iLibrary.add(ctx.getChild(2).getText());
            } else {
                iLibrary.add(ctx.getChild(1).getText());
            }
        }
        super.visitImportDirective(ctx);
        return null;
    }

    private int calculateDIT(ContractDefinitionContext ctx) {
        int dit = 0;
        for (InheritanceSpecifierContext ictx : ctx.inheritanceSpecifier()) {
            String baseName = ictx.userDefinedTypeName().identifier().get(0).getText();
            if (contractDIT.get(baseName) + 1 > dit) {
                dit = contractDIT.get(baseName) + 1;
            }
        }
        contractDIT.put(ctx.identifier().getText(), dit);
        return dit;
    }

    private int calculateNOA(ContractDefinitionContext ctx) {
        contractNOA.put(ctx.identifier().getText(), new HashSet<>());
        for (InheritanceSpecifierContext ictx : ctx.inheritanceSpecifier()) {
            String baseName = ictx.userDefinedTypeName().identifier().get(0).getText();
            contractNOA.get(ctx.identifier().getText()).add(baseName);
            contractNOA.get(ctx.identifier().getText()).addAll(contractNOA.get(baseName));
        }

        return contractNOA.get(ctx.identifier().getText()).size();
    }

    @Override
    public Void visitFunctionDefinition(@NotNull FunctionDefinitionContext ctx) {

        set = new ArrayList<String>();
        if (ctx.identifier() != null) {

            if (ctx.getStart().getLine() >= currentContract.getStart().getLine() && ctx.getStart().getLine() <= currentContract.getStop().getLine()) {

                List<String> a = contractNOI.get(currentContract.getStart().getLine());
                if (a != null) {
                    a.add(ctx.identifier().getText());
                    set = a;
                    contractNOI.put(currentContract.getStart().getLine(), set);

                } else {

                    set.add(ctx.identifier().getText());

                    contractNOI.put(currentContract.getStart().getLine(), set);
                }
            }
        } else {
            set.add(" ");
            contractNOI.put(currentContract.getStart().getLine(), set);
        }
        lineFunction.add(ctx.identifier().start.getLine());
        countFunction++;
        metric.put("countFunction", countFunction);
        if (ctx.returnParameters() != null) {
            countReturn++;
            metric.put("countReturn", countReturn);
        }
        if (ctx.modifierList().getText().contains("internal")) {
            lineInternal.add(ctx.identifier().start.getLine());
            countinternalF++;
            metric.put("ninternalF", countinternalF);
        }

        if (ctx.modifierList().getText().contains("external")) {
            lineExtF.add(ctx.modifierList().start.getLine());
            countExtFunc++;
            metric.put("extF", countExtFunc);

        }

        if (ctx.modifierList().getText().contains("public")) {
            //    System.out.println("ctx.identifier().getText()  " +ctx.identifier().getText());
            linePublicF.add(ctx.identifier().start.getLine());
            countPublicFunc++;
            metric.put("publicF", countPublicFunc);

        }
        super.visitFunctionDefinition(ctx);
        return null;
    }

    @Override
    public Void visitTypeName(@NotNull TypeNameContext ctx) {

        //  System.out.println(" ----    typeName : " + ctx.getText());
        if ((ctx.getText() != null && ctx.getText().contains("["))) {
            //System.out.println(" ----   Array : " + ctx.getText());
            nArray++;
            metric.put("nArray", nArray);

        }

        super.visitTypeName(ctx);
        return null;
    }

    @Override
    public Void visitStatement(@NotNull StatementContext ctx) {
        if (ctx.forStatement() != null) {
            lineCicli.add(ctx.forStatement().start.getLine());
            nCicli++;
            metric.put("nCicli", nCicli);
        }

        if (ctx.whileStatement() != null) {
            nCicli++;
            lineCicli.add(ctx.whileStatement().start.getLine());
            metric.put("nCicli", nCicli);
        }
        if (ctx.doWhileStatement() != null) {
            lineCicli.add(ctx.doWhileStatement().start.getLine());
            nCicli++;
            metric.put("nCicli", nCicli);
        }
        int cInnerStat = 0;
        if (ctx.forStatement() instanceof ForStatementContext) {
            if (ctx.forStatement().statement().block() != null) {
                List<StatementContext> statementForList = ctx.forStatement().statement().block().statement();
                int figli = statementForList.size();
                for (int ff = 0; ff < figli; ff++) {
                    if (((statementForList.get(ff).simpleStatement())) instanceof SimpleStatementContext) {
                        System.out.println("kkkkk" + statementForList.get(ff).simpleStatement().getText() + statementForList.get(ff).simpleStatement().start.getLine());
                        lineCountOperator.add(statementForList.get(ff).simpleStatement().start.getLine());
                        cInnerStat = cOperator.visitSimpleStatement(statementForList.get(ff).simpleStatement());
                        metric.put("countOperator", cInnerStat);

                    }

                }

            }
        }
        if (ctx.ifStatement() instanceof IfStatementContext) {
            if (ctx.ifStatement().statement() != null) {
                List<StatementContext> statementifList = ctx.ifStatement().statement();
                for (int i = 0; i < statementifList.size(); i++) {

                    if (statementifList.get(i).simpleStatement() instanceof SimpleStatementContext) {
                        lineCountOperator.add(statementifList.get(i).simpleStatement().start.getLine());
                    }

                    if (statementifList.get(i).block() instanceof BlockContext) {
                        List<StatementContext> stat = statementifList.get(i).block().statement();

                        for (int j = 0; j < stat.size(); j++) {
                            if (stat.get(j).simpleStatement() instanceof SimpleStatementContext) {
                                lineCountOperator.add(stat.get(j).start.getLine());

                            }
                        }
                    }

                }
            }

            cInnerStat = cOperator.visitIfStatement(ctx.ifStatement());
            metric.put("countOperator", cInnerStat);

        }

        if (ctx.whileStatement() instanceof WhileStatementContext) {
            if (ctx.whileStatement().statement().block() != null) {
                List<StatementContext> statementWhileList = ctx.whileStatement().statement().block().statement();
                for (int ff = 0; ff < statementWhileList.size(); ff++) {

                    if (((statementWhileList.get(ff).simpleStatement())) instanceof SimpleStatementContext) {
                        lineCountOperator.add(statementWhileList.get(ff).simpleStatement().start.getLine());
                        cInnerStat = cOperator.visitSimpleStatement(statementWhileList.get(ff).simpleStatement());
                        metric.put("countOperator", cInnerStat);
                    }

                }
            }
        }

        //   System.out.println("identifier ------ "+ ctx.getText());
        if (ctx.getText() != null && ctx.getText().contains("[") && ctx.getText().contains("memory")) {

            nMemory++;
            metric.put("nMemory", nMemory);
            // System.out.println(nMemory);
        }

        super.visitStatement(ctx);
        return null;
    }

    @Override
    public Void visitEventDefinition(@NotNull EventDefinitionContext ctx) {
        //String event=ctx.getText();

        if (ctx.getText() != null) {
            lineEvent.add(ctx.start.getLine());
            nEvent++;
            metric.put("nEvent", nEvent);
        }

        super.visitEventDefinition(ctx);
        return null;
    }

    @Override
    public Void visitExpression(@NotNull ExpressionContext ctx) {
        for (String n : nome) {
            //System.out.println("vist:" + n);
            if (ctx.getText().contains("=")) {
                if (ctx.start.getText().equals(n)) {
                    lineMTArray.add(ctx.start.getLine());
                    countUseMem = countUseMem + 1;
                }
            }
        }
        for (String a : nomeArray) {
            if (ctx.getText().contains("=")) {
                if (ctx.start.getText().equals(a)) {
                    countUseArray = countUseArray + 1;
                }
            }
        }

        super.visitExpression(ctx);
        return null;
    }

    @Override
    public Void visitVariableDeclarationStatement(@NotNull VariableDeclarationStatementContext ctx) {

        if (!(ctx.expression() == null)) {

            if (ctx.expression().getText().equalsIgnoreCase("0") || ctx.expression().getText().equalsIgnoreCase("false")
                    || ctx.expression().getText().equalsIgnoreCase("\"\"")
                    || ctx.expression().getText().equalsIgnoreCase("0.0")
                    || ctx.expression().getText().equalsIgnoreCase("0x0")) {
                lineCountVdefault.add(ctx.expression().start.getLine());
                countVdefault++;
                //System.out.println("valori default   " +  ctx.expression().getText());
                metric.put("countVdefault", countVdefault);
            }

        }
      
        super.visitVariableDeclarationStatement(ctx);
        return null;

    }

    
    @Override
    public Void visitStateVariableDeclaration(@NotNull StateVariableDeclarationContext ctx) {
        countVi++;
        metric.put("countVi", countVi);
        vI.add(ctx.identifier().getText());
        /**
         * ****************************************
         */
        lineVi.add(ctx.identifier().start.getLine());
        if (ctx.expression() != null) {
            //   System.out.println("text ----- " + ctx.expression().getText());
            if (ctx.expression().getText().equalsIgnoreCase("0") || ctx.expression().getText().equalsIgnoreCase("false")
                    || ctx.expression().getText().equalsIgnoreCase("\"\"")
                    || ctx.expression().getText().equalsIgnoreCase("0.0")
                    || ctx.expression().getText().equalsIgnoreCase("0x0")) {
                countViDefault++;
                metric.put("countViDefault", countViDefault);
                lineCountVidefault.add(ctx.expression().start.getLine());

            }
        }

        super.visitStateVariableDeclaration(ctx);
        return null;

    }

    // Struct
    @Override
    public Void visitStructDefinition(@NotNull StructDefinitionContext ctx) {

        List<VariableDeclarationContext> strctV = ctx.variableDeclaration();
        for (int i = 0; i < strctV.size(); i++) {
            variableStruct.add(strctV.get(i).identifier().getText());
            lineVinS.add(strctV.get(i).start.getLine());
            countS++;
        }
        metric.put("vInS", countS);
        super.visitStructDefinition(ctx);
        return null;
    }

    @Override
    public Void visitVariableDeclaration(@NotNull VariableDeclarationContext ctx) {

        nVar++;
        metric.put("nVar", nVar);
        nome = new ArrayList<String>();
        nomeArray = new ArrayList<String>();
        if (ctx.storageLocation() != null && ctx.storageLocation().getText().equals("memory")) {
            nome.add(ctx.identifier().getText());
            countDicArrayMemory = countDicArrayMemory + 1;
        } else if (ctx.identifier().getParent().getText().contains("]")) {
            nomeArray.add(ctx.identifier().getText());
            countDicArray = countDicArray + 1;
        }

      
        super.visitVariableDeclaration(ctx);
        return null;
    }

    @Override
    public Void visitMapping(@NotNull MappingContext ctx) {
        countMapping++;
        lineNMapping.add(ctx.start.getLine());
        metric.put("mapping", countMapping);
        super.visitMapping(ctx);
        return null;
    }

    @Override
    public Void visitElementaryTypeName(@NotNull ElementaryTypeNameContext ctx) {

        if (ctx.getText().contains("string")) {
            nString++;
            metric.put("nString", nString);
        }
        if (ctx.getText().contains("bytes")) {
            nBytes++;
            metric.put("nBytes", nBytes);

        }

        if (ctx.getText().contains("uint")) {
            nUint++;
            metric.put("nUint", nUint);
        }
        //conta uint256
        if (ctx.getText().equals("uint256")) {
            lineNUint256.add(ctx.start.getLine());
            nUint256++;
            metric.put("nUint256", nUint256);
        }
        //conta boolean per metrica n.29
        if (ctx.getText().equals("bool")) {
            lineNBool.add(ctx.start.getLine());
            nBool++;
            metric.put("nBool", nBool);
        }
        super.visitElementaryTypeName(ctx);
        return null;
    }

    @Override
    public Void visitFunctionCallArguments(@NotNull FunctionCallArgumentsContext ctx) {
        if (ctx.getParent() instanceof ExpressionContext) {
            ExpressionContext ectx = (ExpressionContext) ctx.getParent();
            String a = ectx.getChild(0).getText().toString();
            noif.add(a);
        }
        return null;
    }

    @Override
    public Void visitEventParameter(@NotNull EventParameterContext ctx) {
        /**
         * ***************************************************
         */
        if (ctx.getChild(1) != null && ctx.getChild(1).getText().equals("indexed")) {
            lineNIndexed.add(ctx.start.getLine());
            indexed = ctx.getChild(1).getText();
            nIndexed++;

        }

        metric.put("nIndexed", nIndexed);
        super.visitEventParameter(ctx);
        return null;
    }

    public ArrayList<String> getNome() {
        return nome;
    }

    public int getCountDicArrayMemory() {
        return countDicArrayMemory;
    }

    public int getCountDicArray() {
        return countDicArray;
    }

    public int getCountUseArray() {
        return countUseArray;
    }

    public int getContractCount() {
        return contractDefCounts.getOrDefault("contract", 0);
    }

    public int getLibraryCount() {
        return contractDefCounts.getOrDefault("library", 0);
    }

    public int getLibraryCount1() {
        return contractDefCounts.getOrDefault("function", 0);
    }

    public int getInterfaceCount() {
        return contractDefCounts.getOrDefault("interface", 0);
    }

    public int getTotalContractCount() {
        return count;
    }

    public Map<ContractDefinitionContext, Integer[]> getMetricMap() {
        return contractMetrics;
    }

    public Map<Integer, List<String>> getMap() {
        return contractNOI;
    }

    public HashMap<String, Integer> getMetric() {
        return metric;
    }

    public ArrayList<String> getListImport() {
        return iLibrary;
    }

    public ArrayList<String> getListExtF() {
        return extFunction;
    }

    public ArrayList<String> getListIntF() {
        return intFunction;
    }

    public ArrayList<String> getListpublicF() {
        return publicFunction;
    }

    public ArrayList<String> getListVi() {
        return vI;
    }

    public ArrayList<String> getVariableStructList() {
        return variableStruct;
    }

    public List<String> getNoif() {
        return noif;
    }

    public int getCountUseMem() {
        return countUseMem;
    }

    /**
     * ***************************************
     */
    public ArrayList<Integer> getLineVi() {
        return lineVi;
    }

    public ArrayList<Integer> getLineVinS() {
        return lineVinS;
    }

    public ArrayList<Integer> getLinePublicF() {
        return linePublicF;
    }

    public ArrayList<Integer> getLineExtF() {
        return lineExtF;
    }

    public ArrayList<Integer> getLineCountVdefault() {
        return lineCountVdefault;
    }

    public ArrayList<Integer> getLineCountVidefault() {
        return lineCountVidefault;
    }

    public ArrayList<Integer> getLineImport() {
        return lineImport;
    }

    public ArrayList<Integer> getLineFunction() {
        return lineFunction;
    }

    public ArrayList<Integer> getLineInternalF() {
        return lineInternal;
    }

    public ArrayList<Integer> getLineCicli() {
        return lineCicli;
    }

    public ArrayList<Integer> getLineEvent() {
        return lineEvent;
    }

    public ArrayList<Integer> getLineNIndexed() {
        return lineNIndexed;
    }

    public ArrayList<Integer> getLineNBool() {
        return lineNBool;
    }

    public ArrayList<Integer> getLineNMapping() {
        return lineNMapping;
    }

    public ArrayList<Integer> getLineNUint256() {
        return lineNUint256;
    }

    public ArrayList<Integer> getLineCountOperator() {
        return lineCountOperator;
    }

    public ArrayList<Integer> getLineMTArray() {
        return lineMTArray;
    }
}
