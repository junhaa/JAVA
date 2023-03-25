import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #18352 특정 거리의 도시 찾기
public class Main {
	
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static ArrayList<Integer> BFS(int X, int K) {
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(X);
		visited[X] = true; 
		int L = 0;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				int tmp = Q.poll();
				for(int next : adj[tmp]) {
					if(!visited[next]) {
						visited[next] = true;
						Q.offer(next);
					}
				}
			}
			L ++;
			if(L == K) break;
		}
		ArrayList<Integer> answer = new ArrayList<>();
		if(!Q.isEmpty()) {
			while(!Q.isEmpty()) {
				answer.add(Q.poll() + 1);
			}
			Collections.sort(answer);
			return answer;
		}
		else {
			return answer;
		}
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()) - 1; //start
		adj = new ArrayList[N]; // 0-based
		visited = new boolean[N];
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a].add(b);
		}
		ArrayList<Integer> answer = T.BFS(X, K);
		StringBuilder sb = new StringBuilder();
		if(answer.isEmpty()) {
			System.out.println(-1);
			return;
		}
		
		for(int x : answer) {
			sb.append(x + "\n");
		}
		System.out.println(sb);
	}
}
