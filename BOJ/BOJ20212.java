import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #20212 나무는 쿼리를 싫어해~
class Node{ // Sparse Segment Tree
	long v, lazy;
	int nl, nr;
	Node l, r;
	

	public Node(int nl, int nr){
		this.lazy = 0;
		this.v = 0;
		this.nl = nl;
		this.nr = nr;
	}
	
	public void propagate() {
		v += (nr - nl + 1) * this.lazy;
		if(this.nl != this.nr) {
			int mid = (nl + nr) / 2;
			if(l == null) {
				l = new Node(nl, mid);
			}
			if(r == null) {
				r = new Node(mid + 1, nr);
			}
			l.lazy += this.lazy;
			r.lazy += this.lazy;
		}
		this.lazy = 0;
	}
	
}
class Query implements Comparable<Query>{
	int start, end, num, cnt;
	public Query(int start, int end, int num, int cnt) {
		this.start = start;
		this.end = end;
		this.num = num;
		this.cnt = cnt;
	}
	
	@Override
	public int compareTo(Query o) {
		return this.num - o.num;
	}
}
public class Main {

	static ArrayList<Query> q1 = new ArrayList<>(), q2 = new ArrayList<>();
	static long[] answer;
	static Node root;

	static void update(int L, int R, Node n, int nodeL, int nodeR, int val) {
		n.propagate();
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			n.lazy += val;
			n.propagate();
			return;
		}
		int mid = (nodeL + nodeR) / 2;
		update(L, R, n.l, nodeL, mid, val);
		update(L, R, n.r, mid + 1, nodeR, val);
		
		n.v = n.l.v + n.r.v;
	}
	
	static long getSum(int L, int R, Node n, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		n.propagate();
		if(L <= nodeL && nodeR <= R) return n.v;
		int mid = (nodeL + nodeR) / 2;
		return getSum(L, R, n.l, nodeL, mid) + getSum(L, R, n.r, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 쿼리의 개수 
		StringTokenizer st;
		int qcnt = 0;
		root = new Node(0, 999999999);
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				q1.add(new Query(start, end, Integer.parseInt(st.nextToken()), - 1));
			}
			else {
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				q2.add(new Query(start, end, Integer.parseInt(st.nextToken()) - 1, qcnt ++));
			}
		}
		
		answer = new long[q2.size()];
		Collections.sort(q2);
		int idx = 0;
		for(int i = 0 ; i < q1.size() ; i ++) {
			Query cur1 = q1.get(i);
			T.update(cur1.start, cur1.end, root, 0, 999999999, cur1.num);
			while(idx < q2.size()) {
				Query cur2 = q2.get(idx);
				if(cur2.num == i) {
					answer[cur2.cnt] = T.getSum(cur2.start, cur2.end, root, 0, 999999999);
					idx ++;
				}
				else break;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < answer.length ; i ++) {
			sb.append(answer[i] + "\n");
		}
		System.out.println(sb);
	}
}
