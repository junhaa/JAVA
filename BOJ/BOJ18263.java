import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #18263 Milk Visits
class Milk implements Comparable<Milk>{
	int type, num;
	public Milk(int type, int num) {
		this.type = type;
		this.num = num;
	}
	@Override
	public int compareTo(Milk o) {
		return this.type - o.type;
	}
}
class Query implements Comparable<Query>{
	int a, b, c, num;
	public Query(int a, int b, int c, int num) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.num = num;
	}
	@Override
	public int compareTo(Query o) {
		return this.c - o.c;
	}
}
public class Main {

	static ArrayList<Milk> first = new ArrayList<>();
	static ArrayList<Query> Q = new ArrayList<>();
	static ArrayList<Integer>[] adj, child;
	static boolean[] answer;
	static int C, dcnt, size = 2;
	static int[] tSize, dfsn, cHead, cn, cTail, parent, depth, tree;
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] = val;
		while(i > 1) {
			i /= 2;
			tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
		}
	}
	
	static int getMaxT(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MIN_VALUE;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return Math.max(getMaxT(L, R, nodeNum * 2, nodeL, mid), getMaxT(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
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
		depth[u] = curDepth;
		cn[u] = C;
		if(cHead[C] < 0) cHead[C] = u;
		cTail[C] = u;
		
		if(child[cur].isEmpty()) {
			C ++;
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
	
	static int getMax(int u, int v) {
		int res = Integer.MIN_VALUE;
		u = dfsn[u];
		v = dfsn[v];
		
		if(cn[u] != cn[v]) {
			while(true) {
				int uHead = cHead[cn[u]];
				int vHead = cHead[cn[v]];
				if(depth[uHead] > depth[vHead]) {
					res = Math.max(res, getMaxT(uHead, u, 1, 0, size / 2 - 1));
					u = parent[uHead];
				}
				else {
					res = Math.max(res, getMaxT(vHead, v, 1, 0, size / 2 - 1));
					v = parent[vHead];
				}
				if(cn[u] == cn[v]) break;
			}
		}
		res = Math.max(res, getMaxT(Math.min(u, v), Math.max(u, v), 1, 0, size / 2 - 1));
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N];
		child = new ArrayList[N];
		answer = new boolean[M];
		tSize = new int[N];
		dfsn = new int[N];
		cHead = new int[N];
		cn = new int[N];
		cTail = new int[N];
		parent = new int[N];
		depth = new int[N];
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
			first.add(new Milk(Integer.parseInt(st.nextToken()), i)); // dfsn X
		}
		
		Collections.sort(first);
		
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
		
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			Q.add(new Query(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), i));
		}
		
		Collections.sort(Q);
		int Fidx = 0;
		int Qidx = 0;
		for(int i = 1 ; i <= N ; i ++) {
			while(Fidx < N) {
				Milk cur = first.get(Fidx);
				if(cur.type == i) {
					T.update(dfsn[cur.num], i);
					Fidx ++;
				}
				else break;
			}
			while(Qidx < M) {
				Query q = Q.get(Qidx);
				if(q.c == i) {
					if(T.getMax(q.a, q.b) == i) answer[q.num] = true;
					Qidx ++;
				}
				else break;
			}
		}
		for(int i = 0 ; i < M ; i ++) {
			if(answer[i]) sb.append(1);
			else sb.append(0);
		}
		System.out.println(sb);
	}
}
