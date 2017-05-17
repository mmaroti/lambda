/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lexer;

public class Symbol extends Token {
	public final String name;

	public Symbol(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	private static final int[] OPERATORS = { '+', '-', '*', '/', '>', '<', '=', '\\' };

	public static boolean isOperator(Lexer lexer) {
		for (int i = 0; i < OPERATORS.length; i++)
			if (OPERATORS[i] == lexer.peek())
				return true;
		return false;
	}

	public static Symbol parseOperator(Lexer lexer) {
		assert isOperator(lexer);

		StringBuilder name = new StringBuilder();
		do {
			name.appendCodePoint(lexer.peek());
			lexer.next();
		} while (isOperator(lexer));

		return new Symbol(name.toString());
	}

	public static boolean isIdentifier(Lexer lexer) {
		return Character.isLetter(lexer.peek());
	}

	public static Symbol parseIdentifier(Lexer lexer) {
		assert isIdentifier(lexer);

		StringBuilder name = new StringBuilder();
		do {
			name.appendCodePoint(lexer.peek());
			lexer.next();
		} while (Character.isLetterOrDigit(lexer.peek()));

		return new Symbol(name.toString());
	}
}
