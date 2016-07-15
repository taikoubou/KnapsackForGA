package main;

import java.io.IOException;
import java.util.stream.Stream;
import java.nio.file.*;
import static main.RandomValueGenerator.*;
import static main.Constants.*;

public class Baggage{
	public static int[] weightTh = new int[LENGTH];
	public static int[] value = new int[LENGTH];

	public Baggage(){
		for(int i=0;i<LENGTH;i++){
			weightTh[i] = MakeRandomValue(MAX_WEIGHT);
			value[i] = MakeRandomValue(MAX_VALUE);
		}
	}
	
	public Baggage(String file){
		String[] dataLine = ReadData(file);
		
		try{
			for(int i=0;i<LENGTH;i++){
				String[] tmp = dataLine[i].split(" ");
				weightTh[i] = Integer.parseInt(tmp[0]);
				value[i] = Integer.parseInt(tmp[1]);
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	private String[] ReadData(String datafile){
		String Path = System.getProperty("user.dir");
		String[] data = null; 
		try(Stream<String> streamStr = Files.lines(Paths.get(Path + "/Data/" + datafile))){ //自動でcloseするっぽい
			data = streamStr.map(String::toUpperCase).toArray(String[]::new);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return data;
	}
	
	public int getSumWeight(){
		int ans = 0;
		
		for(int i=0;i<LENGTH;i++)
			ans += weightTh[i];
		
		return ans;
	}
	
	public static void makeBagData(){
		for(int i=0;i<LENGTH;i++){
			System.out.println(MakeRandomValue(MAX_WEIGHT) + " " + MakeRandomValue(MAX_VALUE));
		}
	}
	
	public static void main(String[] args){	//テストデータ生成
	}
}
