import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #12858 Range GCD
public class Main {

	static long[] tree, gtree, lazy;
	static int size = 2;
	
	static long gcd(long a, long b) {
		a = Math.abs(a);
		b = Math.abs(b);
		while(b != 0) {
			long r = a % b;
			a = b ;
			b = r ;
		}
		return a;
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(L, R, nodeNum);
		
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R ) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}

	static long findGcd(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return gtree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		long tmp1 = findGcd(L, R, nodeNum * 2, nodeL, mid);
		long tmp2 = findGcd(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	
		if(tmp1 == 0 && tmp2 == 0) return 0;
		else if(tmp2 == 0) return tmp1;
		else if(tmp1 == 0) return tmp2;
		else {
			return gcd(tmp1, tmp2);
		}
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagate(L, R, nodeNum);
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] = val;
			propagate(L, R, nodeNum);
			return;
		}
		if(nodeL != nodeR) {
			int mid = (nodeL + nodeR) / 2;
			update(L, R, nodeNum * 2, nodeL, mid, val);
			update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
			tree[nodeNum] = tree[nodeNum * 2] + tree[nodeNum * 2 + 1];
		}
	}
	
	static void construct(boolean isGtree) {
		if(isGtree) {
			for(int i = size / 2 - 1 ; i > 0 ; i --) {
				gtree[i] = gcd(gtree[i * 2], gtree[i * 2 + 1]);
			}
		}
		else {
			for(int i = size / 2 - 1 ; i > 0 ; i --) {
				tree[i] = tree[i * 2] + tree[i * 2 + 1];
			}
		}
	}
	
	static void gUpdate(int i, int val) {
		i += size / 2;
		gtree[i] += val;
		while(i > 1) {
			i /= 2;
			gtree[i] = gcd(gtree[i * 2], gtree[i * 2 + 1]);
		}
	}
	
	static void propagate(int L, int R, int nodeNum) {
		if(lazy[nodeNum] != 0) { 
			int i = nodeNum;
			tree[nodeNum] += (R - L + 1) * lazy[nodeNum];
			if(nodeNum < size / 2) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			lazy[nodeNum] = 0;
		}
	}
		
		
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(true) {
			if(size >= N) {
				size*= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size]; // segtree
		gtree = new long[size]; // gcd segtree
		lazy = new long[size];
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			tree[size / 2 + i] = tmp;
		}
		for(int i = 0 ; i < N - 1 ; i ++) {
			gtree[size / 2 + i] = tree[size / 2 + i + 1] - tree[size / 2 + i];
		}
		T.construct(false);
		T.construct(true);
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			if(t == 0) { // 최대공약수 출력
				if(a == b) {
					sb.append(tree[size / 2 + a] + "\n");
				}
				else {
					sb.append(T.gcd(findSum(a, a, 1, 0, size / 2 - 1), findGcd(a, b - 1, 1, 0, size / 2 - 1)) + "\n");
				}
			}
			else { // t를 더하는 연산
				if(a > 0) T.gUpdate(a - 1, t);
				T.gUpdate(b, -t);
				T.update(a, b, 1, 0, size / 2 - 1, t);
			}
		}
		System.out.println(sb);
	}
}
