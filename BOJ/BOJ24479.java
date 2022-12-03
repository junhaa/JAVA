import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #24479 알고리즘 수업 - 깊이 우선 탐색 1
public class Main {
	static int[] visited;
	static ArrayList<Integer>[] edge;
	static int num = 0;
	
	
	static void DFS(int R) {
		visited[R] = ++num;
		for(int next : edge[R]) {
			if(visited[next] == 0) {
				DFS(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()) - 1;
		visited = new int[N];
		edge = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) edge[i] = new ArrayList<>();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			edge[n1].add(n2);
			edge[n2].add(n1);
		}
		for(int i = 0 ; i < N ; i ++) {
			Collections.sort(edge[i]);
		}
		T.DFS(R);
		for(int i = 0 ; i < N ; i ++) {
			sb.append(visited[i] + "\n");
		}
		System.out.println(sb);
	}
}
