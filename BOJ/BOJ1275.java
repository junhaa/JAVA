import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1275 커피숍
public class Main {
	static long[] arr;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
	}
	
	static void update(int i , int val) {
		i += size / 2;
		arr[i] = val;
		while(i > 1) {
			i /= 2;
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
	}
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int a, b, c, d;
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		arr = new long[size];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[size / 2 + i] = Long.parseLong(st.nextToken());
		}
		T.construct();
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			if(a < b) {
				sb.append(T.findSum(a - 1, b - 1, 1, 0, size / 2 - 1) + "\n");
			}
			else {
				sb.append(T.findSum(b - 1, a - 1, 1, 0, size / 2 - 1) + "\n");
			}
			T.update(c - 1, d);
		}
		System.out.println(sb);
	}
}
