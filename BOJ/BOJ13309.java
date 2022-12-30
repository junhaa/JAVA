import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #13309 트리
class Edge{
	int end, weight, num;
	public Edge(int end, int weight, int num) {
		this.end = end;
		this.weight = weight;
		this.num = num;
	}
}

class SegTree{
	int size = 2;
	int[] tree;
	
	public SegTree(int N) {
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
	}
	
	public void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
		}
	}
	
	public int getMin(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MAX_VALUE;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return Math.min(getMin(L, R, nodeNum * 2, nodeL, mid), getMin(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	public void update(int i, int val) {
		i += size / 2;
		tree[i] = val;
		while(i > 1) {
			i /= 2;
			tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
		}
	}
	
}

class HLD{

	private int C, dcnt;
	private int[] tSize, dfsn;
	private ArrayList<Integer>[] child;
	private ArrayList<Edge>[] adj;
	private SegTree seg;
	
	private int[] parent, depth, cn, eVertex, cHead, cTail;
	
	public StringBuilder init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		seg = new SegTree(N);
		child = new ArrayList[N];
		adj = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) {
			child[i] = new ArrayList<>();
			adj[i] = new ArrayList<>();
		}
		tSize = new int[N];
		dfsn = new int[N];
		parent = new int[N];
		depth = new int[N];
		cn = new int[N];
		eVertex = new int[N];
		cHead = new int[N];
		cTail = new int[N]; 
		
		for(int i = 1 ; i <= N - 1 ; i ++) {
			int tmp = Integer.parseInt(br.readLine()) - 1;
			adj[tmp].add(new Edge(i, 1, i));
		}
		
		DFS1(0, -1);
		Arrays.fill(cHead, -1);
		Arrays.fill(cTail, -1);
		parent[0] = -1;
		
		DFS2(0, -1, 0);
		
		for(int cur = 0 ; cur < N ; cur ++) {
			int u = dfsn[cur];
			for(Edge e : adj[cur]) {
				int v = dfsn[e.end];
				if(depth[u] < depth[v]) {
					eVertex[e.num] = v;
					seg.tree[seg.size / 2 + v] = e.weight;
				}
			}
		}
		seg.construct();
		
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			if(d == 1) {
				int tmp = isConnected(b, c);
				if(tmp == 1) {
					sb.append("YES" + "\n");
					seg.update(eVertex[b], 0);
				}
				else {
					sb.append("NO" + "\n");
					seg.update(eVertex[c], 0);
				}
			}
			else {
				int tmp = isConnected(b, c);
				if(tmp == 1) sb.append("YES" + "\n");
				else sb.append("NO" + "\n");
			}
		}
		return sb;
	}
	
	public void DFS1(int cur, int pre) {
		tSize[cur] = 1;
		for(Edge e : adj[cur]) {
			if(e.end != pre) {
				DFS1(e.end, cur);
				child[cur].add(e.end);
				tSize[cur] += tSize[e.end];
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
			C ++;
			return;
			
		}
		
		int chained = child[cur].get(0);
		for(int i = 1 ; i < child[cur].size() ; i ++) {
			int next = child[cur].get(i);
			if(tSize[next] > tSize[chained]) chained = next;
		}
		
		DFS2(chained, cur, curDepth + 1);
		
		for(int next : child[cur]){
			if(next != chained) {
				DFS2(next, cur, curDepth + 1);
			}
			int v = dfsn[next];
			parent[v] = u;
		}
		
	}
	public int isConnected(int u, int v) {
		int res = Integer.MAX_VALUE;
		u = dfsn[u];
		v = dfsn[v];
		
		if(cn[u] != cn[v]) {
			while(true) {
				int uHead = cHead[cn[u]];
				int vHead = cHead[cn[v]];
				if(depth[uHead] > depth[vHead]) {
					res = Math.min(seg.getMin(uHead, u, 1, 0, seg.size / 2 - 1), res);
					u = parent[uHead];
				}
				else {
					res = Math.min(seg.getMin(vHead, v, 1, 0, seg.size / 2 - 1), res);
					v = parent[vHead];
				}
				if(cn[u] == cn[v]) break;
			}
		}
		res = Math.min(seg.getMin(Math.min(u, v) + 1, Math.max(u, v), 1, 0, seg.size / 2 - 1), res);
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
