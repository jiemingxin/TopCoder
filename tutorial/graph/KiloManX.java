package tutorial.graph;

/*
 * Problem Statement (SRM181, Div 1 1000) 
 * ====================
 * http://community.topcoder.com/stat?c=problem_statement&pm=2288&rd=4725 
 * 
 * The KiloMan series has always had a consistent pattern: you start off with one 
 * (rather weak) default weapon, and then defeat some number of bosses. When you defeat 
 * a boss, you then acquire his weapon, which can then be used against other bosses, and 
 * so on. Usually, each boss is weak against some weapon acquired from another boss, so 
 * there is a recommended order in which to tackle the bosses. You have been playing for 
 * a while and wonder exactly how efficient you can get at playing the game. Your metric 
 * for efficiency is the total number of weapon shots fired to defeat all of the bosses.
 * 
 * You have a chart in front of you detailing how much damage each weapon does to each 
 * boss per shot, and you know how much health each boss has. When a boss's health is 
 * reduced to 0 or less, he is defeated. You start off only with the Kilo Buster, which 
 * does 1 damage per shot to any boss. The chart is represented as a String[], with the 
 * ith element containing N one-digit numbers ('0'-'9'), detailing the damage done to 
 * bosses 0, 1, 2, ..., N-1 by the weapon obtained from boss i, and the health is 
 * represented as a int[], with the ith element representing the amount of health that 
 * boss i has.
 * 
 * Given a String[] damageChart representing all the weapon damages, and a int[] 
 * bossHealth showing how much health each boss has, your method should return an int 
 * which is the least number of shots that need to be fired to defeat all of the bosses.
 * 
 * 
 * Constraints
 * =================
 * -	damageChart will contain between 1 and 15 elements, inclusive.
 * -	each element of damageChart will be of the same length, which will be the same as the number of elements in damageChart.
 * -	each element of damageChart will contain only the characters '0'-'9'.
 * -	bossHealth will contain between 1 and 15 elements, inclusive.
 * -	damageChart and bossHealth will contain the same number of elements.
 * -	each element in bossHealth will be between 1 and 1000000, inclusive.
 * 
 * */
 
public class KiloManX {
	
	
	public int leastShots(String[] damageChart, int[] bossHealth){
		//return recursiveComputeLeastShots(damageChart, bossHealth); 	
		return DPComputeLeastShots(damageChart, bossHealth); 
	}
	
	
	
	/***
	 * Compute the least shots using Dynamic Programming (DP) 
	 * 
	 * The idea is to compute a lookup table shotTable, in which 
	 * (1) shotTable[i+1][j+1] stores #shots to defeat the j-th boss using the i-th boss's weapon. 
	 * (2) shotTable[0][j+1]   stores #shots to defeat the j-th boss using the start-off kilo buster 
	 * 	  
	 * @param damageChart
	 * @param bossHealth
	 * @return
	 */
	protected int DPComputeLeastShots(String[] damageChart, int[] bossHealth){
	
		int  nBoss 		   = bossHealth.length; 
		int[][] shotsTable = new int[nBoss+1][nBoss+1]; 
		int[] leastShots   = new int[ 1<<nBoss ];  
		
		///// compute the lookup table 
		for(int i=0;  i<nBoss; i++)
			shotsTable[0][i+1] = bossHealth[i]; 
			
		for(int i=0;  i<nBoss;  i++)
			for(int j=0; j<nBoss; j++){
	
				if( i==j || damageChart[i].charAt(j) == '0'  )
					shotsTable[i+1][j+1] = Integer.MAX_VALUE ; 
				else{
					
					int damage = damageChart[i].charAt(j) - '0'; 
					int health = bossHealth[j]; 
					int nShots = health / damage ; 
					
					if(health % damage !=0) nShots += 1; 
					shotsTable[i+1][j+1] = nShots ; 
				} 
			} 
				
		///// we still need to rely on recursion to compute the result 
		return recursive(leastShots, shotsTable, (1<<nBoss)-1); 
	}
	

	
	/**
	 *  In comparison with the 
	 * 
	 * @param shotsTable
	 * @param visited
	 * @return
	 */
	protected int recursive(int[] leastShots, int[][] shotsTable, int visited){
	
		// the basic cases 
		if( visited == 0) 
			return 0; 
		if( leastShots[visited] !=0 ) 
			return leastShots[visited]; 
		
		int nBoss  = shotsTable[0].length-1;  
		int ret    = Integer.MAX_VALUE ; 
		
		for(int i=0;  i<nBoss;  i++){   
			
			if( (visited & (1<<i)) != 0 ){  // this boss should be visited    
				
				//// compute the least shots to defeat the i-th Boss using defeated bosses' weapons 
				int initShots = Integer.MAX_VALUE ; 
				for(int j=0;  j<nBoss;  j++) // go thru other visited bosses 					
					if( i!=j &&  (visited &(1<<j))!=0  &&  shotsTable[j+1][i+1] < initShots )
						initShots = shotsTable[j+1][i+1];
				
				//// compute the least shots to defeat the rest bosses 
				int restShots  = recursive(leastShots, shotsTable, visited^(1<<i)); 
				if( initShots != Integer.MAX_VALUE  && restShots != Integer.MAX_VALUE) 
					ret = Math.min(ret, initShots + restShots); 
			}
		}
		return ret; 		
	}
	
	
	
	
	
