package main;

import java.util.ArrayList;
//import static main.Constants.*;

public class GAPopulation {
	public ArrayList<GAInvidividual> Genes = new ArrayList<GAInvidividual>();
	public double MaxFitness;
	public double MinFitness;
	public double AveFitness;

	public int PoplationSize = 10;

	public GAPopulation(Baggage bags){	//初期世代の生成
		for(int i=0;i<PoplationSize;i++){
			Genes.add(new GAInvidividual(bags));
		}
	}
	
	public GAPopulation(){
		Genes.add(new GAInvidividual());
	}

	public void setMaxFitness(){
		double ans = -1;
		int N = Genes.size();

		for(int i=0;i<N;i++){
			if(ans < Genes.get(i).getFitness()){
				ans = Genes.get(i).getFitness();
			}
		}
		MaxFitness = ans;
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

	public void setAllFitness(){
		setMaxFitness();
		setMinFitness();
		setAveFitness();
	}

	public double getSumFitness(){
		double ans = 0.0;

		for(int i=0;i<PoplationSize;i++){
			ans += Genes.get(i).getFitness();
		}

		return ans;
	}
}
