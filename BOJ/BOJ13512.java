import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #13512 트리와 쿼리 3

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
	
	public void init(int N) {
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		for(int i = 0 ; i < size / 2 ; i ++) { // 초기 모든 정점 흰색
			tree[size / 2 + i] = Integer.MAX_VALUE;
		}
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
	
	public void change(int i) {
		i += size / 2;
		if(tree[i] != Integer.MAX_VALUE) {
			tree[i] = Integer.MAX_VALUE;
		}
		else {
			tree[i] = i - size / 2;
		}
		while(i > 1) {
			i /= 2;
			tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
		}
	}
}

class HLD{ // 흰색 = Integer.MAX_VALUE , 검은색 = 노드번호
	private ArrayList<Integer>[] child;
	private ArrayList<Integer>[] adj;
	private int[] tSize, dfsn, rdfsn;
	private int C, dcnt;
	private SegTree seg = new SegTree();
	
	private int[] cn, cHead, cTail, parent, depth, eVertex;
	
	public StringBuilder init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N];
		child = new ArrayList[N];
		tSize = new int[N];
		dfsn = new int[N];
		rdfsn = new int[N];
		cn = new int[N];
		cHead = new int[N];
		cTail = new int[N];
		parent = new int[N];
		depth = new int[N];
		eVertex = new int[N];
		seg.init(N);
		seg.construct();
		
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
		
		Arrays.fill(cHead, -1);
		Arrays.fill(cTail, -1);
		parent[0] = -1;
		
		DFS2(0, -1, 0);
	
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				seg.change(dfsn[Integer.parseInt(st.nextToken()) - 1]);
			}
			else {
				int tmp = getMin(Integer.parseInt(st.nextToken()) - 1);
				if(tmp == Integer.MAX_VALUE) {
					sb.append(-1 + "\n");
				}
				else {
					sb.append(rdfsn[tmp] + 1 + "\n");
				}
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
		rdfsn[u] = cur;
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
			if(tSize[chained] < tSize[child[cur].get(i)]) chained = child[cur].get(i);
		} 
		
		DFS2(chained, cur, curDepth + 1);
		
		for(int next : child[cur]) {
			if(next != chained) {
				DFS2(next, cur, curDepth + 1);
			}
			int v = dfsn[next];
			parent[v] = u;
		}
	}
	
	public int getMin(int v) {
		int res = Integer.MAX_VALUE;
		int u = dfsn[0];
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
		res = Math.min(seg.getMin(0, Math.max(u, v), 1, 0, seg.size / 2 - 1), res);
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
