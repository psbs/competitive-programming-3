package chap3.dp.knapsack.p11566;
// WA on this solution. unable to figure out a test case for that

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.text.Segment;

public class Main {
	/**
	 * @param args
	 */
	static InputReader in 		= new InputReader(System.in);
    //static OutputWriter out	=	new OutputWriter(System.out);
	static BufferedReader br=null;

	public static void main(String[] args) throws Exception  {
		br=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try{
					if(process()==0) {
						System.out.print(sb);
						break;
					}
			}
			catch(Exception e) {
				System.out.print(sb);
				//throw e;
				return;
			}
            
            
		}
		//out.flush();
		//out.close();
	}
	
	static int casen = 1;
	static StringBuffer sb = new StringBuffer();
	static int process() throws Exception{
		int n = in.readInt(), x = in.readInt(), t = in.readInt(), k = in.readInt();
		
		if(n == 0 && x == 0 && t == 0 && k == 0) 
			return 0;
		
		n++;
		
		int[] price = new int[k];
		int[] fav = new int[k];
		
		for(int i = 0; i < k; i++) {
			price[i] = in.readInt();
			for(int j = 0; j < n; j++) {
				fav[i] += in.readInt();
			}
		}
		//System.out.println(Arrays.toString(fav));
		
		int[][][] dp = new int[k][2*n + 2][(3*n*100) + 10];
		for(int i = 0; i < dp.length; i++) for(int j = 0; j < dp[i].length; j++) Arrays.fill(dp[i][j], -2);
		int max = (int) Math.floor((((x * n) / (double)  (1.10)) + 1e-9)- (n * t));
		double ans = recur(price, fav, dp, n, 0, max, (2*n), x, t)/(double)n;
		System.out.println(String.format("%.2f", ans));
		return 1;
	}
	
	static int recur(int[] price, int[] fav, int[][][] dp, int n, int curr, int sum, int cnt, int x, int t) {
		int k = price.length;
		int mx = n * x;
		//System.out.println(curr+" : "+sum+" : "+cnt);
		
		if(cnt < 0 || sum < 0) {
			return -1;
		}
		
		
		if(curr == k || cnt == 0 || sum == 0) {
			return 0;
		}
		if(dp[curr][cnt][sum] >= -1) {
			return dp[curr][cnt][sum];
		}
		
		int res1 = recur(price, fav, dp, n, curr + 1, sum, cnt, x, t);
		int res2 = recur(price, fav, dp, n, curr + 1, sum - price[curr], cnt - 1, x, t);
		int res3 = recur(price, fav, dp, n, curr + 1, sum - (2*price[curr]), cnt - 2, x, t);
		
		if(res2 >= 0) res2 += fav[curr];
		if(res3 >= 0) res3 += (2*fav[curr]);
		
		
		dp[curr][cnt][sum] = Math.max(res1, Math.max(res2, res3));
		//System.out.println(curr+" : "+sum+" : "+cnt+" : "+res1+" : "+res2+" : "+res3);
		return dp[curr][cnt][sum];
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
			if(str[i].compareTo("") != 0)
				ret[i] = Integer.parseInt(str[i].trim());
		}
		return ret;
	}
	static List<Integer> giveArrayList(String[] str) {
		List<Integer> ret = new ArrayList<>();
		for(int i = 0; i < str.length; i++) {
			if(str[i].compareTo("") != 0)
				ret.add(Integer.parseInt(str[i].trim()));
		}
		return ret;
	}
	static int findClosest(List<Integer> arr, int val, boolean comp, int s, int e) {
		//System.out.println(s+" : "+e+" : "+val+ " : "+Arrays.toString(arr));
		if(s == -1 || e == -1 || s > e)
			return -1;
		if(arr.size() == 0)
			return -1;
		if(comp && arr.get(s) > val) {
			return -1;
		}
		
		if(!comp && arr.get(e) < val) {
			return -1;
		}
		
		if(s == e) {
			return s;
		}
		
		if((e - s) == 1) {
			if(comp) {
				if(arr.get(e) <= val) {
					return e;
				}
				else {
					return s;
				}
			}
			else {
				if(arr.get(s) >= val) {
					return s;
				}
				else {
					return e;
				}
			}
		}
		int mid = (s + e) / 2;
		if(comp) {
			if(arr.get(mid) > val) {
				return findClosest(arr, val, comp, s, mid);
			}
			else {
				return findClosest(arr, val, comp, mid, e);
			}
		}
		else {
			if(arr.get(mid) < val) {
				return findClosest(arr, val, comp, mid, e);
			}
			else {
				return findClosest(arr, val, comp, s, mid);
			}
		}
	}
	static int findClosest(int[] arr, int val, boolean comp, int s, int e) {
		//System.out.println(s+" : "+e+" : "+val+ " : "+Arrays.toString(arr));
		if(s == -1 || e == -1 || s > e)
			return -1;
		if(arr.length == 0)
			return -1;
		if(comp && arr[s] > val) {
			return -1;
		}
		
		if(!comp && arr[e] < val) {
			return -1;
		}
		
		if(s == e) {
			return s;
		}
		
		if((e - s) == 1) {
			if(comp) {
				if(arr[e] <= val) {
					return e;
				}
				else {
					return s;
				}
			}
			else {
				if(arr[s] >= val) {
					return s;
				}
				else {
					return e;
				}
			}
		}
		int mid = (s + e) / 2;
		if(comp) {
			if(arr[mid] > val) {
				return findClosest(arr, val, comp, s, mid);
			}
			else {
				return findClosest(arr, val, comp, mid, e);
			}
		}
		else {
			if(arr[mid] < val) {
				return findClosest(arr, val, comp, mid, e);
			}
			else {
				return findClosest(arr, val, comp, s, mid);
			}
		}
	}
	static int findClosest(int[] arr, int val, boolean comp) {
		return findClosest(arr, val, comp, 0, arr.length - 1);
	}
	static int findClosest(List<Integer> arr, int val, boolean comp) {
		return findClosest(arr, val, comp, 0, arr.size() - 1);
	}
	
}
class InputReader {

	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public String next() {
		return readString();
	}

	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}

class OutputWriter {
	private final PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object...objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void printLine(Object...objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}

	public void flush() {
		writer.flush();
	}

	}

class IOUtils {

	public static int[] readIntArray(InputReader in, int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = in.readInt();
		return array;
	}

}
