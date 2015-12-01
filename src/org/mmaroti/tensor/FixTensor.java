/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.tensor;

public abstract class FixTensor<ELEM, TENSOR> {
	private final int[] shape;

	public FixTensor(int[] shape) {
		this.shape = shape;
	}

	public int[] getShape() {
		return shape;
	}

	public int getOrder() {
		return shape.length;
	}

	public int getDim(TENSOR tensor, int index) {
		return shape[index];
	}

	public abstract TENSOR create(Iterable<ELEM> elems);

	public abstract TENSOR reshape(TENSOR tensor, int[] map);
}
