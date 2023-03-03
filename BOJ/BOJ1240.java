import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #1240 노드사이의 거리

class Edge{
	int cost, num;
	public Edge(int num, int cost) {
		this.num = num;
		this.cost = cost;
	}
}
public class Main {

	static ArrayList<Edge>[] adj;
	static boolean[] visited;
	static int N;
	
	static int BFS(int s, int e) {
		visited = new boolean[N];
		Queue<Edge> Q = new LinkedList<>();
		Q.offer(new Edge(s, 0));
		visited[s] = true;
		while(!Q.isEmpty()) {
			Edge tmp = Q.poll();
			if(tmp.num == e) return tmp.cost;
			for(Edge next : adj[tmp.num]) {
				if(!visited[next.num]) {
					Q.offer(new Edge(next.num, tmp.cost + next.cost));
					visited[next.num] = true;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		adj = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < N - 1 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(end, cost));
			adj[end].add(new Edge(start, cost));
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			sb.append(T.BFS(s, e) + "\n");
		}
		System.out.println(sb);
	}
}
