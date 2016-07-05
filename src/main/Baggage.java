package main;

import static main.RandomValueGenerator.*;
import static main.Constants.*;

public class Baggage{
	public static int[] weightTh = new int[LENGTH];
	public static int[] value = new int[LENGTH];

	public Baggage(){
		for(int i=0;i<LENGTH;i++){
			weightTh[i] = MakeRandomValue(MAX_WEIGHT);
			value[i] = MakeRandomValue(MAX_VALUE);
		}
	}
}
