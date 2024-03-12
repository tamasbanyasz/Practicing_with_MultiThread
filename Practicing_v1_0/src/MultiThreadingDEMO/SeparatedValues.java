package MultiThreadingDEMO;
import java.util.ArrayList;
import java.util.List;

public class SeparatedValues {
	
	private final  List<Double> doubles = new ArrayList<>();
	private final List<Float> floats = new ArrayList<>();
	private final List<Integer> integers = new ArrayList<>();
	
	
	public List<Double> getListOfDoubles() {
		return doubles;
	}


	public List<Float> getListOfFloats() {
		return floats;
	}


	public List<Integer> getListOfIntegers() {
		return integers;
	}
	
	// Add the converted back value to the correct list
	public void setList(Object something) {
		if(something instanceof Integer) {
			integers.add((Integer)something);
		}
		if(something instanceof Double) {
			doubles.add((Double)something);
		}
		if(something instanceof Float) {
			floats.add((Float)something);
		}
		
	}


	public String toString1() {
		return "CombinedMatrix [Double=" + doubles + "]";
	}
	public String toString2() {
		return "CombinedMatrix [Float=" + floats + "]";
	}
	public String toString3() {
		return "CombinedMatrix [Integer=" + integers + "]";
	}
	
	
	
	
	
}
