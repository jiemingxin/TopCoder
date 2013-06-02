package tutorial.graph;


import org.junit.Assert;
import org.junit.Test;

public class testKiloManX {

	@Test
	public void test0() {
		
		String[] damageChart = {"070",  "500", "140"};  
		int[] bossHealth	 = {150,  150,150}; 
		
		int expected = 218 ; 
		int actual   = new KiloManX().leastShots(damageChart, bossHealth); 
		Assert.assertTrue(expected == actual); 
	}
	
	
	@Test
	public void test1() {
		
		String[] damageChart = {"1542", "7935", "1139", "8882"};  
		int[] bossHealth	 = {150,  150, 150, 150}; 
				
		int expected = 205 ; 
		int actual   = new KiloManX().leastShots(damageChart, bossHealth); 
		Assert.assertTrue(expected == actual); 
	}
	
	
	@Test
	public void test2() {
		
		String[] damageChart = {"07", "40"};  
		int[] bossHealth	 = {150,  10}; 
				
		int expected = 48 ; 
		int actual   = new KiloManX().leastShots(damageChart, bossHealth); 
		Assert.assertTrue(expected == actual); 
	}
	
	
	@Test
	public void test3() {
		
		String[] damageChart = {"02111111", "10711111", "11071111", "11104111",
				 "41110111", "11111031", "11111107", "11111210"};   
		int[] bossHealth	 = {28,28,28,28,28,28,28,28};  
				
		int expected = 92 ; 
		int actual   = new KiloManX().leastShots(damageChart, bossHealth); 
		Assert.assertTrue(expected == actual); 
	}
	

		


	
	@Test
	public void test4() {
		
		String[] damageChart = {"198573618294842",
				 "159819849819205",
				 "698849290010992",
				 "000000000000000",
				 "139581938009384",
				 "158919111891911",
				 "182731827381787",
				 "135788359198718",
				 "187587819218927",
				 "185783759199192",
				 "857819038188122",
				 "897387187472737",
				 "159938981818247",
				 "128974182773177",
				 "135885818282838"};
		
		int[] bossHealth	 = 
				{157, 1984, 577, 3001, 2003, 2984, 5988, 190003,
				9000, 102930, 5938, 1000000, 1000000, 5892, 38}; 
				
		int expected = 260445 ; 
		int actual   = new KiloManX().leastShots(damageChart, bossHealth); 
		Assert.assertTrue(expected == actual); 
	}
	
	
	
	
	
}
