package main;

import static main.RandomValueGenerator.*;

public class GAOperator {
	public final double CROSSOVER = 0.65;	//交叉率(突然変異率は 1-交叉率 で計算する)
	public final double MUTATE = 1.00 - CROSSOVER;
	public final int NPOINT = 2;	//交叉点の数（２だと二点交叉）
	public int[] parents;	//選択された親たちの配列番号

	public GAOperator(int len){
		parents = new int[len];
	}

	public void RunOperator(GAPopulation genes){	//GAオペレーター処理の大きな部分（選択、交叉、変異とか）
		double isCrossOver = MakeRandomValue();
		double isMutate = MakeRandomValue();

		RouletteSelect(genes);	//選択

		if(isCrossOver <= CROSSOVER){
			//交叉
		}
		if(isMutate <= MUTATE){
			//突然変異
		}
	}

	private void LinearScaling(){}	//線形スケーリング（実装しないでやってみる

	private void RouletteSelect(GAPopulation genes){		//ルーレット選択
		int len = genes.PoplationSize;
		double sumfit = genes.getSumFitness();
		double[] table = MakeRandomTable(genes);

		//for(int i=0;i<len;i++)
			//System.out.print(table[i] + ",");

		System.out.println("[[InMethod RoutletSelect]]");

		for(int i=0;i<len;i++){		//同じ数の親を選択 O(n^2)でつらい
			double rand = RouletteRandom(sumfit);	//0.1 <= x <= sumfitまでの乱数を生成
			double oldvalue = 0.0;

			for(int j=0;j<len;j++){
				if(oldvalue < rand && rand <= table[j]){
					parents[i] = j;
					genes.Genes.get(j).PrintFitness();
					System.out.print(" ");
					break;
				}
				else{
					oldvalue = table[j];
				}
			}
		}
		System.out.println("\n[[EndOfMethod RoutletSelect]]\n");
	}

	private double[] MakeRandomTable(GAPopulation genes){	//ルーレット選択に使うテーブルの生成でしてー
		int len = genes.PoplationSize;
		double[] ans = new double[len];
		double oldvalue = 0.0;

		for(int i=0;i<len;i++){
			ans[i] = genes.Genes.get(i).getFitness() + oldvalue;
			oldvalue = ans[i];
		}

		return ans;
	}

	private int SelectParent(){

		return 0;
	}
}
