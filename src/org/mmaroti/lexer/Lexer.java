/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lexer;

import java.io.*;

public class Lexer {
	private final Reader reader;
	private int line;
	private int column;
	private int peek;

	public static final int EOF = -1;

	public Lexer(Reader reader) {
		if (!(reader instanceof BufferedReader))
			reader = new BufferedReader(reader);
		this.reader = reader;

		line = 1;
		column = 0;
		peek = 0;
		next();
	}

	public Lexer(String string) {
		reader = new StringReader(string);

		line = 1;
		column = 0;
		peek = 0;
		next();
	}

	public void error(String message) {
		throw new IllegalArgumentException(message + " at line " + line
				+ " column " + column);
	}

	public int peek() {
		return peek;
	}

	public void next() {
		assert peek != EOF;

		boolean cr = peek == '\r';

		if (peek == '\n') {
			line += 1;
			column = 1;
		} else
			column += 1;

		try {
			peek = reader.read();
		} catch (IOException exp) {
			throw new RuntimeException(exp);
		}

		if (cr && peek != '\n') {
			line += 1;
			column = 1;
		}
	}
}
