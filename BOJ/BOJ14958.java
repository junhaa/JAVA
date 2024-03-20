import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #14958 Rock Paper Scissors
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

	static long[] multiply(long[] a, long[] b) {
		NTT(a, false);
		NTT(b, false);

		int size = a.length;
		for (int i = 0; i < size; i++) {
			a[i] = a[i] * b[i] % mod;
		}

		NTT(a, true);

		return a;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int size = 1;
		while (size < N << 1) {
			size <<= 1;
		}
		String str1 = br.readLine();
		String str2 = br.readLine();

		long[] answer = new long[size];
		long[] a = new long[size];
		long[] b = new long[size];

		// str1 => R str2 => P
		for (int i = 0; i < N; i++) {
			if (str1.charAt(i) == 'R')
				a[i] = 1;
		}

		for (int i = M - 1; i >= 0; i--) {
			if (str2.charAt(i) == 'P')
				b[M - 1 - i] = 1;
		}

		multiply(a, b);

		for (int i = 0; i < size; i++) {
			answer[i] += a[i];
		}

		Arrays.fill(a, 0);
		Arrays.fill(b, 0);

		// str1 => S str2 => R
		for (int i = 0; i < N; i++) {
			if (str1.charAt(i) == 'S')
				a[i] = 1;
		}

		for (int i = M - 1; i >= 0; i--) {
			if (str2.charAt(i) == 'R')
				b[M - 1 - i] = 1;
		}

		multiply(a, b);

		for (int i = 0; i < size; i++) {
			answer[i] += a[i];
		}

		Arrays.fill(a, 0);
		Arrays.fill(b, 0);

		// str1 => P str2 => S
		for (int i = 0; i < N; i++) {
			if (str1.charAt(i) == 'P')
				a[i] = 1;
		}

		for (int i = M - 1; i >= 0; i--) {
			if (str2.charAt(i) == 'S')
				b[M - 1 - i] = 1;
		}

		multiply(a, b);

		for (int i = 0; i < size; i++) {
			answer[i] += a[i];
		}

		long max = Long.MIN_VALUE;

		for (int i = M - 1; i < size; i++) {
			max = Math.max(max, answer[i]);
		}
		System.out.println(max);
	}
}
