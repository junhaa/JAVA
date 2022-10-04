import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #11280 2-SAT - 3
public class Main {
	static int[] sn, snum;
	static boolean[] finished;
	static ArrayList<Integer>[] edge;
	static Stack<Integer> stack = new Stack<>();
	static int cnt = 1, SN = 1;
	
	static int oppo(int i) {
		if(i % 2 == 1) return i - 1;
		else return i + 1;
	}
	
	static int DFS(int i) {
		sn[i] = cnt++;
		stack.push(i);
		
		int result = sn[i];
		for(int next : edge[i]) {
			if(sn[next] == 0) {
				result = Math.min(result, DFS(next));
			}
			else if(!finished[next]) {
				result = Math.min(result, sn[next]);
			}
		}
		
		if(result == sn[i]) {
			while(true) {
				int tmp = stack.pop();
				finished[tmp] = true;
				snum[tmp] = SN;
				if(tmp == i) {
					break;
				}
			}
			SN ++;
		}
		return result;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		sn = new int[N * 2];
		snum = new int[N * 2];
		edge = new ArrayList[N * 2];
		finished = new boolean[N * 2];
		for(int i = 0 ; i < N * 2 ; i ++) {
			edge[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			A = (A < 0 ? -(A + 1) * 2 : A * 2 - 1);
			B = (B < 0 ? -(B + 1) * 2 : B * 2 - 1);
			edge[T.oppo(A)].add(B);
			edge[T.oppo(B)].add(A);
		}
		for(int i = 0 ; i < N * 2 ; i ++) {
			if(sn[i] == 0) DFS(i);
		}
		for(int i = 0 ; i < N ; i ++) {
			if(snum[i * 2] == snum[i * 2 + 1]) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(1);
	}
}
