import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
// BOJ #2494 숫자 맞추기
public class Main {
	static int[] arr1, arr2;
	static int[][] dp;
	static int N;

	static StringBuilder sb = new StringBuilder();

	static int recursive(int leftTurnSum, int idx) {
		if (dp[leftTurnSum][idx] != -1) {
			return dp[leftTurnSum][idx];
		}
		int curNum = (arr1[idx] + leftTurnSum) % 10;
		int rightTurn = arr2[idx] > curNum ? 10 + curNum - arr2[idx] : curNum - arr2[idx];
		int leftTurn = arr2[idx] < curNum ? 10 + arr2[idx] - curNum : arr2[idx] - curNum;
		if (idx == N - 1) {
			if (rightTurn < leftTurn) {
				return dp[leftTurnSum][idx] = rightTurn;
			} else {
				return dp[leftTurnSum][idx] = leftTurn;
			}
		}

		int lRet = recursive((leftTurnSum + leftTurn) % 10, idx + 1) + leftTurn;
		int rRet = recursive(leftTurnSum, idx + 1) + rightTurn;

		if (rRet > lRet) {
			return dp[leftTurnSum][idx] = lRet;
		} else {
			return dp[leftTurnSum][idx] = rRet;
		}
	}

	static void trace(int leftTurnSum, int idx) {
		int curNum = (arr1[idx] + leftTurnSum) % 10;
		int rightTurn = arr2[idx] > curNum ? 10 + curNum - arr2[idx] : curNum - arr2[idx];
		int leftTurn = arr2[idx] < curNum ? 10 + arr2[idx] - curNum : arr2[idx] - curNum;
		if (idx == N - 1) {
			if (rightTurn < leftTurn) {
				if (rightTurn != 0)
					sb.append((idx + 1) + " " + (rightTurn * -1) + "\n");
			} else {
				if (leftTurn != 0)
					sb.append((idx + 1) + " " + leftTurn + "\n");
			}
			return;
		}
		if (dp[(leftTurnSum + leftTurn) % 10][idx + 1] + leftTurn < dp[leftTurnSum][idx + 1] + rightTurn) {
			if (leftTurn != 0)
				sb.append((idx + 1) + " " + leftTurn + "\n");
			trace((leftTurnSum + leftTurn) % 10, idx + 1);
		} else {
			if (rightTurn != 0)
				sb.append((idx + 1) + " " + (rightTurn * -1) + "\n");
			trace(leftTurnSum, idx + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr1 = new int[N];
		arr2 = new int[N];
		dp = new int[10][N];
		for (int i = 0; i < 10; i++) {
			Arrays.fill(dp[i], -1);
		}
		String tmp1 = br.readLine();
		String tmp2 = br.readLine();

		for (int i = 0; i < N; i++) {
			arr1[i] = tmp1.charAt(i) - '0';
			arr2[i] = tmp2.charAt(i) - '0';
		}

		sb.append(recursive(0, 0) + "\n");
		trace(0, 0);

		System.out.println(sb);

	}
}
