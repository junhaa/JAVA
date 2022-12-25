import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1912 연속합
public class Main {

	static int[] arr, dp;
	static int n;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		dp = new int[n];
		Arrays.fill(dp, -(int)1e9);
		for(int i = 0 ; i < n ; i ++) arr[i] = Integer.parseInt(st.nextToken());
		dp[0] = arr[0];
		int max = dp[0];
		for(int i = 1 ; i < n ; i ++) {
			dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
			max = Math.max(dp[i], max);
		}
		System.out.println(max);
	}
}
