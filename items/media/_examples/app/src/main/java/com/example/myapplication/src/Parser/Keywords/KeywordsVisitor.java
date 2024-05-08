// Generated from E:/comp6442/groupwork/gp-24s1/items/media/_examples/app/src/main/java/com/example/myapplication/src/Parser/Keywords.g4 by ANTLR 4.13.1
package com.example.myapplication.src.Parser.Keywords;



import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link KeywordsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface KeywordsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link KeywordsParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(KeywordsParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordsParser#text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(KeywordsParser.TextContext ctx);
}