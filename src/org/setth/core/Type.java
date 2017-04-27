/**
 * Copyright (C) Miklos Maroti, 2017
 */

package org.setth.core;

public abstract class Type {
	public static class Primitive extends Type {
		public final int size;

		public Primitive(int size) {
			assert size >= 0;
			this.size = size;
		}
	}

	public static class Arrow extends Type {
		public final Type argument;
		public final Type result;

		public Arrow(Type argument, Type result) {
			this.argument = argument;
			this.result = result;
		}
	}
}
