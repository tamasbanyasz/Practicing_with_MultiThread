package MultiThreadingDEMO;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/*
 * At first the code create randomly different typed and defined sized 2D arrays. But we won't know what kind of different type of arrays we will getting. 
 * 
 * 
 * Then the code add these arrays into a 'Generic' typed named 'Matrix' class with a specific 'put' method. Every arrays will belong to an own 'Matrix' class. And the 'Matrix' class get a specify name 
 * which is the obtained array type. Then these 'Generic' objects add into a 'List'.
 * 
 * 
 * After the code does separate the arrays from the 'Generic' object into a specified 'List' by the value types. 
 * When it's done the code obtain each values of arrays into a defined type of valued 'List'. ( List<Integer> or List<Float> etc.)
 * Then the code will remove the empty 'Matrix' object from 'listOfmatrix' at the beginning.
 * 
 * At last the code collects into a new list from the separated list of integers the values what are less than 5 using MultiThreading.
 * 
 * 
 * 
 * 
 * We could implement in another way how should separating the arrays.
 * Make the Main class simply. It haven't need to extend the SeparateClass for example.
 * 
 * 
 * 
 * 
 */



public class Main extends SeparateClass{


	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		
		
		Instant start = Instant.now(); 

		List<Matrix<Object[][]>> listOfMatrix = listOfMatrix();
		
		
		List<Matrix<Object[][]>> ints = separateArray(listOfMatrix, "Integer");
		listOfMatrix.removeIf(o -> o.getName() == "Integer");
		addToSeparateList(ints, "Integer");
		
		List<Matrix<Object[][]>> doubs = separateArray(listOfMatrix, "Double");
		listOfMatrix.removeIf(o -> o.getName() == "Double");
		addToSeparateList(doubs, "Double");
		
		List<Matrix<Object[][]>> floats = separateArray(listOfMatrix, "Float");
		listOfMatrix.removeIf(o -> o.getName() == "Float");
		addToSeparateList(floats, "Float");
		
		
		Instant end = Instant.now();
		
		
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
		
		/*
		 *  - MultiThreading -
		 *  
		 *  Find the integers what are less than 5 with 2 thread.
		 *  Probably we could implement it on better way.
		 */
		
		Callable<List<Integer>> task1 = new ThreadInteger(5, 0, getL().size() /2, getL());
		Callable<List<Integer>> task2 = new ThreadInteger(5, getL().size() /2, getL().size(),  getL());
		Instant start2 = Instant.now();
		Future<List<Integer>> result1 = executor.submit(task1);
		Future<List<Integer>> result2 = executor.submit(task2);
		
		// Result in list
		List<Integer> threadingResult = Stream.concat(result1.get().stream(), result2.get().stream())
                .collect(Collectors.toList());
		
		executor.shutdown();
		
		Instant end2 = Instant.now();
		
		/*
		 *  - MultiThreading end -
		 * 
		 */

		System.out.println(threadingResult);
		System.out.println("Original array lenght: " + getL().size());
		System.out.println("Selected numbers array lenght with 2 thread: " + threadingResult.size());
		Duration timeElapsed2 = Duration.between(start2, end2);
		System.out.println("Time taken: "+ timeElapsed2.toMillis() +" milliseconds");
	}
	
	public static List<Matrix<Object[][]>> listOfMatrix() {
		Random randomNum = new Random();
		
		// We could expanding with more types 
		List<String> dataTypes = List.of("Double", "Integer", "Float");
		long numberOfArrays =500_000L;
		
		List<Matrix<Object[][]>> listOfMatrix = new ArrayList<Matrix<Object[][]>>();
		
		for (long i = 0; i < numberOfArrays; i++) {
	
			String selectedDataType = dataTypes.get(randomNum.nextInt(3));
			
			Matrix<Object[][]> matrix = new Matrix<>(selectedDataType);
			
			/*
			 *  We get a different type of array so that's why the unknown 'Object [][]'
			 */
			
			Object[][] createdMatrix = matrix.type(selectedDataType);

			matrix.put(createdMatrix);
			
			listOfMatrix.add(matrix);
			
		}
		return listOfMatrix;
	}
	
	
}




/*
 * This project implementation serves to practicing
 * https://www.youtube.com/watch?v=u1ZoHfJZACA
 */
