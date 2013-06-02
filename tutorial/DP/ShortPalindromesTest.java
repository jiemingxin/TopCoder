package tutorial.DP;

import org.junit.Assert;
import org.junit.Test;

public class ShortPalindromesTest {

	@Test
	public void test0() {
		
		String base  		= "RACE" ;  
		ShortPalindromes ss = new ShortPalindromes(); 
		String shortestPalindrome = "ECARACE" ;  
		Assert.assertTrue( ss.shortest(base).compareTo(shortestPalindrome) == 0) ; 
	}
	
	
	@Test
	public void test1() {
		
		String base  		= "TOPCODER" ;  
		ShortPalindromes ss = new ShortPalindromes(); 
		String shortestPalindrome = "REDTOCPCOTDER" ;  
		Assert.assertTrue( ss.shortest(base).compareTo(shortestPalindrome) == 0) ; 
	}
	
	
	@Test
	public void test2() {
		
		String base  		= "Q" ;  
		ShortPalindromes ss = new ShortPalindromes(); 
		String shortestPalindrome = "Q" ;  
		Assert.assertTrue( ss.shortest(base).compareTo(shortestPalindrome) == 0) ; 
	}
	
	
	@Test
	public void test3() {
		
		String base  		= "MADAMIMADAM" ;  
		ShortPalindromes ss = new ShortPalindromes(); 
		String shortestPalindrome = "MADAMIMADAM" ;  
		Assert.assertTrue( ss.shortest(base).compareTo(shortestPalindrome) == 0) ; 
	}
	
	
	@Test
	public void test4() {
		
		String base  		= "ALRCAGOEUAOEURGCOEUOOIGFA" ;  
		ShortPalindromes ss = new ShortPalindromes(); 
		String shortestPalindrome = "AFLRCAGIOEOUAEOCEGRURGECOEAUOEOIGACRLFA" ;  
		Assert.assertTrue( ss.shortest(base).compareTo(shortestPalindrome) == 0) ; 
	}
	
	
}
