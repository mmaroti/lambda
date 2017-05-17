/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lexer;

import java.util.*;

public class Block extends Token {
	public final String type;
	public final List<Token> list;

	public Block(String type, List<Token> list) {
		this.type = type;
		this.list = list;
	}

	@Override
	public String toString() {
		assert type.length() <= 1;

		StringBuilder b = new StringBuilder(type);
		for (int i = 0; i < list.size(); i++) {
			if (i != 0)
				b.append(' ');
			b.append(list.get(i));
		}

		if (type.length() == 1)
			b.append(BLOCK_END.charAt(BLOCK_BGN.indexOf(type.charAt(0))));

		return b.toString();
	}

	private static final String BLOCK_BGN = "(";
	private static final String BLOCK_END = ")";

	public static boolean isBlock(Lexer lexer) {
		return BLOCK_BGN.indexOf(lexer.peek()) >= 0;
	}

	public static Block parseBlock(Lexer lexer) {
		int bgn = lexer.peek();
		int end = -1;

		lexer.next();

		assert BLOCK_BGN.indexOf(bgn) >= 0;
		end = BLOCK_END.charAt(BLOCK_BGN.indexOf(bgn));

		List<Token> list = Token.parseTokens(lexer);
		if (lexer.peek() == end)
			lexer.next();
		else
			lexer.error("block " + new String(Character.toChars(end)) + " expected");

		return new Block(new String(Character.toChars(bgn)), list);
	}
}
