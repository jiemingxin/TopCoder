package tutorial.DP;

/**
 * Problem Statement ( http://www.topcoder.com/contest/problem/AvoidRoads/ ) 
 * 
 *  In the city, roads are arranged in a grid pattern. Each point on the grid represents a corner where two blocks meet. 
 *  The points are connected by line segments which represent the various street blocks. Using the cartesian coordinate 
 *  system, we can assign a pair of integers to each corner as shown below. 
 *
 *  http://www.topcoder.com/contest/problem/AvoidRoads/AvoidPic1.GIF 
 *  
 *  You are standing at the corner with coordinates 0,0. Your destination is at corner width,height. You will return the 
 *  number of distinct paths that lead to your destination. Each path must use exactly width+height blocks. In addition, 
 *  the city has declared certain street blocks untraversable. These blocks may not be a part of any path. You will be given 
 *  a String[] bad describing which blocks are bad. If (quotes for clarity) "a b c d" is an element of bad, it means the
 *  block from corner a,b to corner c,d is untraversable. For example, let's say
 *
 *  width  = 6 
 *  length = 6 
 *  bad = {"0 0 0 1","6 6 5 6"} 
 *  
 *  The picture below shows the grid, with untraversable blocks darkened in black. A sample path has been highlighted in red.
 *  http://www.topcoder.com/contest/problem/AvoidRoads/AvoidPic2.GIF 
 *  
 *  
 * Constraints
 *   -	width will be between 1 and 100 inclusive. 
 *   -	height will be between 1 and 100 inclusive. 
 *   -	bad will contain between 0 and 50 elements inclusive.
 *   -	Each element of bad will contain between 7 and 14 characters inclusive.
 *   -	Each element of the bad will be in the format "a b c d", 
 *        where a,b,c,d are integers with no extra leading zeros,
 *              a and c are between 0 and width inclusive, 
 *              b and d are between 0 and height inclusive, 
 *              and a,b is one block away from c,d.
 *   -	The return value will be between 0 and 2^63-1 inclusive.
 */ 

public class AvoidRoads {
	
	
	public long numWays(int width, int height, String[] bad){
		
		long[][] numWaysMat = new long[width+1][height+1]; // every element is 0 by default 
		int i, j; 
		 
		for(i=1;  i<=width;  i++)
			if( !isBadRoad(i,0, i-1, 0, bad) ){ 
					numWaysMat[i][0] = 1;  
			}else{
					break ; 
			}
				
		for(j=1;  j<=height; j++)
			if( !isBadRoad(0, j, 0, j-1, bad) ){ 
				numWaysMat[0][j] = 1; 
			}else{ 
				break ; 
			}
		

		for(i=1;  i<=width;  i++)
			for(j=1;  j<=height;  j++){
			
				if( !isBadRoad(i, j, i-1, j, bad) )
					numWaysMat[i][j] += numWaysMat[i-1][j];
				
				if( !isBadRoad(i, j, i, j-1, bad) )
					numWaysMat[i][j] += numWaysMat[i][j-1];				
			}
		
		return numWaysMat[width][height] ; 
	}

	
	
	
	boolean isBadRoad(int x1, int y1, int x2, int y2, String[] bad){
		
		String separator = " "; 
		String blockCode     = x1 + separator + y1 + separator + x2 + separator + y2 ; 
		String blockCodeSwap = x2 + separator + y2 + separator + x1 + separator + y1 ; 
		
		boolean isBadRoad = false ; 
		for(String bc: bad)
			if( bc.equalsIgnoreCase(blockCode) || bc.equalsIgnoreCase(blockCodeSwap)){
					isBadRoad = true ; 
					break ; 
			}
		return isBadRoad ; 
	}
	

	
	public static void main(String[] args){
		
		
		

		
	}
	
	
	

}
