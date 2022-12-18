import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #14268 회사 문화 2
public class Main {

	static int[] tree, lazy, startIdx, endIdx;
	static int size = 2;
	static ArrayList<Integer>[] list;
	static int idx = -1;
	
	static void DFS(int i) {
		startIdx[i] = ++idx;
		for(int next : list[i]) {
			DFS(next);
		}
		endIdx[i] = idx;
	}
	
	static void propagate(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			tree[nodeNum] += (nodeR - nodeL + 1) * lazy[nodeNum];
			lazy[nodeNum] = 0;
		}
	}
	
	static int findNum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findNum(L, R, nodeNum * 2, nodeL, mid) + findNum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagate(nodeL, nodeR, nodeNum);
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) { 
			lazy[nodeNum] += val;
			propagate(nodeL, nodeR, nodeNum);
			return;
		}
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid, val);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		tree[nodeNum] = tree[nodeNum * 2] + tree[nodeNum * 2 + 1];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
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
		list = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) list[i] = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int i = 1 ; i < N ; i ++) {
			list[Integer.parseInt(st.nextToken()) - 1].add(i);
		}
		T.DFS(0);
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());
				T.update(startIdx[tmp], endIdx[tmp], 1, 0, size / 2 - 1, cost);
			}
			else {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				sb.append(T.findNum(startIdx[tmp], startIdx[tmp], 1, 0, size / 2 - 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
