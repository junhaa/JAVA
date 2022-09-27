import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1395 스위치
public class Main {
	static int[] arr;
	static boolean[] lazy;
	
	static void propagation(int nodeL, int nodeR, int nodeNum) {
		if(lazy[nodeNum]) {
			arr[nodeNum] = nodeR - nodeL + 1 - arr[nodeNum];
			if(nodeL != nodeR) {
				if(lazy[nodeNum * 2]) lazy[nodeNum * 2] = false;
				else lazy[nodeNum * 2] = true;
				if(lazy[nodeNum * 2 + 1]) lazy[nodeNum * 2 + 1] = false;
				else lazy[nodeNum * 2 + 1] = true;
			}
			lazy[nodeNum] = false;
		}
	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagation(nodeL, nodeR, nodeNum);
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			lazy[nodeNum] = true;
			propagation(nodeL, nodeR, nodeNum);
			return;
		}
		
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
		arr[nodeNum] = arr[nodeNum * 2] + arr[nodeNum * 2 + 1];
	}
	
	static int findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagation(nodeL, nodeR, nodeNum);
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int size = 2, a, b, c;
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		arr = new int[size];
		lazy = new boolean[size];
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				sb.append(findSum(b - 1, c - 1, 1, 0, N - 1) + "\n");
			}
			else {
				T.update(b - 1, c - 1, 1, 0, N - 1);
			}
		}
		System.out.println(sb);
	}
}
