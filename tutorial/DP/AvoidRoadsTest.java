package tutorial.DP;

import org.junit.Assert;
import org.junit.Test;

public class AvoidRoadsTest {

	@Test
	public void test0() {
		
		int width     = 6 ; 
		int height    = 6 ; 
		String[] bad  = {"0 0 0 1","6 6 5 6"}; 
		
		AvoidRoads ar = new AvoidRoads(); 
		Assert.assertTrue(ar.numWays(width, height, bad) == 252); 
	}
	

	@Test
	public void test1() {
		
		int width     = 1 ; 
		int height    = 1 ; 
		String[] bad  = {}; 
		
		AvoidRoads ar = new AvoidRoads(); 
		Assert.assertTrue(ar.numWays(width, height, bad) == 2); 
	}
	
	
	@Test
	public void test2() {
		
		int width = 35; 
		int height = 31; 
		String[] bad = {}; 
		
		AvoidRoads ar = new AvoidRoads(); 
		Assert.assertTrue(ar.numWays(width, height, bad) == 6406484391866534976l); 
	}


	@Test
	public void test3() {
		
		int width = 2; 
		int height = 2; 
		String[] bad = {"0 0 1 0", "1 2 2 2", "1 1 2 1"}; 
		
		AvoidRoads ar = new AvoidRoads(); 
		Assert.assertTrue(ar.numWays(width, height, bad) == 0); 
	}
	

}
