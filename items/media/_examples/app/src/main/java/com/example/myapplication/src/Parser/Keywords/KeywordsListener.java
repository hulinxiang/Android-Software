// Generated from E:/comp6442/groupwork/gp-24s1/items/media/_examples/app/src/main/java/com/example/myapplication/src/Parser/Keywords.g4 by ANTLR 4.13.1
package com.example.myapplication.src.Parser.Keywords;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KeywordsParser}.
 */
public interface KeywordsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KeywordsParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(KeywordsParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link KeywordsParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(KeywordsParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link KeywordsParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(KeywordsParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link KeywordsParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(KeywordsParser.TextContext ctx);
}