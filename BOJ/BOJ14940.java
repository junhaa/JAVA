import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #14940 쉬운 최단거리
class Point{
	int x, y;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {
	
	static int map[][], visited[][], dx[] = { 0, 1, 0, -1 }, dy[] = { -1, 0, 1, 0 };
	
	
	static void BFS(int starty, int startx, int n, int m) {
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(starty, startx));
		int L = 0;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				Point tmp = Q.poll();
				for(int j = 0 ; j < 4 ; j ++) {
					int ny = tmp.y + dy[j];
					int nx = tmp.x + dx[j];
					if(ny >= 0 && nx >= 0 && ny < n && nx < m && visited[ny][nx] == -1) {
						Q.offer(new Point(ny, nx));
						visited[ny][nx] = L + 1;
					}
				}
			}
			L ++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new int[n][m];
		int starty = -1, startx = -1;
		for(int i = 0 ; i < n ; i ++) {
			Arrays.fill(visited[i], -1);
		}
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < m ; j ++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 2) {
					starty = i;
					startx = j;
					visited[i][j] = 0;
				}
				else if(tmp == 0) visited[i][j] = 0;
			}
		}
		T.BFS(starty, startx, n, m);
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < n ; i ++) {
			for(int j = 0 ; j < m ; j ++) {
				sb.append(visited[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
