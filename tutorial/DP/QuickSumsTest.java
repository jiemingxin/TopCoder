package tutorial.DP;

import org.junit.Assert;
import org.junit.Test;


public class QuickSumsTest {

	@Test
	public void test0() {
		
		QuickSums qs   		= new QuickSums();  
		String numbers 		= "99999" ; 
		int sum        		= 45 ; 
		int numSums    		= qs.minSums(numbers, sum); 
		int expectedSums 	= 4; 
		
		Assert.assertTrue(numSums == expectedSums); 
	}
	
	
	@Test
	public void test1() {
		
		QuickSums qs   		= new QuickSums();  
		String numbers 		= "1110" ; 
		int sum        		= 3 ; 
		int numSums    		= qs.minSums(numbers, sum); 
		int expectedSums 	= 3; 
		
		Assert.assertTrue(numSums == expectedSums); 
	}
	
	
	@Test
	public void test2() {
		
		QuickSums qs   		= new QuickSums();  
		String numbers 		= "0123456789" ; 
		int sum        		= 45 ; 
		int numSums    		= qs.minSums(numbers, sum); 
		int expectedSums 	= 8; 
		
		Assert.assertTrue(numSums == expectedSums); 
	}
	
	
	
	@Test
	public void test3() {
		
		QuickSums qs   		= new QuickSums();  
		String numbers 		= "99999"; 
		int sum        		= 100 ; 
		int numSums    		= qs.minSums(numbers, sum); 
		int expectedSums 	= -1; 
		
		Assert.assertTrue(numSums == expectedSums); 
	}
	
	
	@Test
	public void test4() {
		
		QuickSums qs   		= new QuickSums();  
		String numbers 		= "382834"; 
		int sum        		= 100 ; 
		int numSums    		= qs.minSums(numbers, sum); 
		int expectedSums 	= 2; 
		
		Assert.assertTrue(numSums == expectedSums); 
	}
	
	
	
	@Test
	public void test5() {
		
		QuickSums qs   		= new QuickSums();  
		String numbers 		= "9230560001"; 
		int sum        		= 71 ; 
		int numSums    		= qs.minSums(numbers, sum); 
		int expectedSums 	= 4; 
		
		Assert.assertTrue(numSums == expectedSums); 
	}

}
