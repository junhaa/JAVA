import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #15651 N과 M (3)
public class Main {

	static int N;
	static int M;
	static StringBuilder sb = new StringBuilder();
	
	static void DFS(int[] arr, int L) {
		if(L == M) {
			for(int i = 0 ; i < M ; i ++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i = 1 ; i <= N ; i ++) {
			int[] nextArr = new int[M];
			for(int k = 0 ; k < L ; k ++) {
				nextArr[k] = arr[k];
			}
			nextArr[L] = i;
			DFS(nextArr, L + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] arr = new int[M];
		DFS(arr, 0);
		System.out.println(sb);
	}
}
