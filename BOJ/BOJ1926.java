import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1926 그림 
public class Main {

	static int max = 0, N, M;
	static int map[][];
	static boolean visited[][];
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { -1, 0, 1, 0 };

	static int DFS(int y, int x, int num) {
		visited[y][x] = true;
		int sum = 1;
		for(int i = 0 ; i < 4; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] == 1 && !visited[ny][nx]) {
				sum += DFS(ny, nx, num + 1);
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					cnt ++;
					max = Math.max(T.DFS(i, j, 1), max);
				}
			}
		}
		System.out.println(cnt + "\n" + max);
	}
}
