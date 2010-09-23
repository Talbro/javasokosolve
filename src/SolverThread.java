import java.util.List;

/**
 * 
 */

public class SolverThread extends Thread {
	public volatile List<Move> solution = null;
	public volatile boolean isFinished = false;
	public final Object waitingObject = new Object();
	
	private Board board;
	
	SolverThread(Board board) {
		this.board = board;
	}
	
	@Override
	public void run() {
		LDFSSolver solver = new LDFSSolver(board);
		List<Move> tmpSolution = solver.solve(1000, 10);
		solution = tmpSolution;
		isFinished = true;
		synchronized (waitingObject) {
			waitingObject.notify();
		}
	}
	
}