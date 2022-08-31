import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1670 정상 회담 2
public class Main {

	static int dp[];
	
	static long solution(int N) {
		for(int i = 4 ; i <= N ; i += 2) {
			int K = i / 2;
			int num = 0;
			long sum = 0;
			while(K > 0) {
				if(K != 1) {
					sum += ((long)2 * dp[i - 2 - num] * dp[num]) % 987654321;
					sum %= 987654321;
					K -= 2;
					num += 2;
				}
				else {
					sum += ((long)dp[i - 2 - num] * dp[num]) % 987654321;
					sum %= 987654321;
					K --;
				}
			}
			dp[i] = (int)sum;
		}
		return dp[N]; 
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		dp[2] = 1;
		dp[0] = 1;
		if(N == 2) System.out.println(1);
		else System.out.println(T.solution(N));
		
	}
}
