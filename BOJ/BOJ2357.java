import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2357 최솟값과 최댓값
public class Main {

	static int[] mintree, maxtree;
	static int size = 2;
	
	static int findMin(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MAX_VALUE;
		if(L <= nodeL && nodeR <= R) return mintree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return Math.min(findMin(L, R, nodeNum * 2, nodeL, mid), findMin(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	static int findMax(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return maxtree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return Math.max(findMax(L, R, nodeNum * 2, nodeL, mid), findMax(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			mintree[i] = Math.min(mintree[i * 2], mintree[i * 2 + 1]);
		}
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			maxtree[i] = Math.max(maxtree[i * 2], maxtree[i * 2 + 1]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L, R;
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		mintree = new int[size];
		maxtree = new int[size];
		for(int i = 0 ; i < size / 2 ; i ++) {
			if(i < N) {
				int num = Integer.parseInt(br.readLine());
				mintree[size / 2 + i] = num;
				maxtree[size / 2 + i] = num;
			}
			else {
				mintree[size / 2 + i] = Integer.MAX_VALUE;
			}
		}
		T.construct();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			sb.append(T.findMin(L - 1, R - 1, 1, 0, size / 2 - 1) + " " + T.findMax(L - 1, R - 1, 1, 0, size / 2 - 1) + "\n");
		}
		System.out.println(sb);
	}
}
