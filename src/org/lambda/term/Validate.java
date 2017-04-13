/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public class Validate {
	public static void print(Term<Boolean> term) {
		System.out.println(term);
		System.out.println(term.rewrite());
		System.out.println();
	}

	public static void main(String[] args) {
		Term<Boolean> a = new Addition<Boolean>(new Variable<Boolean>(1),
				new Addition<Boolean>(new Variable<Boolean>(0),
						new Variable<Boolean>(0)));
		print(a);

		Term<Boolean> b = new Apply<Boolean>(new Lambda<Boolean>(a),
				new Integer<Boolean>(10));
		print(b);

		Term<Boolean> s = new Lambda<Boolean>(new Lambda<Boolean>(
				new Lambda<Boolean>(new Apply<Boolean>(new Apply<Boolean>(
						new Variable<Boolean>(2), new Variable<Boolean>(0)),
						new Apply<Boolean>(new Variable<Boolean>(1),
								new Variable<Boolean>(0))))));
		print(s);

		Term<Boolean> k = new Lambda<Boolean>(new Lambda<Boolean>(
				new Variable<Boolean>(1)));
		print(k);

		Term<Boolean> t = new Apply<Boolean>(new Apply<Boolean>(s, k), k);
		print(t);
	}
}
