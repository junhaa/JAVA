import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #21922 학부 연구생 민상
class Point{
	int y, x, dir;
	public Point(int y, int x, int dir) {
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}
public class Main {

	static int[][] map, cmap;
	static ArrayList<Point> start = new ArrayList<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int N, M;
	static int BFS() {
		int answer = 0;
		cmap = new int[N][M];
		Queue<Point> Q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][4];
		for(Point p : start) {
			Arrays.fill(visited[p.y][p.x], true);
			if(cmap[p.y][p.x] != 5) {
				answer ++;
				cmap[p.y][p.x] = 5;
			}
			for(int i = 0 ; i < 4 ; i ++) {
				Q.offer(new Point(p.y, p.x, i));
			}
		}
		while(!Q.isEmpty()) {
			Point tmp = Q.poll();
			int nx = tmp.x + dx[tmp.dir];
			int ny = tmp.y + dy[tmp.dir];
			if(nx >= 0 && ny >= 0 && ny < N && nx < M && !visited[ny][nx][tmp.dir] && map[ny][nx] != 9) {
				visited[ny][nx][tmp.dir] = true;
				if(cmap[ny][nx] != 5) {
					answer ++;
					cmap[ny][nx] = 5;
				}
				
				if(map[ny][nx] == 1 && (tmp.dir == 1 || tmp.dir == 3)) {
					continue;
				}
				else if(map[ny][nx] == 2 && (tmp.dir == 0 || tmp.dir == 2)) {
					continue;
				}
				else if(map[ny][nx] == 3) {
					int ndir;
					if(tmp.dir >= 2) {
						ndir = 2 + (tmp.dir + 1) % 2;
					}
					else {
						ndir = (tmp.dir + 1) % 2;
					}
					Q.offer(new Point(ny, nx, ndir));
					continue;
				}
				else if(map[ny][nx] == 4){
					int ndir = 3 - tmp.dir;
					Q.offer(new Point(ny, nx, ndir));
					continue;
				}
				
				else {
					Q.offer(new Point(ny, nx, tmp.dir));
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) start.add(new Point(i, j, -1));
			}
		}
		int answer = T.BFS();
		System.out.println(answer);
	}
}
