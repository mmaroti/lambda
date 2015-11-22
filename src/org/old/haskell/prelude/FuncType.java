/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.old.haskell.prelude;

public class FuncType<ARG_TYP extends Type, RET_TYP extends Type> extends Type {

	public final ARG_TYP arg;
	public final RET_TYP ret;

	public FuncType(ARG_TYP arg, RET_TYP ret) {
		this.arg = arg;
		this.ret = ret;
	}
}
