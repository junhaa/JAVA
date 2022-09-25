import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2042 구간 합 구하기
public class Main {
	static long[] stree;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			stree[i] = stree[i * 2] + stree[i * 2 + 1];
		}
	}
	
	static void update(int i, long val) {
		i += size / 2;
		stree[i] = val;
		while(i > 1) {
			i /= 2;
			stree[i] = stree[i * 2] + stree[i * 2 + 1];
		}
	}
	
	static long sum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || L > nodeR) return 0;
		if(L <= nodeL && nodeR <= R) return stree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return sum(L, R, nodeNum * 2, nodeL, mid) + sum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		while(true) {
			size *= 2;
			if(size >= N) {
				size *= 2;
				break;
			}
		}
		stree = new long[size];
		for(int i = 0 ; i < N ; i ++) {
			stree[size / 2 + i] = Long.parseLong(br.readLine());
		}
		T.construct();
		int a,b;
		long c;
		for(int i = 0 ; i < M + K ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());
			if(a == 1) { // 바꾸기
				T.update(b - 1, c);
			}
			else { // 합
				sb.append(T.sum(b - 1, (int)c - 1, 1, 0, size / 2 - 1)).append('\n');
			}
		}
		System.out.println(sb);
	}
}
