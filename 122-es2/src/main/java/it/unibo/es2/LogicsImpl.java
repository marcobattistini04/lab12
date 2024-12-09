package it.unibo.es2;


import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {
	private final static String SPACE = " ";
	private final static String AST = "*";
	private final int size;
	private List<List<String>> values;
	public LogicsImpl(final int size) {
		this.size = size;
		this.values = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			List<String> column = new ArrayList<>();
			this.values.addLast(column);
			for (int j = 0; j < size; j ++) {
				column.addLast(SPACE);
			}
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public String hit(final int x, final int y) {
		if(this.values.get(x).get(y).equals(SPACE)) {
			this.values.get(x).set(y, AST);
		} else {
			this.values.get(x).set(y, SPACE);
		}

		return this.values.get(x).get(y);
	}

	private boolean  columnsTest(final List<String> column) {
		return column.stream().allMatch(elem -> elem.equals(AST));
	}
	private boolean rowsTest(final List<String> row){
		return row.stream().allMatch(elem -> elem.equals(AST));
	}

	private List<String> rowValues(final List<List<String>> values, final int indexRow) {
		List<String> row = new ArrayList<>();
		for(List<String> elem : values) {
			row.addLast(elem.get(indexRow));
		}
		return row;
	}

	@Override
	public boolean toQuit() {
		boolean quit = false;
		for (int i = 0; i < size; i++) {
			quit = rowsTest(rowValues(values, i));
			if( quit == true) {
				return quit;
			}
		}
		for (List<String> column : values) {
			quit = columnsTest(column);
			if( quit == true) {
				return quit;
			}
		}
		return quit;
	}

}
