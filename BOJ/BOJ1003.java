import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Count{
	int c0;
	int c1;
	public Count(int c0, int c1) {
		this.c0 = c0;
		this.c1 = c1;
	}
}
// BOJ #1003 피보나치 함수
public class Main {
	static Count dp[] = new Count[41];

	static void solution() {
			for(int i = 2 ; i < 41 ; i ++) {
				dp[i] = new Count(dp[i - 1].c0 + dp[i - 2].c0 , dp[i - 1].c1 + dp[i - 2].c1);
			}
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		dp[0] = new Count(1, 0);
		dp[1] = new Count(0, 1);
		T.solution();
		while(test-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N].c0 + " " + dp[N].c1).append('\n');
		}
		System.out.println(sb);
	}
}
