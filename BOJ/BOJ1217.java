import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #1217 하우스 M.D.
public class Main {

	static int[] sn, dfsn;
	static boolean[] finished;
	static ArrayList<Integer>[] edge;
	static Stack<Integer> stack = new Stack<>();
	static int cnt, SN;
	
	static int oppo(int i) { 
		if(i % 2 == 1) return i - 1;
		else return i + 1;
	}
	
	static int getSCC(int i) {
		dfsn[i] = ++cnt;
		stack.push(i);
		
		int result = dfsn[i];
		for(int next : edge[i]) {
			if(dfsn[next] == 0) result = Math.min(getSCC(next), result);
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
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N == 0 && M == 0) break;
			cnt = 0;
			SN = 0;
			finished = new boolean[M * 2];
			edge = new ArrayList[M * 2];
			sn = new int[M * 2];
			dfsn = new int[M * 2];
			for(int i = 0 ; i < M * 2 ; i ++) edge[i] = new ArrayList<>();
			for(int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				A = (A < 0 ? -(A + 1) * 2 : A * 2 - 1);
				B = (B < 0 ? -(B + 1) * 2 : B * 2 - 1);
				edge[oppo(A)].add(B);
				edge[oppo(B)].add(A);
			}
			for(int i = 0 ; i < M * 2 ; i ++) {
				if(dfsn[i] == 0) getSCC(i);
			}
			boolean flag = true;
			for(int i = 0 ; i < M ; i ++) {
				if(sn[i * 2] == sn[i * 2 + 1]) {
					flag = false;
					break;
				}
			}
			if(flag) sb.append("1" + "\n");
			else sb.append("0" + "\n");
		}
		System.out.println(sb);
	}
}
