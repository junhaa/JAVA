import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1970 건배
public class Main {
	static int[] coke;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		coke = new int[N];
		dp = new int[N][N];
		for(int i = 0 ; i < N ; i ++){
			coke[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[i], -1);
		}

		System.out.println(main.cheers(0, N - 1));
	}

	private int cheers(int start, int end){
		if(start >= end) return 0;
		if(dp[start][end] != -1) return dp[start][end];
		int max = cheers(start + 1, end);
		for(int i = start + 1 ; i <= end ; i ++){
			if(coke[start] == coke[i]){
				max = Math.max(cheers(i + 1, end) + cheers(start + 1, i - 1) + 1, max);
			}
		}
		dp[start][end] = max;
		return max;
	}
}
