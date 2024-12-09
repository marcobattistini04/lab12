package it.unibo.es3;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {
	public enum Direction {
		LEFT(0, -1),
		RIGHT(0, 1),
		UP(-1, 0),
		DOWN(1, 0),
		UPRIGHT(-1, 1),
		UPLEFT(-1, -1),
		DOWNRIGHT(1, 1),
		DOWNLEFT(1, -1);
		private final int dx;
		private final int dy;
				Direction(int x, int y) {
					this.dx = x;
					this.dy = y;
				}
		public Pair<Integer, Integer> move (Pair<Integer, Integer> elem) {
			return new Pair<Integer,Integer>(elem.getX() + dx, elem.getY() + dy);
		}
	}

	private final static int INITCELLS = 3;
	private boolean firstUse = true;
	private final int size;
	private final List<Pair<Integer, Integer>> positions;
	public LogicsImpl(final int size) {
		this.size = size;
		this.positions = new ArrayList<>();
	}

	@Override
	public List<Pair<Integer, Integer>> getPositions() {
		 return List.copyOf(this.positions);
	}

	private List<Pair<Integer, Integer>> generateRandom(){
		final List<Pair<Integer, Integer>> list = new ArrayList<>();
		Random ran = new Random();
		for(int i = 0; i < INITCELLS ; i++) {
			final int x = ran.nextInt(this.size);
			final int y = ran.nextInt(this.size);
			list.add(new Pair<Integer, Integer>(x, y));
		}

		return list;
	}

	private boolean isConsistent(Pair<Integer, Integer> elem) {
		return elem.getX() >= 0 && elem.getX() < size && elem.getY() >= 0 && elem.getY() < size; 
	}

	private Set<Pair<Integer, Integer>> neighbours(
		final Pair<Integer, Integer> elem, 
		final List<Pair<Integer, Integer>> actualEntries ) {
		Set<Pair<Integer, Integer>> neighbours = new HashSet<>();
		for(Direction dir : Direction.values()) {
			var neigh = dir.move(elem);
			if (isConsistent(neigh) && !actualEntries.contains(neigh)) {
					neighbours.add(neigh);
			}
		}
		return neighbours;
	}

	@Override
	public void hit() {
		if(firstUse) {
			this.positions.addAll(generateRandom());
			firstUse = false;
		} else {
			List<Pair<Integer, Integer>> actualEntries = new ArrayList<>();
			actualEntries.addAll(positions);
			for( Pair<Integer, Integer> elem : positions) {
				actualEntries.addAll(neighbours(elem, actualEntries));
			}
			positions.clear();
			positions.addAll(actualEntries);
		}
	}

	@Override
	public boolean toQuit() {
		return positions.size() == size * size;
	}
}
