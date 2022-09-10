import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1405 미친 로봇 
public class Main {
	static int[] d = new int[4];
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 }; 
	static double answer = 0;
	static int N; 
	
	static void DFS(int x, int y, double prob, int L) {
		visited[y][x] = true;
		if(L == N) {
			answer += prob;
			visited[y][x] = false;
			return;
		}
		for(int i = 0 ; i < 4 ; i ++) {
			if(d[i] == 0) continue;
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(visited[yy][xx]) continue;
			DFS(xx, yy, prob * d[i] / 100, L + 1);
		}
		visited[y][x] = false;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		visited = new boolean[N * 2 + 1][N * 2 + 1];
		for(int i = 0 ; i < 4 ; i ++) {
			int num = Integer.parseInt(st.nextToken());
			d[i] = num;
		}
		T.DFS(N, N, 1, 0);
		System.out.println(answer);
	}
}
