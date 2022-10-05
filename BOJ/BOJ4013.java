import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #4013 ATM
class Edge{
	int node;
	int cost;
	public Edge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
}
public class Main {
	
	static ArrayList<Integer>[] edge;
	static ArrayList<Integer>[] sccEdge;
	static int SN, cnt;
	static boolean[] finished, canReach;
	static Stack<Integer> stack = new Stack<>();
	static int[] sn, dfsn, dp, cash, id;

	static int getSCC(int i) {
		stack.push(i);
		dfsn[i] = ++cnt;
		
		int result = dfsn[i];
		for(int next : edge[i]) {
			if(dfsn[next] == 0) result = Math.min(getSCC(next), result);
			else if(!finished[next]) result = Math.min(dfsn[next], result);
		}
		
		if(result == dfsn[i]) {
			while(true) {
				int tmp = stack.pop();
				finished[tmp] = true;
				sn[tmp] = SN;
				if(tmp == i) break;
			}
			SN++;
		}
		return result;
	}
	
	static void topologySort(int S) {
		dp[sn[S]] = cash[sn[S]]; 
		Queue<Integer> Q = new LinkedList<>();
		for(int i = 0 ; i < SN ; i ++) {
			if(id[i] == 0) { 
				Q.offer(i);
			}
		}
		
		while(!Q.isEmpty()) {
			int tmp = Q.poll();
			for(int next : sccEdge[tmp]) {
				if(canReach[tmp]) {
					int nextD = dp[tmp] + cash[next];
					dp[next] = Math.max(dp[next], nextD);
					canReach[next] = true;
				}
				if(--id[next] == 0) Q.offer(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		sn = new int[N];
		dfsn = new int[N];
		edge = new ArrayList[N];
		finished = new boolean[N];
		SN = 0;
		cnt = 0;
		for(int i = 0 ; i < N ; i ++) edge[i] = new ArrayList<>();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			edge[start].add(end);
		}
		for(int i = 0 ; i < N ; i ++) {
			if(dfsn[i] == 0) getSCC(i);
		}
		cash = new int[SN];
		dp = new int[SN];
		sccEdge = new ArrayList[SN];
		id = new int[SN];
		canReach = new boolean[SN];
		for(int i = 0 ; i < SN ; i ++) sccEdge[i] = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			cash[sn[i]] += Integer.parseInt(br.readLine());
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()) - 1;
		int P = Integer.parseInt(st.nextToken());
		canReach[sn[S]] = true;
		for(int i = 0 ; i < N ; i ++) {
			for(int node : edge[i]) {
				if(sn[i] != sn[node]) { 
					id[sn[node]] ++;
					sccEdge[sn[i]].add(sn[node]); // 줄일 수 있음
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		T.topologySort(S);
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < P ; i ++) {
			int tmp = Integer.parseInt(st.nextToken()) - 1;
			max = Math.max(max, dp[sn[tmp]]);
		}
		System.out.println(max);
	}
}
