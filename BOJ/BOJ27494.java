import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #27494 2023년은 검은 토끼의 해
public class Main {

	static int[][][] dp;
	static String N;
	static int[] arr = {2, 0, 2, 3};
	
	static int solution(int idx, int num, int last) {
		if(idx == N.length()) {
			if(num == 4) return 1;
			else return 0;
		}
		if(dp[idx][num][last] != -1) return dp[idx][num][last];
		int tmp = 0;
		int len;
		if(last == 0) len = N.charAt(idx) - '0';
		else len = 9;
		for(int i = 0 ; i < len ; i ++) {
			if(num <= 3 && i == arr[num]) {
				tmp += solution(idx + 1, num + 1, 1);
			}
			else tmp += solution(idx + 1, num, 1);
		}
		if(num <= 3 && len == arr[num]) {
			tmp += solution(idx + 1, num + 1, last);
		}
		else tmp += solution(idx + 1, num, last);
		
		return dp[idx][num][last] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = br.readLine();
		dp = new int[N.length()][5][2];
		for(int i = 0 ; i < N.length() ; i ++) {
			for(int j = 0 ; j < 5 ; j ++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		int tmp = 0;
		int len;
		len = N.charAt(0) - '0';
		for(int i = 0 ; i < len ; i ++) {
			if(i == arr[0]) {
				tmp += solution(1, 1, 1);
			}
			else tmp += solution(1, 0, 1);
		}
		if(len == arr[0]) {
			tmp += solution(1, 1, 0);
		}
		else tmp += solution(1, 0, 0);
		
		System.out.println(tmp);
	}
}
