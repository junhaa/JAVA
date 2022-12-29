import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #13510 트리와 쿼리 1
class Edge{
	private int end, weight, num;
	public Edge(int end, int weight, int num) {
		this.end = end;
		this.weight = weight;
		this.num = num;
	}
	public int getEnd() {
		return end;
	}
	public int getNum() {
		return num;
	}
	public int getWeight() {
		return weight;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}

class SegTree{
	private int size = 2;
	private int[] tree;
	
	public void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
		}
	}
	
	public int getMax(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MIN_VALUE;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return Math.max(getMax(L, R, nodeNum * 2, nodeL, mid), getMax(L, R, nodeNum * 2 + 1, mid + 1, nodeR)); 
	}
	
	public void init(int N) {
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
	}
	public void setLeaf(int idx, int val) {
		tree[idx] = val;
	}
	public int getSize() {
		return size;
	}
	
	public void update(int i, int val) {
		i += size / 2;
		tree[i] = val;
		while(i > 1) {
			i /= 2;
			tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
		}
	}
}

class HLD{
	private int C, dcnt;
	private int[] tSize, dfsn;
	private ArrayList<Integer>[] child;
	private ArrayList<Edge>[] adj;
	private SegTree seg = new SegTree();
	
	// 새로운 정점번호로 참조 (dfsn)
	private int[] parent, depth, cn, eVertex, cHead, cTail;
	
	public StringBuilder init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		seg.init(N);
		child = new ArrayList[N];
		adj = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) {
			child[i] = new ArrayList<>();
			adj[i] = new ArrayList<>();
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new Edge(v, w, i));
			adj[v].add(new Edge(u, w, i));
		}
		
		tSize = new int[N];
		dfsn = new int[N];
		
		parent = new int[N];
		depth = new int[N];
		cn = new int[N];
		eVertex = new int[N];
		cHead = new int[N];
		cTail = new int[N];
		
		DFS1(0, -1);
		Arrays.fill(cHead, -1);
		Arrays.fill(cTail, -1);
		parent[0] = -1;
		
		DFS2(0, -1, 0);
	
		for(int cur = 0 ; cur < N ; cur ++) {
			int u = dfsn[cur];
			for(Edge e : adj[cur]) {
				int v = dfsn[e.getEnd()];
				
				if(depth[u] < depth[v]) {
					eVertex[e.getNum()] = v;
					seg.setLeaf(seg.getSize() / 2 + v, e.getWeight());
				}
			}
		}
		seg.construct();
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			
			if(Integer.parseInt(st.nextToken()) == 1) {
				update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			else {
				sb.append(getMax(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1) + "\n");
			}
		}
		
		return sb;
	}
	
	private void DFS1(int cur, int pre) { // 서브트리 크기와 자식 리스트 갱신
		tSize[cur] = 1; // tSize = 서브트리 크기
		for(Edge e : adj[cur]) {
			int next = e.getEnd();	
			int weight = e.getWeight();
			int edgeNum = e.getNum();
			if(next != pre) {
				DFS1(next, cur);
				child[cur].add(next);
				tSize[cur] += tSize[next];
			}
		}
	}
	
	private void DFS2(int cur, int pre, int curDepth) { // 체인 그룹짓기, 부모 배열 저장
		int u = dfsn[cur] = dcnt ++;
		cn[u] = C;
		depth[u] = curDepth;
		if(cHead[C] < 0) cHead[C] = u;
		cTail[C] = u;
		
		if(child[cur].isEmpty()) { // 리프 노드면 
			C++;
			return;
		}
		
		int chained = child[cur].get(0); // 무거운 간선 찾기
		for(int i = 1 ; i < child[cur].size() ; i ++) {
			int next = child[cur].get(i);
			if(tSize[chained] < tSize[next]) chained = next;			
		}
		
		DFS2(chained, cur, curDepth + 1); // 무거운 쪽으로 먼저 DFS2 수행
		parent[dfsn[chained]] = u;
		for(int next : child[cur]) { // 나머지 간선 DFS2
			if(next != chained) DFS2(next, cur, curDepth + 1);
			int v = dfsn[next];
			parent[v] = u;
		}
	}
	public int getMax(int u, int v) {
		int res = Integer.MIN_VALUE;
		u = dfsn[u];
		v = dfsn[v];
		
		if(cn[u] != cn[v]) {
			while(true) {
				int uHead = cHead[cn[u]];
				int vHead = cHead[cn[v]];
				if(depth[uHead] > depth[vHead]) {
					res = Math.max(seg.getMax(uHead, u, 1, 0, seg.getSize() / 2 - 1), res);
					u = parent[uHead];
				}
				else {
					res = Math.max(seg.getMax(vHead, v, 1, 0, seg.getSize() / 2 - 1), res);
					v = parent[vHead];
				}
				if(cn[u] == cn[v]) break;
			}
		}
		res = Math.max(seg.getMax(Math.min(u, v) + 1, Math.max(u, v), 1, 0, seg.getSize() / 2 - 1), res);

		return res;
	}
	
	public void update(int u, int val) {
		seg.update(eVertex[u], val);
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		HLD hld = new HLD();
		System.out.println(hld.init());
	}

}
