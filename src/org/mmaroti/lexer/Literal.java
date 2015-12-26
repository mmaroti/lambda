/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lexer;

public class Literal extends Token {
	public final Object value;

	public Literal(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		if (value instanceof Integer)
			return value.toString();
		else if (value instanceof String) {
			String s = (String) value;

			StringBuilder b = new StringBuilder("\"");
			for (int pos = 0; pos < s.length();) {
				int cp = s.codePointAt(pos);

				int j = ESCAPED_CHR.indexOf(cp);
				if (j < 0)
					b.appendCodePoint(cp);
				else {
					b.append('"');
					b.append(ESCAPED_VAL.charAt(j));
				}

				pos += Character.charCount(cp);
			}
			b.append('"');

			return b.toString();
		} else
			throw new IllegalArgumentException("unknown literal");
	}

	public static boolean isInteger(Lexer lexer) {
		return '0' <= lexer.peek() && lexer.peek() <= '9';
	}

	public static Literal parseInteger(Lexer lexer) {
		assert '0' <= lexer.peek() && lexer.peek() <= '9';

		long a = 0;
		do {
			a *= 10;
			a += lexer.peek() - '0';
			if (a > Integer.MAX_VALUE)
				lexer.error("too large integer literal");

			lexer.next();
		} while ('0' <= lexer.peek() && lexer.peek() <= '9');

		return new Literal(new Integer((int) a));
	}

	private static final String ESCAPED_CHR = "\\\"n";
	private static final String ESCAPED_VAL = "\\\"\n";

	public static boolean isString(Lexer lexer) {
		return lexer.peek() == '"';
	}

	public static Literal parseString(Lexer lexer) {
		assert lexer.peek() == '"';
		lexer.next();

		StringBuilder s = new StringBuilder();
		while (lexer.peek() != '"') {
			if (lexer.peek() != '\\') {
				int i = ESCAPED_CHR.indexOf(lexer.peek());
				if (i >= 0)
					lexer.error("unexpected unescaped character");
				else
					s.appendCodePoint(lexer.peek());
			} else {
				lexer.next();
				int i = ESCAPED_CHR.indexOf(lexer.peek());
				if (i < 0)
					lexer.error("invalid escaped character");
				else
					s.append(ESCAPED_VAL.charAt(i));
			}
		}
		lexer.next();

		return new Literal(s.toString());
	}
}
