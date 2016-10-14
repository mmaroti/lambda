/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda6;

public class Validation {
	public static void main(String[] args) {
		Term a = new Addition(new Integer(1), new Variable(0));
		System.out.println(a);

		Term b = new Lambda(new Variable(0));
		System.out.println(b);

		Term c = new Lambda(new Variable(1));
		System.out.println(c);

		Rewriter rewriter = new Rewriter();
		System.out.println(rewriter.compile(c).evaluate(new Integer(2)));
	}
}
