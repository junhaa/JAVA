import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #2152 여행 계획 세우기
public class Main {

	static int[] sn, dfsn, dp, num, id;
	static ArrayList<Integer>[] edge, sccEdge;
	static boolean[] finished;
	static int SN, cnt;
	static Stack<Integer> stack = new Stack<>();
	static boolean[] canReach;
	
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
			SN ++;
		}
		return result;
	}
	
	static void topologySort(int S) {
		Queue<Integer> Q = new LinkedList<>();
		for(int i = 0 ; i < SN ; i ++) {
			if(id[i] == 0) Q.offer(i);
		}
		while(!Q.isEmpty()) {
			int tmp = Q.poll();
			for(int nextS : sccEdge[tmp]) {
				if(canReach[tmp]) {
					dp[nextS] = Math.max(dp[nextS], dp[tmp] + num[nextS]);
					canReach[nextS] = true;
				}
				if(--id[nextS] == 0) Q.offer(nextS);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, M, S, E;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken()) - 1;
		E = Integer.parseInt(st.nextToken()) - 1;
		sn = new int[N];
		dfsn = new int[N];
		finished = new boolean[N];
		edge = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) edge[i] = new ArrayList<>();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			edge[start].add(end);
		}
		SN = 0;
		cnt = 0;
		for(int i = 0 ; i < N ; i ++) {
			if(dfsn[i] == 0) T.getSCC(i);
		}
		canReach = new boolean[SN];
		canReach[sn[S]] = true;
		num = new int[SN];
		dp = new int[SN];
		id = new int[SN];
		sccEdge = new ArrayList[SN];
		for(int i = 0 ; i < SN ; i ++) sccEdge[i] = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			for(int n : edge[i]) {
				if(sn[i] != sn[n]) { 
					id[sn[n]] ++;
					sccEdge[sn[i]].add(sn[n]);
				}
			}
			num[sn[i]] ++;
		}
		//dp[sn[S]] = num[sn[S]];
		T.topologySort(S);
		System.out.println(dp[sn[E]]);
	}
}
