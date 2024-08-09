import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #14650 걷다보니 신천역 삼 (Small) 
public class Main {
	private static int[] cur = new int[3];
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		System.out.println(backTracking(0, 0));
	}

	static long backTracking(int curIdx, int last) {
		long ans = 0;
		if (curIdx == N) {
			int canFirst = 0;
			int sum = 0;
			long mulSum = 0;
			for (int i = 0; i < 3; i++) {
				if (cur[i] != 0) {
					if (i != 0) {
						canFirst++;
					}
					sum += cur[i];
					mulSum += i * cur[i];
				}
			}

			if (mulSum % 3 != 0)
				return 0;
			if (canFirst == 0)
				return 0;
			long curSum = sum - cur[0];
			curSum *= fac(sum - 1);
			for (int i = 0; i < 3; i++) {
				curSum /= fac(cur[i]);
			}

			return curSum;
		}
		for (int i = last; i < 3; i++) {
			cur[i]++;
			ans += backTracking(curIdx + 1, i);
			cur[i]--;
		}
		return ans;
	}

	static long fac(int num) {
		long sum = 1;
		for (int k = num; k >= 1; k--) {
			sum *= k;
		}
		return sum;
	}
}
