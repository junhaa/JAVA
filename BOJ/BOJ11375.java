import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #11375 열혈강호
public class Main {

	
	static int[] A, B;
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	
	static boolean dfs(int a) {
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
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		A = new int[N];
		B = new int[M];
		Arrays.fill(A, -1);
		Arrays.fill(B, -1);
		adj = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int k = 0 ; k < num ; k ++) {
				adj[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			if(A[i] == -1) {
				visited = new boolean[N];
				if(dfs(i)) answer ++;
			}
		}
		System.out.println(answer);
	}
}
