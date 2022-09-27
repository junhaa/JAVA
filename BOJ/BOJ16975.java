import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #16975 수열과 쿼리 21
public class Main {

	static long[] arr;
	static long[] lazy;
	static int size = 2;
	
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
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		propagation(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] = val;
			propagation(nodeL, nodeR, nodeNum);
			return;
		}
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid, val);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		arr[nodeNum] = arr[nodeNum * 2] + arr[nodeNum * 2 + 1];
	}
	
	static long findNum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagation(nodeL, nodeR, nodeNum);
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findNum(L, R, nodeNum * 2, nodeL, mid) + findNum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int M, a, b, c, d;
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		arr = new long[size];
		lazy = new long[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[size / 2 + i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			if(a == 1) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				T.update(b - 1, c - 1, 1, 0, size / 2 - 1, d);
			}
			else {
				b = Integer.parseInt(st.nextToken());
				sb.append(T.findNum(b - 1, b - 1, 1, 0, size / 2 - 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
