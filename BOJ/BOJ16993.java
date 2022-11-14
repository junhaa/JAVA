import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #16993 연속합과 쿼리 
class Node{
	int Lval, Rval, val, all;
	public Node(int Lval, int Rval, int val, int all) {
		this.Lval = Lval;
		this.Rval = Rval;
		this.val = val;
		this.all = all;
	}
}
public class Main {
	static final int MAX = (int)-1e9 + 1;
	static Node[] tree;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {	
			int Lval = MAX, Rval = MAX, val = MAX, all = 0;
			Lval = Math.max(tree[i * 2].all + tree[i * 2 + 1].Lval, tree[i * 2].Lval);
			Rval = Math.max(tree[i * 2].Rval + tree[i * 2 + 1].all, tree[i * 2 + 1].Rval);
			val = Math.max(tree[i * 2].val, tree[i * 2 + 1].val);
			val = Math.max(val, tree[i * 2].Rval + tree[i * 2 + 1].Lval);
			all = tree[i * 2].all + tree[i * 2 + 1].all;
			tree[i] = new Node(Lval, Rval, val, all);
		}
	}
	
	static Node findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return new Node(MAX, MAX, MAX, 0);
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		Node left = findSum(L, R, nodeNum * 2, nodeL, mid);
		Node right = findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
		int Lval = MAX, Rval = MAX, val = MAX, all = 0;
		Lval = Math.max(left.all + right.Lval, left.Lval);
		Rval = Math.max(left.Rval + right.all, right.Rval);
		val = Math.max(left.val, right.val);
		val = Math.max(val, left.Rval + right.Lval);
		all = left.all + right.all;
		return new Node(Lval, Rval, val, all);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new Node[size];
		for(int i = 0 ; i < size / 2 ; i ++) {
			if(i < N) {
				int tmp = Integer.parseInt(st.nextToken());
				tree[i + size / 2] = new Node(tmp, tmp, tmp, tmp);
			}
			else tree[i + size / 2] = new Node(MAX, MAX, MAX,0);
		}
		T.construct();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			Node tmp = findSum(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1, 0, size / 2 - 1);
			int ans = Math.max(tmp.Lval, tmp.Rval);
			ans = Math.max(ans, tmp.all);
			ans = Math.max(ans, tmp.val);
			sb.append(ans + "\n");
		}
		System.out.println(sb);
	}
}
