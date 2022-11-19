import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #11724 연결 요소의 개수
public class Main {

	static ArrayList<Integer>[] list;
	static boolean[] visited;
	
	static void DFS(int i) {
		visited[i] = true;
		for(int next : list[i]) {
			if(!visited[next]) {
				DFS(next);
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		visited = new boolean[N];
		for(int i = 0 ; i < N ; i ++) list[i] = new ArrayList<>();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			list[a].add(b);
			list[b].add(a);
		}
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			if(!visited[i]) {
				T.DFS(i);
				answer ++;
			}
		}
		System.out.println(answer);
	}
}
