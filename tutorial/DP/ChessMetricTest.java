package tutorial.DP;


import org.junit.Assert;
import org.junit.Test;

public class ChessMetricTest {

	@Test
	public void test0() {

		ChessMetric cm = new ChessMetric(); 
		
		int size     = 3 ; 
		int[] start  = {0,0}; 
		int[] end    = {1,0}; 
		int numMoves = 1 ; 
				
		Assert.assertTrue(cm.howMany(size, start, end, numMoves) == 1); 
	}

	
	@Test
	public void test1() {

		ChessMetric cm = new ChessMetric(); 
		
		int size     = 3 ; 
		int[] start  = {0,0}; 
		int[] end    = {1,2}; 
		int numMoves = 1 ; 
				
		Assert.assertTrue(cm.howMany(size, start, end, numMoves) == 1); 
	}
	
	
	@Test
	public void test2() {

		ChessMetric cm = new ChessMetric(); 
		
		int size     = 3 ; 
		int[] start  = {0,0}; 
		int[] end    = {2,2}; 
		int numMoves = 1 ; 
				
		Assert.assertTrue(cm.howMany(size, start, end, numMoves) == 0); 
	}
	

	
	@Test
	public void test3() {

		ChessMetric cm = new ChessMetric(); 
		
		int size     = 3 ; 
		int[] start  = {0,0}; 
		int[] end    = {0,0}; 
		int numMoves = 2 ; 
		
		Assert.assertTrue(cm.howMany(size, start, end, numMoves) == 5); 
	}
	


	@Test
	public void test4() {

		ChessMetric cm = new ChessMetric(); 
		
		int size     = 100 ; 
		int[] start  = {0,0}; 
		int[] end    = {0,99}; 
		int numMoves = 50 ; 
		
		Assert.assertTrue(cm.howMany(size, start, end, numMoves) == 243097320072600l); 
	}
	
	
}
