package tutorial.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;


/*
 * Problem statement  http://community.topcoder.com/stat?c=problem_statement&pm=2998&rd=5857  
 * ==========================
 * 
 * In one mode of the grafix software package, the user blocks off portions of a masking layer using opaque rectangles. The bitmap used as 
 * the masking layer is 400 pixels tall and 600 pixels wide. Once the rectangles have been blocked off, the user can perform painting actions 
 * through the remaining areas of the masking layer, known as holes. To be precise, each hole is a maximal collection of contiguous pixels that
 * are not covered by any of the opaque rectangles. Two pixels are contiguous if they share an edge, and contiguity is transitive.
 * 
 * You are given a String[] named rectangles, the elements of which specify the rectangles that have been blocked off in the masking layer. 
 * Each String in rectangles consists of four integers separated by single spaces, with no additional spaces in the string. The first two 
 * integers are the window coordinates of the top left pixel in the given rectangle, and the last two integers are the window coordinates of
 * its bottom right pixel. The window coordinates of a pixel are a pair of integers specifying the row number and column number of the pixel, 
 * in that order. Rows are numbered from top to bottom, starting with 0 and ending with 399. Columns are numbered from left to right, starting
 * with 0 and ending with 599. Every pixel within and along the border of the rectangle defined by these opposing corners is blocked off.
 * 
 * Return a int[] containing the area, in pixels, of every hole in the resulting masking area, sorted from smallest area to greatest.
 * 
 * Notes
 * ==========================
 * Window coordinates are not the same as Cartesian coordinates. Follow the definition given in the second paragraph of the problem statement.
 * 
 * Constraints
 * ==========================
 * - rectangles contains between 1 and 50 elements, inclusive
 * - each element of rectangles has the form "ROW COL ROW COL", 
 *   where: "ROW" is a placeholder for a non-zero-padded integer between 0 and 399, inclusive; "COL" is a placeholder for a non-zero-padded 
 *   integer between 0 and 599, inclusive; the first row number is no greater than the second row number; the first column number is no 
 *   greater than the second column number
 * 
 * 
 * MY NOTES
 * ========================== 
 *  To avoid recursion, use the Stack explicitly 
 * 
 * 
 * 
 * 
 */

public class grafixMask {

	public static int cols  = 600 ; 
	public static int rows  = 400; 
	
	private int[][] mask ; 
	
	
	private class Node {

		private int r, c; 		
		public Node(int r, int c){
			this.r = r; 
			this.c = c; 
		}
	} 
	
	
	public grafixMask(){
		 mask = new int[rows][cols]; 
	}
	
	
	public int[] sortedAreas(String[] rectangles){
		
		// initialization
		for(int r=0;  r<rows;  r++)
			Arrays.fill(mask[r], 0); 
		
		// mark the block area 
		for(String rectangle: rectangles){
			String[] corners = rectangle.split(" ");
			int topLeftR     = Integer.parseInt(corners[0]); 
			int topLeftC     = Integer.parseInt(corners[1]); 
			int botRightR    = Integer.parseInt(corners[2]); 
			int botRightC    = Integer.parseInt(corners[3]); 
			
			for(int r=topLeftR; r<=botRightR; r++)
				for(int c=topLeftC;  c<=botRightC;  c++)
					mask[r][c] = 1;  // blocked region 
		}
		
		// now start counting 
		ArrayList<Integer> areas = new ArrayList<Integer>(); 
		for(int r=0 ;  r<rows;  r++)
			for(int c=0;  c<cols;  c++){
				if(mask[r][c]==0){
					areas.add( getRegionArea(r, c) ); 
				}			
			}
	
		// sort the areas 
		Collections.sort(areas); 
		int[] sorted = new int[areas.size()]; 
		for(int i=0; i<sorted.length; i++) 
			sorted[i] =  areas.get(i); 
		return sorted; 	
	}
	
	
	

	protected int getRegionArea(int row, int col){
	
		int 	count     = 0 ; 			
		Stack<Node> nodes = new Stack<Node>(); 
		nodes.push(new Node(row, col)); 
		
		
		// here we use the stack explicitly rather than using recursion 
		while(!nodes.isEmpty()){
		
			Node node 	= nodes.pop(); 
			int r 		= node.r ; 
			int c 		= node.c ; 
						
			if( r<0 || r>=rows ) continue ;  
			if( c<0 || c>=cols ) continue ; 
			if( mask[r][c] != 0) continue ; 
			
			mask[r][c] = 1;
			count ++; 
			
			// now we confirm that the current node is an empty cell, 
			// so we can push its neighbors into the stack
			
			// if we push them into the stack before checking the cell, 
			// the programm will not stop as it keeps expanding 
			
			// this is actually BFS search 
			nodes.push( new Node(r-1, c  )); 
			nodes.push( new Node(r,   c+1)); 
			nodes.push( new Node(r+1, c)); 
			nodes.push( new Node(r,   c-1)); 
			
		}
		return count ;  
	}
	
	
	
	
	public static void main(String[] args){
		
		grafixMask gm       = new grafixMask(); 
		String[] rectangles = {"0 292 399 307"}; 

		System.out.println( Arrays.toString(gm.sortedAreas(rectangles)) ); 
	}
	
	
	
}
