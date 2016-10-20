/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public class Validate {
	public static void print(Term term) {
		System.out.println(term);
		System.out.println(term.rewrite());
		System.out.println();
	}

	public static void main(String[] args) {
		Term a = new Addition(new Variable(1), new Addition(new Variable(0), new Variable(0)));
		print(a);

		Term b = new Apply(new Lambda(a), new Integer(10));
		print(b);

		Term s = new Lambda(new Lambda(new Lambda(new Apply(new Apply(new Variable(2), new Variable(0)), new Apply(
			new Variable(1), new Variable(0))))));
		print(s);

		Term k = new Lambda(new Lambda(new Variable(1)));
		print(k);

		Term t = new Apply(new Apply(s, k), k);
		print(t);
	}
}
