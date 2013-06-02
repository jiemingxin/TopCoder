package tutorial.DP;


/* 
 * 
 * Problem Statements 
 * 
   Suppose you had an n by n chess board and a super piece called a kingknight. Using only one move the kingknight denoted 'K' 
   below can reach any of the spaces denoted 'X' or 'L' below:
	   .......
	   ..L.L..
	   .LXXXL.
	   ..XKX..
	   .LXXXL.
	   ..L.L..
	   .......
	In other words, the kingknight can move either one space in any direction (vertical, horizontal or diagonally) or can make an 
	'L' shaped move. An 'L' shaped move involves moving 2 spaces horizontally then 1 space vertically or 2 spaces vertically then 1 
	space horizontally. In the drawing above, the 'L' shaped moves are marked with 'L's whereas the one space moves are marked with 'X's.
	In addition, a kingknight may never jump off the board.

	Given the size of the board, the start position of the kingknight and the end position of the kingknight, your method will return 
	how many possible ways there are of getting from start to end in exactly numMoves moves. start and finish are int[]s each containing 
	2 elements. The first element will be the (0-based) row position and the second will be the (0-based) column position. 
	Rows and columns will increment down and to the right respectively. The board itself will have rows and columns ranging from 0 to 
	size-1 inclusive. 

	Note, two ways of getting from start to end are distinct if their respective move sequences differ in any way. In addition, you are 
	allowed to use spaces on the board (including start and finish) repeatedly during a particular path from start to finish. We will 
	ensure that the total number of paths is less than or equal to 2^63-1 (the upper bound for a long).
	
	Constraints
	-	size will be between 3 and 100 inclusive
	-	start will contain exactly 2 elements
	-	finish will contain exactly 2 elements
	-	Each element of start and finish will be between 1 and size-1 inclusive
	-	numMoves will be between 1 and 50 inclusive
	-	The total number of paths will be at most 2^63-1.
*/

public class ChessMetric {
	

	/**
	 * ---------------------------------------------------------------------------------
	 * This problem can definitely be solved by Dynamic Programming. The key to
	 * DP is the state transition. Here a state involves three variabels: 
	 * (x, y, k) where (x,y) is the coordinates of the point, and k is the 
	 * #moves from the starting point to point (x,y). Hence the state transition 
	 * recurrence is: 
	 * 
	 *  (1)  M(x,y,k) = \sum_(x',y') M(x',y',k-1), where (x',y') is reachable from (x,y) 
	 *     in one move. 
	 *     
	 *  (2)  the initial cases: M(sx,sy,0)   = 0, where (sx, sy)  is the starting point 
	 *                        M(sx',sy',1) = 1, where (sx',sy') is the points reachable 
	 *                        from (sx,sy) in one move 
	 * ---------------------------------------------------------------------------------
	 * My NOTE: 
	 * 
	 *    This solution is inspired by http://blog.francischen.info/?p=500 
	 * 
	 *    My idea was correct however I am not sure of the implementation. My thought was
	 *    that I need to store all the possible positions reachable from the starting 
	 *    position, which would make the code complicated. This implementation, however,
	 *    stores all the cell positions, even those not reachable from the starting point.
	 *    It looks like a waste however it makes the code much neater.
	 * 
	 */
	public long howMany(int size, int[] start, int[] end, int numMoves){
		
		long[][][] numPaths = new long[size][size][numMoves+1]; 
		
		int[] dists = { 1,  0, -1,  0,  0,  1,  0, -1,  
				        1,  1,  1, -1, -1, -1, -1,  1, 
				       -2,  1,  2,  1, -2, -1, -2,  1, 
				       -1, -2, -1,  2,  1,  2,  1, -2 }; 
		
		numPaths[start[0]][start[1]][0] = 1 ; // only 1 way, i.e., no need to move 
		
		for(int i=1;  i<=numMoves;  i++)
		  for(int r=0;  r<size;  r++)
			  for(int c=0;  c<size;  c++)
				  for(int k=0;  k<32;  k+=2)
				  {
					  int nr = r + dists[k]; 
					  int nc = c + dists[k+1]; 
					  if( nr >=0 && nr <size && nc>=0 && nc < size)
						  numPaths[nr][nc][i] += numPaths[r][c][i-1]; 
				  }
	
		return numPaths[end[0]][end[1]][numMoves]; 
	}
	
	
}
