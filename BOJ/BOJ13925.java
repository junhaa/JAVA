import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #13925 수열과 쿼리 13
public class Main {

	static int[] arr;
	static int[][] lazy;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1; i > 0 ; i --) {
			arr[i] = (arr[i * 2] + arr[i * 2 + 1]) % 1000000007;
		}
	}
	
	static void propagation(int nodeNum, int nodeL, int nodeR) { // 곱하기 1로 초기화
		long tmp;
		if(lazy[nodeNum][0] == 0 && lazy[nodeNum][1] == 1 && lazy[nodeNum][2] == 0) return;
		if(lazy[nodeNum][0] != 0) {
			tmp = (long)lazy[nodeNum][0] * (nodeR - nodeL + 1) % 1000000007;
		}
		else {
			tmp = arr[nodeNum];
		}
		tmp *= lazy[nodeNum][1] % 1000000007;
		tmp += (long)lazy[nodeNum][2] * (nodeR - nodeL + 1) % 1000000007;
		arr[nodeNum] = (int)(tmp % 1000000007);
		if(nodeL != nodeR) {
			if(lazy[nodeNum][0] != 0) {
				lazy[nodeNum * 2][0] = lazy[nodeNum][0];
				lazy[nodeNum * 2 + 1][0] = lazy[nodeNum][0];
				lazy[nodeNum * 2][1] = lazy[nodeNum][1];
				lazy[nodeNum * 2 + 1][1] = lazy[nodeNum][1];
				lazy[nodeNum * 2][2] = lazy[nodeNum][2];
				lazy[nodeNum * 2 + 1][2] = lazy[nodeNum][2];
			}
			else {
				lazy[nodeNum * 2][1] = (int)(((long)lazy[nodeNum][1] * lazy[nodeNum * 2][1]) % 1000000007);
				lazy[nodeNum * 2 + 1][1] = (int)(((long)lazy[nodeNum][1] * lazy[nodeNum * 2 + 1][1]) % 1000000007);
				lazy[nodeNum * 2][2] = (int)(((long)lazy[nodeNum][1] * lazy[nodeNum * 2][2]) % 1000000007);
				lazy[nodeNum * 2 + 1][2] = (int)(((long)lazy[nodeNum][1] * lazy[nodeNum * 2 + 1][2]) % 1000000007);
				lazy[nodeNum * 2][2] = (int)(((long)lazy[nodeNum * 2][2] + lazy[nodeNum][2]) % 1000000007);
				lazy[nodeNum * 2 + 1][2] = (int)(((long)lazy[nodeNum * 2 + 1][2] + lazy[nodeNum][2]) % 1000000007);
			}
		}
		lazy[nodeNum][0] = 0;
		lazy[nodeNum][1] = 1;
		lazy[nodeNum][2] = 0;
 	}
	
	static void update(int L, int R, int nodeNum, int nodeL, int nodeR, int query, int val) {
		propagation(nodeNum, nodeL, nodeR);
		
		if(R < nodeL || nodeR < L) return;
		if(L <= nodeL && nodeR <= R) {
			switch (query) {
			case 1: { // add
				lazy[nodeNum][2] += val;
				lazy[nodeNum][2] %= 1000000007;
				break;
			}
			case 2: { // mul
				long tmp;
				tmp = lazy[nodeNum][1] * val;
				lazy[nodeNum][1] = (int)(tmp % 1000000007);
				tmp = lazy[nodeNum][2] * val;
				lazy[nodeNum][2] = (int)(tmp % 1000000007);
				break;
			}
			case 3: { // change
				lazy[nodeNum][0] = val;
				lazy[nodeNum][1] = 1;
				lazy[nodeNum][2] = 0;
			}
			}
			propagation(nodeNum, nodeL, nodeR);
			return;
		}
		int mid = (nodeL + nodeR) / 2;
		update(L, R, nodeNum * 2, nodeL, mid, query, val);
		update(L, R, nodeNum * 2 + 1, mid + 1, nodeR, query, val);
		arr[nodeNum] = (arr[nodeNum * 2] + arr[nodeNum * 2 + 1]) % 1000000007 ;
	}
	
	static int findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		propagation(nodeNum, nodeL, nodeR);
		
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return (findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR)) % 1000000007;
		
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
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
		arr = new int[size];
		lazy = new int[size][3]; // 쿼리 3 2 1 순서
		for(int i = 1; i < size ; i ++) {
			lazy[i][1] = 1;
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[size / 2 + i] = Integer.parseInt(st.nextToken());
		}
		T.construct();
		M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			if(a == 4) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				sb.append(T.findSum(b - 1, c - 1, 1, 0, size / 2 - 1) + "\n");
			}
			else {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				T.update(b - 1, c - 1, 1, 0, size / 2 - 1, a, d);
			}
		}
		System.out.println(sb);
	}
}
