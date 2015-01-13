/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell;

public class Prelude extends Language {

	private static Var A = new Var(TYPE, "a");
	private static Var B = new Var(TYPE, "b");

	private static Var AX = new Var(A, "x");
	private static Var BX = new Var(B, "x");

	// ------- Bool

	public static Obj BOOL = new Lit(TYPE, "Bool");

	public static Obj TRUE = new Dat(BOOL, "True");

	public static Obj FALSE = new Dat(BOOL, "False");

	// ------- Maybe

	public static Obj MAYBE = new Lam(A, new Dat(TYPE, "Maybe", A));

	public static Obj NOTHING = new Lam(B, new Dat(MAYBE.apply(B), "Nothing"));

	public static Obj JUST = new Lam(B, new Lam(BX, new Dat(MAYBE.apply(B),
			"Just", BX)));

	// ------- Main

	public static void main(String[] args) {
		System.out.println(BOOL.show());
		System.out.println(TRUE.show());
		System.out.println(FALSE.show());

		System.out.println(MAYBE.show());
		System.out.println(MAYBE.apply(BOOL).show());

		System.out.println(NOTHING.show());
		System.out.println(NOTHING.apply(BOOL).show());

		System.out.println(JUST.show());
		System.out.println(JUST.apply(BOOL).show());
		System.out.println(JUST.apply(BOOL).apply(FALSE).show());
	}
}
