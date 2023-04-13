import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2116 주사위 
public class Main {

	static int[][] dice;
	static int[] rev = { 5, 3, 4, 1, 2, 0 };
	static int N;
	
	static int recursive(int L, int last, int sum) {
		if(L == N) {
			return sum;
		}
		int reverse = -1;
		for(int i = 0 ; i < 6 ; i ++) {
			if(dice[i][L] == last) reverse = dice[rev[i]][L];
		}
		if(Math.max(last, reverse) != 6) return recursive(L + 1, reverse, sum + 6);
		else if (Math.min(last, reverse) != 5) return recursive(L + 1, reverse, sum + 5);
		else return recursive(L + 1, reverse, sum + 4);		
	}
	
	public static void main(String[] args) throws IOException  {
		Main T = new Main();
		int answer = Integer.MIN_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dice = new int[6][N];
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 6 ; j ++) {
				dice[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ; i < 6 ; i ++) {
			answer = Math.max(answer, T.recursive(0, i + 1, 0));
		}
		System.out.println(answer);
	}
}	
