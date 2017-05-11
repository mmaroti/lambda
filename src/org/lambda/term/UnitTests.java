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
		System.out.println();
	}

	public static void test1() {
		Term<Data> type = new Literal<Data>(null);
		Term<Data> iadd = new Literal<Data>(BinaryOp.INT_ADD);

		Term<Data> a = iadd.apply(new Literal<Data>(new IntData(1))).apply(
				new Literal<Data>(new IntData(2)));
		print(a);

		Term<Data> b = iadd.apply(new Variable<Data>(1)).apply(
				new Variable<Data>(0));
		print(b);

		Term<Data> c = iadd.apply(new Variable<Data>(1)).apply(
				iadd.apply(new Variable<Data>(0)).apply(new Variable<Data>(0)));
		print(c);

		Term<Data> d = new Lambda<Data>(type, c).apply(new Literal<Data>(
				new IntData(10)));
		print(d);
	}

	public static void test2() {
		Term<Data> type = new Literal<Data>(null);
		Term<Data> v0 = new Variable<Data>(0);
		Term<Data> v1 = new Variable<Data>(1);
		Term<Data> v2 = new Variable<Data>(2);

		Term<Data> s = new Lambda<Data>(type, new Lambda<Data>(type,
				new Lambda<Data>(type, (v2.apply(v0)).apply(v1.apply(v0)))));
		print(s);

		Term<Data> k = new Lambda<Data>(type, new Lambda<Data>(type, v1));
		print(k);

		Term<Data> t = s.apply(k).apply(k);
		print(t);
	}

	public static void main(String[] args) {
		test1();
		test2();
	}
}
