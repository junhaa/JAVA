import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #12837 가계부 (Hard)
public class Main {

	static long[] tree;
	static int size = 2;
	
	static void update(int idx, int val) {
		idx += size / 2;
		tree[idx] += val;
		while(idx > 1) {
			idx /= 2; 
			tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
		}
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		int a, b, c;
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				T.update(b - 1, c);
			}
			else {
				sb.append(T.findSum(b - 1, c - 1, 1, 0, size / 2 - 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
