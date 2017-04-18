/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public class TestLang {
	public static final String PRIM_ADD = "+";
	public static final String PRIM_PROD = "*";
	public static final String PRIM_AND = "&";
	public static final String PRIM_OR = "|";
	public static final String PRIM_NOT = "!";

	public static void print(Term<Object> term) {
		System.out.println(term);
		// System.out.println(term.write());
		System.out.println(term.rewrite());
		System.out.println();
	}

	public static void main(String[] args) {
		Term<Object> a = new Addition<Object>(new Variable<Object>(1),
				new Variable<Object>(0));
		print(a);

		Term<Object> b = new Addition<Object>(new Variable<Object>(1),
				new Addition<Object>(new Variable<Object>(0),
						new Variable<Object>(0)));
		print(b);

		Term<Object> c = new Apply<Object>(new Lambda<Object>(b),
				new Integer<Object>(10));
		print(c);

		Term<Object> s = new Lambda<Object>(new Lambda<Object>(
				new Lambda<Object>(new Apply<Object>(new Apply<Object>(
						new Variable<Object>(2), new Variable<Object>(0)),
						new Apply<Object>(new Variable<Object>(1),
								new Variable<Object>(0))))));
		print(s);

		Term<Object> k = new Lambda<Object>(new Lambda<Object>(
				new Variable<Object>(1)));
		print(k);

		Term<Object> t = new Apply<Object>(new Apply<Object>(s, k), k);
		print(t);
	}
}