package tutorial.DP;

import java.util.Arrays;

/*
 * 
 * Problem Statement 
 *    http://community.topcoder.com/stat?c=problem_statement&pm=2829&rd=5072 
 * 
 * Given a string of digits, find the minimum number of additions required for the string to equal some 
 * target number. Each addition is the equivalent of inserting a plus sign somewhere into the string of
 * digits. After all plus signs are inserted, evaluate the sum as usual. For example, consider the string "12" 
 * (quotes for clarity). With zero additions, we can achieve the number 12. If we insert one plus sign
 * into the string, we get "1+2", which evaluates to 3. So, in that case, given "12", a minimum of 1 
 * addition is required to get the number 3. As another example, consider "303" and a target sum of 6. 
 * The best strategy is not "3+0+3", but "3+03". You can do this because leading zeros do not change 
 * the result.
 * 
 * Write a class QuickSums that contains the method minSums, which takes a String numbers and an int sum. 
 * The method should calculate and return the minimum number of additions required to create an
 * expression from numbers that evaluates to sum. If this is impossible, return -1.
 * 
 * Constraints
 *  -	numbers will contain between 1 and 10 characters, inclusive.
 *  -	Each character in numbers will be a digit.
 *  -	sum will be between 0 and 100, inclusive.
 * 
 * Notes 
 *  - 10-digit numbers could be too large to be an integer 

 * 
 */


public class QuickSums {
	
	private int maxNumChars = 10 ; 
	private int maxSum      = 100 ; 
	private int[][][] memo ; 
	
	
	QuickSums(){
		memo = new int[maxNumChars][maxNumChars][maxSum+1]; 
	}
	
	
	public int minSums(String numbers, int sum){
		
		int nDigits = numbers.length(); 
		int i, j ; 
		
		// the value of (-1) indicates uninitialised values 
		for(i=0;   i<nDigits;  i++) 
			for(j=0;   j<nDigits;  j++)
				Arrays.fill(memo[i][j], -1); 
		
		// initialisation 
		for(i=0;   i<nDigits;    i++){
			int value = numbers.charAt(i) - '0'; 
			for(int s=0;  s<=maxSum;  s++)
				if( s == value)
					memo[i][i][s] = 0 ; 
				else 
					memo[i][i][s] = Integer.MAX_VALUE; 
		}
			
		int numSums = getValue(numbers, 0, nDigits-1, sum);  
		return (numSums == Integer.MAX_VALUE? -1: numSums);
	}
	
	
	
	
	
	protected int getValue(String numbers, int i, int j, int sum){
		
		if( memo[i][j][sum] != -1 ) return memo[i][j][sum]; 
		
		if( j-i+1 < 10){ // here we consider numbers[i..j] as a number 
						 // we only consider numbers containing less than 10 digits   
			             // numbers with more than 10 digits are long data type, which exceed the problem's contraint
			String subNumbers = numbers.substring(i, j+1);  
			if( Integer.parseInt( subNumbers ) == sum )
				return memo[i][j][sum] = 0; 
		} 
		
		int min = Integer.MAX_VALUE ;  
		for(int k=i;  k<j;  k++){
			int ss = Integer.parseInt( numbers.substring(i,k+1) );
			if( ss <= sum ){
				int submin = getValue(numbers, k+1, j, sum-ss); 
				if( submin != Integer.MAX_VALUE )  // to avoid overflow 
					min = Math.min(submin+1, min); // break the current interval [i,j] into  [i,k], and [k+1, j] 
				                                   // and therefore there is 1 plus sign 
			}
		}
			
		return memo[i][j][sum] = min; 
	}
	

	
	public static void main(String[] args){
		
		QuickSums qs   = new QuickSums(); 				
		
		String numbers = "9230560001" ; 
		int sum        = 71 ; 
		
		//String numbers = "001"; 
		//int sum = 1; 
		
		int numPluses  = qs.minSums(numbers, sum); 
				
		System.out.println( numPluses); 
	}
	
	
	
}
