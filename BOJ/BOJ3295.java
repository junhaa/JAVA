import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #3295 단방향 링크 네트워크
public class Main {

	static int[] A, B;
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	
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
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			adj = new ArrayList[N];
			for(int i = 0 ; i < N ; i ++) adj[i] = new ArrayList<>();
			A = new int[N];
			B = new int[N];
			Arrays.fill(A, -1);
			Arrays.fill(B, -1);
			for(int i = 0 ; i < M ; i ++) {
				st = new StringTokenizer(br.readLine());
				adj[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}
			int cnt = 0;
			for(int i = 0 ; i < N ; i ++) {
				if(A[i] == -1) {
					visited = new boolean[N];
					if(T.dfs(i)) cnt ++;
				}
			}
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}
}
