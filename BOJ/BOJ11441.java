import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11441 합 구하기
public class Main {
	
	static int[] tree;
	static int size = 2;
	
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
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
		tree = new int[size];
		for(int i = 0 ; i < N ; i ++) {
			tree[size / 2 + i] = Integer.parseInt(st.nextToken());
		}
		T.construct();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			sb.append(findSum(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 1, 0, size / 2 - 1) + "\n");
		}
		System.out.println(sb);
	}
		
}
