import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #9345 디지털 비디오 디스크(DVDs)
class Node{
	int min;
	int max;
	public Node(int min, int max) {
		this.min = min;
		this.max = max;
	}
}

public class Main {
	
	static Node tree[];
	static int size;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = new Node(Math.min(tree[i * 2].min, tree[i * 2 + 1].min) , Math.max(tree[i * 2].max, tree[i * 2 + 1].max));
		}
	}
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] = new Node(val, val);
		while(i > 1) {
			i /= 2;
			tree[i] = new Node(Math.min(tree[i * 2].min, tree[i * 2 + 1].min) , Math.max(tree[i * 2].max, tree[i * 2 + 1].max));
		}
	}
	
	static int findMin(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MAX_VALUE;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum].min;
		int mid = (nodeL + nodeR) / 2;
		return Math.min(findMin(L, R, nodeNum * 2, nodeL, mid), findMin(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	static int findMax(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MIN_VALUE;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum].max;
		int mid = (nodeL + nodeR) / 2;
		return Math.max(findMax(L, R, nodeNum * 2, nodeL, mid), findMax(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			size = 2;
			while(true) {
				if(size >= N) {
					size *= 2;
					break;
				}
				size *= 2;
			}
			tree = new Node[size];
			for(int i = 0 ; i < size / 2 ; i ++) {
				if(i < N) tree[size / 2 + i] = new Node(i, i);
				else tree[size / 2 + i] = new Node(Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
			T.construct();
			for(int i = 0 ; i < K ; i ++) {
				st = new StringTokenizer(br.readLine());
				int Q = Integer.parseInt(st.nextToken());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				if(Q == 0) {
					int tmp = tree[size / 2 + A].min;
					T.update(A, tree[size / 2 + B].min);
					T.update(B, tmp);
				}
				else {
					int max = findMax(A, B, 1, 0, size / 2 - 1);
					int min = findMin(A, B, 1, 0, size / 2 - 1);
					if(max == B && min == A) sb.append("YES" + "\n");
					else sb.append("NO" + "\n");
				}
			}
		}
		System.out.println(sb);
	}
	/* 
	// (누적합 X)
	static long[] tree;
	static int size;
	
	
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
	
	static void update(int i, long val) {
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
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			size = 2;
			while(true) {
				if(size >= N) {
					size *= 2;
					break;
				}
				size *= 2;
			}
			tree = new long[size];
			for(int i = 0 ; i < N ; i ++) {
				tree[size / 2 + i] = i;
			}
			T.construct();
			for(int i = 0 ; i < K ; i ++) {
				st = new StringTokenizer(br.readLine());
				int Q = Integer.parseInt(st.nextToken());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				if(Q == 0) { // DVD를 바꾸는 경우
					long tmp = tree[size / 2 + A];
					T.update(A, tree[size / 2 + B]);
					T.update(B, tmp);
				}
				else { 
					double d = (A + B) / 2.0;
					long sum = findSum(A, B, 1, 0, size / 2 - 1);
					if((long)(d * (B - A + 1)) == sum) sb.append("YES" +  "\n");
					else sb.append("NO" + "\n");
				}
				
			}
		}
		System.out.println(sb);
	}
	*/
}
