package dp;

import static main.Constants.*;
import main.Baggage;

public class DP {
	static Baggage bags = new Baggage(FILE);
	static int SUM_WEIGHT = bags.getSumWeight();
	static int[][] dp = new int[LENGTH + 1][SUM_WEIGHT + 1];
	
	public static void main(String[] args){
		for(int i=0;i<LENGTH + 1;i++)
			for(int j=0;j< SUM_WEIGHT + 1;j++)
				dp[i][j] = -1;
		
		System.out.println(memoF(0,SUM_WEIGHT));
	}
	public static int memoF(int i,int weight){
		if(dp[i][weight] != -1)
			return dp[i][weight];
		
		int res = 0;
		if(i == LENGTH){
			res = 0;
		}
		else if(weight < bags.weightTh[i])
			res = memoF(i + 1,weight);
		else {
			res = Math.max(memoF(i + 1,weight), bags.value[i] + memoF(i + 1,weight - bags.weightTh[i]));
		}
		
		return dp[i][weight] = res;
	}
}
