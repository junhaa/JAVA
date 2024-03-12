import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1006 습격자 초라기 
public class Main {

	static int N, W;
	static int[][][] mem;
	static int[][] arr;
	static private final int MIN = (int)-1e9;

	static int dp(int cycle, int last, int idx) {
		if (mem[cycle][last][idx] != MIN) {
			return mem[cycle][last][idx];
		}
		if (idx == N - 1) {
			switch (cycle) {
				case 0: // up
					return mem[cycle][last][idx] = 1;
				case 1: // down
					return mem[cycle][last][idx] = 1;
				case 2: // both
					return mem[cycle][last][idx] = 2;
				case 3: // both x
					return mem[cycle][last][idx] = last == 3 && arr[0][idx] + arr[1][idx] <= W ? 1 : 0;
			}
		}
		if (idx == 0) {
			int max = 0;
			switch (cycle) {
				case 0: // cycle case 0 -> up
					if (arr[1][0] + arr[1][N - 1] <= W) {
						if (arr[0][idx] + arr[0][idx + 1] <= W) { // down
							max = Math.max(dp(cycle, 1, idx + 1) + 1, max);
						}
						max = Math.max(dp(cycle, 3, idx + 1), max);
					}
					break;
				case 1: // case 1 -> down
					if (arr[0][0] + arr[0][N - 1] <= W) {
						if (arr[1][idx] + arr[1][idx + 1] <= W) { // up
							max = Math.max(dp(cycle, 0, idx + 1) + 1, max);
						}
						max = Math.max(dp(cycle, 3, idx + 1), max);
					}
					break;
				case 2: // case 2 -> both
					if (arr[1][0] + arr[1][N - 1] <= W && arr[0][0] + arr[0][N - 1] <= W) {
						max = Math.max(dp(cycle, 3, idx + 1), max);
					}
					break;

				case 3: // case 3 -> both x
					if (arr[1][idx] + arr[1][idx + 1] <= W) { // up
						max = Math.max(dp(cycle, 0, idx + 1) + 1, max);
					}
					if (arr[0][idx] + arr[0][idx + 1] <= W) { // down
						max = Math.max(dp(cycle, 1, idx + 1) + 1, max);
					}
					if (arr[1][idx] + arr[1][idx + 1] <= W && arr[0][idx] + arr[0][idx + 1] <= W) { // both
						max = Math.max(dp(cycle, 2, idx + 1) + 2, max);
					}
					if (arr[0][idx] + arr[1][idx] <= W) {
						max = Math.max(dp(cycle, 3, idx + 1) + 1, max);
					}
					max = Math.max(dp(cycle, 3, idx + 1), max);
					break;
			}
			return mem[cycle][last][idx] = max;
		}

		int max = 0;

		switch (last) {
			case 0: // up
				if (arr[0][idx] + arr[0][idx + 1] <= W) { // down
					if (idx == N - 2 && (cycle == 1 || cycle == 2))
						;
					else
						max = Math.max(dp(cycle, 1, idx + 1) + 1, max);
				}
				max = Math.max(dp(cycle, 3, idx + 1), max);
				break;

			case 1: // down
				if (arr[1][idx] + arr[1][idx + 1] <= W) { // up
					if (idx == N - 2 && (cycle == 0 || cycle == 2))
						;
					else
						max = Math.max(dp(cycle, 0, idx + 1) + 1, max);
				}
				max = Math.max(dp(cycle, 3, idx + 1), max);
				break;

			case 2: // both
				max = Math.max(dp(cycle, 3, idx + 1), max);
				break;

			case 3: // both x + Updown
				if (arr[0][idx] + arr[1][idx] <= W) {
					max = Math.max(dp(cycle, 3, idx + 1) + 1, max);
				}
				if (arr[0][idx] + arr[0][idx + 1] <= W) { // down
					if (idx == N - 2 && (cycle == 1 || cycle == 2))
						;
					else
						max = Math.max(dp(cycle, 1, idx + 1) + 1, max);
				}
				if (arr[1][idx] + arr[1][idx + 1] <= W) { // up
					if (idx == N - 2 && (cycle == 0 || cycle == 2))
						;
					else
						max = Math.max(dp(cycle, 0, idx + 1) + 1, max);
				}
				if (arr[0][idx] + arr[0][idx + 1] <= W && arr[1][idx] + arr[1][idx + 1] <= W) {
					if (idx == N - 2 && (cycle != 3))
						;
					else
						max = Math.max(dp(cycle, 2, idx + 1) + 2, max);
				}
				max = Math.max(dp(cycle, 3, idx + 1), max);
				break;
		}
		return mem[cycle][last][idx] = max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			mem = new int[4][4][N];
			arr = new int[2][N];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			if (N == 1) {
				if (arr[0][0] + arr[1][0] <= W) {
					sb.append(1 + "\n");
				} else {
					sb.append(2 + "\n");
				}
				continue;
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Arrays.fill(mem[i][j], MIN);
				}
			}
			for (int i = 0; i < 4; i++) {
				dp(i, 3, 0);
			}

			int max = 0;
			for (int i = 0; i < 4; i++) {
				max = Math.max(mem[i][3][0], max);
			}
			sb.append(((2 * N) - max) + "\n");
		}
		System.out.println(sb);
	}
}
