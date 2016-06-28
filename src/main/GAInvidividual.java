package main;

import static main.RandomValueGenerator.*;
import static main.Constants.*;

//GAの個体を表すクラス
public class GAInvidividual{
	private int gtype;	//2進数で取り扱う
	private double ptype;	//バッグの中の価値
	private double fitness;
	public int rank;
	private int weight;

	public GAInvidividual(Baggage bags){
		weight = 0;
		MakeRandomGtype(bags);
	}

	public void MakeRandomGtype(Baggage bags){
		do{
			gtype = InitRandomGType(LENGTH);
			DecodeGtype(bags);
		}while(weight > MAX_BAG);
		//System.out.println("Init Gtype:" + gtype);
	}

	public void DecodeGtype(Baggage bags){
		int ptype = 0;
		int weight = 0;
		int mask = 0x01;

		for(int i=0;i<LENGTH;i++){
			if((gtype & mask) == mask){
				ptype += bags.value[i];
				weight += bags.weight[i];
			}
			mask <<= 1;
		}
		this.ptype = ptype;
		this.weight = weight;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness() {
		this.fitness = ptype;
	}

	public void PrintGtype(){
		char[] bitGtype = new char[LENGTH];
		int mask = 0x01;

		for(int i=0;i<LENGTH;i++){
			if((gtype & mask) == mask)
				bitGtype[LENGTH-(i+1)] = '1';
			else
				bitGtype[LENGTH-(i+1)] = '0';
			mask <<= 1;
		}
		System.out.print(bitGtype);
	}

	public void PrintPtype(){
		System.out.print(ptype);
	}

	public void PrintWeight(){
		System.out.print(weight);
	}

	public void PrintFitness(){
		System.out.print(fitness);
	}
}
