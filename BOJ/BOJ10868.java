import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #10868 최솟값
public class Main {

	static int[] arr;
	static int size = 2;
	
	static void construct() {
		for(int i = size / 2 - 1; i > 0 ; i --) {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int a, b;
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
				arr[size / 2 + i] = Integer.parseInt(br.readLine());
			}
			else {
				arr[size / 2 + i] = Integer.MAX_VALUE;
			}
		}
		T.construct();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(T.findMin(a - 1, b - 1, 1, 0, size / 2 - 1) + "\n");
		}
		System.out.println(sb);
	}
}
