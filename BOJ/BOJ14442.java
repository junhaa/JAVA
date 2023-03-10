import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #14442 벽 부수고 이동하기 2
class Point{
	int x, y, k;
	public Point(int y, int x, int k) {
		this.y = y;
		this.x = x;
		this.k = k;
	}
}
public class Main {

	static boolean[][][] visited;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int N, M, K;
	
	
	static int BFS() {
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(0, 0, 0));
		int L = 1;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int j = 0 ; j < len ; j ++) {
				Point tmp = Q.poll();
				if(tmp.x == M - 1 && tmp.y == N - 1) return L;
				for(int i = 0 ; i < 4 ; i ++) {
					int nx = tmp.x + dx[i];
					int ny = tmp.y + dy[i];
					if(nx >= 0 && ny >= 0 && ny < N && nx < M) {
						int nk = tmp.k;
						if(map[ny][nx] == 1) { 
							if(nk == K) continue;
							else nk++;
						}
						if(visited[nk][ny][nx]) continue;
						Q.offer(new Point(ny, nx, nk));
						visited[nk][ny][nx] = true;
					}
				}
			}
			L ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[K + 1][N][M];
		map = new int[N][M];
		
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		System.out.println(T.BFS());
		
	}
}
