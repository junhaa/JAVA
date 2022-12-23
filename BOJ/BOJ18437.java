import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #18437 회사 문화 5
public class Main {

	static int[] tree, lazy, startIdx, endIdx;
	static int size = 2, idx = -1;
	static ArrayList<Integer>[] list;
	
	static void DFS(int i) {
		startIdx[i] = ++idx;
		for(int next : list[i]) DFS(next);
		endIdx[i] = idx;
	}
	
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void propagate(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] = lazy[nodeNum];
				lazy[nodeNum * 2 + 1] = lazy[nodeNum];
			}
			tree[nodeNum] = lazy[nodeNum] == 1 ? (nodeR - nodeL + 1) : 0;
			lazy[nodeNum] = 0;
		}
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
	
	static int findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		list = new ArrayList[N];
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		lazy = new int[size];
		startIdx = new int[N];
		endIdx = new int[N];
		for(int i = 0 ; i < N ; i ++) list[i] = new ArrayList<>();
		
		for(int i = 1 ; i < N ; i ++) {
			list[Integer.parseInt(st.nextToken()) - 1].add(i);
		}
		T.DFS(0);
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			tree[startIdx[i] + size / 2] = 1;
		}
		
		T.construct();
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int menu = Integer.parseInt(st.nextToken());
			if(menu == 1) {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				if(startIdx[tmp] != endIdx[tmp]) {
					T.update(startIdx[tmp] + 1, endIdx[tmp], 1, 0, size / 2 - 1, 1);
				}
			}
			else if(menu == 2) {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				if(startIdx[tmp] != endIdx[tmp]) {
					T.update(startIdx[tmp] + 1, endIdx[tmp], 1, 0, size / 2 - 1,  -1);
				}
			}
			else {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				sb.append(T.findSum(startIdx[tmp] + 1, endIdx[tmp], 1, 0, size / 2 - 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
