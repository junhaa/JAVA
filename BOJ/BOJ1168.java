import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1168 요세푸스 문제 2
public class Main {
	static int[] tree;
	static int size = 2;
	static StringBuilder sb = new StringBuilder();
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void update(int i, int val) {
		tree[i] = val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void trace(int des, int nodeNum) {
		if(nodeNum >= size / 2) {
			sb.append(", " + (nodeNum - size / 2 + 1));
			update(nodeNum, 0);
			return;
		}
		if(tree[nodeNum * 2] >= des) {
			trace(des, nodeNum * 2);
		}
		else {
			trace(des - tree[nodeNum * 2], nodeNum * 2 + 1);
		}
	}
	
	static void findNum(int N, int K) {
		int node = K;
		sb.append(K);
		update(K - 1 + size / 2, 0);
		while(true) {
			if(tree[1] == 0) break;
			node += K - 1;
			if(node % tree[1] == 0) {
				node = tree[1];
			}
			else if(node > tree[1]) {
				node %= tree[1];
			}
			if(tree[1] == 1) node = 1;
			trace(node, 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		sb.append("<");
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		for(int i = 0 ; i < N  ; i ++) {
			tree[size / 2 + i] = 1;
		}
		T.construct();
		T.findNum(N, K);
		sb.append(">");
		System.out.println(sb);
	}
}
