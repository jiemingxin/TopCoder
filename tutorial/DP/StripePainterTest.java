package tutorial.DP;

import org.junit.Assert;
import org.junit.Test;

public class StripePainterTest {

	
	@Test
	public void test0() {
		 String stripe = "RGBGR" ;    	 
		 int nStrokes  = 3 ; 
		 Assert.assertTrue( new StripePainter().minStrokes(stripe) == nStrokes); 
	}
	
	@Test
	public void test1() {
		 String stripe = "RGRG" ;    	 
		 int nStrokes  = 3 ; 
		 Assert.assertTrue( new StripePainter().minStrokes(stripe) == nStrokes); 
		 Assert.assertTrue( new StripePainter().minStrokesM(stripe) == nStrokes); 
	}
	
	
	@Test
	public void test2() {
		 String stripe = "ABACADA" ;    	 
		 int nStrokes  = 4 ; 
		 Assert.assertTrue( new StripePainter().minStrokes(stripe) == nStrokes); 
		 Assert.assertTrue( new StripePainter().minStrokesM(stripe) == nStrokes); 
	}
	
	
	@Test
	public void test3() {
		 String stripe = "AABBCCDDCCBBAABBCCDD" ;    	 
		 int nStrokes  = 7 ; 
		 Assert.assertTrue( new StripePainter().minStrokes(stripe) == nStrokes); 
		 Assert.assertTrue( new StripePainter().minStrokesM(stripe) == nStrokes); 
	}
	
}
