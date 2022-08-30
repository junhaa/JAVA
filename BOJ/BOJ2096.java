import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2096 내려가기
class Score{
	int min;
	int max;
	public Score(int min, int max) {
		this.min = min;
		this.max = max;
	}
}

public class Main {

	static Score[] dp = new Score[3];
	static int[] next = new int[3];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int maxA = Integer.MIN_VALUE, minA = Integer.MAX_VALUE;
		int N = Integer.parseInt(br.readLine()) - 1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 3 ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			dp[i] = new Score(tmp, tmp);
		}
		while(N -- > 0) {
			st = new StringTokenizer(br.readLine());
			Score[] tmp = new Score[3];
			for(int i = 0 ; i < 3 ; i ++) {
				next[i] = Integer.parseInt(st.nextToken());
			}
			for(int i = 0 ; i < 3 ; i ++) {
				for(int k = -1 ; k < 2 ; k ++) {
					int idx = i + k;
					if(i + k < 0 || i + k > 2) continue;
					if(tmp[idx] == null) {
						tmp[idx] = new Score(dp[i].min + next[idx], dp[i].max + next[idx]);
					}
					else {
						tmp[idx].min = Math.min(dp[i].min + next[idx], tmp[idx].min);
						tmp[idx].max = Math.max(dp[i].max + next[idx], tmp[idx].max);
					}
				}
			}
			for(int i = 0 ; i < 3 ; i ++) {
				dp[i].min = tmp[i].min;
				dp[i].max = tmp[i].max;
			}
		}
		for(int i = 0 ; i < 3 ; i ++) {
			maxA = Math.max(maxA, dp[i].max);
			minA = Math.min(minA, dp[i].min);
		}
		System.out.println(maxA + " " + minA);
	}
}
