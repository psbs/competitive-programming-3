package chap1.medium.p11683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static BufferedReader br=null;
	/**
	 * @param args
	 */
	static String out = "";

	public static void main(String[] args) {
		br=new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		line = readLine();

		while(line != null && line.length()!=0) {
            if(process(line)==0) {
            	return;
            }
            
			line = readLine();
		}
		
	}
	
	static String readLine(){
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	static int[] giveArray(String[] str) {
		int[] ret = new int[str.length];
		for(int i = 0; i < str.length; i++) {
			ret[i] = Integer.parseInt(str[i]);
		}
		return ret;
	}
	
	static int ind = 0;
	static int process(String line) {
		int[] inp = giveArray(line.trim().split(" "));
		
		if(inp[0] == 0)
			return 0;
		
		int[] arr = giveArray(readLine().trim().split(" "));
		
		int cost = 0, max = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] < arr[i-1])
				cost += arr[i-1] - arr[i];
			//max = Math.min(max, arr[i]);
		}
		
		cost += inp[0] - max;
		System.out.println(cost);
		//System.out.println(Arrays.toString(arr));
		return 1;
	}
	

}
