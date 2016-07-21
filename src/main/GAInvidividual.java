package main;

import static main.RandomValueGenerator.*;
import static main.Constants.*;
import static main.Baggage.*;

//GAの個体を表すクラス
public class GAInvidividual{
	private int gtype;	//2進数で取り扱う
	private double ptype;	//バッグの中の価値
	private double fitness;
	public int rank;
	private int weight;

	public GAInvidividual(Baggage bags){
		weight = 0;
		ptype = -1;
		MakeRandomGtype(bags);
	}
	
	public GAInvidividual(){
		weight = 0;
		gtype = 0;
		ptype = -1;
	}

	public void MakeRandomGtype(Baggage bags){
		do{
			gtype = InitRandomGType(LENGTH);
			DecodeGtype();
		}while(weight > MAX_BAG);
		//System.out.println("Init Gtype:" + gtype);
	}

	private void DecodeGtype(){
		int ptype = 0;
		int weighttmp = 0;
		int mask = 0x01;

		for(int i=0;i<LENGTH;i++){
			if((gtype & mask) == mask){
				ptype += value[i];
				weighttmp += weightTh[i];
			}
			mask <<= 1;
		}
		this.ptype = ptype;
		this.weight = weighttmp;
	}
	
	public double getPtype(){
		return this.ptype;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness() {
		//if(ptype == -1)
		DecodeGtype();
		this.fitness = ptype;
	}
	
	public void setGtype(int gtype){
		this.gtype = gtype;
	}
	
	public int getGtype(){
		return this.gtype;
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
