import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// BOJ #11843 Monkey and Apple-trees
class Node{
	int val, lazy;
	int nodeL, nodeR;
	Node l,r;
	public Node(int nodeL, int nodeR) {
		this.val = 0;
		this.lazy = 0;
		this.nodeL = nodeL;
		this.nodeR = nodeR;
	}
	public void propagate() {
		if(this.lazy != 0) val = (nodeR - nodeL + 1); 
		if(nodeL != nodeR){
			int mid = (nodeL + nodeR) / 2;
			if(l == null) l = new Node(nodeL, mid);
			if(r == null) r = new Node(mid + 1, nodeR);
			l.lazy += this.lazy;
			r.lazy += this.lazy;
		}
	}
}
public class Main { 

	static Node root = new Node(1, (int)1e9);
	
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
		n.val = n.l.val + n.r.val;
	}
	
	static int getSum(int L, int R, Node n, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		n.propagate();
		if(L <= nodeL && nodeR <= R) return n.val;
		int mid = (nodeL + nodeR) / 2;
		return getSum(L, R, n.l, nodeL, mid) + getSum(L, R, n.r, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int M = Integer.parseInt(br.readLine());
		int C = 0;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				C = T.getSum(start + C, end + C, root, 1, (int)1e9);
				bw.write(String.valueOf(C) + "\n");
			}
			else {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				T.update(start + C, end + C, root, 1, (int)1e9, 1);
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
