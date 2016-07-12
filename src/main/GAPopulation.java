package main;

import java.util.ArrayList;
import static main.Constants.*;

public class GAPopulation {
	public ArrayList<GAInvidividual> Genes = new ArrayList<GAInvidividual>();
	private double MaxFitness;
	private double MinFitness;
	private double AveFitness;
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
	
	public int getEliteGtype(){
		return this.EliteGtype;
	}

	public void setDataFitness(){
		setMaxFitness();
		setMinFitness();
		setAveFitness();
	}
	
	public void NewGeneration(int genes[]){
		int N = Genes.size();
		
		for(int i=0;i<N;i++)
			Genes.get(i).setGtype(genes[i]);
	}
	
	public void PrintDataFitness(int n){
		System.out.println(n + "\t" + this.MaxFitness + "\t\t" + this.MinFitness + "\t\t" + this.AveFitness);
	}

	public double getSumFitness(){
		double ans = 0.0;

		for(int i=0;i<PoplationSize;i++){
			ans += Genes.get(i).getFitness();
		}

		return ans;
	}
}
