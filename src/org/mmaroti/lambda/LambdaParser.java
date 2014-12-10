/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda;

import java.util.*;
import org.mmaroti.lambda.Calculus.*;
import org.mmaroti.lambda.LambdaCalculus.*;

public class LambdaParser {
	public final String string;
	public final int length;

	int index = 0;
	private Map<String,Variable> variables = new HashMap<String,Variable>();
	
	private LambdaParser(String string) {
		this.string = string;
		this.length = string.length();
	}

	private void error(String what) {
		throw new IllegalArgumentException(what + " at " + index + " \""
				+ string.substring(index, index + 5) + "\"");
	}

	private void parseSpaces() {
		while (index < length && Character.isWhitespace(string.charAt(index)))
			index += 1;
	}

	private boolean parseMatch(String s) {
		parseSpaces();

		if (string.startsWith(s, index)) {
			index += s.length();
			return true;
		} else
			return false;
	}

	Variable parseVariable() {
		parseSpaces();

		int start = index;
		while (index < length && Character.isLowerCase(string.charAt(index)))
			index += 1;

		if (start == index)
			return null;

		String name = string.substring(start, index);
		Variable var = variables.get(name);
		if (var == null) {
			var = new Variable(name);
			variables.put(name, var);
		}

		return var;
	}

	private Term parseAbstraction() {
		if (!parseMatch("(fun"))
			return null;

		Variable var = parseVariable();
		if (var == null)
			error("fun variable expected");

		Term expr = parseTerm();
		if (expr == null)
			error("fun expression expected");

		if (!parseMatch(")"))
			error("fun block not closed");

		return new Abstraction(var, expr);
	}

	private Term parseApplication() {
		if (!parseMatch("(call"))
			return null;

		Term fun = parseTerm();
		if (fun == null)
			error("call function expected");

		Term arg = parseTerm();
		if (arg == null)
			error("call argument expected");

		if (!parseMatch(")"))
			error("call block not closed");

		return new Application(fun, arg);
	}

	private Term parseRuntime() {
		if (parseMatch("ADD"))
			return new Literal(RuntimeContext.ADD);

		return null;
	}

	private Term parseNumber() {
		int start = index;
		while (index < length && Character.isDigit(string.charAt(index)))
			index += 1;

		if (start == index)
			return null;

		int val = Integer.parseInt(string.substring(start, index));
		return new Literal(new RuntimeContext.Number(val));
	}

	private Term parseTerm() {
		Term t = parseAbstraction();
		if (t != null)
			return t;

		t = parseApplication();
		if (t != null)
			return t;

		t = parseRuntime();
		if (t != null)
			return t;

		t = parseVariable();
		if (t != null)
			return t;

		t = parseNumber();
		if (t != null)
			return t;

		error("unrecognized term");
		return null;
	}

	private void parseEnd() {
		parseSpaces();
		if (index != length)
			error("extra token found");
	}

	public static Term parse(String string) {
		LambdaParser parser = new LambdaParser(string);

		Term term = parser.parseTerm();
		parser.parseEnd();

		return term;
	}
}
