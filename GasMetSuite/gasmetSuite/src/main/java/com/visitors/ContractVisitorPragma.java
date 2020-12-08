package com.visitors;

import java.util.ArrayList;

import org.antlr.v4.runtime.misc.NotNull;

import com.antlr4.grammar.solidity.SolidityBaseVisitor;
import com.antlr4.grammar.solidity.SolidityParser;
import com.antlr4.grammar.solidity.SolidityParser.ContractDefinitionContext;
import com.antlr4.grammar.solidity.SolidityParser.PragmaDirectiveContext;
import com.antlr4.grammar.solidity.SolidityParser.SourceUnitContext;



@SuppressWarnings("deprecation")
public class ContractVisitorPragma extends SolidityBaseVisitor<Void> {

  
    private String sourceText;
    private String contractName;
	private String pragmaValue;

    public ContractVisitorPragma(String sourceText) {
        this.sourceText = sourceText;
    }

    public ContractVisitorPragma() {
    }
    
public String getContractName(){
       
    return contractName;
}
public String getPragmaValue(){
       
    return pragmaValue;
}
   
    public Void visitContractDefinition(@NotNull ContractDefinitionContext ctx) {
           contractName=ctx.getChild(1).getText();
       
        return null;
    }
    

    public Void visitPragmaDirective(@NotNull PragmaDirectiveContext ctx) {
       
     
            if(ctx.pragmaValue().getText().contains("^")){
            pragmaValue=ctx.pragmaValue().getText().replace("^", "#").split("#")[1];
            }else{
                pragmaValue=ctx.pragmaValue().getText();
            }
       
        super.visitPragmaDirective(ctx);
        return null;
    }

   

}
