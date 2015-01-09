/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda4;

import org.mmaroti.lambda4.Calculus.Term;

public class Test {
	public static void main(String[] args) {
		final Runtime<Calculus.Term> runtime = new Calculus();

		final Calculus.Term t = runtime.literal("true");
		final Calculus.Term n = runtime.literal("not");
		final Calculus.Term l = runtime.closure("x", new Runtime.Closure<Calculus.Term>() {
			@Override
			public Term evaluate(Runtime<Term> runtime, Term argument) {
				return runtime.apply(n, argument);
			}
		});
		
		final Calculus.Term a = runtime.apply(l, l);
		System.out.println(a);
	}
}
