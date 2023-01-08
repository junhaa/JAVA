import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #16978 수열과 쿼리 22
class Query implements Comparable<Query>{
	int a, b ,c , d;
	public Query(int a, int b, int c, int d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	@Override
	public int compareTo(Query o) {
		return this.a - o.a;
	}
}
public class Main {

	static int size = 2;
	static long[] tree, answer;
	static ArrayList<Query> q1 = new ArrayList<>(), q2 = new ArrayList<>();
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] = val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		for(int i = 0 ; i < N ; i ++) {
			tree[size / 2 + i] = Integer.parseInt(st.nextToken());
		}
		T.construct();
		int qcnt = 0;
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				q1.add(new Query(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), -1, -1));
			}
			else {
				qcnt ++;
				q2.add(new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, qcnt - 1));
			}
		}
		answer = new long[qcnt];
		Collections.sort(q2);
		int idx = 0;
		while(true) {
			Query cur = q2.get(idx);
			if(cur.a == 0) {
				answer[cur.d] = findSum(cur.b, cur.c, 1, 0, size / 2 - 1);
				idx ++;
			}
			else break;
		}
		for(int i = 0 ; i < q1.size() ; i ++) {
			Query cur = q1.get(i);
			T.update(cur.a, cur.b);
			while(true) {
				if(idx < q2.size() && q2.get(idx).a == i + 1) {
					Query cur2 = q2.get(idx ++);
					answer[cur2.d] = findSum(cur2.b, cur2.c, 1, 0, size / 2 - 1);
				}
				else break;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(long x : answer) {
			sb.append(x + "\n");
		}
		System.out.println(sb);
	}
}
