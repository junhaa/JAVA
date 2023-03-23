import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #24446 알고리즘 수업 - 너비 우선 탐색 3
public class Main {
	
	static int[] depth;
	static ArrayList<Integer>[] adj;
	static void BFS(int R) {
		Queue<Integer> Q = new LinkedList<Integer>();
		depth[R] = 0;
		Q.offer(R);
		int L = 1;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				int tmp = Q.poll();
				for(int next : adj[tmp]) {
					if(depth[next] == -1) {
						depth[next] = L;
						Q.offer(next);
					}
				}
			}
			L ++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()) - 1;
		depth = new int[N];
		adj = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<Integer>();
		}
		Arrays.fill(depth, -1);
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a].add(b);
			adj[b].add(a);
		}
		T.BFS(R);
		for(int i = 0 ; i < N ; i ++) {
			sb.append(depth[i] + "\n");
		}
		System.out.println(sb);
	}
}
