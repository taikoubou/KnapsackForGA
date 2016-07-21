package dp;

import static main.Constants.*;
import main.Baggage;

public class DP {
	static Baggage bags = new Baggage(FILE);
	static int SUM_WEIGHT = bags.getSumWeight();
	static int[][] dp = new int[LENGTH + 1][SUM_WEIGHT + 1];
//	static String gene = "";
	
	public static void main(String[] args){
		for(int i=0;i<LENGTH + 1;i++)
			for(int j=0;j< SUM_WEIGHT + 1;j++)
				dp[i][j] = -1;
		
		System.out.println(memoF(0,MAX_BAG));
	}
	public static int memoF(int i,int weight){
		if(dp[i][weight] != -1)
			return dp[i][weight];
		
		int res = 0;
		if(i == LENGTH){
			res = 0;
		}
		else if(weight < Baggage.weightTh[i]){
			res = memoF(i + 1,weight);
		}
		else {
			res = Math.max(memoF(i + 1,weight), Baggage.value[i] + memoF(i + 1,weight - Baggage.weightTh[i]));
		}
		
		return dp[i][weight] = res;
	}
}
