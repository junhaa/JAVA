import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #5916 농장 관리

class SegTree {
	int size = 2;
	long[] tree;
	int[] lazy;

	public void init(int N) {
		while (true) {
			if (size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		lazy = new int[size];
	}

	public void propagate(int nodeNum, int nodeL, int nodeR) {
		if (lazy[nodeNum] != 0) {
			if (nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			tree[nodeNum] += (nodeR - nodeL + 1) * lazy[nodeNum];
			lazy[nodeNum] = 0;
		}
	}

	public void update(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeNum, nodeL, nodeR);
		if (R < nodeL || nodeR < L)
			return;
		if (L <= nodeL && nodeR <= R) {
			lazy[nodeNum]++;
			propagate(nodeNum, nodeL, nodeR);
			return;
		}
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
		tree[nodeNum] = tree[nodeNum * 2] + tree[nodeNum * 2 + 1];
	}

	public long getSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeNum, nodeL, nodeR);

		if (R < nodeL || nodeR < L)
			return 0;
		if (L <= nodeL && nodeR <= R)
			return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return getSum(L, R, nodeNum * 2, nodeL, mid) + getSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}

}

class HLD {
	private int C, dcnt;
	private int[] dfsn, tSize;
	private ArrayList<Integer>[] adj, child;
	private SegTree seg;
	
	private int[] cHead, cTail, parent, cn, eVertex, depth;
	
	public StringBuilder init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		seg = new SegTree();
		seg.init(N);
		
		dfsn = new int[N];
		tSize = new int[N];
		adj = new ArrayList[N];
		child = new ArrayList[N];
		
		cHead = new int[N];
		cTail = new int[N];
		parent = new int[N];
		cn = new int[N];
		eVertex = new int[N];
		depth = new int[N];
		
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
		}
		
		
		for(int i = 1 ; i < N ; i ++) {
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
		
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("P")) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				update(a, b);
			}
			else {
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int idx = Math.max(dfsn[a], dfsn[b]);
				sb.append(seg.getSum(idx, idx, 1, 0, seg.size / 2 - 1) + "\n");
			}
		}
		
		return sb;
	}
	
	public long getSum(int u, int v) {
		int res = 0;
		u = dfsn[u];
		v = dfsn[v];
		
		if(cn[u] != cn[v]) {
			while(true) {
				int uHead = cHead[cn[u]];
				int vHead = cHead[cn[v]];
				if(depth[uHead] > depth[vHead]) {
					res += seg.getSum(uHead, u, 1, 0, seg.size / 2 - 1);
					u = parent[uHead];
				}
				else {
					res += seg.getSum(vHead, v, 1, 0, seg.size / 2 - 1);
					v = parent[vHead];
				}
				if(cn[u] == cn[v]) break;
			}
		}
		res += seg.getSum(Math.min(u, v) + 1, Math.max(u, v), 1, 0, seg.size / 2 - 1);
		return res;
	}
	
	public void update(int u, int v) {
		u = dfsn[u];
		v = dfsn[v];
		
		if(cn[u] != cn[v]) {
			while(true) {
				int uHead = cHead[cn[u]];
				int vHead = cHead[cn[v]];
				if(depth[uHead] > depth[vHead]) {
					seg.update(uHead, u, 1, 0, seg.size / 2 - 1);
					u = parent[uHead];
				}
				else {
					seg.update(vHead, v, 1, 0, seg.size / 2 - 1);
					v = parent[vHead];
				}
				if(cn[u] == cn[v]) break;
			}
		}
		seg.update(Math.min(u, v) + 1, Math.max(u, v), 1, 0, seg.size / 2 - 1);
	}
	
	public void DFS1(int cur, int pre) {
		tSize[cur] = 1;
		for(int next : adj[cur]) {
			if(next != pre) {
				DFS1(next, cur);
				child[cur].add(next);
				tSize[cur] += tSize[next];
			}
		}
	}

	public void DFS2(int cur, int pre, int curDepth) {
		int u = dfsn[cur] = dcnt++;
		cn[u] = C;
		depth[u] = curDepth;
		if(cHead[C] < 0) cHead[C] = u;
		cTail[C] = u;
		
		if(child[cur].isEmpty()) {
			C++;
			return;
		}
		
		int chained = child[cur].get(0);
		for(int i = 1 ; i < child[cur].size() ; i ++) {
			int next = child[cur].get(i);
			if(tSize[next] > tSize[chained]) chained = next;
		}
		
		DFS2(chained, cur, curDepth + 1);
		for(int next : child[cur]) {
			if(next != chained) DFS2(next, cur, curDepth + 1);
			int v = dfsn[next];
			parent[v] = u;
		}
		
 	}
	
	
}

public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		HLD hld = new HLD();
		System.out.println(hld.init());
	}
}
