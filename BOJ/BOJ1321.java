import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1321 군인
public class Main {

	static int[] tree;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	
	static int findNum(int num, int nodeNum) {
		if(nodeNum >= size / 2) {
			return (nodeNum - size / 2) + 1;
		}
		if(num > tree[nodeNum * 2]) {
			return findNum(num - tree[nodeNum * 2], nodeNum * 2 + 1);
		}
		else {
			return findNum(num, nodeNum * 2);
		}
	}
	
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] += val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			tree[size / 2 + i] = Integer.parseInt(st.nextToken());
		}
		T.construct();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			if(q == 1) {
				T.update(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
			}
			else {
				sb.append(T.findNum(Integer.parseInt(st.nextToken()), 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
