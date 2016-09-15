/**
 *	Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda5.term;

public class Test {
	public static void main(String[] args) {
		Term i = new Lambda("x", new Variable(0));
		System.out.println(i);
		// System.out.println(i.evaluate(null));

		Term k = new Lambda("x", new Lambda("y", new Variable(1)));
		System.out.println(k);

		Term s = new Lambda("x", new Lambda("y", new Lambda("z", new Apply(
				new Apply(new Variable(2), new Variable(0)), new Apply(
						new Variable(1), new Variable(0))))));
		System.out.println(s);
		
		Term f = new IntLiteral(5);
		System.out.println(f);
		System.out.println(f.evaluate(null));
		
		Term i5 = new Apply(i, f);
		System.out.println(i5);
		System.out.println(i5.evaluate(null));
	}
}
