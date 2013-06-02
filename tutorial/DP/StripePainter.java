package tutorial.DP;

import java.io.PrintStream;
import java.util.Arrays;

/*
 * Problem Statement http://community.topcoder.com/stat?c=problem_statement&pm=1215&rd=4555
 * ============================
 * 
 * Karel is a frustrated painter who works by day in an electrical repair shop. Inspired by the 
 * color-coded bands on resistors, he is painting a series of long, narrow canvases with bold colored 
 * stripes. However, Karel is lazy and wants to minimize the number of brush strokes it takes to paint 
 * each canvas.
 * 
 * Abbreviating each color to a single uppercase letter, Karel would write the stripe pattern 
 * red-green-blue-green-red as "RGBGR" (quotes added for clarity). It would take him three brush 
 * strokes to paint this pattern. The first stroke would cover the entire canvas in red (RRRRR). 
 * The second stroke would leave a band of red on either side and fill in the rest with green (RGGGR). 
 * The final brush stroke would fill in the blue stripe in the center (RGBGR).
 * 
 * Given a stripe pattern stripes as a String, calculate the minimum number of brush strokes required
 * to paint that pattern.
 * 
 * 
 * Notes
 * =============================
 * The blank canvas is an ugly color and must not show through.
 * 
 * Constraints
 * =============================
 * -	stripes will contain only uppercase letters ('A'-'Z', inclusive).
 * -	stripes will contain between 1 and 50 characters, inclusive.
 * 
 * Thoughts 
 * ==============================
 * 
 *  It is not hard to see the state variable can be determined by (1) the start and ending positions
 *  of the stripe ; (2) the color. Since we  will deduce the colors from the input, a state can rely
 *  on just the starting and ending positions only. 
 * 
 *  The key to this questions is the fact that two adjacent chars who share the same color can be
 *  painted in one stroke. 
 *  
 *  The state transition formula is :
 *  Left f(i,j) be the minimum number of strokes to paint S[i..j], then we have 
 *  
 *    f(i,i): obviously it requires one 1 stroke ; // a single character           
 *    f(i,j): we will break the input into two parts, and compute their costs f(i,k) and f(k+1,j) for i<=k<j  
 *            when combining the two costs, we need to consider the following cases:
 *              if S[i] == S[j] then we can save 1 stroke ==> f(i,j) = f(i,k) + f(k+1, j) -1   
 *              if S[k] == S[k+1] then we can also save 1 stroke ==> f(i,j) = f(i,k) + f(k+1,j) -1  
 *            we compare these two cases because they are same-color cases on the boudaries of
 *            two subproblems. 
 *  
 *  Now the difficulty is in the programming: when calculating f(i,j), we need to make sure 
 *  f(i+1,j) and f(i, j-1) are available. From a visual perspective, we are computing a upper
 *  triangluar matrix based on the input string. Initially the diagonal can be easily filled 
 *  with values of 1. 
 *  
 *  ABOUT DP: 
 *    Usually we only use DP and the input to compute a matrix. The key is in the computation
 * rather than in the manipulating the input. So don't need to worry about how to manipulate the 
 *   
 * 
 */

public class StripePainter {

	
	 /**
	  *  This is the "official" solution given by Topcoder website. 
	  *  It is based on dynamic programming  
	  *  
	  * @param stripe
	  * @return
	  */
	 public int minStrokes(String stripe){
		 
		int [][]dp=new int[stripe.length()][stripe.length()];
		for(int [] d : dp) Arrays.fill(d,1000); 
		for(int x=0; x<stripe.length(); x++) dp[x][x]=1;  // basic cases 
		 
		for(int size=1; size<stripe.length(); size++)
		   for(int from=0; from<stripe.length(); from++){
		          
		     int to=from+size;
		     if(to>=stripe.length()) continue;
		          
		     int min=50;
		     for(int k=from; k<to; k++){
		         min = Math.min(min, dp[from][k]+dp[k+1][to]);
		         
		         // NOTE: now we have to subproblems S[from..x] and S[x+1..to]  
		         // We need to consider the equlity cases for both ends of subproblems:
		         //  i.e. S[from]==S[x], S[from]==[to], S[x] == S[x+1] and S[x+1] == S[to] 
		         // 
		         // The following statements only considers the equality case between the two subproblems
		         // i.e., (1) stripe[from] = stripe[x+1], (2) stripe[x] and stripes[x+1] 
		         // It is unnecessary to compare the equality cases inside the two subproblems:
		         // i.e., (2) stripe[from] == stripe[x]  and (2) stripe[x+1] and stripe[to] 
		         // as these cases will be considered when dealing with those subproblems. 
		         		         
		         if(stripe.charAt(k)==stripe.charAt(k+1)) min=Math.min(min, dp[from][k]+dp[k+1][to]-1);
		         if(stripe.charAt(from)==stripe.charAt(to)) min=Math.min(min, dp[from][k]+dp[k+1][to]-1);		         
		     }
		    dp[from][to]=min;
		}	     		
		return dp[0][stripe.length()-1];
	 }
	
	
	 /**
	  *  Another implementation using memorisation technique 
	  * 
	  * @param stripe
	  * @return
	  */
	 public int minStrokesM(String stripe){
		 
		int nChars = stripe.length(); 
	    int [][]dp=new int[nChars][nChars];
		for(int [] d : dp) Arrays.fill(d,-1); 
		for(int x=0; x<stripe.length(); x++) dp[x][x]=1;  // basic cases  
		
		return getValue(stripe, dp, 0, nChars-1); 
	 }
	 
	 
	 
	 protected int getValue(String stripe, int[][] dp, int i, int j){
		 
		 if( dp[i][j] != -1 ) return dp[i][j]; 
		 
		 int min = Integer.MAX_VALUE ; 
		 for(int k=i;  k<j;  k++){
			 int value = getValue(stripe, dp, i, k) + getValue(stripe, dp, k+1, j); 
			 min = Math.min(min, value ); 
			 if( stripe.charAt(i) == stripe.charAt(j)) 
				 min = Math.min(min, value-1); 
			 if( stripe.charAt(k) == stripe.charAt(k+1)) 
				 min = Math.min(min, value-1); 	 
		 }
		 dp[i][j] = min ; 
		 
		 return dp[i][j]; 
	 }
			 
	 
	 
	 
	 /**
	  *  Output the Dynamic Programming's state matrix, for debugging purpose 
	  * @param dp
	  */
	 protected static void printStateMatrix(int[][] dp)
	 {
		 int nSize 			= dp.length; 
		 PrintStream out 	= System.out ; 
		 
		 out.print(" \t"); 
		 for(int i=0;   i<nSize;  i++) 
			 out.print(i +"\t");
		 out.println(); 
		 
		 for(int i=0;  i<nSize;   i++){
			 out.print(i + "\t"); 
			 for(int j=0;  j<nSize; j++)
				out.print( dp[i][j] +"\t"); 
			 out.println(); 
		 }
		 out.println(); 
	 } 
		
	
}
