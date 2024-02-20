import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #10327 피보나치 문제해결전략
public class Main {
	static int[] a = new int[45], b = new int[45]; // 가장 작은 경우 a = 1, b = 1 일때 피보나치에서 45번째 수가 n의 범위인 10억을 넘어감

	static int[] EEA(int a, int b) {
		int r1 = a, r2 = b, s1 = 1, s2 = 0, t1 = 0, t2 = 1;

		while (r2 != 0) {
			int q = r1 / r2;
			int r = r1 % r2;
			int s = s1 - q * s2;
			int t = t1 - q * t2;
			r1 = r2;
			r2 = r;
			s1 = s2;
			s2 = s;
			t1 = t2;
			t2 = t;
		}
		return new int[] {r1, s1, t1};
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		a[0] = 1;
		a[1] = 0;
		b[0] = 0;
		b[1] = 1;

		for (int i = 2; i < 45; i++) {
			a[i] = a[i - 1] + a[i - 2];
			b[i] = b[i - 1] + b[i - 2];
		}

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			long minDif = Long.MAX_VALUE;
			long mx = Long.MAX_VALUE, my = Long.MAX_VALUE;
			for (int i = 0; i < 45; i++) {
				int[] ret = EEA(a[i], b[i]);
				// a[i]ret[1] + b[i]ret[2] = ret[0]
				if (n % ret[0] != 0) {
					continue;
				}
				int mul = n / ret[0];
				long cx = (long)ret[1] * mul;
				long cy = (long)ret[2] * mul;

				//x, y값이 0보다 작거나 같으면 X
				if (cx <= 0) {
					long tmp = Math.abs(cx) / b[i] + 1;
					cx += b[i] * tmp;
					cy -= a[i] * tmp;
				} else if (cy <= 0) {
					long tmp = Math.abs(cy) / a[i] + 1;
					cx -= b[i] * tmp;
					cy += a[i] * tmp;
				}

				long t = 0;

				if (cx <= 0 || cy <= 0)
					continue;

				if (cx < cy) {
					t = (cy - cx) / (a[i] + b[i]);
					cx += b[i] * t;
					cy -= a[i] * t;
				} else {
					t = (long)Math.ceil((double)(cx - cy) / (a[i] + b[i]));
					cx -= b[i] * t;
					cy += a[i] * t;
				}

				if (cx <= 0 || cy <= 0)
					continue;

				if (cy < my || cy == my && cx < mx) {
					my = cy;
					mx = cx;
				}
			}
			sb.append(mx + " " + my + "\n");
		}
		System.out.println(sb);
	}
}

