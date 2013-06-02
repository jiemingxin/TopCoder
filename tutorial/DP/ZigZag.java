package tutorial.DP;


/**
 *  
 * Problem Statement 
 *  
 * A sequence of numbers is called a zig-zag sequence if the differences between successive numbers 
 * strictly alternate between positive and negative. The first difference (if one exists) may be either 
 * positive or negative. A sequence with fewer than two elements is trivially a zig-zag sequence.
 *  
 * For example, 1,7,4,9,2,5 is a zig-zag sequence because the differences (6,-3,5,-7,3) are alternately 
 * positive and negative. In contrast, 1,4,7,2,5 and 1,7,4,5,5 are not zig-zag sequences, the first because 
 * its first two differences are positive and the second because its last difference is zero.
 * 
 * Given a sequence of integers, sequence, return the length of the longest subsequence of sequence that 
 * is a zig-zag sequence. A subsequence is obtained by deleting some number of elements (possibly zero) 
 * from the original sequence, leaving the remaining elements in their original order.
 * 
 * Constraints
 *  - sequence contains between 1 and 50 elements, inclusive.
 *  - Each element of sequence is between 1 and 1000, inclusive.
 * 
 * Examples
 * 0)	{ 1, 7, 4, 9, 2, 5 }
 * Returns: 6 (The entire sequence is a zig-zag sequence)
 * 
 * 1)	{ 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 }
 * Returns: 7 (There are several subsequences that achieve this length. One is 1,17,10,13,10,16,8.2)	
 * 
 * 2) { 44 }  
 * Returns: 1
 * 
 * 3) { 1, 2, 3, 4, 5, 6, 7, 8, 9 }
 * Returns: 2
 * 
 * 4) { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 }
 * Returns: 8
 * 
 * 5) { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 
 *      600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
 *      67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
 *      477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
 *      249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }
 * Returns: 36
 * 
 * @author jie.xu 
 */

public class ZigZag {
	
	public int longestZigZag(int[] sequence){
	
		int nelems = sequence.length ; 
		if(nelems == 1) return 1; 
		
		int[] LZS  = new int[nelems] ; // LZS[i] stores the length of the longest zigzag squence ending wtih sequence [i] 
		int[] PREV = new int[nelems] ; // PREV[i] stores the index of the previous element in the longest zigzag sequence ending
		                               // with sequence[i] 
		
		LZS[0]  = 1 ;  // a trival zigzag sequence  
		if( sequence[1] != sequence[0] ){
			LZS[1]  = 2 ;  
			PREV[1] = 0 ;
		}else{
			LZS[1]  = 1 ; 
			PREV[1] = 1 ; 
		}
		
		for(int i=2;  i<nelems;  i++){
			
			LZS[i] =1 ; 
			int j; 
			for(j=i-1;   j>0;   j--)
				if( (sequence[i] - sequence[j])*(sequence[j]-sequence[PREV[j]]) <0 ) 
					break ; 
			
			if(j!=0){
				LZS[i]  = LZS[j] + 1 ; 
				PREV[i] = j; 
			}else
				PREV[i] = i; // a trivial zigzag sequence 
			
		}
	
		int maxLength = 0; 
		for(int k=0;  k<nelems; k++)
			if(LZS[k] > maxLength) 
				maxLength = LZS[k]; 
			
		return maxLength ; 
	}

	

		
		
		
}
