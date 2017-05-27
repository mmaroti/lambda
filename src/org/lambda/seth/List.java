/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.seth;

public abstract class List extends Data {
	@Override
	public String toString() {
		String s = "[";

		List list = this;
		while (list instanceof Cons) {
			if (list != this)
				s += ", ";

			Cons c = (Cons) list;
			s += c.head.toString();
			list = c.tail;
		}

		s += "]";
		return s;
	}

	public boolean equals(Object other) {
		if (!(other instanceof List))
			return false;

		List list1 = this;
		List list2 = (List) other;

		while (list1 instanceof Cons && list2 instanceof Cons) {
			Cons cons1 = (Cons) list1;
			Cons cons2 = (Cons) list2;

			if (!cons1.head.equals(cons2.head))
				return false;

			list1 = cons1.tail;
			list2 = cons2.tail;
		}

		return list1 == list2;
	}

	public int length() {
		int len = 0;
		List list = this;
		while (list instanceof Cons) {
			len += 1;
			list = ((Cons) list).tail;
		}
		return len;
	}

	public static List NILL = new List() {
	};

	public static class Cons extends List {
		public final Data head;
		public final List tail;

		public Cons(Data head, List tail) {
			this.head = head;
			this.tail = tail;
		}
	}
}
