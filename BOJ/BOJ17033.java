import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #17033 Cow Land

class SegTree{
	int size = 2;
	long[] tree;
	
	public void init(int N) {
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
	}
	
	public void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] ^ tree[i * 2 + 1];
		}
	}
	
	public void update(int i, int val) {
		i += size / 2;
		tree[i] = val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] ^ tree[i * 2 + 1];
		}
	}
	
	public long getXOR(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return getXOR(L, R, nodeNum * 2, nodeL, mid) ^ getXOR(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
}

class HLD{
	int C, dcnt;
	int[] A, tSize, dfsn;
	ArrayList<Integer>[] adj, child;
	SegTree seg = new SegTree();
	
	int[] parent, cn, cHead, cTail, eVertex, depth;
	
	public StringBuilder init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		A = new int[N];
		tSize = new int[N];
		dfsn = new int[N];
		parent = new int[N];
		cn = new int[N];
		cHead = new int[N];
		cTail = new int[N];
		eVertex = new int[N];
		depth = new int[N];
		adj = new ArrayList[N];
		child = new ArrayList[N];
		seg.init(N);
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			A[i] = Integer.parseInt(st.nextToken());
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
		
		for(int i = 0 ; i < N ; i ++) {
			seg.tree[seg.size / 2 + dfsn[i]] = A[i];
		}
		seg.construct();
		
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1){
				seg.update(dfsn[Integer.parseInt(st.nextToken()) - 1], Integer.parseInt(st.nextToken()));
			}
			else {
				sb.append(query(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1) + "\n");
			}
		}
		return sb;
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
		int u = dfsn[cur] = dcnt ++;
		
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
			if(tSize[chained] < tSize[next]) chained = next;
		}
		
		DFS2(chained, cur, curDepth + 1);
		for(int next : child[cur]) {
			if(next != chained) DFS2(next, cur, curDepth + 1);
			int v = dfsn[next];
			parent[v] = u;
		}
	}
	
	public long query(int u, int v) {
		long res = 0;
		u = dfsn[u];
		v = dfsn[v];
		
		if(cn[u] != cn[v]) {
			while(true) {
				int uHead = cHead[cn[u]];
				int vHead = cHead[cn[v]];
				if(depth[uHead] > depth[vHead]) {
					res ^= seg.getXOR(uHead, u, 1, 0, seg.size / 2 - 1);
					u = parent[uHead];
				}
				else {
					res ^= seg.getXOR(vHead, v, 1, 0, seg.size / 2 - 1);
					v = parent[vHead];
				}
				if(cn[u] == cn[v]) break;
			}
		}
		res ^= seg.getXOR(Math.min(u, v), Math.max(u, v), 1, 0, seg.size / 2 - 1);
		return res;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		HLD hld = new HLD();
		System.out.println(hld.init());
	}
}
