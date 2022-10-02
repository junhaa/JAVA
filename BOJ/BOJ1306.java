import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1306 달려라 홍준
public class Main {
	
	static int[] arr;
	static int size = 2;

	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			arr[i] = Math.max(arr[i * 2], arr[i * 2 + 1]);
		}
	}
	
	static int findMax(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MIN_VALUE;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return Math.max(findMax(L, R, nodeNum * 2, nodeL, mid), findMax(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		arr = new int[size];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[size / 2 + i] = Integer.parseInt(st.nextToken());
		}
		T.construct();
		for(int i = M ; i <= N - M + 1 ; i ++) {
			sb.append(T.findMax(i - M, i + M - 2, 1, 0, size / 2 - 1) + " ");
		}
		System.out.println(sb);
	}
}
