import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #3687 성냥개비
public class Main {
	static int[] cnt = {-1, -1, 1, 7, 4, 2, 0, 8};
	static final long MAX = (long)1e16 + 7;
	static long[] dp = new long[101];
	public static void main(String[] args) throws IOException {
		Main main = new Main();

		main.init();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T -- > 0){
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N] + " " + main.solveMax(N) +  "\n");
		}
		System.out.println(sb);
	}

	private void init(){
		Arrays.fill(dp, MAX);
		for(int i = 2 ; i < 8 ; i ++){
			dp[i] = cnt[i];
		}

		dp[6] = 6;

		for(int i = 8 ; i <= 100 ; i ++){
			for(int j = 2 ; j < 8 ; j ++){
				dp[i] = Math.min(dp[i], dp[i - j] * 10 + cnt[j]);
			}
		}
	}


	private String solveMax(int N){
		String ret = "";
		if(N % 2 == 1){
			ret += 7;
			N -= 3;
		}
		int repeat = N / 2;
		for(int i = 0 ; i < repeat ; i ++){
			ret += 1;
		}
		return ret;
	}
}
