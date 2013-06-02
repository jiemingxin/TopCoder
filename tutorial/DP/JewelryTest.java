package tutorial.DP;

import org.junit.Assert;
import org.junit.Test;

public class JewelryTest {

	@Test
	public void test0() {
		
		Jewelry jew  = new Jewelry(); 
		int[] values = {1,2,5,3,4,5}; 
		Assert.assertTrue(jew.howMany(values) == 9); 
	}
	
	
	@Test
	public void test1() {
	
		Jewelry jew  = new Jewelry(); 
		int[] values = { 1000,1000,1000,1000,1000,
						 1000,1000,1000,1000,1000,
						 1000,1000,1000,1000,1000,
						 1000,1000,1000,1000,1000,
						 1000,1000,1000,1000,1000,
						 1000,1000,1000,1000,1000}; 
		
		Assert.assertTrue(jew.howMany(values) == 18252025766940l); 
	}
	
	
	
	@Test
	public void test2() {
		
		Jewelry jew  = new Jewelry(); 
		int[] values = {1,2,3,4,5}; 
		Assert.assertTrue(jew.howMany(values) == 4); 
	}
	
	
	
	
	@Test
	public void test3() {
		Jewelry jew  = new Jewelry(); 
		int[] values = {7,7,8,9,10,11,1,2,2,3,4,5,6};
		Assert.assertTrue(jew.howMany(values) == 607); 
	}
	
	
	@Test
	public void test4() {
		
		Jewelry jew  = new Jewelry(); 
		int[] values = {123,217,661,678,796,964,54,111,417,526,917,923}; 
		Assert.assertTrue(jew.howMany(values) == 0); 
	}
	
	

}
