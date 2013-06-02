package tutorial.DP;

import java.util.Arrays;

/**
 * Problem Statement
 * 
 * You are planting a flower garden with bulbs to give you joyous flowers throughout the year. However, 
 * you wish to plant the flowers such that they do not block other flowers while they are visible.
 * 
 * You will be given a int[] height, a int[] bloom, and a int[] wilt. Each type of flower is represented
 * by the element at the same index of height, bloom, and wilt. height represents how high each type of 
 * flower grows, bloom represents the morning that each type of flower springs from the ground, and wilt
 * represents the evening that each type of flower shrivels up and dies. Each element in bloom and wilt
 * will be a number between 1 and 365 inclusive, and wilt[i] will always be greater than bloom[i]. You 
 * must plant all of the flowers of the same type in a single row for appearance, and you also want to 
 * have the tallest flowers as far forward as possible. However, if a flower type is taller than another
 * type, and both types can be out of the ground at the same time, the shorter flower must be planted in
 * front of the taller flower to prevent blocking. A flower blooms in the morning, and wilts in the evening, 
 * so even if one flower is blooming on the same day another flower is wilting, one can block the other.
 * 
 * You should return a int[] which contains the elements of height in the order you should plant your
 * flowers to acheive the above goals. The front of the garden is represented by the first element in 
 * your return value, and is where you view the garden from. The elements of height will all be unique, 
 * so there will always be a well-defined ordering.
 * 
 * 
 * Constraints
 *   - height will have between 2 and 50 elements, inclusive.
 *   - bloom will have the same number of elements as height
 *   - wilt will have the same number of elements as height
 *   - height will have no repeated elements.
 *   - Each element of height will be between 1 and 1000, inclusive. 
 *   - Each element of bloom will be between 1 and 365, inclusive.
 *   - Each element of wilt will be between 1 and 365, inclusive.
 *   - For each element i of bloom and wilt, wilt[i] will be greater than bloom[i].
 *   
 * Examples
 * 
 * 0)	{5,4,3,2,1} {1,1,1,1,1} {365,365,365,365,365}
 * Returns: { 1,  2,  3,  4,  5 }
 * These flowers all bloom on January 1st and wilt on December 31st. Since they all may block each 
 * other, you must order them from shortest to tallest.
 * 
 * 1)	{5,4,3,2,1} {1,5,10,15,20} {4,9,14,19,24}
 * Returns: { 5,  4,  3,  2,  1 } 
 * The same set of flowers now bloom all at separate times. Since they will never block each other, 
 * you can order them from tallest to shortest to get the tallest ones as far forward as possible.
 * 
 * 2)   {5,4,3,2,1} {1,5,10,15,20} {5,10,15,20,25}
 * Returns: { 1,  2,  3,  4,  5 }
 * Although each flower only blocks at most one other, they all must be ordered from shortest to 
 * tallest to prevent any blocking from occurring.
 * 
 * 3)	{5,4,3,2,1} {1,5,10,15,20} {5,10,14,20,25} 
 * Returns: { 3,  4,  5,  1,  2 }
 * The difference here is that the third type of flower wilts one day earlier than the blooming of 
 * the fourth flower. Therefore, we can put the flowers of height 3 first, then the flowers of height 4,
 * then height 5, and finally the flowers of height 1 and 2. Note that we could have also ordered them 
 * with height 1 first, but this does not result in the maximum possible height being first in the garden.
 * 
 * 4)	{1,2,3,4,5,6}{1,3,1,3,1,3} {2,4,2,4,2,4}
 * Returns: { 2,  4,  6,  1,  3,  5 }
 * 
 * 5)	{3,2,5,4}{1,2,11,10}{4,3,12,13}
 * Returns: { 4,  5,  2,  3 }
 * 
 * @author jie.xu 
 *
 */

public class FlowerGarden {

	
	public int[] getOrdering(int[] height, int[] bloom, int[] wilt){
		
		int nPlants       = height.length ; 
		int[][] conflicts = computeConflictsMatrix(height, bloom, wilt); 
		int[] picked      = new int[nPlants]; 
		int[] ordering    = new int[nPlants]; 
		int counts        = 0 ; 
		
		while( counts < nPlants ){
			int idx = getMaxHeightIndex(height, conflicts, picked); 
			ordering[counts++] = height[idx];  
			picked[idx]		   = 1; 	
			updateConflictsMatrix(idx, conflicts); 
		}
		return ordering ; 
	}
	
	
	/**
	 *  Get the index of the plant with maximum height, which can be 
	 *  a plant: 
	 *  (1) that has no conflicts with other plants 
	 *  (2) that has shorter height in a time conflict with another plant 
	 *   
	 * 
	 * @param height    - the heights of all plants 
	 * @param conflicts - the conflict matrix 
	 * @param picked    - if plant i has been picked then picked[i]= 1 otherwise 0 
	 * @return
	 */
	protected int getMaxHeightIndex(int[] height, int[][] conflicts, int[] picked){
		
		int nPlants = height.length; 
		int maxIdx=-1, maxHeight=-1 ; 
		for(int i=0;  i<nPlants;  i++){
			if( picked[i] == 1) continue ; 
			boolean canBePicked = true ; 
			for(int j=0;   j<nPlants;  j++)
				if( conflicts[j][i] == 1){ 
					canBePicked = false ; 
					break ; 
				}
			if(canBePicked  && height[i] > maxHeight){
				maxHeight = height[i]; 
				maxIdx    = i ; 
			}
		}
		return maxIdx ; 
	}
	
	
	
	/**
	 *  Update the conflicts table 
	 * 
	 * @param index - the index of the plant which has been picked 
	 */
	protected void updateConflictsMatrix(int index, int[][] conflicts){
		
		int nPlants = conflicts.length ; 
		for(int i=0;  i<nPlants;  i++)
			conflicts[index][i] = 0 ;  // no more conflicts as this plant has been picked 
	}

	
	
	/**
	 *  Returns a conflicts matrix mat in which, 
	 *     mat[i][j] is 1 if there is a conflicts between plant i and plant j, 
	 *     and height[i] < height[j] 
	 *  
	 * @return the conflits matrix 
	 */
	protected int[][] computeConflictsMatrix(int[] height, int[] bloom, int[] wilt ){
		
		int nPlants       = height.length ; 
		int[][] conflicts = new int[nPlants][nPlants]; 
		
		for(int i=0;  i<nPlants;  i++)
			for(int j=i+1;  j<nPlants; j++)
				if( (bloom[i] <= bloom[j] && bloom[j] <= wilt[i]) || 
					(bloom[j] <= bloom[i] && bloom[i] <= wilt[j]) ){
					
					if( height[i] < height[j]) 
						conflicts[i][j] = 1 ; 
					else 
						conflicts[j][i] = 1 ; 
				}
		
		return conflicts ; 		
	}
	
	
	public static void main(String[] args){
	
		int[] height = {5,4,3,2,1} ; 
		int[] bloom  = {1,1,1,1,1} ;
		int[] wilt   = {365,365,365,365,365};
		
		FlowerGarden fg = new FlowerGarden(); 
		int[] ordering  = fg.getOrdering(height, bloom, wilt); 
		System.out.println( Arrays.toString(ordering)); 

	}

	
	
	
	
}
