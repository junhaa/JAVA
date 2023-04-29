import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11962 Counting Haybales
class Node{
	long sum, min;
	public Node(long sum, long min) {
		this.sum = sum;
		this.min = min;
	}
}
public class Main {

	static int size = 2;
	static Node[] tree;
	static long[] lazy;
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i -- ) {
			long sum = tree[i * 2].sum + tree[i * 2 + 1].sum;
			long min = Math.min(tree[i * 2].min, tree[i * 2 + 1].min);
			tree[i] = new Node(sum, min);
		}
	}	
	
	static void propagate(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			tree[nodeNum].sum += (nodeR - nodeL + 1) * lazy[nodeNum];
			tree[nodeNum].min += lazy[nodeNum];
			lazy[nodeNum] = 0;
		}
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagate(nodeL, nodeR, nodeNum);
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] = val;
			propagate(nodeL, nodeR, nodeNum);
			return;
		}
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid, val);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		tree[nodeNum].sum = tree[nodeNum * 2].sum + tree[nodeNum * 2 + 1].sum;
		tree[nodeNum].min = Math.min(tree[nodeNum * 2].min, tree[nodeNum * 2 + 1].min);
	}
	
	static long getSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum].sum;
		int mid = (nodeL + nodeR) / 2;
		return getSum(L, R, nodeNum * 2, nodeL, mid) + getSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}

	static long getMin(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagate(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return Long.MAX_VALUE;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum].min;
		int mid = (nodeL + nodeR) / 2;
		return Math.min(getMin(L, R, nodeNum * 2, nodeL, mid), getMin(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new Node[size];
		lazy = new long[size];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < size / 2 ; i ++) {
			if(i < N) {
				long tmp = Long.parseLong(st.nextToken());
				tree[size / 2 + i] = new Node(tmp, tmp);
			}
			else {
				tree[size / 2 + i] = new Node(0, Long.MAX_VALUE);
			}
		}
		T.construct();
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			char Qchar = st.nextToken().charAt(0);
			if(Qchar == 'M') {
				sb.append(T.getMin(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1, 0, size / 2 - 1) + "\n");
			}
			else if(Qchar == 'S') {
				sb.append(T.getSum(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1, 0, size / 2 - 1) + "\n");
			}
			else {
				T.update(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1, 0, size / 2 - 1, Integer.parseInt(st.nextToken()));
			}
		}
		System.out.println(sb);
	}
}
