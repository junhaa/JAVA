import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1948 임계경로
class Edge{
	int node;
	int cost;
	public Edge(int node , int cost) {
		this.node = node;
		this.cost = cost;
	}
}
public class Main {

	static int[] id, time;
	static ArrayList<Edge>[] list, back;
	
	static void solution(int n, int m, int sNode, int eNode) {
		Queue<Integer> Q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		boolean[] visited = new boolean[n + 1]; // 역추적 중복 방지
		//boolean[] flag = new boolean[n + 1];
		int cnt = 0;
		/*
		for(int i = 1 ; i <= n ; i ++) {
			if(id[i] == 0) {
				for(Edge x : list[i]) {
					Q.offer(x.node);
					time[x.node] = Math.max(time[x.node], x.cost);
				}
			}
		}
		*/
		Q.offer(sNode);
		
		while(!Q.isEmpty()) {
			int tmp = Q.poll();
			for(Edge next : list[tmp]) {
				if(--id[next.node] == 0) { 
					Q.offer(next.node);
					//flag[next.node] = true;
				}
				time[next.node] = Math.max(time[next.node], time[tmp] + next.cost);
			}
		}
		
		Q.offer(eNode);
		while(!Q.isEmpty()) {
			int tmp = Q.poll();
			for(Edge x : back[tmp]) {
				if(time[tmp] - x.cost == time[x.node]) {
					cnt ++;
					if(!visited[x.node]) {
					Q.offer(x.node);
					visited[x.node] = true;
				}
			}
		}
		}
		sb.append(time[eNode]).append('\n').append(cnt);
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine()); // 도시의 개수
		list = new ArrayList[n + 1];
		back = new ArrayList[n + 1];
		id = new int[n + 1];
		time = new int[n + 1];
		int m = Integer.parseInt(br.readLine()); // 도로의 개수
		for(int i = 1 ; i <= n ; i ++) {
			list[i] = new ArrayList<>();
			back[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[start].add(new Edge(end, cost));
			back[end].add(new Edge(start, cost));
			id[end] ++;
		}
		st = new StringTokenizer(br.readLine());
		int sNode = Integer.parseInt(st.nextToken());
		int eNode = Integer.parseInt(st.nextToken());
		T.solution(n, m, sNode, eNode);
	}
}
