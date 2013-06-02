package tutorial.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/*
 * Problem Statement http://community.topcoder.com/stat?c=problem_statement&pm=1524&rd=4472 
 * =========================
 * 
 * You work for a very large company that markets many different products. In some cases, one product you market competes with another. 
 * To help deal with this situation you have split the intended consumers into two groups, namely Adults and Teenagers. If your company 
 * markets 2 products that compete with each other, selling one to Adults and the other to Teenagers will help maximize profits. Given a 
 * list of the products that compete with each other, you are going to determine whether all can be marketed such that no pair of competing
 * products are both sold to Teenagers or both sold to Adults. 
 * 
 * If such an arrangement is not feasible your method will return -1. Otherwise, it should return the number of possible ways of marketing
 * all of the products.
 * 
 * The products will be given in a String[] compete whose kth element describes product k. The kth element will be a single-space delimited
 * list of integers. These integers will refer to the products that the kth product competes with. For example:
 *            compete = {"1 4", "2","3", "0",  ""}
 * The example above shows product 0 competes with 1 and 4, product 1 competes with 2, product 2 competes with 3, and product 3 competes with
 * 0. Note, competition is symmetric so product 1 competing with product 2 means product 2 competes with product 1 as well.
 * 
 * Ways to market:
 *  1) 0 to Teenagers, 1 to Adults, 2 to Teenagers, 3 to Adults, and 4 to Adults
 *  2) 0 to Adults, 1 to Teenagers, 2 to Adults, 3 to Teenagers, and 4 to Teenagers
 * Your method would return 2.
 * 
 * 
 * Constraints
 * -	compete will contain between 1 and 30 elements, inclusive.
 * -	Each element of compete will have between 0 and 50 characters, inclusive. 
 * -	Each element of compete will be a single space delimited sequence of integers such that:
 * 		All of the integers are unique.
 * 		Each integer contains no extra leading zeros.
 *      Each integer is between 0 and k-1 inclusive where k is the number of elements in compete.
 * -	No element of compete contains leading or trailing whitespace.
 * -	Element i of compete will not contain the value i.
 * -	If i occurs in the jth element of compete, j will not occur in the ith element of compete. 
 * 
 * MY NOTES 
 * =========================  
 * 1. We need to use the Queue data structure for BFS, however there is no Queue but LinkedList in Java. We still 
 *    use the Queue interface on LinkedList wtih Java 
 *    
 * 2. Sometimes there are loops inside one graph so a path might be considered twice. To eliminate this case, 
 *    wipe off that edge once you have processed it. 
 * 
 * */


public class Marketing {


	
	public long howMany(String[] compete){

		int nNodes   	 = compete.length; 
		int[]   levelIDs = new int[nNodes]; 
		int[][] competes = new int[nNodes][nNodes]; 
		
		// record the competitions 
		for(int i=0;  i<nNodes;  i++){
			
			if( compete[i].equalsIgnoreCase("")) continue ; 

			String[] competitors = compete[i].split(" "); 
			for(String n: competitors){
				int nodeID = Integer.parseInt(n); 
				competes[i][nodeID] = 1; 
				competes[nodeID][i] = 1; 
			}
		}
		
		// start computing 
		int nValidComponents = 0 ;  
		for(int i=0; i<nNodes;  i++){
			if( levelIDs[i] == 0 ){
				if( findValidComponent(i, competes, levelIDs) )
					nValidComponents ++; 
				else 
					return -1; 
			}
		}
		return (long) Math.pow(2, nValidComponents); 
	}
	
	
	
	
	
	boolean findValidComponent(int currNodeIdx, int[][] competes, int[] levelIDs){
		
		Queue<Integer> nodes = new LinkedList<Integer>(); 
		int nNodes 		= levelIDs.length ; 
		int level 		= 1 ; 
		
		levelIDs[currNodeIdx] = level ;  
		nodes.add( currNodeIdx ); 
		
		while( !nodes.isEmpty()){
		
			int currIdx   = nodes.poll(); 
			int nextLevel = levelIDs[currIdx] + 1; 
			
			// add its neighbours into the queue, BFS 
			for(int nextIdx=0;  nextIdx<nNodes;  nextIdx++){
				
				if( competes[currIdx][nextIdx] != 0 ){  // there is one edge from current node to next node  
					
					if( levelIDs[nextIdx] == 0 )
						levelIDs[nextIdx] = nextLevel ; 
					
					if(levelIDs[nextIdx] != nextLevel )
						return false ; 
										
					competes[currIdx][nextIdx] = 0; // eliminate this path 
					competes[nextIdx][currIdx] = 0;
			
					nodes.add(nextIdx);
				}
			}
		}
		
		return true; 		
	}
	
	
	public static void main(String[] args){
	
		Marketing mk     	= new Marketing();
		String[] compete 	= {"1 4","2","3","0",""}; 
		long actual 		= mk.howMany(compete); 
	
		System.out.println(actual);
		
	}
	
	
	

}
