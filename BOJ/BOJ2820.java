import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #2820 자동차 공장
public class Main {
	
	static int[] idxArr, treeIdxArr, startPay;
	static long[] tree;
	static int size = 2, idx = 0;
	static long[] lazy;
	static ArrayList<Integer>[] list;
	
	static void DFS(int i) {
		idx ++;
		treeIdxArr[i] = idx - 1;
		int cur = idx;
		for(int next : list[i]) {
			DFS(next);
		}
		idxArr[i] = idx - cur;
	}
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void propagate(int nodeNum, int nodeL, int nodeR) {
		if(lazy[nodeNum] != 0) {
			tree[nodeNum] += (nodeR - nodeL + 1) * lazy[nodeNum];
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			lazy[nodeNum] = 0;
		}
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagate(nodeNum, nodeL, nodeR);
		
		if(R < nodeL || nodeR < L) {
			return;
		}
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] += val;
			propagate(nodeNum, nodeL, nodeR);
			return;
		}
		
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid, val);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		tree[nodeNum] = tree[nodeNum * 2] + tree[nodeNum * 2 + 1];
	}
	
	static long findNum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeNum, nodeL, nodeR);
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		
		int mid = (nodeL + nodeR) / 2;
		return findNum(L, R, nodeNum * 2, nodeL, mid) + findNum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		idxArr = new int[N];
		list = new ArrayList[N];
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		lazy = new long[size];
		startPay = new int[N];
		treeIdxArr = new int[N];
		startPay[0] = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) list[i] = new ArrayList<>();
		for(int i = 1 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			startPay[i] = Integer.parseInt(st.nextToken());
			list[Integer.parseInt(st.nextToken()) - 1].add(i);
		}
		T.DFS(0);
		for(int i = 0 ; i < N ; i ++) {
			tree[treeIdxArr[i] + size / 2] = startPay[i];
		}
		T.construct();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("p")) {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				if(idxArr[tmp] != 0) {
					T.update(treeIdxArr[tmp] + 1, treeIdxArr[tmp] + idxArr[tmp], 1, 0, size / 2 - 1, Integer.parseInt(st.nextToken()));
				}
			}
			else {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				sb.append(T.findNum(treeIdxArr[tmp], treeIdxArr[tmp], 1, 0, size / 2 - 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
