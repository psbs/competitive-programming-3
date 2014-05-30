package chap1.easy.p11764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
	
	static int process(String line) {
		int n = Integer.parseInt(line.trim());
		for(int i = 0; i < n; i++) {
			int h = 0, l = 0;
			int len = Integer.parseInt(readLine().trim());
			int[] arr = giveArray(readLine().trim().split(" "));
			for(int j = 1; j < len; j++) {
				if(arr[j] > arr[j-1])
					h++;
				if(arr[j] < arr[j-1])
					l++;
			}
			System.out.println("Case "+(i+1)+": "+h + " " + l);
		}
		return 0;

	}

}
