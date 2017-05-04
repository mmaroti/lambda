/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public class TestLang {
	private static WriterExec<Object> WRITER = new WriterExec<Object>();
	private static RewriterExec<Object> REWRITER = new RewriterExec<Object>();

	public static void print(Term<Object> term) {
		System.out.println(term);
		System.out.println(WRITER.evaluate(term));
		System.out.println(REWRITER.evaluate(term));
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
