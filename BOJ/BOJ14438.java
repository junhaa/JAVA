import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #14438 수열과 쿼리 17
public class Main {
	static int[] arr;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			arr[i] = Math.min(arr[i * 2], arr[i * 2 + 1]);
		}
	}
	
	static void update(int i, int val) {
		i += size / 2;
		arr[i] = val;
		while(i > 1) {
			i /= 2;
			arr[i] = Math.min(arr[i * 2], arr[i * 2 + 1]);
		}
	}
	
	static int findMin(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MAX_VALUE;
		if(L <= nodeL && nodeR <= R) return arr[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return Math.min(findMin(L, R, nodeNum * 2, nodeL, mid), findMin(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		arr = new int[size];
		for(int i = 0 ; i < size / 2 ; i ++) {
			if(i < N) {
				arr[size / 2 + i] = Integer.parseInt(st.nextToken());
			}
			else {
				arr[size / 2 + i] = Integer.MAX_VALUE;
			}
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
				T.update(b - 1, c); 
			}
			else {
				sb.append(T.findMin(b - 1, c - 1, 1, 0, size / 2 - 1) + "\n");
			}
		}
		System.out.println(sb);
	}
}
