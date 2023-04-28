import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #16174 점프왕 쩰리 (Large)
class Point{
	int y, x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {
	
	static int map[][];
	static int dx[] = { 1, 0 }, dy[] = { 0, 1 }, N;
	static boolean visited[][];
	static boolean BFS() {
		Queue<Point> Q = new LinkedList<>();
		visited[0][0] = true;
		Q.offer(new Point(0, 0));
		while(!Q.isEmpty()) {
			Point tmp = Q.poll();
			if(map[tmp.y][tmp.x] == -1) return true;
			for(int i = 0 ; i < 2 ; i ++) {
				int nx = tmp.x + dx[i] * map[tmp.y][tmp.x];
				int ny = tmp.y + dy[i] * map[tmp.y][tmp.x];
				if(nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[ny][nx]) {
					visited[ny][nx] = true;
					Q.offer(new Point(ny, nx));
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(T.BFS()) {
			System.out.println("HaruHaru");
		}
		else System.out.println("Hing");
	}
}
