import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #10999 구간 합 구하기 2
public class Main {

	static long[] arr;
	static long[] lazy;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
	}
	
	static void propagation(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum] != 0) {
			arr[nodeNum] += (nodeR - nodeL + 1) * lazy[nodeNum];
			if(nodeL != nodeR) {
				lazy[nodeNum * 2] += lazy[nodeNum];
				lazy[nodeNum * 2 + 1] += lazy[nodeNum];
			}
			lazy[nodeNum] = 0;
		}
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, long val) {
		propagation(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] += val;
			propagation(nodeL, nodeR, nodeNum);
			return;
		}
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid, val);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		arr[nodeNum] = arr[nodeNum * 2] + arr[nodeNum * 2 + 1];
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagation(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int a, b, c;
		long d;
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		arr = new long[size];
		lazy = new long[size];
		for(int i = 0 ; i < N ; i ++) {
			arr[size / 2 + i] = Long.parseLong(br.readLine());
		}
		T.construct();
		for(int i = 0 ; i < M + K ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			if(a == 1) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Long.parseLong(st.nextToken());
				T.update(b - 1, c - 1, 1, 0, size / 2 - 1, d);
			}
			else {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				sb.append(T.findSum(b - 1, c - 1, 1, 0, size / 2 - 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
