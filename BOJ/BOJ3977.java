import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #3977 축구 전술
public class Main {
	
	static ArrayList<Integer>[] edge;
	static int[] sn, dfsn, id;
	static boolean[] finished;
	static int cnt , SN ;
	static Stack<Integer> stack = new Stack<>();
	
	static int getSCC(int i) {
		dfsn[i] = ++cnt;
		stack.push(i);
		
		int result = dfsn[i];
		for(int next : edge[i]) {
			if(dfsn[next] == 0) result = Math.min(result ,getSCC(next));
			else if(!finished[next]) result = Math.min(dfsn[next], result); 
		}
		
		if(result == dfsn[i]) {
			while(true) {
				int tmp = stack.pop();
				sn[tmp] = SN;
				finished[tmp] = true;
				if(tmp == i) break;
			}
			SN ++;
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
			edge = new ArrayList[N];
			dfsn = new int[N];
			sn = new int[N];
			finished = new boolean[N];
			cnt = 0;
			SN = 0;
			for(int i = 0 ; i < N ; i ++) {
				edge[i] = new ArrayList<>();
			}
			for(int i = 0 ; i < M ; i ++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				edge[start].add(end);
			}
			for(int i = 0 ; i < N ; i ++) {
				if(dfsn[i] == 0) T.getSCC(i);
			}
			id = new int[SN];
			for(int i = 0 ; i < N ; i ++) {
				for(int next : edge[i]) {
					if(sn[i] != sn[next]) id[sn[next]] ++;
				}	
			}
			int num = 0, idx = 0; // id가 0인 sn의 개수
			for(int i = 0 ; i < SN ; i ++) {
				if(id[i] == 0) {	
				num ++;
				idx = i;
				}
			}
			
			if(num > 1) {
				sb.append("Confused" + "\n");
			}
			else {
				for(int i = 0 ; i < N ; i ++) {
					if(sn[i] == idx) sb.append(i + "\n");
				}
			}
			sb.append("\n");
			if(test != 0) br.readLine();
		}
		System.out.println(sb);
	}
}
