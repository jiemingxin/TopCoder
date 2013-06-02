package tutorial.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Problem Statement 
 *   http://community.topcoder.com/stat?c=problem_statement&pm=1166&rd=4705 
 * 
 * You have been given a list of jewelry items that must be split amongst two people: Frank and Bob. 
 * Frank likes very expensive jewelry. Bob doesn't care how expensive the jewelry is, as long as he 
 * gets a lot of jewelry. Based on these criteria you have devised the following policy:
 * 
 * 
 * 1) Each piece of jewelry given to Frank must be valued greater than or equal to each piece of 
 *    jewelry given to Bob. In other words, Frank's least expensive piece of jewelry must be valued
 *    greater than or equal to Bob's most expensive piece of jewelry.
 *    
 * 2) The total value of the jewelry given to Frank must exactly equal the total value of the 
 *    jewelry given to Bob.
 *    
 * 3) There can be pieces of jewelry given to neither Bob nor Frank.
 * 
 * 4) Frank and Bob must each get at least 1 piece of jewelry.
 * 
 * Given the value of each piece, you will determine the number of different ways you can allocate 
 * the jewelry to Bob and Frank following the above policy. For example:
 * 
 *  values = {1,2,5,3,4,5} 
 *  Valid allocations are:
 *  Bob       		Frank 
 *  1,2		         3 
 *  1,3        		 4 
 *  1,4		         5  (first 5) 
 *  1,4              5  (second 5) 
 *  2,3 		     5  (first 5) 
 *  2,3              5  (second 5) 
 *  5  (first 5)     5  (second 5)
 *  5  (second 5)	 5  (first 5) 
 *  1,2,3,4        5,5 
 *  
 *  Note that each '5' is a different piece of jewelry and needs to be accounted for separately.
 *  There are 9 legal ways of allocating the jewelry to Bob and Frank given the policy, so your 
 *  method would return 9.
 *  
 *  
 *  Constraints
 *  -	values will contain between 2 and 30 elements inclusive.
 *  -	Each element of values will be between 1 and 1000 inclusive.
 *  
 *  
 */

public class Jewelry {

	
	
	/**
	 *   This problem hides two knapsack problem in a smaller scale 
	 * 
	 * @param values
	 * @return
	 */
	
	public long howMany(int[] values){

		int i, s, count; 
		int nValues = values.length ; 
		Arrays.sort(values); 
		
		/** get the range of values */ 
		int minSum = values[0]; 
		int maxSum   = 0 ; 
		for(i=0;  i<nValues;  i++)
			maxSum += values[i]; 
		int sumRange = maxSum +1 ;  
		
		/** count the duplicates for each element */ 
		Map<Integer, Integer> elemCounts = new HashMap<Integer, Integer>(); 
		for(i=0;  i<nValues;  i++)
			if(elemCounts.containsKey(values[i])){
				Integer c = elemCounts.get(values[i]); 
				elemCounts.put(values[i], ++c); 
			}else
				elemCounts.put(values[i], 1); 
			
		//////////////////////////////////////////////////////////
		// The allocation for bob                     
		//////////////////////////////////////////////////////////
		
		int[][] bobStates   = new int[values.length][sumRange];  
			
		/** 
		 *  Note that the values array is sorted in ascending order. 
		 * 
		 *  bobStates[i][s] means the #(jewelry allocation) in which the maximum jewelry value 
		 *  is values[i], and the total value of jewelry is j. 
		 * 
		 * To make up a sum s: 
		 *  
		 * (1) if values[i] is selected ==> the reminaing sum is (s-values[i]). 
		 *     since values[i] is the maximum number in this allocation,  and the array is
		 *     sorted in ascending order. We will need to pick items which are smaller than 
		 *     values[i-1]  to make up for the sum (s-values[i])
		 *     
		 * (2) if values[i] not selected ==> the remaining sum is still s 
		 *     Since values[i] is not selected, the maximum number becomes values[i-1]  
		 * 
		 * As a result, the DP state transition formula is 
		 *   bobStates[i][s] = bobStates[i-1][s-values[i]] + bobStates[i-1][s] 
		 * 
		 * the initial states: 
		 *   bobStates[i][s] =  (1) #(duplicate values[i]) if s = values[i] 
		 *                      (2) 0 if s < values[i]  
		 *                      
		 *   bobStates[0][s] = 
		 *   		(1) #(duplicate values[nValues]) if s = values[0] 
		 *          (2)  C(#duplicates, k) if s = k * values[nValues], k<=#(duplicate values[nValue])
		 *  	    (3)  0                       
		 *                        
		 */ 
		for(i=0;  i<nValues;  i++)
			for(s=minSum;  s<=maxSum;  s++){
				if( s< values[i]) 
					bobStates[i][s] = 0 ; 
				if( s == values[i]){
					count = elemCounts.get(values[i]); 
					bobStates[i][s] = count ; 
				}
			}
		
		
				
		for(i=1;  i<nValues;  i++)
			for(s=values[i]+1;  s<=maxSum;  s++)
				bobStates[i][s] = bobStates[i-1][s] + bobStates[i-1][s-values[i]]; 
		

		//////////////////////////////////////////////////////////
		// The allocation for Frank                   
		//////////////////////////////////////////////////////////
		
		/**
		 *  Note that the values array is sorted in ascending order.  
		 * 
		 *  frankStates[i][j]  means #(jewelry allocation) in which the minimum jewelry 
		 *  value is values[i], and the total value of jewelry is j  
		 * 
		 *  To make up for a sum s: 
		 *  
		 *  (1) if values[i] is selected ==> the remaining sum is (s-values[i]). 
		 *     since values[i] is the minimum number in this allocation, we will need to 
		 *     pick items which are bigger than values[i] for the remaining sum 
		 *     
		 *  (2) if values[i] is not selected ==> the remaining sum is still s 
		 *     since values[i] is the minimum number, we will pick items which are bigger than
		 *     values[i] for the remaining sum 
		 *     
		 *  As a result, the DP state transition formula is 
		 *     frankStates[i][s] = frankStates[i+1][s-values[i]] + frankStates[i+1][s]  
		 *  
		 * the initial states: 
		 *   frankStates[i][s] =  (1) #(duplicate values[i]) if s = values[i] 
		 *                        (2) 0 if s < values[i]  
		 *   
		 *   frankStates[nValues][s] = 
		 *   		(1) 	#(duplicate values[nValues]) if s = values[i] 
		 *          (2)  1 if s = k * values[nValues], k<= #(duplicate values[nValue])
		 *  	    (3)  0 
		 */
		int[][] frankStates = new int[values.length][sumRange];  
		
		for(i=0;  i<nValues;  i++)
			for(s=minSum;  s<=maxSum;  s++){
				if( s< values[i]) 
					frankStates[i][s] = 0 ; 
				if( s == values[i]){
					count = elemCounts.get(values[i]); 
					frankStates[i][s] = count ; 
				} 
			}
		
		for(i=1;  i<nValues;  i++)
			for(s=values[i]+1;  s<=maxSum;  s++)
				frankStates[i][s] = frankStates[i+1][s] + frankStates[i+1][s-values[i]]; 				 
		
		//////////////////////////////////////////////////////////
		// Computing the total counts                  
		//////////////////////////////////////////////////////////
		
		// for(int i=0;  i<nValues;  i++)
			
		

		return 0 ; 
		
		
	}
	
	
	
	
	/**
	 *  It returns C(m,n), i.e., #ways to choose n elements from m duplicates 
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	protected long mChooseN(int m, int n){
		
	
		return 0; 
		
	}
	
	
	
	
}
