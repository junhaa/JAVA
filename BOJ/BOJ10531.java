import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #10531 Golf Bot
public class Main {
	private static final long w = 3;
	private static final long mod = 998244353;
	private static final int LEN = 200_000;

	static long pow(long a, long b) {
		long res = 1;
		while (b > 0) {
			if ((b & 1) > 0) {
				res = res * a % mod;
			}
			b >>= 1;
			a = a * a % mod;
		}
		return res;
	}

	static void NTT(long[] arr, boolean inv) {
		int len = arr.length;

		long x = pow(w, (mod - 1) / len);
		if (inv)
			x = pow(x, mod - 2);

		int[] root = new int[len];
		root[0] = 1;
		for (int i = 1; i < len; i++) {
			root[i] = (int)((root[i - 1] * x) % mod);
		}

		for (int i = 0; i < len; i++) {
			int rev = 0;
			for (int j = 1, tg = i; j < len; j <<= 1, tg >>= 1) {
				rev = (rev << 1) + (tg & 1);
			}
			if (i < rev) {
				long tmp = arr[i];
				arr[i] = arr[rev];
				arr[rev] = tmp;
			}
		}

		for (int i = 2; i <= len; i <<= 1) {
			int step = len / i;
			for (int j = 0; j < len; j += i) {
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
			long t = pow(len, mod - 2);
			for (int i = 0; i < len; i++) {
				arr[i] = arr[i] * t % mod;
			}
		}
	}

	static void mul(long[] arr) {
		NTT(arr, false);

		int len = arr.length;

		for (int i = 0; i < len; i++) {
			arr[i] = (arr[i] * arr[i]) % mod;
		}

		NTT(arr, true);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int size = 2;
		while (size < LEN << 1) {
			size <<= 1;
		}

		long[] arr = new long[size];
		arr[0] = 1;

		for (int i = 0; i < N; i++) {
			int idx = Integer.parseInt(br.readLine());
			arr[idx]++;
		}

		mul(arr);

		int M = Integer.parseInt(br.readLine());
		int answer = 0;
		for (int i = 0; i < M; i++) {
			int cur = Integer.parseInt(br.readLine());
			if (arr[cur] != 0)
				answer++;
		}

		System.out.println(answer);
	}
}
