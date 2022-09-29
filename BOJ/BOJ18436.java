import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #18436 수열과 쿼리 37
public class Main {
	static int[] arr;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
	}
	
	/*
	
	static void init(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		if(R < nodeL || nodeR <= L) return;
		if(L <= nodeL && nodeR <= R) arr[nodeNum] = val;
		int mid = (nodeL + nodeR) / 2;
		init(L, R, nodeNum * 2, nodeL, mid, val);
		init(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
		
	}
	
	*/
	
	static void update(int i, int val) {
		i += size / 2;
		arr[i] = val;
		while(i > 1) {
			i /= 2;
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
	}
	static int query1(int L, int R, int nodeNum, int nodeL, int nodeR) { // 짝수 쿼리 
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return query1(L, R, nodeNum * 2, nodeL, mid) + query1(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	static int query2(int L, int R, int nodeNum, int nodeL, int nodeR) { // 홀수 쿼리 
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return (nodeR - nodeL + 1) - arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return query2(L, R, nodeNum * 2, nodeL, mid) + query2(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
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
		arr = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(tmp % 2 == 0) arr[size / 2 + i] = 1;
			else arr[size / 2 + i] = 0;
		}
		T.construct();
		int M = Integer.parseInt(br.readLine());
		int a, b, c;
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				if((arr[size / 2 + b - 1]) == 1 && c % 2 != 0) {
					T.update(b - 1, 0);
				}
				else if((arr[size / 2 + b - 1]) == 0 && c % 2 != 1) {
					T.update(b - 1, 1);
				}
			}
			else if(a == 2) {
				sb.append(T.query1(b - 1, c - 1, 1, 0, size / 2 - 1) + "\n");
			}
			else {
				sb.append(T.query2(b - 1, c - 1, 1, 0, size / 2 - 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
