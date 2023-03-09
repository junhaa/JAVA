import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #2479 경로 찾기 
public class Main {

	static int[] arr;
	static ArrayList<Integer>[] adj;
	static int[] visited;

	static boolean isBin(int n) {
		double tmp = n;
		if(tmp == 1) return true;
		while(tmp >= 2) {
			tmp /= 2;
		}
		if(tmp == 1) return true;
		else return false;
	}
	
	static int BFS(int s, int e) {
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(s);
		visited[s] = s;

		while (!Q.isEmpty()) {
			int tmp = Q.poll();
			if(tmp == e) return 1;
			for (int next : adj[tmp]) {
				if (visited[next] == -1) {
					visited[next] = tmp;
					Q.offer(next);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visited = new int[N];
		Arrays.fill(visited, -1);
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			arr[i] = Integer.valueOf(input, 2);
		}
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (T.isBin(arr[i] ^ arr[j])) {
					adj[i].add(j);
					adj[j].add(i);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()) - 1;
		int e = Integer.parseInt(st.nextToken()) - 1;
		Stack<Integer> stack = new Stack<>();
		if(T.BFS(s, e) > 0) {
			StringBuilder sb = new StringBuilder();
			int tmp = e;
			stack.push(e);
			while(tmp != s) {
				stack.push(visited[tmp]);
				tmp = visited[tmp];
			}
			while(!stack.isEmpty()) {
				sb.append((stack.pop() + 1) + " ");
			}
			System.out.println(sb);
		}
		else {
			System.out.println(-1);
		}
	}
}
