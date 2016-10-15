/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lambda7;

import java.io.*;

public class Parser {
	private final Reader reader;
	private int line;
	private int column;
	private int cp;

	public static final int EOF = -1;

	public Parser(Reader reader) {
		if (!(reader instanceof BufferedReader))
			reader = new BufferedReader(reader);
		this.reader = reader;

		line = 0;
		cp = '\n';
		next();
	}

	public Parser(String string) {
		reader = new StringReader(string);

		line = 0;
		cp = '\n';
		next();
	}

	public String location() {
		return "line " + line + " column " + column;
	}

	public int peek() {
		return cp;
	}

	public void next() {
		if (cp == '\n') {
			line += 1;
			column = 1;
		} else
			column += 1;

		try {
			cp = reader.read();
		} catch (IOException exp) {
			throw new RuntimeException(exp);
		}
	}
	
	public void spaces() {
		while (Character.isSpaceChar(cp))
			next();
	}

	public String identifier() {
		spaces();

		if (!Character.isLetter(cp))
			return null;

		StringBuilder s = new StringBuilder();
		while (Character.isLetter(cp) || Character.isDigit(cp)) {
			s.appendCodePoint(cp);
			next();
		}

		return s.toString();
	}

	public Integer integer() {
		spaces();

		if (!Character.isDigit(cp))
			return null;

		long a = cp - '0';
		next();

		while (Character.isDigit(cp)) {
			a *= 10;
			a += cp - '0';

			if (a > Integer.MAX_VALUE)
				throw new IllegalArgumentException("too large integer at"
						+ location());

			next();
		}

		if (Character.isLetter(cp))
			throw new IllegalArgumentException("unexpected letter at "
					+ location());

		return new Integer((int) a);
	}
}
