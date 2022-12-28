import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #15782 Calculate! 2
public class Main {

	static ArrayList<Integer>[] edge;
	static long[] tree, lazy;
	static int[] startIdx, endIdx;
	static int idx = -1, size = 2;
	
	
	static void DFS(int cur) {
		startIdx[cur] = ++idx;
		for(int next : edge[cur]) { 
			if(startIdx[next] == -1) DFS(next);
		}
		endIdx[cur] = idx;
	}
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] ^ tree[i * 2 + 1];
		}
	}
	
	static void propagate(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] ^= lazy[nodeNum];
				lazy[nodeNum * 2 + 1] ^= lazy[nodeNum];
			}
			int length = nodeR - nodeL + 1;
			if(length % 2 == 1) {
				tree[nodeNum] ^= lazy[nodeNum];
			}
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
		tree[nodeNum] = tree[nodeNum * 2] ^ tree[nodeNum * 2 + 1];
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) ^ findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		lazy = new long[size];
		edge = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) edge[i] = new ArrayList<>();
		startIdx = new int[N];
		endIdx = new int[N];
		Arrays.fill(startIdx, -1);
		for(int i = 0 ; i < N - 1 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			edge[start].add(end);
			edge[end].add(start);
		}
		T.DFS(0);
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			tree[startIdx[i] + size / 2] = Integer.parseInt(st.nextToken());
		}
		T.construct();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				sb.append(findSum(startIdx[tmp], endIdx[tmp], 1, 0, size / 2 - 1) + "\n");
			}
			else {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				T.update(startIdx[tmp], endIdx[tmp], 1, 0, size / 2 - 1, Integer.parseInt(st.nextToken()));
			}
		}
		System.out.println(sb);
	}
}
