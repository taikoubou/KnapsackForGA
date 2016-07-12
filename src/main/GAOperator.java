package main;

import static main.Baggage.*;
import static main.RandomValueGenerator.*;
import static main.Constants.*;

public class GAOperator {
	public final double CROSSOVER = 0.65;	//交叉率
	public final double MUTATE = 0.10;
	//public final int NPOINT = 2;	//交叉点の数（２だと二点交叉）
	public int[] parentlist;	//選択された親たちの配列番号

	public static void main(String[] arges){	//メソッドテスト用
	}

	public GAOperator(GAPopulation genes){
		parentlist = new int[genes.PoplationSize];
	}
	
	public void setParentlist(GAPopulation genes){
		parentlist = RouletteSelect(genes);
	}

	public int[] RunOperator(GAPopulation genes){	//GAオペレーター処理の大きな部分（選択、交叉、変異とか）
		double isCrossOver = MakeRandomValue();
		int[] tmpChild = new int[2];
		int parents = -1;
		
		//parentlist = RouletteSelect(genes);	//選択

		if(isCrossOver <= CROSSOVER){
			//交叉
			int parent1,parent2;
			int point1 = MakeGtypePoint(LENGTH) , point2 = MakeGtypePoint(LENGTH);
			parents = SelectParent(genes.PoplationSize,parentlist);
			parent1 = (int)Math.floor(parents/genes.PoplationSize);
			parent2 = parents%genes.PoplationSize;
			//System.out.println("paretes:parent1:parent2" + parents + parent1 + parent2);
			
			while(point1 == point2)		//交叉点が同じじゃないようにする
				point2 = MakeGtypePoint(LENGTH);
			
			tmpChild = TwoPointCrossover(genes.Genes.get(parent1).getGtype(),genes.Genes.get(parent2).getGtype(),point1,point2);
		}
		//突然変異
		tmpChild = Mutation(tmpChild);
		
		//致死遺伝子かどうか調べる
		if(isLethal(tmpChild[0])){
			//ランダムに荷物捨てる処理
			do{
				tmpChild[0] = geneDrop(tmpChild[0]);
			}while(isLethal(tmpChild[0]));
		}
		if(isLethal(tmpChild[1])){
			do{
				tmpChild[1] = geneDrop(tmpChild[1]);
			}while(isLethal(tmpChild[1]));
		}
		
		return tmpChild;
	}

	//private void LinearScaling(){}	//線形スケーリング（実装しないでやってみる

	private int[] RouletteSelect(GAPopulation genes){		//ルーレット選択
		int len = genes.PoplationSize;
		int[] parents = new int[genes.PoplationSize];
		double sumfit = genes.getSumFitness();
		double[] table = MakeRandomTable(genes);

		//for(int i=0;i<len;i++)
			//System.out.print(table[i] + ",");

		//System.out.println("[[InMethod RoutletSelect]]");

		for(int i=0;i<len;i++){		//同じ数の親を選択 O(n^2)でつらい
			double rand = RouletteRandom(sumfit);	//0.1 <= x <= sumfitまでの乱数を生成
			double oldvalue = 0.0;

			for(int j=0;j<len;j++){
				if(oldvalue < rand && rand <= table[j]){
					parents[i] = j;
					//genes.Genes.get(j).PrintFitness();
					//System.out.print(" ");
					break;
				}
				else{
					oldvalue = table[j];
				}
			}
		}
		//System.out.println("\n[[EndOfMethod RoutletSelect]]\n");

		return parents;
	}

	private int[] TwoPointCrossover(int parent1,int parent2,int point1,int point2){	//gtypeが引数
		int[] ans = {0x00,0x00};
		int tmp = 0x00;
		int tmp2 = 0x00;
		int mask = -1;

		if(point2 < point1){
			int t = point1;
			point1 = point2;
			point2 = t;
		}

		//System.out.println(point1 + ":" + point2);

		mask <<= (point2 - point1 + 1);
		mask = ~mask;

		tmp = (parent2 >> point1) & mask;	//交叉点間のビットを取得（たぶん
		tmp2 = (parent1 >> point1) & mask;

		ans[0] = (parent1 >> point2 + 1) << (point2 - point1 + 1) | tmp;
		ans[0] <<= point1;
		ans[0] |= (parent1) & ~(-1 << point1);
		
		ans[1] = (parent2 >> point2 + 1) << (point2 - point1 + 1) | tmp2;
		ans[1] <<= point1;
		ans[1] |= (parent2) & ~(-1 << point1);

		return ans;
	}
	
	private int geneDrop(int gene){
		int dropPoint = MakeRandomDropPoint(LENGTH);
		int mask = 0x01;
		
		mask <<= dropPoint;
		
		if((gene & mask) == mask){
			gene ^= mask;
		}
		
		return gene;
	}
	
	private boolean isLethal(int gene){
		boolean ans = false;
		
		if(G2Weight(gene) > MAX_BAG)
			ans = true;
		
		return ans;
	}
	
	private int[] Mutation(int gene[]){
		int mask = 0x01;
		
		for(int i=0;i<LENGTH;i++){
			double isMutate = MakeRandomValue();
			
			if(isMutate < MUTATE)
				gene[0] ^= mask;
			mask <<= 1;
		}
		mask = 0x01;
		for(int i=0;i<LENGTH;i++){
			double isMutate = MakeRandomValue();
			
			if(isMutate < MUTATE)
				gene[1] ^= mask;
			mask <<= 1;
		}
		
		return gene;
	}
	
	public int G2Weight(int gtype){
		int weighttmp = 0;
		int mask = 0x01;

		for(int i=0;i<LENGTH;i++){
			if((gtype & mask) == mask)
				weighttmp += weightTh[i];
			mask <<= 1;
		}
		return weighttmp;
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

	private int SelectParent(int num,int parents[]){	//num:親の数
		//ここ修正1016 sturedeyayする
		int parent1 = parents[MakeRandomValue(num)];	//親の配列の添字を入れてる
		int parent2 = parents[MakeRandomValue(num)];

		while(parent1 == parent2)	//同じ親を選択しないようにするため
			parent2 = parents[MakeRandomValue(num)];

		return (parent1 * num + parent2);	//個体数進数
	}

	static void hoge(int foo){
		char[] bitGtype = new char[LENGTH];
		int mask = 0x01;

		for(int i=0;i<LENGTH;i++){
			if((foo & mask) == mask)
				bitGtype[LENGTH-(i+1)] = '1';
			else
				bitGtype[LENGTH-(i+1)] = '0';
			mask <<= 1;
		}
		System.out.print(bitGtype);
		//return bitGtype;
	}
}
