package tutorial.DP;

import java.util.Arrays;
import org.apache.log4j.Logger;   


/**
 * 
 * Problem Statement
 *   The old song declares "Go ahead and hate your neighbor", and the residents of Onetinville have 
 *   taken those words to heart. Every resident hates his next-door neighbors on both sides. Nobody 
 *   is willing to live farther away from the town's well than his neighbors, so the town has been 
 *   arranged in a big circle around the well. Unfortunately, the town's well is in disrepair and needs
 *   to be restored. You have been hired to collect donations for the Save Our Well fund.
 *   
 *   Each of the town's residents is willing to donate a certain amount, as specified in the int[] 
 *   donations, which is listed in clockwise order around the well. However, nobody is willing to 
 *   contribute to a fund to which his neighbor has also contributed. Next-door neighbors are always 
 *   listed consecutively in donations, except that the first and last entries in donations are also
 *   for next-door neighbors. You must calculate and return the maximum amount of donations that can 
 *   be collected.
 *   
 * Constraints
 *   donations contains between 2 and 40 elements, inclusive.
 *   Each element in donations is between 1 and 1000, inclusive.
 *   
 * Examples 
 * 0)	{ 10, 3, 2, 5, 7, 8 } 
 * Returns: 19 
 * The maximum donation is 19, achieved by 10+2+7. It would be better to take 10+5+8 except that the
 *  10 and 8 donations are from neighbors.
 *  
 * 1)	{ 11, 15 }
 * Returns: 15
 * 
 * 2)	{ 7, 7, 7, 7, 7, 7, 7 }
 * Returns: 21
 * 
 * 3)	{ 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 }
 * Returns: 16
 * 
 * 4)  { 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,  
 *       6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
 *       52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 }
 * Returns: 2926  
 *     
 * @author Jie. Xu 
 *
 */

public class BadNeighbors {

	public final static Logger logger = Logger.getLogger(BadNeighbors.class);     
	
	
	/**
	 *  Solve this problem using Dynamic Programming 
	 * 
	 * @param donations
	 * @return
	 */
	public static int maxDonationsDP(int[] donations){
		
		int nbs = donations.length ; 
		if( nbs< 3)
			return Math.max(donations[0], donations[1]);
		
		
		int[] contrib0 = new int[nbs];  // contributions including the first neighbour
		int[] contribN = new int[nbs];  // contributions including the last neighbour
 		
		// contrib0[i] keeps the maximum donation which contains the contribution
		//  from 0..ith neighbour and the contribution from the ith neighbor is 
		//  included 
		contrib0[0] = donations[0]; //  
		contrib0[1] = donations[1]; //  donations[0] cannot be used as 0 is 1's neighbour 
		contrib0[2] = donations[2] + donations[0]; // donations[1] cannot be used 
		for(int i=3; i<nbs-1; i++)
			if( contrib0[i-2] + donations[i] > contrib0[i-1] )
				contrib0[i] = contrib0[i-2] + donations[i]; 
			else
				contrib0[i] = contrib0[i-1];
		
		
		contribN[0] = 0; 
		contribN[1] = donations[1]; //  
		contribN[2] = donations[2]; 
		for(int i=3; i<nbs; i++)
			if( contribN[i-2] + donations[i] > contribN[i-1] )
				contribN[i] = contribN[i-2] + donations[i]; 
			else
				contribN[i] = contribN[i-1];
		if( contrib0[nbs-2] > contribN[nbs-1]) 
			return contrib0[nbs-2]; 
		else 
			return contribN[nbs-1];		
	}
	
	
	
	/**
	 *  Solving this problem using Recursion 
	 * 
	 * @param donations
	 * @return
	 */
	public static int maxDonationsRC(int[] donations){
		
		int n = donations.length; 
		
		// copyOfRange: inclusive + exclusive 
		int sum1 = maxSubSum(Arrays.copyOfRange(donations, 0, n-1)); 
		int sum2 = maxSubSum(Arrays.copyOfRange(donations, 1, n-2)) + donations[n-1]; 
			
		return Math.max(sum1, sum2); 		

	}
	
	
	protected static int maxSubSum(int[] donations){
		
		int n = donations.length;  
		
		if( n == 0 ) return 0; 
		if( n == 1 ) return donations[0]; 
		if( n == 2 ) return Math.max(donations[0], donations[1]);   
		
		int sum1 = maxSubSum(Arrays.copyOfRange(donations, 0, n-2)) + donations[n-1]; 
		int sum2 = maxSubSum(Arrays.copyOfRange(donations, 0, n-1)); 
		int val  = Math.max(sum1, sum2); 
		
		return val;  
	}
	
	
	
    public static void main(String[] args){
    	
    	int[] donations = {10,3,2,5,7,8}; 
//    	int[] donations = {11, 15}; 
//    	int[] donations = { 7, 7, 7, 7, 7, 7, 7 };
//    	int[] donations = { 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,  
//    			  6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
//    			  52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 }; 

    	System.out.println(BadNeighbors.maxDonationsDP(donations)); 

    }


		
	
	
	
}
