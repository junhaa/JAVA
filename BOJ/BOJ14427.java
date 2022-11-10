import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #14427 수열과 쿼리 15
class Node{
	int idx;
	int val;
	public Node(int idx, int val) {
		this.idx = idx;
		this.val = val;
	}
}
public class Main {

	static Node[] tree;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			if(tree[i * 2].val <= tree[i * 2 + 1].val) {
				tree[i] = new Node(tree[i * 2].idx, tree[i * 2].val);
			}
			else tree[i] = new Node(tree[i * 2 + 1].idx, tree[i * 2 + 1].val);
		}
	}
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i].val = val;
		while(i > 1) {
			i /= 2;
			if(tree[i * 2].val <= tree[i * 2 + 1].val) {
				tree[i] = new Node(tree[i * 2].idx, tree[i * 2].val);
			}
			else tree[i] = new Node(tree[i * 2 + 1].idx, tree[i * 2 + 1].val);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new Node[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < size / 2 ; i ++) {
			if(i < N) {
				tree[i + size / 2] = new Node(i, Integer.parseInt(st.nextToken()));
			}
			else {
				tree[i + size / 2] = new Node(i, Integer.MAX_VALUE);
			}
		}
		T.construct();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			if(query == 1) {
				T.update(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
			}
			else {
				sb.append((tree[1].idx + 1 ) + "\n");
			}
		}
		System.out.println(sb);
	}
}
