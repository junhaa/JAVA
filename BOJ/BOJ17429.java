import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #17429 국제 메시 기구

class SegTree{
	int size = 2;
	int[] tree, lazy, lazyMul;
	
	public void init(int N) {
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		lazy = new int[size];
		lazyMul = new int[size];
		Arrays.fill(lazyMul, 1);
	}
	
	public void propagate(int nodeNum, int nodeL, int nodeR) { 
//		if(lazy[nodeNum] != 0) {
//			if(nodeL != nodeR) {
//				lazy[nodeNum * 2] += lazy[nodeNum];
//				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
//			}
//			tree[nodeNum] += (nodeR - nodeL + 1) * lazy[nodeNum];
//			lazy[nodeNum] = 0;
//		}
//		if(lazyMul[nodeNum] != 1) {
//			if(nodeL != nodeR) {
//				lazyMul[nodeNum * 2] *= lazyMul[nodeNum];
//				lazyMul[nodeNum * 2 + 1] *= lazyMul[nodeNum]; 
//			}
//			tree[nodeNum] *= lazyMul[nodeNum];
//			lazyMul[nodeNum] = 1;
//		}
		
		int a1 = lazyMul[nodeNum];
		int b1 = lazy[nodeNum];
		if(a1 == 1 && b1 == 0) return;
		if(nodeL != nodeR) {
			for(int i = nodeNum * 2 ; i <= nodeNum * 2 + 1 ; i ++) {
				lazyMul[i] *= a1;
				int tmp = lazy[i];
				lazy[i] = a1 * tmp + b1;
			}
		}
		tree[nodeNum] = a1 * tree[nodeNum] + (nodeR - nodeL + 1) * b1;
		lazyMul[nodeNum] = 1;
		lazy[nodeNum] = 0;
		
	}
	
	public void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagate(nodeNum, nodeL, nodeR);
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] += val;
			propagate(nodeNum, nodeL, nodeR);
			return;
		}
		
		int mid = (nodeR + nodeL) / 2;
		update(L, R, nodeNum * 2, nodeL, mid, val);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		
		tree[nodeNum] = tree[nodeNum * 2] + tree[nodeNum * 2 + 1];
	}
	
	public void updateMul(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagate(nodeNum, nodeL, nodeR);
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazyMul[nodeNum] *= val;
			lazy[nodeNum] *= val;
			propagate(nodeNum, nodeL, nodeR);
			return;
		}
		
		int mid = (nodeR + nodeL) / 2;
		updateMul(L, R, nodeNum * 2, nodeL, mid, val);
		updateMul(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		
		tree[nodeNum] = tree[nodeNum * 2] + tree[nodeNum * 2 + 1];
	}
	
	public int getSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeNum, nodeL, nodeR);
		
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <=R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return getSum(L, R, nodeNum * 2, nodeL, mid) + getSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
		
	}
	
}

class HLD{
	int C, dcnt = -1;
	int[] tSize, dfsn;
	ArrayList<Integer>[] adj, child;
	SegTree seg = new SegTree();
	
	int[] eVertex, startIdx, endIdx, cn, depth, parent, cHead, cTail;
	
	public long unsigned32(int n) {
		  return n & 0xFFFFFFFFL;
	}
	
	public StringBuilder init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		seg.init(N);
		tSize = new int[N];
		dfsn = new int[N];
		eVertex = new int[N];
		startIdx = new int[N];
		endIdx = new int[N];
		cn = new int[N];
		depth = new int[N];
		parent = new int[N];
		cHead = new int[N];
		cTail = new int[N];
		adj = new ArrayList[N];
		child = new ArrayList[N];
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
		
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			int menu = Integer.parseInt(st.nextToken());
			switch (menu) {
			case 1:
			case 3:
				updateSub(menu, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
				break;
			
			case 2:
			case 4:
				update(menu, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
				break;
			
			case 5:
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				sb.append(unsigned32(seg.getSum(startIdx[tmp], endIdx[tmp], 1, 0, seg.size / 2 - 1)) + "\n");
				break;
			
			case 6:
				sb.append(unsigned32(getSum(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1)) + "\n");
				break;
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
		int u = dfsn[cur] = ++ dcnt;
		startIdx[cur] = u;
		cn[u] = C;
		depth[u] = curDepth;
		if(cHead[C] < 0) cHead[C] = u;
		cTail[C] = u;
		
		if(child[cur].isEmpty()) {
			C++;
			endIdx[cur] = dcnt;
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
		endIdx[cur] = dcnt;
	}
	
	public void updateSub(int menu, int x, int y) {
		if(menu == 1) {
			seg.update(startIdx[x], endIdx[x], 1, 0, seg.size / 2 - 1, y);
		}
		else {
			seg.updateMul(startIdx[x], endIdx[x], 1, 0, seg.size / 2 - 1, y);
		}
	}
	
	public void update(int menu, int x, int y, int val) {
		if(menu == 2) {
			int u = dfsn[x];
			int v = dfsn[y];
		
			if(cn[u] != cn[v]) { 
				while(true) {
					int uHead = cHead[cn[u]];
					int vHead = cHead[cn[v]];
					if(depth[uHead] > depth[vHead]) {
						seg.update(uHead, u, 1, 0, seg.size / 2 - 1, val);
						u = parent[uHead];
					}
					else {
						seg.update(vHead, v, 1, 0, seg.size / 2 - 1, val);
						v = parent[vHead];
					}
					if(cn[u] == cn[v]) break;
				}
			}
			seg.update(Math.min(u, v), Math.max(u, v), 1, 0, seg.size / 2 - 1, val);
		}
		else {
			int u = dfsn[x];
			int v = dfsn[y];
		
			if(cn[u] != cn[v]) { 
				while(true) {
					int uHead = cHead[cn[u]];
					int vHead = cHead[cn[v]];
					if(depth[uHead] > depth[vHead]) {
						seg.updateMul(uHead, u, 1, 0, seg.size / 2 - 1, val);
						u = parent[uHead];
					}
					else {
						seg.updateMul(vHead, v, 1, 0, seg.size / 2 - 1, val);
						v = parent[vHead];
					}
					if(cn[u] == cn[v]) break;
				}
			}
			seg.updateMul(Math.min(u, v), Math.max(u, v), 1, 0, seg.size / 2 - 1, val);
		}
	}
	
	public int getSum(int x, int y) {
		int res = 0;
		int u = dfsn[x];
		int v = dfsn[y];
		
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
		res += seg.getSum(Math.min(u, v), Math.max(u, v), 1, 0, seg.size / 2 - 1);
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
