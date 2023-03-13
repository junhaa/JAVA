import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #19942 다이어트 
public class Main {
 
	static int[] min;
	static int minval = Integer.MAX_VALUE;
	static boolean[] inc;
	static String answer;
	static int[][] cost;
	static int N;
	static long anssum = 0;
	
	static void backTracking(int[] curcost, int last) {
		last ++;
		if(last == N) {
			for(int i = 0 ; i < 4 ; i ++) {
				if(curcost[i] < min[i]) {
					return;
				}
			}
			if(curcost[4] < minval) {
				minval = curcost[4];
				anssum = 0;
				String tmp = "";
				int idx = 0 ;
				for(int i = 0 ; i < N ; i ++) {
					if(inc[i]) {
						tmp += i + 1;
						tmp += " ";
						anssum += (long)Math.pow(10, N - 1 - idx++) * i;
					}
				}
				answer = tmp;
			}
			else if(curcost[4] == minval) {
				long tmpsum = 0;
				int idx = 0;
				String tmp = "";
				for(int i = 0 ; i < N ; i ++) {
					if(inc[i]) {	
						tmp += i + 1;
						tmp += " ";
						tmpsum += (long)Math.pow(10, N - 1 - idx++) * i;
					}
				}
				if(tmpsum < anssum) {
					anssum = tmpsum;
					answer = tmp;
				}
			}
			return;
		}
		for(int i = 0 ; i < 5 ; i ++) {
			curcost[i] += cost[last][i];
		}
		inc[last] = true;
		backTracking(curcost, last);
		for(int i = 0 ; i < 5 ; i ++) {
			curcost[i] -= cost[last][i];
		}
		inc[last] = false;
		backTracking(curcost, last);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	
		StringTokenizer st = new StringTokenizer(br.readLine());
		inc = new boolean[N];
		min = new int[4];
		for(int i = 0 ; i < 4 ; i ++) {
			min[i] = Integer.parseInt(st.nextToken());
		}
		cost = new int[N][5];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 5 ; j ++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		T.backTracking(new int[5], -1);
		if(minval == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(minval);
		System.out.println(answer);
	}
}
