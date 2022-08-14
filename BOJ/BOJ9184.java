import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #9184 신나는 함수 실행
public class Main {
	static int[][][] dp = new int[21][21][21];

	static int recursive(int a, int b , int c) {
		if(a <= 0 || b <= 0 || c <= 0) return 1;
		if(a > 20 || b > 20 || c > 20) return dp[20][20][20] = recursive(20, 20, 20);
		if(dp[a][b][c] != Integer.MIN_VALUE) return dp[a][b][c]; 
		if(a < b && b < c ) return dp[a][b][c] = recursive(a, b, c-1) + recursive(a, b-1, c-1) - recursive(a, b-1, c);
		else return dp[a][b][c] =  recursive(a-1, b, c) + recursive(a-1, b-1, c) + recursive(a-1, b, c-1) - recursive(a-1, b-1, c-1);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < 21 ; i ++) {
			for(int k = 0 ; k < 21 ; k ++) {
				for(int j = 0 ; j < 21 ; j ++) {
					dp[i][k][j] = Integer.MIN_VALUE;
				}
			}
		}
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == -1 && b == -1 && c == -1) break;
			sb.append("w(" + a + ", " + b + ", " + c + ") = ").append(T.recursive(a, b, c)).append('\n');
		}
	
		System.out.println(sb);
	}
	
}
