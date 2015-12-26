/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lexer;

public class Test {
	public static void main(String[] args) {
		Token tok = Token.parse("\\f g x -> f x (g x)");
		System.out.println(tok);
	}
}
