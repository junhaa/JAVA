import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #22289 큰 수 곱셈 (2)
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

	static void DFT(long[] arr, boolean inv) {
		int len = arr.length;

		long x = pow(w, (mod - 1) / len);
		if (inv)
			x = pow(x, mod - 2);

		long[] root = new long[len];
		root[0] = 1;

		for (int i = 1; i < len; i++) {
			root[i] = (int)((root[i - 1] * x) % mod);
		}

		// 메모리 최적화를 위한 정렬
		for (int i = 0; i < len; i++) {
			int reverse = 0;
			//한 칸씩 비트를 이동시키면서 swap 위치 탐색
			for (int j = 1, target = i; j < len; j <<= 1, target >>= 1) {
				reverse = (reverse << 1) + (target & 1);
			}
			if (i < reverse) {
				// swap
				long tmp = arr[i];
				arr[i] = arr[reverse];
				arr[reverse] = tmp;
			}
		}

		for (int i = 2; i <= len; i <<= 1) {
			int step = len / i;
			for (int j = 0; j < len; j += i) {
				for (int k = 0; k < (i >> 1); k++) {
					long u = arr[j | k];
					long v = arr[j | k | i >> 1] * root[step * k] % mod;
					arr[j | k] = ((u + v) % mod);
					arr[j | k | i >> 1] = ((u - v) % mod) < 0 ? ((u - v) % mod + mod) : ((u - v) % mod);
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

	static long[] multiply(long[] A, long[] B, int size) {
		DFT(A, false);
		DFT(B, false);

		long[] C = new long[size];

		for (int i = 0; i < size; i++)
			C[i] = A[i] * B[i] % mod;
		//IDFT
		DFT(C, true);
		return C;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		String strA = st.nextToken();
		String strB = st.nextToken();

		int size = 2;
		// zero - padding
		while (size < strA.length() + strB.length())
			size <<= 1;

		long[] A = new long[size];
		long[] B = new long[size];

		for (int i = 0; i < strA.length(); i++) {
			A[i] = strA.charAt(i) - '0';
		}

		for (int i = 0; i < strB.length(); i++) {
			B[i] = strB.charAt(i) - '0';
		}

		long[] C = multiply(A, B, size);

		int answer[] = new int[strA.length() + strB.length() + 1];

		StringBuilder sb = new StringBuilder();
		for (int idx = strA.length() + strB.length() - 2; idx >= 0; idx--) {
			int aidx = answer.length - 1 - (strA.length() + strB.length() - 2 - idx);
			answer[aidx] += C[idx];
			answer[aidx - 1] += answer[aidx] / 10;
			answer[aidx] %= 10;
		}

		int idx = 0;

		while (answer[idx] == 0) {
			idx++;
			if (idx == answer.length) {
				System.out.println(0);
				return;
			}
		}

		for (; idx < answer.length; idx++) {
			sb.append(answer[idx]);
		}
		System.out.println(sb);
	}
}
