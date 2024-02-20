import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #1067 이동
public class Main {
	private static final long w = 3;
	private static final long mod = 998244353;

	static long pow(long a, long b) {
		long res = 1;
		while (b > 0) {
			if ((b & 1) > 0)
				res = res * a % mod;
			b >>= 1;
			a = a * a % mod;
		}
		return res;
	}

	static void NTT(long[] arr, boolean inv) {
		int N = arr.length;
		long x = pow(w, (mod - 1) / N);
		if (inv)
			x = pow(x, mod - 2);

		long[] root = new long[N];
		root[0] = 1;
		for (int i = 1; i < N; i++) {
			root[i] = (root[i - 1] * x) % mod;
		}

		for (int i = 0; i < N; i++) {
			int reverse = 0;
			for (int j = 1, target = i; j < N; j <<= 1, target >>= 1) {
				reverse = (reverse << 1) + (target & 1);
			}
			if (i < reverse) {
				long tmp = arr[i];
				arr[i] = arr[reverse];
				arr[reverse] = tmp;
			}
		}

		for (int i = 2; i <= N; i <<= 1) {
			int step = N / i;
			for (int j = 0; j < N; j += i) {
				for (int k = 0; k < (i >> 1); k++) {
					long u = arr[j | k];
					long v = arr[j | k | i >> 1] * root[step * k] % mod;
					arr[j | k] = (u + v) % mod;
					arr[j | k | i >> 1] = (u - v) % mod;
					if (arr[j | k | i >> 1] < 0)
						arr[j | k | i >> 1] += mod;
				}
			}
		}
		if (inv) {
			long t = pow(N, mod - 2);
			for (int i = 0; i < N; i++) {
				arr[i] = arr[i] * t % mod;
			}
		}
	}

	static long[] multiply(long[] a, long[] b, int N) {
		NTT(a, false);
		NTT(b, false);

		int size = a.length;
		long[] c = new long[size];
		for (int i = 0; i < size; i++) {
			c[i] = a[i] * b[i] % mod;
		}

		NTT(c, true);

		for (int i = 0; i < N - 1; i++) {
			c[N + i] += c[i];
		}
		return c;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		int size = 2;
		while (size < N << 1)
			size <<= 1;

		long[] a = new long[size];
		long[] b = new long[size];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		// reverse
		for (int i = 0; i < N / 2; i++) {
			long tmp = a[N - 1 - i];
			a[N - 1 - i] = a[i];
			a[i] = tmp;
		}

		long[] ret = multiply(a, b, N);

		long max = Long.MIN_VALUE;

		for (int i = N - 1; i < 2 * N - 1; i++) {
			max = Math.max(max, ret[i]);
		}
		System.out.println(max);
	}
}
