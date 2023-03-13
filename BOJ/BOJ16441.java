import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16441 아기돼지와 늑대
class Point{
	int x, y;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {

	static char[][] map, nmap;
	static boolean[][][] visited;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static Queue<Point> Q = new LinkedList<>();
	static ArrayList<Point> list = new ArrayList<>();
	
	static void BFS() {
		while(!Q.isEmpty()) {
			Point tmp = Q.poll();
			for(int i = 0 ; i < 4 ; i ++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				if(map[ny][nx] != '#' && !visited[ny][nx][i]) {
					visited[ny][nx][i] = true;
					if(map[ny][nx] == '.') {
						Q.offer(new Point(ny, nx));
					}
					if(map[ny][nx] == '+') {
						while(true) {
							nx = nx + dx[i];
							ny = ny + dy[i];
							if(!visited[ny][nx][i]) {
								visited[ny][nx][i] = true;
								if(map[ny][nx] == '#') {
									Q.offer(new Point(ny - dy[i], nx - dx[i]));
									break;
								}
								if(map[ny][nx] == '.') {
									Q.offer(new Point(ny, nx));
									break;
								}
							}
							else break;
						}
					}
				}
			}
		}
	}
	
	static boolean isTrue(boolean[] visited) {
		for(int i = 0 ; i < 4 ; i ++) {
			if(visited[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		nmap = new char[N][M];
		visited = new boolean[N][M][4];
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'W') {
					list.add(new Point(i, j));
					Q.offer(new Point(i, j));
					Arrays.fill(visited[i][j], true);
					map[i][j] = '.';
				}
			}
		}
		T.BFS();
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				if(map[i][j] == '.' && T.isTrue(visited[i][j])) {
					nmap[i][j] = 'P';
				}
				else nmap[i][j] = map[i][j];
			}
		}
		for(Point x : list) {
			nmap[x.y][x.x] = 'W';
		}
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				sb.append(nmap[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
