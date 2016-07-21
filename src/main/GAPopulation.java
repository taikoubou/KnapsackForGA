package main;

import java.util.ArrayList;
import java.util.Arrays;

import static main.Constants.*;

public class GAPopulation {
	public ArrayList<GAInvidividual> Genes = new ArrayList<GAInvidividual>();
	private double MaxFitness;
	private double MinFitness;
	private double AveFitness;
	private double MinAveFitness;
	private int EliteGtype;

	public int PoplationSize = POPLATIONSIZE;

	public GAPopulation(Baggage bags){	//初期世代の生成
		for(int i=0;i<PoplationSize;i++){
			Genes.add(new GAInvidividual(bags));
		}
	}
	
	public GAPopulation(){
		Genes.add(new GAInvidividual());
	}
	
	public void setAllFitness(){
		int N = Genes.size();
		
		for(int i=0;i<N;i++)
			Genes.get(i).setFitness();
	}

	public void setMaxFitness(){
		double ans = -1;
		int Eg = 0;
		int N = Genes.size();

		for(int i=0;i<N;i++){
			if(ans < Genes.get(i).getFitness()){
				ans = Genes.get(i).getFitness();
				Eg = Genes.get(i).getGtype();
			}
		}
		MaxFitness = ans;
		EliteGtype = Eg;
	}

	public void setMinFitness(){
		double ans = 1e16;
		int N = Genes.size();

		for(int i=0;i<N;i++){
			if(ans > Genes.get(i).getFitness()){
				ans = Genes.get(i).getFitness();
			}
		}
		MinFitness = ans;
	}

	public void setAveFitness(){
		double sum = 0.0;
		int N = Genes.size();

		sum = this.getSumFitness();

		AveFitness = sum/(double)N;
	}
	
	public void setMinAveFitness(){
		double sum = 0.0;
		int N = Genes.size();
		int len = (int)(Math.floorDiv(N, 4));
		double[] ptypelist = new double[N];
		
		for(int i=0;i<N;i++)
			ptypelist[i] = Genes.get(i).getPtype();
		
		Arrays.sort(ptypelist);
		
		for(int i=0;i<len;i++)
			sum += ptypelist[i];
		
		this.MinAveFitness = sum / len;
	}
	
	public int getEliteGtype(){
		return this.EliteGtype;
	}

	public void setDataFitness(){
		setMaxFitness();
		setMinFitness();
		setAveFitness();
		setMinAveFitness();
	}
	
	private void resetData(){
		MaxFitness = 0.0;
		MinFitness = 1e16;
		AveFitness = 0.0;
		MinAveFitness = 0.0;
	}
	
	public void NewGeneration(int genes[]){
		int N = Genes.size();
		
		for(int i=0;i<N;i++)
			Genes.get(i).setGtype(genes[i]);
		
		resetData();
	}
	
	public void PrintDataFitness(int n){
		System.out.print(n + "\t" + this.MaxFitness + "\t\t" + this.MinFitness + "\t\t");
		System.out.printf("%.10f\t", this.AveFitness);
		System.out.printf("%.10f", this.MinAveFitness);
	}

	public double getSumFitness(){
		double ans = 0.0;

		for(int i=0;i<PoplationSize;i++){
			ans += Genes.get(i).getFitness();
		}

		return ans;
	}
}
