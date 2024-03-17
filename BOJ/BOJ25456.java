import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #25456 궁금한 시프트
public class Main {

	private static final long w = 3;
	private static final long mod = 998244353;
	static int size = 2;

	static long pow(long a, long b) {
		long res = 1;
		while (b > 0) {
			if ((b & 1) == 1) {
				res = res * a % mod;
			}
			b >>= 1;
			a = a * a % mod;
		}
		return res;
	}

	static void NTT(long[] arr, boolean inv) {
		long x = pow(w, (mod - 1) / size);
		if (inv)
			x = pow(x, mod - 2);

		int[] root = new int[size];
		root[0] = 1;
		for (int i = 1; i < size; i++) {
			root[i] = (int)((root[i - 1] * x) % mod);
		}

		for (int i = 0; i < size; i++) {
			int rev = 0;
			for (int j = 1, tg = i; j < size; j <<= 1, tg >>= 1) {
				rev = (rev << 1) + (tg & 1);
			}
			if (i < rev) {
				long tmp = arr[i];
				arr[i] = arr[rev];
				arr[rev] = tmp;
			}
		}

		for (int i = 2; i <= size; i <<= 1) {
			int step = size / i;
			for (int j = 0; j < size; j += i) {
				for (int k = 0; k < (i >> 1); k++) {
					long u = arr[j | k];
					long v = arr[j | k | i >> 1] * root[step * k] % mod;
					arr[j | k] = (u + v) % mod;
					arr[j | k | i >> 1] = (u - v) % mod;
					if (arr[j | k | i >> 1] < 0) {
						arr[j | k | i >> 1] += mod;
					}
				}
			}
		}
		if (inv) {
			long t = pow(size, mod - 2);
			for (int i = 0; i < size; i++) {
				arr[i] = arr[i] * t % mod;
			}
		}
	}

	static void mul(long[] a, long[] b) {
		NTT(a, false);
		NTT(b, false);
		for (int i = 0; i < size; i++) {
			a[i] = a[i] * b[i] % mod;
		}

		NTT(a, true);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strA = br.readLine();
		String strB = br.readLine();
		int len = strA.length();

		while (size < len << 1) {
			size <<= 1;
		}

		long[] arrA = new long[size];
		long[] arrB = new long[size];
		for (int i = 0; i < len; i++) {
			if (strA.charAt(i) == '1') {
				arrA[i] = 1;
			}
			if (strB.charAt(i) == '1') {
				arrB[i] = 1;
			}
		}

		mul(arrA, arrB);
		long max = Long.MIN_VALUE;

		for (int i = 0; i < size; i++) {
			max = Math.max(max, arrA[i]);
		}

		System.out.println(max);
	}
}
