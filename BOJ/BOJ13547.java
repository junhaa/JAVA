import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #23836 어떤 우유의 배달목록 (Hard)
public class Main {

	static ArrayList<Integer>[] adj, child;
	static long[] tree;
	static int[] lazy, tSize, dfsn, cn, cHead, cTail, parent, depth;
	static int C, dcnt, size = 2;
	
	static void DFS1(int cur, int pre) {
		tSize[cur] = 1;
		for(int next : adj[cur]) {
			if(next != pre) {
				DFS1(next, cur);
				child[cur].add(next);
				tSize[cur] += tSize[next];
			}
		}
	}
	
	static void DFS2(int cur, int pre, int curDepth) {
		int u = dfsn[cur] = dcnt++;
		depth[u] = curDepth;
		cn[u] = C;
		if(cHead[C] < 0) cHead[C] = u;
		cTail[C] = u;
		
		if(child[cur].isEmpty()) {
			C++;
			return;
		}
		
		int chained = child[cur].get(0);
		for(int i = 1 ; i < child[cur].size(); i ++) {
			int next = child[cur].get(i);
			if(tSize[chained] < tSize[next]) chained = next;
		}
		
		DFS2(chained, cur, curDepth + 1);
		
		for(int next : child[cur]) {
			if(next != chained) DFS2(next, cur, curDepth + 1);
			int v = dfsn[next];
			parent[v] = u;
		}
	}
	
	static long getSumT(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeNum, nodeL, nodeR);
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return getSumT(L, R, nodeNum * 2, nodeL, mid) + getSumT(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static void propagate(int nodeNum, int nodeL, int nodeR) {
		if(lazy[nodeNum] != 0) {
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			tree[nodeNum] += (nodeR - nodeL + 1) * lazy[nodeNum];
			lazy[nodeNum] = 0;
		}
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagate(nodeNum, nodeL, nodeR);
		
		if(R < nodeL || nodeR < L) return;
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
	
	static long getSum(int u, int v) {
		long res = 0;
		u = dfsn[u];
		v = dfsn[v];
		
		if(cn[u] != cn[v]) {
			while(true) {
				int uHead = cHead[cn[u]];
				int vHead = cHead[cn[v]];
				if(depth[u] > depth[v]) {
					res += getSumT(uHead, u, 1, 0, size / 2 - 1);
					u = parent[uHead];
				}
				else {
					res += getSumT(vHead, v, 1, 0, size / 2 - 1);
					v = parent[vHead];
				}
				if(cn[u] == cn[v]) break;
			}
		}
		res += getSumT(Math.min(u, v), Math.max(u, v), 1, 0, size / 2 - 1);
		return res;
	}
	
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		
		tree = new long[size];
		lazy = new int[size];
		adj = new ArrayList[N];
		child = new ArrayList[N];
		tSize = new int[N];
		dfsn = new int[N];
		cn  = new int[N];
		cHead = new int[N];
		cTail = new int[N];
		parent = new int[N];
		depth = new int[N];
		
		for(int i = 0 ; i < N ; i ++){
			adj[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < N - 1 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			adj[start].add(end);
			adj[end].add(start);
		}
		
		DFS1(0, -1);
		
		parent[0] = -1;
		Arrays.fill(cHead, -1);
		Arrays.fill(cTail, -1);
		
		DFS2(0, -1, 0);
		
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				T.update(dfsn[start],dfsn[end], 1, 0, size / 2 - 1, 1);
				if(end + 1 < N) T.update(dfsn[end + 1], dfsn[end + 1], 1, 0, size / 2 - 1, -(end - start + 1));
			}
			else {
				sb.append(T.getSum(dfsn[0], dfsn[Integer.parseInt(st.nextToken()) - 1]) + "\n");
			}
		}
		System.out.println(sb);
	}
}
