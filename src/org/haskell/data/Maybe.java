/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.data;

public class Maybe<JUST> {
	private static final Object nothing = new Object();
	private final JUST just;

	public boolean isNothing() {
		return just == nothing;
	}

	public JUST getJust() {
		if (just == nothing)
			throw new IllegalStateException();

		return just;
	}

	public Maybe(JUST just) {
		this.just = just;
	}

	@SuppressWarnings("unchecked")
	public Maybe() {
		just = (JUST) nothing;
	}
}
