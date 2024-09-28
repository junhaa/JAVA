import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2616 소형기관차
public class Main {
	static int[] pSum, arr;
	static int[][] dp;
	static int cartCapacity, N;

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pSum = new int[N + 1];
		arr = new int[N];
		dp = new int[3][N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0 ; i < N ; i ++){
			arr[i] = Integer.parseInt(st.nextToken());
			pSum[i + 1] = pSum[i] + arr[i];
		}

		// for(int i = 0 ; i < 3 ; i ++){
		// 	Arrays.fill(dp[i], Integer.MIN_VALUE);
		// }
		cartCapacity = Integer.parseInt(br.readLine());

		// System.out.println(main.recursive(0, 0));

		System.out.println(main.solve());
	}

	private int recursive(int idx, int rc){
		if(rc == 3) return 0;
		if(dp[rc][idx] != Integer.MIN_VALUE) return dp[rc][idx];
		int max = Integer.MIN_VALUE;
		for(int i = idx ; i <= N - (3 - rc) * cartCapacity ; i ++){
			int curSum = getSum(i, i + cartCapacity - 1);
			max = Math.max(max, curSum + recursive(i + cartCapacity, rc + 1));
		}

		return dp[rc][idx] = max;
	}

	private int solve(){
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < 3 ; i ++){
			for(int j = i * cartCapacity; j < N - cartCapacity + 1 ; j ++){
				dp[i][j] = Math.max(j == 0 ? 0 : dp[i][j - 1], (i == 0 ? 0 : dp[i - 1][j - cartCapacity]) + getSum(j, j + cartCapacity - 1));
				max = Math.max(dp[i][j], max);
			}
		}
		return max;
	}


	private int getSum(int start, int end){
		return pSum[end + 1] - pSum[start];
	}
}
