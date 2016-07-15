package main;

import static main.Constants.*;

public class GAMain {
	public static void main(String[] args){
		Baggage bags = new Baggage(FILE);
		GAPopulation test1 = new GAPopulation(bags);	//コンストラクタで初期世代を生成しているはず
		GAOperator operator = new GAOperator(test1);

		System.out.println("DataFile:" + FILE);
		
		System.out.println("Start GA!!");
		
		System.out.println("N\tMaxFitness\tMinFitness\tAveFitness\t\tEliteGtype");
		
		for(long i=0;i<1000;i++){
			test1.setAllFitness();
			test1.setDataFitness();
			
			test1.PrintDataFitness((int)i);
			
			operator.setParentlist(test1);
			
			int[] newGenes = new int[POPLATIONSIZE];
			int[] tmpg = new int[2];
			for(int j=0;j<POPLATIONSIZE-1;j+=2){
				tmpg = operator.RunOperator(test1);
				newGenes[j] = tmpg[0];
				newGenes[j+1] = tmpg[1]; 
			}
			
			newGenes[POPLATIONSIZE-1] = test1.getEliteGtype();
			System.out.print("\t");
			operator.PrintGtype(newGenes[POPLATIONSIZE -1]);
			System.out.println();
			
			test1.NewGeneration(newGenes);
		}
	}

	public static void PrintBags(Baggage bags){
		System.out.println("Bags list");

		System.out.print("[value, weight]={");
		for(int i=0;i<LENGTH;i++){
			System.out.print("[" + Baggage.value[i] + ", " + Baggage.weightTh[i] + "]");
			if((i % 9 == 0) && i != 0)System.out.print("\n\t\t");
		}
		System.out.println("}");
	}

	public static void PrintTest(GAPopulation hoge){
		//System.out.println("[Poplation]\nMaxFitness\tMinFitness\tAveFitness");
		//System.out.println(hoge.MaxFitness + "\t\t" + hoge.MinFitness + "\t\t" + hoge.AveFitness);

		System.out.println("\n[Invidivial]");
		System.out.println("Gtype\t\t\t\t\tPtype\tWeight\tFitness");

		for(int i=0;i<hoge.PoplationSize;i++){
			hoge.Genes.get(i).PrintGtype();
			System.out.print("\t");
			hoge.Genes.get(i).PrintPtype();
			System.out.print("\t");
			hoge.Genes.get(i).PrintWeight();
			System.out.print("\t");
			hoge.Genes.get(i).PrintFitness();
			System.out.print("\n");
		}

		System.out.println();
	}

	public static void PrintParents(GAOperator hoge,GAPopulation foo){
		System.out.println("[Parents]");

		for(int i=0;i<foo.PoplationSize;i++){
			System.out.print(hoge.parentlist[i] + ":" + foo.Genes.get(hoge.parentlist[i]).getFitness() + " ");
		}
		System.out.println();
	}
}