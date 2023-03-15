import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1726 로봇 
class Robot{
	int x, y, dir;
	public Robot(int y, int x, int dir) {
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}
public class Main {

	static int N, M;
	static boolean[][][] visited;
	static boolean[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static Robot goal;
	
	
	static int BFS(Robot start) {
		Queue<Robot> Q = new LinkedList<>();
		Q.offer(start);
		visited[start.y][start.x][start.dir] = true;
		int L = 0;
		while(!Q.isEmpty()) {
			int len = Q.size();
				for(int j = 0 ; j < len ; j ++) {
				Robot tmp = Q.poll();
				if(tmp.y == goal.y && tmp.x == goal.x && tmp.dir == goal.dir) return L;
				
				for(int i = 1 ; i <= 3 ; i ++) {
					int ny = tmp.y + dy[tmp.dir] * i;
					int nx = tmp.x + dx[tmp.dir] * i;
					if(ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx]) {
						if(!visited[ny][nx][tmp.dir]) {
							visited[ny][nx][tmp.dir] = true;
							Q.offer(new Robot(ny, nx, tmp.dir));
						}
					}
					else break;
				}
				int ndir = (tmp.dir + 3) % 4;
				if(!visited[tmp.y][tmp.x][ndir]) {
					visited[tmp.y][tmp.x][ndir] = true;
					Q.offer(new Robot(tmp.y, tmp.x, ndir));
				}
				ndir = (tmp.dir + 1) % 4;
				if(!visited[tmp.y][tmp.x][ndir]) {
					visited[tmp.y][tmp.x][ndir] = true;
					Q.offer(new Robot(tmp.y, tmp.x, ndir));
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
		map = new boolean[N][M];
		visited = new boolean[N][M][4];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				if(Integer.parseInt(st.nextToken()) == 0) {
					map[i][j] = true;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int sy = Integer.parseInt(st.nextToken()) - 1;
		int sx = Integer.parseInt(st.nextToken()) - 1;
		int sdir = Integer.parseInt(st.nextToken());
		if(sdir == 4) sdir = 0;
		else if(sdir == 2) sdir = 3;
		else if(sdir == 3) sdir = 2;
		st = new StringTokenizer(br.readLine());
		int ey = Integer.parseInt(st.nextToken()) - 1;
		int ex = Integer.parseInt(st.nextToken()) - 1;
		int edir = Integer.parseInt(st.nextToken());
		if(edir == 4) edir = 0;
		else if(edir == 2) edir = 3;
		else if(edir == 3) edir = 2;
		goal = new Robot(ey, ex, edir);
 		System.out.println(T.BFS(new Robot(sy, sx, sdir)));
	}
}
