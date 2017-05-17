/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.seth;

public abstract class List extends Data {
	@Override
	public String toString() {
		String s = "[";

		List l = this;
		while (l instanceof Cons) {
			if (l != this)
				s += ", ";

			Cons c = (Cons) l;
			s += c.head.toString();
			l = c.tail;
		}

		s += "]";
		return s;
	}

	public static class Nill extends List {
		public Nill() {
		}
	}

	public static class Cons extends List {
		public final Data head;
		public final List tail;

		public Cons(Data head, List tail) {
			this.head = head;
			this.tail = tail;
		}
	}
}
