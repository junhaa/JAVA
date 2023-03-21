import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #5913 준규와 사과
public class Main {

	static boolean[][] map = new boolean[5][5], visited = new boolean[5][5]; // 0-based
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int tree = 25;
	static int answer = 0;
	
	
	static void DFS(int y, int x, int cnt) {
		if(y == 4 && x == 4) {
			if(cnt == tree) answer ++;
		}
		visited[y][x] = true;
		for(int i = 0 ; i < 4 ; i ++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && !visited[ny][nx] && !map[ny][nx]) {
				
				DFS(ny, nx, cnt + 1);
				
			}
		}
		visited[y][x] = false;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		tree -= K;
		StringTokenizer st ;
		for(int i = 0 ; i < K ; i ++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}
		T.DFS(0, 0, 1);
		System.out.println(answer);
	}
}
