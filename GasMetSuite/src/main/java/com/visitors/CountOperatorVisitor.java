package com.visitors;


import java.util.List;
import org.antlr.v4.runtime.misc.NotNull;
import com.antlr4.grammar.solidity.SolidityBaseVisitor;
import com.antlr4.grammar.solidity.SolidityParser;
import com.antlr4.grammar.solidity.SolidityParser.BlockContext;
import com.antlr4.grammar.solidity.SolidityParser.DoWhileStatementContext;
import com.antlr4.grammar.solidity.SolidityParser.ForStatementContext;
import com.antlr4.grammar.solidity.SolidityParser.FunctionDefinitionContext;
import com.antlr4.grammar.solidity.SolidityParser.IfStatementContext;
import com.antlr4.grammar.solidity.SolidityParser.ParameterListContext;
import com.antlr4.grammar.solidity.SolidityParser.SimpleStatementContext;
import com.antlr4.grammar.solidity.SolidityParser.StatementContext;
import com.antlr4.grammar.solidity.SolidityParser.WhileStatementContext;

@SuppressWarnings("deprecation")
public class CountOperatorVisitor extends SolidityBaseVisitor<Integer> {
	private char match[]={'+','-', '*','/','%','^','='}; 
	int sizeMatch=match.length;
	private int operatorIf;
	private int operatorFor;
	private int operatorSimple;
	private int operatorWhile;
	private int operatorDoWhile;
	public CountOperatorVisitor() {

	}

	@Override
	public Integer visitIfStatement(@NotNull IfStatementContext ctx) {

		List <StatementContext>	statementifList= ctx.statement();
		for (int i=0;i<statementifList.size();i++) {

			if (statementifList.get(i).simpleStatement() instanceof SimpleStatementContext) {
				operatorIf=visitSimpleStatement(statementifList.get(i).simpleStatement());

			}

			if (statementifList.get(i).block() instanceof BlockContext) {
				List <StatementContext>	stat= statementifList.get(i).block().statement();
				for (int j=0;j<stat.size();j++) {
					operatorIf=visitSimpleStatement(stat.get(j).simpleStatement());

				}}

		}
		
		return operatorIf;
	}

	@Override
	public Integer visitForStatement(@NotNull ForStatementContext ctx) {

		if(ctx instanceof ForStatementContext) {
                    if(ctx.statement().block()!=null){
			List <StatementContext>	statementForList= ctx.statement().block().statement();
			int figli= statementForList.size();
			for (int ff=0; ff<figli;ff++) {
				if(((statementForList.get(ff).simpleStatement())) instanceof SimpleStatementContext) {
					operatorFor=visitSimpleStatement(statementForList.get(ff).simpleStatement());

				}
			}	}

		}

		return operatorFor;	
	}


	@Override
	public Integer visitSimpleStatement(@NotNull SimpleStatementContext ctx) {
		if(ctx instanceof SimpleStatementContext) {
			String singolaEspressione=ctx.getText();
			for (int c=0; c<singolaEspressione.length();c++) {
				for(int p=0;p<sizeMatch;p++) {
					if(singolaEspressione.charAt(c)==match[p]) {
						operatorSimple++;		
					}

				}
			}		

		}
		return operatorSimple;

	}
	@Override
	public Integer visitWhileStatement(@NotNull WhileStatementContext ctx) {
		if(ctx instanceof WhileStatementContext) {
                       if(ctx.statement().block()!=null){
			List <StatementContext>	statementWhileList= ctx.statement().block().statement();
			int figliW= statementWhileList.size();
			for (int ff=0; ff<figliW;ff++) {
				if(((statementWhileList.get(ff).simpleStatement())) instanceof SimpleStatementContext) {
					operatorWhile=visitSimpleStatement(statementWhileList.get(ff).simpleStatement());
				}
			}}	
		}
		return operatorWhile;



	}
	@Override
	public Integer visitDoWhileStatement(@NotNull DoWhileStatementContext ctx) {
		if(ctx instanceof DoWhileStatementContext) {
			List <StatementContext>	statementWhileList= ctx.statement().block().statement();
			int figliW= statementWhileList.size();
			for (int ff=0; ff<figliW;ff++) {
				if(((statementWhileList.get(ff).simpleStatement())) instanceof SimpleStatementContext) {
					operatorDoWhile=visitSimpleStatement(statementWhileList.get(ff).simpleStatement());
				}
			}	
		}
		return operatorDoWhile;



	}
	



}

