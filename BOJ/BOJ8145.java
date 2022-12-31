import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #8145 Megalopolis

class SegTree{
	int size = 2;
	int[] tree;
	
	public void init(int N) {
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		for(int i = size / 2 ; i < size ; i ++) {
			tree[i] = 1;
		}
		for(int i = size / 2 - 1; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	public void update(int i) {
		i += size / 2;
		tree[i] = 0;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	public int getSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return getSum(L, R, nodeNum * 2, nodeL, mid) + getSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
}

class HLD{
	int C, dcnt;
	int[] dfsn, tSize;
	ArrayList<Integer>[] adj, child;
	SegTree seg = new SegTree();
	
	int[] cn, cHead, cTail, parent, depth;
	
	public StringBuilder init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		seg.init(N);
		dfsn = new int[N];
		tSize = new int[N];
		cn = new int[N];
		cHead = new int[N];
		cTail = new int[N];
		parent = new int[N];
		depth = new int[N];
		adj = new ArrayList[N];
		child = new ArrayList[N];
		
		for(int i = 0 ; i < N ; i ++) {
			adj[i] =  new ArrayList<>();
			child[i] = new ArrayList<>();
		}
		
		for(int i = 1 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			adj[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
		}
		
		DFS1(0, -1);
		
		parent[0] = -1;
		Arrays.fill(cHead, -1);
		Arrays.fill(cTail, -1);
		
		DFS2(0, -1, 0);
		
		int M = Integer.parseInt(br.readLine()) + N - 1;
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("W")) {
				sb.append(getSum(0, Integer.parseInt(st.nextToken()) - 1) + "\n");
			}
			else {
				st.nextToken();
				seg.update(dfsn[Integer.parseInt(st.nextToken()) - 1]);
			}
		}
		return sb;
		
	}
	
	public void DFS1(int cur, int pre) {
		tSize[cur] = 1;
		for(int next : adj[cur]) {
			DFS1(next, cur);
			child[cur].add(next);
			tSize[cur] += tSize[next];
		}
	}
	
	public void DFS2(int cur, int pre, int curDepth) {
		int u = dfsn[cur] = dcnt ++;
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
	
	public int getSum(int u, int v) {
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
	
}

public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		HLD hld = new HLD();
		System.out.println(hld.init());
	}
}
