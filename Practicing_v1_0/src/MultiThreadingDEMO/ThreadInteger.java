package MultiThreadingDEMO;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ThreadInteger extends SeparateClass implements Callable<List<Integer>>{
	
	private final long number;
	private final long lower;
	private final long upper; 
	private List<Integer> listOfMatrix;

	

	public ThreadInteger(long number, long lower, long upper, List<Integer> listOfMatrix) {
		super();
		this.number = number;
		this.lower = lower;
		this.upper = upper;
		this.listOfMatrix = listOfMatrix;
	}

	@Override
	public List<Integer> call() throws Exception {
		List<Integer> onlyIntegers = new ArrayList<>();
		for (long i = lower; i < upper; i++) {
			if(number > listOfMatrix.get((int) i)) {
				onlyIntegers.add(listOfMatrix.get((int) i));
			}
		}
		return onlyIntegers;
	}

}
