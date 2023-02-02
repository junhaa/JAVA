import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #12869 뮤탈리스크
public class Main {

	static int dp[][][];
	
	static void solution(int s1, int s2, int s3, int num) {
		if(s1 < 0) s1 = 0;
		if(s2 < 0) s2 = 0;
		if(s3 < 0) s3 = 0;
		if(dp[s1][s2][s3] != -1) { 
			if(dp[s1][s2][s3] <= num) return;
			else dp[s1][s2][s3] = num;
		}
		else dp[s1][s2][s3] = num;
		if(s1 == 0 && s2 == 0 && s3 == 0) return;
		solution(s1 - 9, s2 - 3, s3 - 1, num + 1);
		solution(s1 - 9, s2 - 1, s3 - 3, num + 1);
		solution(s1 - 3, s2 - 9, s3 - 1, num + 1);
		solution(s1 - 3, s2 - 1, s3 - 9, num + 1);
		solution(s1 - 1, s2 - 9, s3 - 3, num + 1);
		solution(s1 - 1, s2 - 3, s3 - 9, num + 1);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int scv1, scv2, scv3;
		scv1 = Integer.parseInt(st.nextToken());
		if(N == 1) scv2 = 0;
		else scv2 = Integer.parseInt(st.nextToken());
		if(N == 1 || N == 2) scv3 = 0;
		else scv3 = Integer.parseInt(st.nextToken());
		dp = new int[scv1 + 1][scv2 + 1][scv3 + 1];
		for(int i = 0 ; i <= scv1 ; i ++) {
			for(int j = 0 ; j <= scv2 ; j ++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		T.solution(scv1, scv2, scv3, 0);
		System.out.println(dp[0][0][0]);
	}
}
