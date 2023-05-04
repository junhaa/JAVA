import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #14699 관악산 등산 

class Rest implements Comparable<Rest>{
	int h, idx;
	public Rest(int h, int idx) {
		this.h = h;
		this.idx = idx;
	}
	@Override
	public int compareTo(Rest o) {
		return o.h - this.h;
	}
}
public class Main {

	static int[] dp;
	static ArrayList<Rest> height = new ArrayList<Rest>();
	static HashSet<Integer>[] adj;
	
	static int DFS(int curIdx) {
		if(dp[curIdx] != -1) {
			return dp[curIdx];
		}
		if(adj[curIdx].isEmpty()) {
			return dp[curIdx] = 1;
		}
		
		int max = Integer.MIN_VALUE;
		for(int next : adj[curIdx]) {
			max = Math.max(DFS(next), max);
		}
		return dp[curIdx] = max + 1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		dp = new int[N];
		adj = new HashSet[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			height.add(new Rest(Integer.parseInt(st.nextToken()), i));
			adj[i] = new HashSet<Integer>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			if(height.get(a).h > height.get(b).h) {
				adj[b].add(a);
			}
			else {
				adj[a].add(b);
			}
		}
		Arrays.fill(dp, -1);
		Collections.sort(height);
		for(Rest cur : height) {
			T.DFS(cur.idx);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			sb.append(dp[i] + "\n");
		}
		System.out.println(sb);
	}	
}
