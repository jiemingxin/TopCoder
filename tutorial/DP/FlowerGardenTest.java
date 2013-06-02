package tutorial.DP;

import org.junit.Assert;
import org.junit.Test;

public class FlowerGardenTest {

	@Test
	public void test1() {
		
		int[] height  = {5,4,3,2,1} ; 
		int[] bloom   = {1,1,1,1,1} ;
		int[] wilt    = {365,365,365,365,365};
		int[] correct = {1, 2, 3, 4, 5}; 
		
		FlowerGarden fg = new FlowerGarden(); 
		int[] ordering  = fg.getOrdering(height, bloom, wilt); 
		Assert.assertTrue(checkArrays(correct, ordering)); 
	}
	
	
	@Test
	public void test2() {
		
		int[] height  = {5,4,3,2,1} ; 
		int[] bloom   = {1,5,10,15,20};
		int[] wilt    = {4,9,14,19,24};
		int[] correct = {5, 4, 3, 2, 1}; 
		
		FlowerGarden fg = new FlowerGarden(); 
		int[] ordering  = fg.getOrdering(height, bloom, wilt); 
		Assert.assertTrue(checkArrays(correct, ordering)); 
	}

	
	
	
	@Test
	public void test3() {
		
		int[] height  = {5,4,3,2,1} ; 
		int[] bloom   = {1,5,10,15,20};
		int[] wilt    = {5,10,15,20,25};
		int[] correct = {1, 2, 3, 4, 5};  
		
		FlowerGarden fg = new FlowerGarden(); 
		int[] ordering  = fg.getOrdering(height, bloom, wilt); 
		Assert.assertTrue(checkArrays(correct, ordering)); 
	}
	
	
	
	@Test
	public void test4() {
		
		int[] height  = {1,2,3,4,5,6} ; 
		int[] bloom   = {1,3,1,3,1,3};
		int[] wilt    = {2,4,2,4,2,4};
		int[] correct = {2, 4, 6, 1, 3,5};  
		
		FlowerGarden fg = new FlowerGarden(); 
		int[] ordering  = fg.getOrdering(height, bloom, wilt); 
		Assert.assertTrue(checkArrays(correct, ordering)); 
	}
	

	@Test
	public void test5() {
		
		int[] height  = {3,2,5,4} ; 
		int[] bloom   = {1,2,11,10};
		int[] wilt    = {4,3,12,13};
		int[] correct = {4,5,2,3};  
		
		FlowerGarden fg = new FlowerGarden(); 
		int[] ordering  = fg.getOrdering(height, bloom, wilt); 
		Assert.assertTrue(checkArrays(correct, ordering)); 
	}
	
	
	
	protected static boolean checkArrays(int[] correct, int[] output){
		
		Assert.assertEquals(correct.length, output.length); 
		boolean isTheSame = true ; 
		for(int i=0;  i<correct.length;  i++)
			if(correct[i] != output[i]){
				isTheSame = false ; 
				break ; 
			}
		return isTheSame ; 
	}
	
	
	

}
