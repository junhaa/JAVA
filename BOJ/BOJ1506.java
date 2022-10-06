import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #1506 경찰서
public class Main {
	
	static int[] cost, sn, dfsn,sccCost;
	static boolean[] finished;
	static Stack<Integer> stack = new Stack<>();
	static int cnt = 0 , SN = 0;
	static ArrayList<Integer>[] edge;
	
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
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		edge = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) edge[i] = new ArrayList<>();
		cost = new int[N];
		sn = new int[N];
		dfsn = new int[N];
		finished = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int j = 0 ; j < N ; j ++) {
				if(input.charAt(j) == '1') edge[i].add(j); 
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			if(dfsn[i] == 0) getSCC(i);
		}
		sccCost = new int[SN];
		Arrays.fill(sccCost, Integer.MAX_VALUE);
		for(int i = 0 ; i < N ; i ++) {
			sccCost[sn[i]] = Math.min(sccCost[sn[i]], cost[i]); 
		}
		int answer = 0;
		for(int i = 0 ; i < SN ; i ++) {
			answer += sccCost[i];
		}
		System.out.println(answer);
	}
}
