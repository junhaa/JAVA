import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1260 DFS와 BFS
public class Main {
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer>[] edge;
	static boolean[] visited;
	
	static void DFS(int V) {
		visited[V] = true;
		sb.append(V + " ");
		for(int next : edge[V]) {
			if(!visited[next]) DFS(next);
		}
	}
	
	static void BFS(int V) {
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(V);
		visited[V] = true;
		while(!Q.isEmpty()) {
			int tmp = Q.poll();
			sb.append(tmp +" ");
			for(int next : edge[tmp]) {
				if(!visited[next]) { 
					Q.offer(next);
					visited[next] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		edge = new ArrayList[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			 edge[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			edge[start].add(end);
			edge[end].add(start);
		}
		for(int i = 1 ; i <= N ; i ++) {
			Collections.sort(edge[i]);
		}
		visited = new boolean[N + 1];
		T.DFS(V);
		sb.append("\n");
		visited = new boolean[N + 1];
		T.BFS(V);
		System.out.println(sb);
	}
}
