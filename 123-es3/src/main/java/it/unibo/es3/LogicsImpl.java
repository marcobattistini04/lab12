package it.unibo.es3;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicsImpl implements Logics {
	private final static int INITCELLS = 3;
	private final int size;
	
	public LogicsImpl(final int size) {
		this.size = size;
	}

	@Override
	public List<Pair<Integer, Integer>> getPositions() {
		final List<Pair<Integer, Integer>> list = new ArrayList<>();
		final Random random = new Random();
		int x;
		int y;
		for(int i = 0; i < INITCELLS; i++) {
			x = random.nextInt(size);
			y = random.nextInt(size);
			list.add(new Pair<Integer,Integer>(x, y));
		}
		return list;
	}


	@Override
	public void hit() {
	}

	@Override
	public boolean toQuit() {
		return false;
	}



}
