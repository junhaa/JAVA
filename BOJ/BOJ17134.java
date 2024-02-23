import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
// BOJ #17134 르모앙의 추측
public class Main {

	private static final long w = 3;
	private static final long mod = 998244353;
	private static final int LEN = 1_000_000;

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

	static long[] multiply(long[] a, long[] b) {
		NTT(a, false);
		NTT(b, false);

		int size = a.length;
		long[] c = new long[size];
		for (int i = 0; i < size; i++) {
			c[i] = a[i] * b[i] % mod;
		}

		NTT(c, true);

		return c;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = 2;
		while (size < LEN << 1) {
			size <<= 1;
		}

		long[] a = new long[size]; // 소수
		long[] b = new long[size]; // 짝수로 된 세미소스
		Arrays.fill(a, 1);
		a[0] = a[1] = 0;

		int len = (int)Math.sqrt(size);

		for (int i = 2; i <= len; i++) {
			for (int j = i * i; j <= LEN; j += i) {
				a[j] = 0;
			}
		}

		for (int i = LEN; i < size; i++) {
			a[i] = 0;
		}

		for (int i = 0; i <= LEN >> 1; i++) {
			if (a[i] == 1) {
				b[i * 2] = 1;
			}
		}

		for (int i = LEN; i < size; i++) {
			if (a[i] == 1 || b[i] == 1) {
				System.out.println("ERROR");
			}
		}

		a[2] = 0; // a는 홀수로 된 소수여야 함

		long[] ret = multiply(a, b);
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int cur = Integer.parseInt(br.readLine());
			sb.append(ret[cur] + "\n");
		}
		System.out.println(sb);
	}
}
