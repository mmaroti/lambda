/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.data.*;

public class UnitTests {
	private static WriterEval<Data> WRITER = new WriterEval<Data>();
	private static CalculatorEval CALCULATOR = new CalculatorEval();
	private static PartialEval<Data> PARTIAL = new PartialEval<Data>(CALCULATOR);

	public static void print(Term<Data> term) {
		System.out.println(term);
		System.out.println(WRITER.evaluate(term));
		System.out.println(PARTIAL.evaluate(term));
		// if (term.getExtent() == 0)
		// System.out.println(CALCULATOR.evaluate(term));
		System.out.println();
	}

	public static void test1() {
		Term<Data> a = new Operator<Data>(OpData.INT_ADD, new Literal<Data>(
				new IntData(1)), new Literal<Data>(new IntData(2)));
		print(a);

		Term<Data> b = new Apply<Data>(new Literal<Data>(OpData.INT_ADD),
				new Pair<Data>(new Variable<Data>(1), new Variable<Data>(0)));
		print(b);

		Term<Data> c = new Apply<Data>(new Literal<Data>(OpData.INT_ADD),
				new Pair<Data>(new Variable<Data>(1), new Apply<Data>(
						new Literal<Data>(OpData.INT_ADD), new Pair<Data>(
								new Variable<Data>(0), new Variable<Data>(0)))));
		print(c);

		Term<Data> d = new Apply<Data>(new Lambda<Data>(c), new Literal<Data>(
				new IntData(10)));
		print(d);
	}

	public static void test2() {
		Term<Data> s = new Lambda<Data>(new Lambda<Data>(new Lambda<Data>(
				new Apply<Data>(new Apply<Data>(new Variable<Data>(2),
						new Variable<Data>(0)), new Apply<Data>(
						new Variable<Data>(1), new Variable<Data>(0))))));
		print(s);

		Term<Data> k = new Lambda<Data>(new Lambda<Data>(new Variable<Data>(1)));
		print(k);

		Term<Data> t = new Apply<Data>(new Apply<Data>(s, k), k);
		print(t);
	}

	public static void main(String[] args) {
		test1();
		// test2();
	}
}
