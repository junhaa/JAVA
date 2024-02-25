import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #5051 피타고라스의 정리
public class Main {
	private static final long w = 3;
	private static final long mod = 998244353;

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
		long[] c = new long[len];

		for (int i = 0; i < len; i++) {
			arr[i] = (arr[i] * arr[i]) % mod;
		}

		NTT(arr, true);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int size = 2;
		while (size < N << 1) {
			size <<= 1;
		}

		long[] arr = new long[size];
		int[] check = new int[size];

		for (int i = 1; i < N; i++) {
			int idx = (int)((long)i * i % N);
			check[idx]++;
			arr[idx]++;
		}

		mul(arr);

		for (int i = 0; i < size; i++) {
			if (arr[i] != 0) {
				long sum = 0;
				// (i / 2) + (i / 2)로 만들어진 경우의 수, a <= b를 만족해야 함
				if (i % 2 == 0 && check[i / 2] != 0) {
					int num = check[i / 2];
					arr[i] -= (long)num * num;
					// num + num Combi 2 => k^2/2 + k/2 로 가능하지만 double형으로 선언해야 함
					sum += num + (((long)num * (num - 1)) / 2);
				}
				arr[i] /= 2;
				arr[i] += sum;
			}
		}

		for (int i = N; i < size; i++) {
			arr[i % N] += arr[i];
			arr[i] = 0;
		}
		long ans = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] != 0 && check[i] != 0) {
				ans += arr[i] * check[i];
			}
		}
		System.out.println(ans);
	}
}
