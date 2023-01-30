import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #15486 퇴사 2
class Counsel {
	int t, p;
	public Counsel(int t, int p){
		this.t = t;
		this.p = p;
	}
}
public class Main {

	static Counsel[] c;
	static int dp[];
	static int N, max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		c = new Counsel[N + 1];
		dp = new int[N + 1];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			c[i] = new Counsel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		c[N] = new Counsel(1, 0);
		for(int i = 0 ; i <= N ; i ++) {
			max = Math.max(max, dp[i]);
			if(i + c[i].t <= N) {
				dp[i + c[i].t] = Math.max(dp[i + c[i].t], max + c[i].p);
			}
		}
		System.out.println(max);
	}
}
