import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #14428 수열과 쿼리 16
class Node{
	int idx;
	int num;
	public Node(int num, int idx) {
		this.num = num;
		this.idx = idx;
	}
}
public class Main {
	static Node[] arr;
	static int size = 2, idx;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			int a = arr[i * 2].num;
			int b = arr[i * 2 + 1].num;
			if(a <= b) {
				arr[i] = new Node(a, arr[i * 2].idx);
			}
			else {
				arr[i] = new Node(b, arr[i * 2 + 1].idx);
			}
		}
	}
	
	static void update(int i, int val) {
		i += size / 2;
		arr[i].num = val;
		while(i > 1) {
			i /= 2;
			if(arr[i * 2].num <= arr[i * 2 + 1].num) {
				arr[i].num = arr[i * 2].num;
				arr[i].idx = arr[i * 2].idx;
			}
			else {
				arr[i].num = arr[i * 2 + 1].num;
				arr[i].idx = arr[i * 2 + 1].idx;
			}
		}
	}
	
	static Node findMin(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		Node a = findMin(L, R, nodeNum * 2, nodeL, mid);
		Node b = findMin(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
		if(a.num <= b.num) {
			return a;
		}
		else {
			return b;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int M, a, b, c;
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		arr = new Node[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < size / 2 ; i ++) {
			if(i < N) {
				arr[i + size / 2] = new Node(Integer.parseInt(st.nextToken()), i + size / 2);
			}
			else {
				arr[i + size / 2] = new Node(Integer.MAX_VALUE, i + size / 2);
			}
		}
		T.construct();
		M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				T.update(b - 1, c);
			}
			else {
				sb.append((T.findMin(b - 1, c - 1, 1, 0, size / 2 - 1).idx - size / 2) + 1 + "\n");
			}
		}
		System.out.println(sb);
	}
}