	/**
	 * A recursive solution, my first attempt. However it cannot finish test case 4 in a 
	 * short time ( <30 minutes), and therefore it requires optimization 
	 * 
	 * @param damageChart
	 * @param bossHealth
	 * @return the least number of shots to defeat all bosses 
	 */
	protected int recursiveComputeLeastShots(String[] damageChart, int[] bossHealth){
		
		int leastShots = Integer.MAX_VALUE ; 
		int nBoss    = bossHealth.length; 
		
		for(int i=0;  i<nBoss;  i++){
		
			// the start-off kilo buster does 1 damage to each boss 
			int shots 	= bossHealth[i]; 
			
			// set the flag 
			int visited = 0 ; 
			visited    |= (1 << i); 
			
			// compute the shots recursively 
			int restShots =  recursiveComputeLeastShots(damageChart, bossHealth, visited); 
			if( restShots == Integer.MAX_VALUE ) // this happens when there is not enough
				shots = Integer.MAX_VALUE ;      // damage to defeat all bosses 
			else 
				shots += restShots ; 
				
			leastShots = Math.min(shots, leastShots); 	
		}
		return leastShots; 
	}
	
	
	
	
	protected int recursiveComputeLeastShots(
			String[] damageChart, int[] bossHealth, int visited){
		
		int nBosses = bossHealth.length; 
		if( visited == ((1 << nBosses) -1)) // all bosses have been visited 
			return 0; 
		
		int leastShots = Integer.MAX_VALUE ; 
		for(int i=0;  i<nBosses; i++){
			
			if( ((visited >> i)& 1) == 1 ) continue ; // is it visited ? 
			
			// compute the minimum shots to defeat the current boss 
			int health 		= bossHealth[i]; 
			int minShots 	= Integer.MAX_VALUE ; 
			for(int j=0;  j<nBosses; j++){  // use the weapon from the defeated bosses 
				if( ((visited >> j)& 1) == 0 ) continue ; 
				int damage = damageChart[j].charAt(i) - '0'; 
				if( damage == 0 ) continue; // cannot defeat this boss using the current damage 
			
				int shots  = health / damage ; 
				if( health %damage != 0) shots += 1; 
				minShots = Math.min(minShots, shots); 
			}
			
			// current boss has been visited 
			int visitFlags = visited ; 
			visitFlags    |= (1 << i); 
			
			// compute the shots to defeat the rest bosses 
			int shots = minShots ; 
			if( shots != Integer.MAX_VALUE ){ 
				
				int restShots = recursiveComputeLeastShots(damageChart, bossHealth, visitFlags); 
				if( restShots == Integer.MAX_VALUE)
					 shots = Integer.MAX_VALUE ; 
				else 
					 shots += restShots ;
			}
			leastShots = Math.min(leastShots, shots); 	
		}
		return leastShots; 	
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args){
		
//		String[] damageChart = {"02111111", "10711111", "11071111", "11104111",
//				 "41110111", "11111031", "11111107", "11111210"};   
//		int[] bossHealth	 = {28,28,28,28,28,28,28,28};   
		
		String[] damageChart = {"07", "40"};  
		int[] bossHealth	 = {150,  10}; 
		
		
		System.out.println( new KiloManX().leastShots(damageChart, bossHealth)); 
	}
	
	
	
	

}
