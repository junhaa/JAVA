import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #6118 숨바꼭질
public class Main {

	static int minNode, L, size;
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	
	static void BFS() {
		Queue<Integer> Q = new LinkedList<>();
		visited[0] = true;
		Q.offer(0);
		L = 0;
		while(!Q.isEmpty()) {
			minNode = Integer.MAX_VALUE;
			size = Q.size();
			for(int i = 0 ; i < size ; i ++) {
				int tmp = Q.poll();
				minNode = Math.min(tmp, minNode);
				for(int next : adj[tmp]) {
					if(!visited[next]) {
						Q.offer(next);
						visited[next] = true;
					}
				}
			}
			L ++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N];
		visited = new boolean[N];
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1; // 0 - based;
			int end = Integer.parseInt(st.nextToken()) - 1;
			adj[start].add(end);
			adj[end].add(start);
		}
		T.BFS();
		System.out.println( ++minNode + " " + --L + " " + size);
	}
}
