import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #3653 영화 수집
public class Main {
	static int[] tree;
	static int[] idx;
	static int size ;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] = val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1]; 
		}
	}
	
	static int findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			size = 2;
			while(true) {
				if(size >= n + m) {
					size *= 2;
					break;
				}
				size *= 2;
			}
			tree = new int[size];
			idx = new int[n + 1];
			for(int i = 0 ; i < n ; i ++) {
				idx[i + 1] = n - i - 1;
				tree[size / 2 + i] = 1;
			}
			T.construct();
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= m ; i ++) {
				int num = Integer.parseInt(st.nextToken()); // 보려고 하는 영화의 번호
				sb.append(T.findSum(idx[num] + 1, m + n, 1, 0, size / 2 - 1) + " ");
				T.update(idx[num], 0);
				idx[num] = n + i - 1;
				T.update(idx[num], 1);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
