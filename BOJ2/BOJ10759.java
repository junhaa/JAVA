import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Point{
	int y, x;

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
// BOJ #10759 팰린드롬 경로 3
public class Main {
	static int[][][] dp;
	static Character[][] map;
	static final int MOD = (int)1e9 + 7;
	static int N;
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new Character[N][N];
		dp = new int[2][N][N];
		String curStr;
		for(int i = 0 ; i < N ; i ++){
			curStr = br.readLine();
			for(int j = 0 ; j < N ; j ++){
				map[i][j] = curStr.charAt(j);
			}
		}

		for(int i = 0 ; i < N ; i ++){
			dp[(N - 1) & 1][i][i] = 1;
		}

		main.solve();
		System.out.println(dp[0 & 1][0][0]);
	}

	private void solve(){
		for(int l = N - 2 ; l >= 0 ; l --){
			for(int r = 0 ; r < N ; r ++){
				Arrays.fill(dp[l & 1][r], 0);
			}
			for(int i = 0 ; i <= l ; i ++){
				Point ulP = convertPointMap(i, l, true);
				for(int j = 0 ; j <= l ; j ++){
					Point drP = convertPointMap(j, l, false);
					if(map[ulP.y][ulP.x] != map[drP.y][drP.x]) continue;
					dp[l & 1][i][j] += dp[(l + 1) & 1][i][j];
					dp[l & 1][i][j] %= MOD;
					dp[l & 1][i][j] += dp[(l + 1) & 1][i + 1][j];
					dp[l & 1][i][j] %= MOD;
					dp[l & 1][i][j] += dp[(l + 1) & 1][i][j + 1];
					dp[l & 1][i][j] %= MOD;
					dp[l & 1][i][j] += dp[(l + 1) & 1][i + 1][j + 1];
					dp[l & 1][i][j] %= MOD;
				}
			}
		}
	}

	private Point convertPointMap(int idx, int level, boolean isLeftUp){
		if(isLeftUp){
			return new Point(level - idx, idx);
		}
		else{
			return new Point(N - 1 - idx, N - 1 - level + idx);
		}
	}
}
