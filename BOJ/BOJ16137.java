import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16137 견우와 직녀
class Point{
	int y, x, b, last;
	public Point(int y, int x, int b, int last) {
		this.y = y;
		this.x = x;
		this.b = b;
		this.last = last;
	}
}
public class Main {

	static int[][] map;
	static boolean[][][] visited;
	static int N, M;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	
	static int BFS() {
		Queue<Point> Q = new LinkedList<>();
		int time = 0;
		Q.offer(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				Point tmp = Q.poll();
				if(tmp.x == N - 1 && tmp.y == N - 1) return time;
				if(tmp.b == 1 && map[tmp.y][tmp.x] == -1 && time % M != 0) {
					Q.offer(tmp);
					continue;
				}
				if(map[tmp.y][tmp.x] >= 2 && time % map[tmp.y][tmp.x] != 0) {
					Q.offer(tmp);
					continue;
				}
				for(int j = 0 ; j < 4 ; j ++) {
					int nx = tmp.x + dx[j];
					int ny = tmp.y + dy[j];
					if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
					if(map[ny][nx] < 0) {
						if(tmp.last == 1 || map[ny][nx] == -2 || tmp.b == 1 || visited[0][ny][nx]) continue;
						Q.offer(new Point(ny, nx, 1, 1));
						visited[0][ny][nx] = true;
					}
					else {
						if(map[ny][nx] > 1) {
							if(!visited[tmp.b][ny][nx] && tmp.last == 0) {
								Q.offer(new Point(ny, nx, tmp.b, 1));
								visited[tmp.b][ny][nx] = true;
							}
						}
						else if(!visited[tmp.b][ny][nx]) { 
							Q.offer(new Point(ny, nx, tmp.b, 0));
							visited[tmp.b][ny][nx] = true;
						}
					}
				}
			}
			time ++;
		}
		return -1;
	}
	
	static void findCross() {
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j ++) {
				if(map[i][j] <= 0) {
					boolean r = false;
					boolean c = false;
					for(int k = 0 ; k < 4 ; k ++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if(ny >= 0 && nx >= 0 && ny < N && nx < N && map[ny][nx] != 1) {  
							if(k % 2 == 0) r = true;
							else c = true;
						}
					}
					if(r && c) map[i][j] = -2;
					else map[i][j] = -1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[2][N][N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		T.findCross();
		System.out.println(T.BFS());
	}
}
