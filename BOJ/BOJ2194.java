import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #2194 유닛 이동시키기 
class Point{
	int y, x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {

	static int N, M, A, B, K;
	static ArrayList<Point> list = new ArrayList<Point>();
	static int[][] prefixx, prefixy; // right, down
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static boolean[][] visited;
	static Point E = null;
	
	static boolean canMove(Point tmp, int dir) {
		if(dir == 0) {
			if(prefixx[tmp.y - 1][tmp.x + B] - prefixx[tmp.y - 1][tmp.x] == 0) return true;
			else return false;
		}
		else if(dir == 1) {
			if(prefixy[tmp.y + A][tmp.x + B] - prefixy[tmp.y][tmp.x + B] == 0) return true;
			else return false;
		}
		else if(dir == 2) {
			if(prefixx[tmp.y + A][tmp.x + B] - prefixx[tmp.y + A][tmp.x] == 0) return true;
			else return false;
		}
		else {
			if(prefixy[tmp.y + A][tmp.x - 1] - prefixy[tmp.y][tmp.x - 1] == 0) return true;
			else return false;
		}
	}
	
	static int BFS(Point start) {
		Queue<Point> Q = new LinkedList<Point>();
		Q.offer(start);
		visited[start.y][start.x] = true;
		int L = 0;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				Point tmp = Q.poll();
				if(tmp.x == E.x && tmp.y == E.y) return L;
				for(int j = 0 ; j < 4 ; j ++) {
					int nx = tmp.x + dx[j];
					int ny = tmp.y + dy[j];
					if(ny >= 0 && ny + A - 1 < N && nx >= 0 && nx + B - 1 < M && canMove(tmp, j) && !visited[ny][nx]) {
						Q.offer(new Point(ny, nx));
						visited[ny][nx] = true;
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
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		prefixx = new int[N][M + 1];
		prefixy = new int[N + 1][M];
		for(int i = 0 ; i < K ; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				prefixx[i][j + 1] = prefixx[i][j] + map[i][j];
				prefixy[i + 1][j] = prefixy[i][j] + map[i][j];
			}
		}
		st = new StringTokenizer(br.readLine());
		Point S = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		st = new StringTokenizer(br.readLine());
		E = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		
		System.out.println(T.BFS(S));
	}
}
