package tutorial.graph;

import java.util.Arrays;

/**
 * 
 * Problem Statement  http://community.topcoder.com/stat?c=problem_statement&pm=1593&rd=4494 
 * ========================== 
 * 
 * An essential part of circuit design and general system optimization is critical path analysis. On a chip, the critical path 
 * represents the longest path any signal would have to travel during execution. In this problem we will be analyzing chip designs 
 * to determine their critical path length. The chips in this problem will not contain any cycles, i.e. there exists no path from 
 * one component of a chip back to itself.
 * 
 * Given a String[] connects representing the wiring scheme, and a String[] costs representing the cost of each connection, your 
 * method will return the size of the most costly path between any 2 components on the chip. In other words, you are to find the 
 * longest path in a directed, acyclic graph. Element j of connects will list the components of the chip that can be reached directly
 * from the jth component (0-based). Element j of costs will list the costs of each connection mentioned in the jth element of connects. 
 * As mentioned above, the chip will not contain any cyclic paths. For example:
 * 
 * connects = {"1 2",  "2",  ""}
 * costs    = {"5 3",  "7",  ""}
 * 
 * In this example, component 0 connects to components 1 and 2 with costs 5 and 3 respectively. Component 1 connects to component 2 
 * with a cost of 7. All connections mentioned are directed. This means a connection from component i to component j does not imply a
 * connection from component j to component i. Since we are looking for the longest path between any 2 components, your method would 
 * return 12.
 * 
 * @author lkff
 *
 */

public class Circuits {
	
	
	public int howLong(String[] connects, String[] costs){
	
		 int nNodes 		= connects.length ; 
		 int[][] edgeCosts 	= new int[nNodes][nNodes]; 
		 for(int i=0;  i<nNodes; i++)
			 Arrays.fill(edgeCosts[i], -1); 
		 
		 int[] maxCosts = new int[nNodes]; // maxCosts[i] stores the length of the longest path from node i 
		 Arrays.fill(maxCosts, -1); 
		 
		 for(int i=0;  i<connects.length;  i++){
			
			 if( connects[i].isEmpty() ) continue ; 
			 
			 String[] nodes   = connects[i].split(" "); 
			 String[] weights = costs[i].split(" "); 

			 for(int j=0;  j<nodes.length; j++)
				 edgeCosts[i][Integer.parseInt(nodes[j])] = Integer.parseInt( weights[j] );
		 }
		 
		 for(int i=0; i<nNodes; i++)
			 computeMaxCost(i, maxCosts, edgeCosts); 
		 
		 int maxCost = Integer.MIN_VALUE ; 
		 for(int i=0; i<nNodes; i++) 
			 maxCost = Math.max(maxCosts[i], maxCost); 
		
		return maxCost ; 
	}

	
	public void computeMaxCost(int idx, int[] maxCosts, int[][] edgeCosts)
	{
		if(maxCosts[idx] != -1) return; 
		
		int nNodes  =  maxCosts.length ; 
		int maxCost = Integer.MIN_VALUE ; 
		for(int j=0;  j<nNodes;  j++){
			if( edgeCosts[idx][j] != -1){  // there is one connections from Node idx to Node j 
				if( maxCosts[j] == -1) 
					computeMaxCost(j, maxCosts, edgeCosts); 
				maxCost = Math.max(edgeCosts[idx][j] + maxCosts[j], maxCost); 
			}
			if(maxCost != Integer.MIN_VALUE) 
				maxCosts[idx] = maxCost ; 
			else 
				maxCost = 0 ; 			
		}
	}
	
	
	
	
	public static void main(String[] args){
		
		Circuits cs = new Circuits(); 
		String[] connects = {"1 2",  "2", ""} ; 
		String[] costs	  = {"5 3",  "7", ""} ; 
		
		System.out.println( cs.howLong(connects, costs)); 
		

	}
	

}
