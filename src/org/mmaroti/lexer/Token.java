/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lexer;

import java.util.*;

public abstract class Token {
	@Override
	public abstract String toString();

	public static void parseSpaces(Lexer lexer) {
		while (Character.isSpaceChar(lexer.peek()))
			lexer.next();
	}

	public static List<Token> parseTokens(Lexer lexer) {
		List<Token> list = new ArrayList<Token>();

		for (;;) {
			parseSpaces(lexer);
			if (Block.isBlock(lexer))
				list.add(Block.parseBlock(lexer));
			else if (Symbol.isOperator(lexer))
				list.add(Symbol.parseOperator(lexer));
			else if (Symbol.isIdentifier(lexer))
				list.add(Symbol.parseIdentifier(lexer));
			else if (Literal.isInteger(lexer))
				list.add(Literal.parseInteger(lexer));
			else if (Literal.isString(lexer))
				list.add(Literal.parseString(lexer));
			else
				break;
		}

		return list;
	}

	public static Block parse(String string) {
		Lexer lexer = new Lexer(string);
		List<Token> list = parseTokens(lexer);
		if (lexer.peek() != -1)
			lexer.error("not fully parsed");

		return new Block("", list);
	}
}
