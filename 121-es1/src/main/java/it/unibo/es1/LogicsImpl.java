package it.unibo.es1;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {
	private final int size;
	private List<Integer> values = new ArrayList<>();

	public LogicsImpl(int size) {
		this.size = size;
		for (int i = 0; i < size; i++){
			this.values.addLast(0);
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public List<Integer> values() {
		return List.copyOf(this.values);
	}

	@Override
	public List<Boolean> enablings() {
		List<Boolean> enabledButtons = new ArrayList<>();
		for (int elem : this.values) {
			if(elem == this.size) {
				enabledButtons.addLast(false);
			} else {
				enabledButtons.addLast(true);
			}
		}
		return enabledButtons;
	}

	@Override
	public int hit(int elem) {
		this.values.set(elem, this.values.get(elem) + 1);
		return this.values.get(elem);
	}

	private boolean sameValues() {
		final int currentValue = this.values.get(0);
		for (final int elem : this.values) {
			if(currentValue != elem) {
				return false;
			}
		}
		return true;
	}
	@Override
	public String result() {
		String result = "";
		result = result + "<<";
		for (int i = 0; i < this.size -1 ; i++) {
			result = result + this.values.get(i) + "|";
		}
		result = result + this.values.get(size - 1) + ">>";
		return result;
	}

	@Override
	public boolean toQuit() {
		if (sameValues()) {
			return true;
		}
		return false;
	}
}
