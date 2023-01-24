import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #11377 열혈강호 3
public class Main {

	static int[] A, B;
	static boolean[] visited;
	static ArrayList<Integer>[] adj;
	
	public boolean dfs(int a) {
		visited[a] = true;
		for(int b : adj[a]) {
			if(B[b] == -1 || !visited[B[b]] && dfs(B[b])) {
				A[a] = b;
				B[b] = a;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N * 2];
		for(int i = 0 ; i < N * 2 ; i ++) {
			adj[i] = new ArrayList<>();
		}
		A = new int[N * 2];
		B = new int[M];
		Arrays.fill(A, -1);
		Arrays.fill(B, -1);
		
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j = 0 ; j < num ; j ++) { 
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				adj[i].add(tmp);
				adj[i + N].add(tmp);
			}
		}
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			if(A[i] == -1) {
				visited = new boolean[N * 2];
				if(T.dfs(i)) answer ++; 
			}
		}
		int cnt = 0;
		for(int i = N ; i < 2 * N ; i ++) {
			if(A[i] == -1) {
				visited = new boolean[N * 2];
				if(T.dfs(i)) {
					answer ++;
					if(++cnt == K) break;
				}
			}
		}
		System.out.println(answer);
	}
}
