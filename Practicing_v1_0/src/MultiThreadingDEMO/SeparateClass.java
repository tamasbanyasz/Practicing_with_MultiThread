package MultiThreadingDEMO;
import java.util.List;
import java.util.stream.Collectors;

public class SeparateClass {
	
	private static SeparatedValues separateValues = new SeparatedValues();
	
	// Separate arrays by types
	public static List<Matrix<Object[][]>> separateArray(List<Matrix<Object[][]>> listOfMatrix, String typeAsName) {
		List<Matrix<Object[][]>> separatedArray = listOfMatrix.stream()
                .filter(o -> o.getName() == typeAsName)
                .collect(Collectors.toList());
		
		return separatedArray;
	}
	
	/*
	 * Convert values back one by one into 'Object' type.
	 * 
	 * Method get a different type of arrays and it will be working with different type of values so that's why the unknown 'Object's.
	 * 
	 */
	
	public static void addToSeparateList(List<Matrix<Object[][]>> matrix, String typeAsname) {
		for (Matrix<Object[][]> obj : matrix) {
			Object[][] asTwoDimObj = (Object[][])obj.get();
			for (Object[] asOneDimObj : asTwoDimObj) {
				for (Object value: asOneDimObj) {
					separateValues.setList(ownTypeOfValue(typeAsname, value));
				}
			}
		}	
	}
	
	// Create from 'Object' type value to a specified type of value
	private static Object ownTypeOfValue(String choose, Object value)  {
		
		return switch (choose) {
		case "Double" ->
			returndoub(value);
			
		case "Integer" ->
			returnint(value);
			
		case "Float" ->
			returnfloat(value);
			
		default ->
			throw new IllegalArgumentException("Unexpected value: " + choose);
		};
	}
	
	/* 
	 * Practicing switch/case method
	 *  
	 */
	private static Double returndoub(Object value) {
		Double doubl = (Double) value;
		return doubl;
	}
	
	private static Integer returnint(Object value) {
		Integer intgr = (Integer) value;
		return intgr;
	}
	
	private static Float returnfloat(Object value) {
		Float flt = (Float) value;
		return flt;
	}
	
	public static List<Integer> getL()  {
		return separateValues.getListOfIntegers();
	}

}
