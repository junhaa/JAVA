import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2468 안전 영역
public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int answer = 1;
	
	
	static void DFS(int y, int x, int height) {
		visited[y][x] = true;
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx >= 0 && yy >= 0 && xx < N && yy < N && !visited[yy][xx] && map[yy][xx] > height) {
				DFS(yy,xx,height);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < N ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
				max = Math.max(map[i][k], max);
			}
		}
		for(int i = 1 ; i <= max ; i ++) {
			visited = new boolean[N][N];
			int tmp = 0;
			for(int k = 0 ; k < N ; k ++) {
				for(int j = 0 ; j < N ; j ++) {
					if(!visited[k][j] && map[k][j] > i) {
						DFS(k, j, i);
						tmp ++;
					}
				}
			}
			answer = Math.max(tmp, answer);
		}
		System.out.println(answer);
	}
}
