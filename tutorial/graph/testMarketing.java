package tutorial.graph;


import org.junit.Assert;
import org.junit.Test;


public class testMarketing {

	@Test
	public void test0() {
		
		Marketing mk     	= new Marketing();
		String[] compete 	= {"1 4","2","3","0",""}; 
		long expected 		= 2 ; 
		long actual 		= mk.howMany(compete); 
		
		Assert.assertEquals(expected, actual); 
	}
	
	
	
	@Test
	public void test1() {
		
		Marketing mk     	= new Marketing();
		String[] compete 	= {"1","2","0"}; 
		long expected 		= -1 ; 
		long actual 		= mk.howMany(compete); 
		
		Assert.assertEquals(expected, actual); 
	}
	
	
	
	@Test
	public void test2() {
		
		Marketing mk     	= new Marketing();
		String[] compete 	= {"1","2","3","0","0 5","1"}; 
		long expected 		= 2 ; 
		long actual 		= mk.howMany(compete); 
		
		Assert.assertEquals(expected, actual); 
	}
	
	
	
	@Test
	public void test3() {
		
		Marketing mk     	= new Marketing();
		String[] compete 	= {"","","","","","","","","","",
				 				"","","","","","","","","","",
				 				"","","","","","","","","",""}; 
		long expected 		= 1073741824 ; 
		long actual 		= mk.howMany(compete); 
		
		Assert.assertEquals(expected, actual); 
	}
	
	
	
	@Test
	public void test4() {
		
		Marketing mk     	= new Marketing();
		String[] compete 	= {"1","2","3","0","5","6","4"}; 
		long expected 		= -1 ; 
		long actual 		= mk.howMany(compete); 
		
		Assert.assertEquals(expected, actual); 
	}
	

}
