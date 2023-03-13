import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #5567 결혼식
public class Main {

	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		adj = new ArrayList[n];
		visited = new boolean[n];
		StringTokenizer st;
		for(int i = 0 ; i < n ; i ++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			adj[start].add(end);
			adj[end].add(start);
		}
		ArrayList<Integer> f = new ArrayList<>();
		int answer = 0;
		for(int i = 0 ; i < adj[0].size() ; i ++) {
			f.add(adj[0].get(i));
			visited[adj[0].get(i)] = true;
			answer ++;
		}
		for(int i = 0 ; i < f.size() ; i ++) {
			int tmp = f.get(i);
			for(int next : adj[tmp]) {
				if(next == 0 || visited[next]) continue;
				answer ++;
				visited[next] = true; 
			}
		}
		
		System.out.println(answer);

		
	}	
}
