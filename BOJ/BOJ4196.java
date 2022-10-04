import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #4196 도미노
public class Main {
	
	static int[] sn, dfsn;
	static int cnt, SN;
	static ArrayList<Integer>[] edge;
	static boolean[] finished;
	static Stack<Integer> stack = new Stack<>();
	
	static int getSCC(int i) {
		dfsn[i] = ++cnt;
		stack.push(i);
		
		int result = dfsn[i];
		for(int next : edge[i]) {
			if(dfsn[next] == 0) result = Math.min(getSCC(next) ,result);
			else if(!finished[next]) result = Math.min(dfsn[next], result);
		}
		
		if(result == dfsn[i]) {
			while(true) {
				int tmp = stack.pop();
				sn[tmp] = SN;
				finished[tmp] = true;
				if(tmp == i) break;
			}
			SN++;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			sn = new int[N];
			dfsn = new int[N];
			cnt = 0;
			SN = 0;
			finished = new boolean[N];
			edge = new ArrayList[N];
			for(int i = 0 ; i < N ; i ++) {
				edge[i] = new ArrayList<>();
			}
			for(int i = 0 ; i < M ; i ++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				edge[start].add(end);
			}
			for(int i = 0 ; i < N ; i ++) {
				if(dfsn[i] == 0) {
					T.getSCC(i);
				}
			}
			int[] id = new int[SN];
			int answer = 0;
			for(int i = 0 ; i < N ; i ++) {
				for(int e : edge[i]) {
					if(sn[i] != sn[e]) id[sn[e]] ++;
				}
			}
			for(int i = 0 ; i < SN ; i ++) {
				if(id[i] == 0) answer ++;
			}
			sb.append(answer + "\n");
		}
		System.out.println(sb);
	}
}
