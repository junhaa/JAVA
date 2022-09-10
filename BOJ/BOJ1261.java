import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1261 알고스팟
class Point implements Comparable<Point>{
	int x;
	int y;
	int wall;
	public Point(int x, int y, int wall) {
		this.x = x;
		this.y = y;
		this.wall = wall;
	}
	@Override
	public int compareTo(Point o) {
		return this.wall - o.wall;
	}
}
public class Main {

	static boolean[][] map;
	static int[][] visited;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int N, M;
	static int BFS() {
		PriorityQueue<Point> Q = new PriorityQueue<>();
		Q.offer(new Point(0,0,0));
		visited[0][0] = 0;
		while(!Q.isEmpty()) {
			Point tmp = Q.poll();
			if(tmp.x == M - 1 && tmp.y == N - 1) return tmp.wall;
			if(visited[tmp.y][tmp.x] < tmp.wall) continue;
			for (int i = 0; i < 4; i++) {
				int xx = tmp.x + dx[i];
				int yy = tmp.y + dy[i];
				if (yy >= 0 && xx >= 0 && yy < N && xx < M) {
					if (map[yy][xx] && tmp.wall + 1 < visited[yy][xx]) {
						visited[yy][xx] = tmp.wall + 1;
						Q.offer(new Point(xx, yy, tmp.wall + 1));
					} else if (!map[yy][xx] && visited[yy][xx] > tmp.wall) {
						visited[yy][xx] = tmp.wall;
						Q.offer(new Point(xx, yy, tmp.wall));
					}
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		 Main T = new Main();
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 M = Integer.parseInt(st.nextToken()); // 가로 
		 N = Integer.parseInt(st.nextToken()); // 세로
		 map = new boolean[N][M];
		 visited = new int[N][M];
		 for(int i = 0 ; i < N ; i ++) {
			 String input = br.readLine();
			 for(int k = 0 ; k < M ; k ++) {
				 if(input.charAt(k) == '1') map[i][k] = true;
			 }
		 }
		 for(int i = 0 ; i < N ; i ++) {
			 Arrays.fill(visited[i], Integer.MAX_VALUE);
		 }
		 visited[0][0] = 0;
		 System.out.println(T.BFS());
	}
}
