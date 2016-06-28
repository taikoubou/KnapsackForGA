package main;

import static main.RandomValueGenerator.*;
import static main.Constants.*;

public class Baggage{
	public int[] weight = new int[LENGTH];
	public int[] value = new int[LENGTH];

	public Baggage(){
		for(int i=0;i<LENGTH;i++){
			weight[i] = MakeRandomValue(MAX_WEIGHT);
			value[i] = MakeRandomValue(MAX_VALUE);
		}
	}
}
