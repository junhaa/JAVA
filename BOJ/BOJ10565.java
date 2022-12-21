import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #10565 Salary Inequity
class Node{
	int min;
	int max;
	public Node(int min, int max) {
		this.min = min;
		this.max = max;
	}
}
public class Main {
	
	static Node[] tree;
	static int[] startIdx, endIdx, lazy;
	static ArrayList<Integer>[] list;
	static int size, idx;
	
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
			tree[nodeNum].max += lazy[nodeNum];
			tree[nodeNum].min += lazy[nodeNum];
			lazy[nodeNum] = 0;
		}
	}
	
	static int findMax(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
				
		if(R < nodeL || nodeR < L) return (int)-1e9;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum].max;
		int mid = (nodeL + nodeR) / 2;
		return Math.max(findMax(L, R, nodeNum * 2, nodeL, mid),findMax(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}

	static int findMin(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return (int)1e9;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum].min;
		int mid = (nodeL + nodeR) / 2;
		return Math.min(findMin(L, R, nodeNum * 2, nodeL, mid),findMin(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
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
		tree[nodeNum].max = Math.max(tree[nodeNum * 2].max, tree[nodeNum * 2 + 1].max);
		tree[nodeNum].min = Math.min(tree[nodeNum * 2].min, tree[nodeNum * 2 + 1].min);
	}
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = new Node(Math.min(tree[i * 2].min, tree[i * 2 + 1].min), Math.max(tree[i * 2].max, tree[i * 2 + 1].max));
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(test-- > 0) {
			size = 2;
			idx = -1;
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			list = new ArrayList[N];
			for(int i = 0 ; i < N ; i ++) list[i] = new ArrayList<>();
			while(true) {
				if(size >= N) {
					size *= 2;
					break;
				}
				size *= 2;
			}
			tree = new Node[size];
			startIdx = new int[N];
			endIdx = new int[N];
			lazy = new int[size];
			int min = Integer.MAX_VALUE;
			for(int i = 1 ; i < N ; i ++) {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				list[tmp].add(i);
				min = Math.min(min, tmp);
			}
			st = new StringTokenizer(br.readLine());
			DFS(min);
			for(int i = 0 ; i < size / 2; i ++) {
				if(i < N) {
					int val = Integer.parseInt(st.nextToken());
					tree[startIdx[i] + size / 2] = new Node(val, val);
				}
				else {
					tree[i + size / 2] = new Node((int)1e9, (int)-1e9);
				}
			}
			T.construct();
			int Q = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < Q ; i ++) {
				st = new StringTokenizer(br.readLine());
				if(st.nextToken().equals("Q")) {
					int tmp = Integer.parseInt(st.nextToken()) - 1;
					int maxN = T.findMax(startIdx[tmp], endIdx[tmp], 1, 0, size / 2 - 1);
					int minN = T.findMin(startIdx[tmp], endIdx[tmp], 1, 0, size / 2 - 1);
					sb.append(maxN - minN).append("\n");
				}
				else {
					int tmp = Integer.parseInt(st.nextToken()) - 1;
					T.update(startIdx[tmp], endIdx[tmp], 1, 0, size / 2 - 1, Integer.parseInt(st.nextToken()));
				}
			}
		}
		System.out.println(sb);
	}
}
