import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #10090 Counting Inversions
public class Main {

	static int[] arr, tree;
	static int size = 2;

	
	static void update(int i) {
		i += size / 2;
		tree[i] = 1;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = N - 1 ; i >= 0 ; i --) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		long answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			answer += findSum(0, arr[i] - 2, 1, 0, size / 2 - 1);
			T.update(arr[i] - 1);
		}
		System.out.println(answer);
	}
}
