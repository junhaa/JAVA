import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #2482 색상환
public class Main {

	static final int MOD = 1000000003;
	static int[][][][] dp;
	static int N, K;
	static int solution(int idx, int cnt, int last, int first) {
		if(idx == N - 1) {
			if(cnt == K) return 1;
			if(last == 0 && cnt == K - 1 && first == 0) return 1;
			return 0;
		}
		if(cnt == K) return 1;
		if(dp[idx][cnt][last][first] != -1) return dp[idx][cnt][last][first];
		
		if(idx == 0) {
			dp[idx][cnt][last][1] = solution(idx + 1, cnt + 1, 1, 1); 
			dp[idx][cnt][last][0] = solution(idx + 1, cnt , 0, 0);
			return (dp[idx][cnt][last][1] + dp[idx][cnt][last][0]) % MOD;
		}
		if(last == 0) {
			return dp[idx][cnt][last][first] = (solution(idx + 1, cnt + 1, 1, first) + solution(idx + 1, cnt, 0, first)) % MOD;
		}
		else {
			return dp[idx][cnt][last][first] = solution(idx + 1, cnt, 0, first);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		dp = new int[N][K + 1][2][2];
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < K ; j ++) {
				for(int k = 0 ; k < 2 ; k ++) {
					Arrays.fill(dp[i][j][k], -1);
				}
			}
		}
		System.out.println(T.solution(0, 0, 0, 0));
	 }
}
