import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2579 계단 오르기
public class Main {

	static int score[];
	static int[][] dp;
	static int N;
	
	public int stair(int num, int cnt) {
		if(num == N) return score[num];
		if(dp[num][cnt] != 0) return dp[num][cnt];
		int tmp = Integer.MIN_VALUE;
		if(cnt < 2) tmp = Math.max(tmp, stair(num + 1, cnt + 1) + score[num]);
		if(num + 2 <= N) tmp = Math.max(tmp, stair(num + 2 , 1) + score[num]);
		return dp[num][cnt] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		score = new int[N + 1];
		dp = new int[N + 1][3];
		for(int i = 1 ; i <= N ; i ++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(T.stair(0, 0));
	}
}
