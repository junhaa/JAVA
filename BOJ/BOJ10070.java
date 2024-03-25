import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #10070 벽
public class Main {
	static int N, K, size = 2;
	static int[] min, max, answer;

	static void propagate(int nodeL, int nodeR, int nodeNum) {
		if (nodeL != nodeR) {
			if (max[nodeNum] != 0) {
				for (int i = nodeNum << 1; i <= (nodeNum << 1 | 1); i++) {
					max[i] = Math.max(max[i], max[nodeNum]);
					min[i] = Math.max(min[i], max[nodeNum]);
				}
			}
			if (min[nodeNum] != Integer.MAX_VALUE) {
				for (int i = nodeNum << 1; i <= (nodeNum << 1 | 1); i++) {
					max[i] = Math.min(max[i], min[nodeNum]);
					min[i] = Math.min(min[i], min[nodeNum]);
				}
			}
			max[nodeNum] = 0;
			min[nodeNum] = Integer.MAX_VALUE;
		}
	}

	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val, int op) {
		propagate(nodeL, nodeR, nodeNum);

		if (R < nodeL || nodeR < L)
			return;
		if (L <= nodeL && nodeR <= R) {
			if (op == 1) {
				if (nodeL == nodeR) {
					max[nodeNum] = Math.max(val, max[nodeNum]);
					min[nodeNum] = Math.max(val, min[nodeNum]);
				} else
					max[nodeNum] = val;
			} else {
				if (nodeL == nodeR) {
					max[nodeNum] = Math.min(val, max[nodeNum]);
					min[nodeNum] = Math.min(val, min[nodeNum]);
				} else
					min[nodeNum] = val;
			}
			propagate(nodeL, nodeR, nodeNum);
			return;
		}

		int mid = (nodeL + nodeR) >> 1;
		update(L, R, nodeNum << 1, nodeL, mid, val, op);
		update(L, R, nodeNum << 1 | 1, mid + 1, nodeR, val, op);
	}

	static int getVal(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);

		if (R < nodeL || nodeR < L)
			return 0;
		if (L <= nodeL && nodeR <= R) {
			return Math.min(min[nodeNum], max[nodeNum]);
		}

		int mid = (nodeL + nodeR) >> 1;
		return getVal(L, R, nodeNum << 1, nodeL, mid) + getVal(L, R, nodeNum << 1 | 1, mid + 1, nodeR);
	}

	static int getVal(int idx) {
		return getVal(idx, idx, 1, 0, size / 2 - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		while (size < N << 1) {
			size <<= 1;
		}

		min = new int[size];
		max = new int[size];
		answer = new int[size / 2];
		Arrays.fill(min, Integer.MAX_VALUE);

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			update(L, R, 1, 0, size / 2 - 1, height, op);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(getVal(i) + "\n");
		}
		System.out.println(sb);
	}
}
