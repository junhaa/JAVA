import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #2591 숫자카드
public class Main {

	static String input;
	static int[] dp;
	
	static int solution(int idx) {
		if(idx > input.length()) return 0;
		if(idx == input.length()) return 1;
		if(dp[idx] != -1) return dp[idx];
		if(input.charAt(idx) == '0') return 0;
		int tmp = 0;
		tmp += solution(idx + 1);
		if(idx < input.length() - 1 && (input.charAt(idx) - '0') * 10 + input.charAt(idx + 1) - '0' <= 34) {
			tmp += solution(idx + 2);
		}
		return dp[idx] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		dp = new int[input.length()];
		Arrays.fill(dp, -1);
		System.out.println(T.solution(0));
		System.out.println();
	}
}
