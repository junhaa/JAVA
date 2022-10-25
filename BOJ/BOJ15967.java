import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #15967 에바쿰
public class Main {
	static long[] tree, lazy;
	static int size = 2;

	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] = val;
			propagate(nodeL, nodeR, nodeNum);
			return;
		}
		
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid, val);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		tree[nodeNum] = tree[nodeNum * 2] + tree[nodeNum * 2 + 1];
	}
	
	static void construct() {
		for(int i = size / 2 - 1;  i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void propagate(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			tree[nodeNum] += (nodeR - nodeL + 1) * lazy[nodeNum];
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			lazy[nodeNum] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q1 = Integer.parseInt(st.nextToken());
		int Q2 = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		lazy = new long[size];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			tree[size / 2 + i] = Integer.parseInt(st.nextToken());
		}
		T.construct();
		for(int i = 0 ; i < Q1 + Q2 ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) { // 1 query
				sb.append(findSum(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1, 0, size / 2 - 1) + "\n");
			}
			else {
				update(Integer.parseInt(st.nextToken()) - 1,Integer.parseInt(st.nextToken()) - 1, 1, 0, size / 2 - 1, Integer.parseInt(st.nextToken()));
			}
		}
		System.out.println(sb);
	}
}
