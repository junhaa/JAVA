import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #3895 그리고 하나가 남았다
public class Main {
	static int[] tree;
	static int size = 2;
	static StringBuilder sb = new StringBuilder();

	static void construct() {
		for (int i = size / 2 - 1; i > 0; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}

	static void update(int i, int val) {
		tree[i] = val;
		while (i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}

	static void trace(int des, int nodeNum) {
		if (nodeNum >= size / 2) {
			if (tree[1] == 1) {
				sb.append((nodeNum - size / 2 + 1) + "\n");
			}
			update(nodeNum, 0);
			// System.out.print(nodeNum - size / 2 + " ");
			return;
		}
		if (tree[nodeNum * 2] >= des) {
			trace(des, nodeNum * 2);
		} else {
			trace(des - tree[nodeNum * 2], nodeNum * 2 + 1);
		}
	}

	static void findNum(int K, int M) {
		update(M + size / 2, 0);
		int node = M;
		while (true) {
			if (tree[1] == 0)
				break;
			node += K;
			if (node % tree[1] == 0) {
				node = tree[1];
			} else if (node > tree[1]) {
				node %= tree[1];
			}
			trace(node, 1);
			node--;
		}
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken()) - 1; // 0 - based
			if (N == 0 && K == 0 && M == -1)
				break;
			size = 2;

			while (size < N << 1) {
				size <<= 1;
			}
			tree = new int[size];

			for (int i = 0; i < N; i++) {
				tree[size / 2 + i] = 1;
			}

			T.construct();
			update(M + size / 2, 0);
			T.findNum(K, M);
		}
		System.out.println(sb);
	}
}
