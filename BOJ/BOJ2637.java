import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

// BOJ #2637 장난감 조립
public class Main {
	
	static ArrayList<Integer>[] list; // id list
	static int[] id;
	static TreeMap<Integer, Integer>[] dp;
	
	static StringBuilder solution(int N) {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> Q = new LinkedList<>();
		for(int i = 1; i <= N ; i ++) {
			if(id[i] == 0) { 
				Q.add(i);
				dp[i].put(i, 1);
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			if(Q.isEmpty()) {
				return new StringBuilder("error");
			}
			int tmp = Q.poll();
			for(int x : list[tmp]) {
				if(--id[x] == 0) { 
					Q.add(x);
					TreeMap<Integer, Integer> map = new TreeMap<>();
					for(int idx : dp[x].keySet()) {
						int num = dp[x].get(idx);
						for(int dpidx : dp[idx].keySet()) {
							map.put(dpidx, map.getOrDefault(dpidx, 0) + dp[idx].get(dpidx) * num); 
						}
					}
					dp[x] = map;
				}
			}
		}
		for(int i : dp[N].keySet()) {
			sb.append(i + " ").append(dp[N].get(i)).append('\n');
		}
		return sb;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		dp = new TreeMap[N + 1];
		id = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
			dp[i] = new TreeMap<>();
		}
		
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			dp[X].put(Y, K);
			list[Y].add(X);
			id[X] ++;
		}
		System.out.println(T.solution(N));
	}
}
