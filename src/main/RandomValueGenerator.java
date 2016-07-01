package main;

import java.util.Random;

public class RandomValueGenerator{
	private static Random rnd = new Random();

	public static int InitRandomGType(int bits){ //32bits以下で考える(荷物の数によってbit数を変える
		return rnd.nextInt((int)Math.pow(2, bits));
	}

	public static int MakeRandomValue(int max){		//0からmaxまで
		return rnd.nextInt(max);
	}

	public static double RouletteRandom(double max){	//0.1*maxからmaxまで
		double ans = (rnd.nextDouble() + 0.1) * max;

		return ans > max ? 1.0 : ans;
	}

	public static double MakeRandomValue(){	//0...0からmaxまで
		return rnd.nextDouble();
	}
	
	public static int MakeGtypePoint(int bits){	//交叉点の生成 1からbits-2までの値（二点交叉用）
		int ans = rnd.nextInt(bits) - 1;
		
		return ans < 1 ? 1 : ans;
	}
}