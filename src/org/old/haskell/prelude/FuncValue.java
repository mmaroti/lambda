/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.old.haskell.prelude;

public abstract class FuncValue<ARG_TYP extends Type, ARG_VAL extends Value<ARG_TYP>, RET_TYP extends Type, RET_VAL extends Value<RET_TYP>>
		extends Value<FuncType<ARG_TYP, RET_TYP>> {

	public FuncValue(FuncType<ARG_TYP, RET_TYP> type) {
		super(type);
	}

	public abstract RET_VAL apply(ARG_VAL arg);
}
