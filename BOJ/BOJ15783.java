import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #15783 세진 바이러스 	
public class Main {
	
	static int[] n, sn;
	static boolean[] finished;
	static Stack<Integer> stack = new Stack<>();
	static ArrayList<Integer>[] list;
	static int cnt = 0, scnt = 1;
	
	static int DFS(int i) {
		n[i] = ++cnt;
		stack.push(i);
		
		int result = n[i];
		for(int next : list[i]) {
			if(n[next] == 0) {
				result = Math.min(DFS(next), result);
			}
			else if(!finished[next]) {
				result = Math.min(n[next], result);
			}
		}
		if(result == n[i]) {
			while(true) {
				int tmp = stack.pop();
				sn[tmp] = scnt;
				finished[tmp] = true;
				if(tmp == i) break;
			}
			scnt ++;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		n = new int[N];
		sn = new int[N];
		finished = new boolean[N];
		list = new ArrayList[N];
		for(int i = 0 ; i < N ; i ++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
		}
		for(int i = 0 ; i < N ; i ++) {
			if(n[i] == 0) {
				DFS(i);
			}
		}
		int[] id = new int[scnt];
		for(int i = 0 ; i < N ; i ++) {
			for(int next : list[i]) {
				if(sn[next] != sn[i]) id[sn[next]] ++;
			}
		}
		int answer = 0;
		for(int i = 1 ; i < scnt ; i ++) {
			if(id[i] == 0) answer ++;
		}
		System.out.println(answer);
	}
}
