import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #16888 루트 게임
public class Main {

	static int[] dp = new int[1000001];
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i * i <= 1000000 ; i ++) {
			dp[i * i] = 1;
		}
		for(int i = 1 ; i < 1000001 ; i ++) {
			if(dp[i] != 1) {
				for(int j = 1 ; j * j + i <= 1000000 ; j ++) {
					dp[j * j + i] = 1;
				}
			}
		}
		while(test -- > 0) {
			if(dp[Integer.parseInt(br.readLine())] % 2 == 1) sb.append("koosaga\n");
			else sb.append("cubelover\n");
		}
		System.out.println(sb);
	}
}
