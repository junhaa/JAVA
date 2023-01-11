import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2927 남극 탐험
class Query{
	String s;
	int a, b;
	public Query(String s, int a, int b) {
		this.s = s;
		this.a = a;
		this.b = b;
	}
}
public class Main {
	
	static int C, dcnt, size = 2;
	static int[] first, uf, dfsn, tSize, tree;
	static Query[] q;
	static ArrayList<Integer>[] adj, child;

	static int[] cn, cHead, cTail, depth, parent; 
	
	static int find(int x) {
		return (x == uf[x]) ? x : find(uf[x]); 
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) uf[fb] = fa;
		else uf[fa] = fb;
	}
	
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
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] = val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int getSumT(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return getSumT(L, R, nodeNum * 2, nodeL, mid) + getSumT(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static int getSum(int u, int v) {
		int res = 0;
		u = dfsn[u];
		v = dfsn[v];
		
		if(cn[u] != cn[v]) {
			while(true) {
				int uHead = cHead[cn[u]];
				int vHead = cHead[cn[v]];
				if(depth[uHead] > depth[vHead]) {
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
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		first = new int[N];
		uf = new int[N];
		dfsn = new int[N];
		tSize = new int[N];
		cn = new int[N];
		cHead = new int[N];
		cTail = new int[N];
		depth = new int[N];
		parent = new int[N];
		adj = new ArrayList[N];
		child = new ArrayList[N];
		
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		
		for(int i = 0 ; i < N ; i ++) {
			uf[i] = i;
			adj[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
			first[i] = Integer.parseInt(st.nextToken());
		}
		
		int Q = Integer.parseInt(br.readLine());
		q = new Query[Q];
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			Query tmp = new Query(st.nextToken(), Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
			if(tmp.s.equals("bridge")) {
				if(T.find(tmp.a) != T.find(tmp.b)) {
					T.union(tmp.a, tmp.b);
					adj[tmp.a].add(tmp.b);
					adj[tmp.b].add(tmp.a);
				}
			}
			else if(tmp.s.equals("penguins")) tmp.b ++;
			q[i] = tmp;
		}
		
		for(int i = 0 ; i < N ; i ++) { // 트리가 여러개 생성 가능 1번 노드로 모두 연결 후 HLD
			if(T.find(0) != T.find(i)) {
				T.union(0, i);
				adj[0].add(i);
				adj[i].add(0);
			}
		}
		
		for(int i = 0 ; i < N ; i ++) {
			uf[i] = i;
		}
		
		T.DFS1(0, -1);
		
		parent[0] = -1;
		Arrays.fill(cTail, -1);
		Arrays.fill(cHead, -1);
		
		T.DFS2(0, -1, 0);
		
		for(int i = 0 ; i < N ; i ++) {
			tree[dfsn[i] + size / 2] = first[i];
		}
		
		T.construct();
		
		for(int i = 0 ; i < Q ; i ++) {
			Query tmp = q[i];
			if(tmp.s.equals("bridge")) {
				if(T.find(tmp.a) == T.find(tmp.b)) {
					sb.append("no" + "\n");
				}
				else {
					T.union(tmp.a, tmp.b);
					sb.append("yes" + "\n");
				}
			}
			else if(tmp.s.equals("penguins")) {
				T.update(dfsn[tmp.a], tmp.b);
			}
			else {
				if(T.find(tmp.a) != T.find(tmp.b)) {
					sb.append("impossible" + "\n");
				}
				else {
					sb.append(T.getSum(tmp.a, tmp.b) + "\n");
				}
			}
		}
		System.out.println(sb);
	}
}
