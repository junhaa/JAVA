import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11052 카드 구매하기
public class Main {

	static int dp[];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(st.nextToken());
				
		for(int i = 1 ; i <= N ; i ++) {
			dp[i] = n1 * i;
		}
		for(int i = 2 ; i <= N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			//if(dp[i] >= tmp) continue;
			dp[i] = Math.max(dp[i], tmp);
			for(int j = i + 1 ; j <= N ; j ++) {
				dp[j] = Math.max(dp[j], dp[i] + dp[j - i]);
			}
		}
		System.out.println(dp[N]);
	}
}
