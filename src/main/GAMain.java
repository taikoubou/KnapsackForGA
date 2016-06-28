package main;

import static main.Constants.*;

public class GAMain {
	public static void main(String[] args){
		Baggage bags = new Baggage();
		GAPopulation test1 = new GAPopulation(bags);
		GAOperator operator = new GAOperator(test1.PoplationSize);

		PrintBags(bags);

		System.out.println("GA Population Test");

		for(int i=0;i<test1.PoplationSize;i++){
			test1.Genes.get(i).DecodeGtype(bags);
			test1.Genes.get(i).setFitness();
		}

		test1.setAllFitness();

		PrintTest(test1);



		PrintParents(operator,test1);

	}

	public static void PrintBags(Baggage bags){
		System.out.println("Bags list");

		System.out.print("[value, weight]={");
		for(int i=0;i<LENGTH;i++){
			System.out.print("[" + bags.value[i] + ", " + bags.weight[i] + "]");
			if((i % 9 == 0) && i != 0)System.out.print("\n\t\t");
		}
		System.out.println("}");
	}

	public static void PrintTest(GAPopulation hoge){
		System.out.println("[Poplation]\nMaxFitness\tMinFitness\tAveFitness");
		System.out.println(hoge.MaxFitness + "\t\t" + hoge.MinFitness + "\t\t" + hoge.AveFitness);

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
			System.out.print(hoge.parents[i] + ":" + foo.Genes.get(hoge.parents[i]).getFitness() + " ");
		}
		System.out.println();
	}
}