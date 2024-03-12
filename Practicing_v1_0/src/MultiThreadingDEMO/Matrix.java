package MultiThreadingDEMO;
import java.util.Random;

public class Matrix<T> {
	
	private T object;
	private String name;
	
	public Matrix(String typeAsName) {
		this.name = typeAsName;
		
	}
	
	public String getName() {
		return name;
	}

	public void put(T objectToPut) {
		if (isEmpty() ) {
			this.object = objectToPut;
			System.out.println("Object "+ name + " is in the 'T object'.");
		}else {
			System.out.println("'T object' has already exist.");
		}
	}
	
	public T get() {
		if (isEmpty() ) {
			return null;
			
		}else {
			return object;
		}
	}
	
	public boolean isEmpty() {
		return object == null;
	}

	@Override
	public String toString() {
		return "Matrix [object=" + object + ", name=" + name + "]";
	}
	
	public Object[][] type (String selectedDataType){
		System.out.println(selectedDataType);  
		
		return switch (selectedDataType) {
        case "Double" -> 
        fillingTheArray(new Double[4][4], new Random().nextDouble(10));
            
        case "Integer" ->
        fillingTheArray(new Integer[4][4], new Random().nextInt(10));
        	
        case "Float" ->
        fillingTheArray(new Float[4][4], new Random().nextFloat(10));
        
        
        /*
         * ... and other types if we want
         * 
         */
        
        	
		default -> throw new IllegalArgumentException("Unexpected value: " + selectedDataType);
            
		};
	}
	
	private static <V> Object[][] fillingTheArray (Object[][] emptyObj, V value) {
		for (int i = 0; i < emptyObj.length; i++) {
    		for (int j = 0; j <  emptyObj.length; j++) {					 
    			emptyObj[i][j] = value;
			 }
    	}
		return emptyObj;
	}
}
