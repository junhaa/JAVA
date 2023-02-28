import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16509 장군
class Point{
	int y, x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {

	static int[][] map = new int[10][9];
	static boolean[][] visited = new boolean[10][9];
	static int[] dx = { -1, 1, 1, 1, 1, -1, -1, -1 };
	static int[] dy = { -1, -1, -1, 1, 1, 1, 1, -1 };
	
	static boolean check(int y, int x) {
		if(y < 10 && y >= 0 && x < 9 && x >= 0) return true;
		return false;
	}
	
	static int BFS(Point s, Point e) {
		Queue<Point> Q = new LinkedList<>();
		visited[s.y][s.x] = true;
		Q.offer(s);
		int L = 0;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				Point tmp = Q.poll();
				if(e.y == tmp.y && e.x == tmp.x) return L;
				/*
				 * for(int j = 0 ; j < 8 ; j ++) { int nx = tmp.x + dx[j]; int ny = tmp.y +
				 * dy[j]; if(check(ny, nx) && !visited[ny][nx]) { visited[ny][nx] = true;
				 * Q.offer(new Point(ny, nx)); } }
				 */
				for(int j = 0 ; j < 4 ; j ++) {
					if(j == 0) {
						int nx = tmp.x;
						int ny = tmp.y - 1;
						if(e.x == nx && e.y == ny) continue;
						for(int k = 1 ; k <= 2 ; k ++) {
							int nx1 = nx + dx[j * 2] * k;
							int ny1 = ny + dy[j * 2] * k;
							if(k == 1 && nx1 == e.x && ny1 == e.y) break;
							if(k == 2) {
								if(check(ny1, nx1) && !visited[ny1][nx1]) {
									visited[ny1][nx1] = true;
									Q.offer(new Point(ny1, nx1));
								}
							}
						}
						for(int k = 1 ; k <= 2 ; k ++) {
							int nx2 = nx + dx[j * 2 + 1] * k;
							int ny2 = ny + dy[j * 2 + 1] * k;
							if(k == 1 && nx2 == e.x && ny2 == e.y) break;
							if(k == 2) {
								if(check(ny2, nx2) && !visited[ny2][nx2]) {
									visited[ny2][nx2] = true;
									Q.offer(new Point(ny2, nx2));
								}
							}
						}
						
					}
					else if(j == 1) {
						int nx = tmp.x + 1;
						int ny = tmp.y;
						if(e.x == nx && e.y == ny) continue;
						for(int k = 1 ; k <= 2 ; k ++) {
							int nx1 = nx + dx[j * 2] * k;
							int ny1 = ny + dy[j * 2] * k;
							if(k == 1 && nx1 == e.x && ny1 == e.y) break;
							if(k == 2) {
								if(check(ny1, nx1) && !visited[ny1][nx1]) {
									visited[ny1][nx1] = true;
									Q.offer(new Point(ny1, nx1));
								}
							}
						}
						for(int k = 1 ; k <= 2 ; k ++) {
							int nx2 = nx + dx[j * 2 + 1] * k;
							int ny2 = ny + dy[j * 2 + 1] * k;
							if(k == 1 && nx2 == e.x && ny2 == e.y) break;
							if(k == 2) {
								if(check(ny2, nx2) && !visited[ny2][nx2]) {
									visited[ny2][nx2] = true;
									Q.offer(new Point(ny2, nx2));
								}
							}
						}
					
					}
					else if(j == 2) {
						int nx = tmp.x;
						int ny = tmp.y + 1;
						if(e.x == nx && e.y == ny) continue;
						for(int k = 1 ; k <= 2 ; k ++) {
							int nx1 = nx + dx[j * 2] * k;
							int ny1 = ny + dy[j * 2] * k;
							if(k == 1 && nx1 == e.x && ny1 == e.y) break;
							if(k == 2) {
								if(check(ny1, nx1) && !visited[ny1][nx1]) {
									visited[ny1][nx1] = true;
									Q.offer(new Point(ny1, nx1));
								}
							}
						}
						for(int k = 1 ; k <= 2 ; k ++) {
							int nx2 = nx + dx[j * 2 + 1] * k;
							int ny2 = ny + dy[j * 2 + 1] * k;
							if(k == 1 && nx2 == e.x && ny2 == e.y) break;
							if(k == 2) {
								if(check(ny2, nx2) && !visited[ny2][nx2]) {
									visited[ny2][nx2] = true;
									Q.offer(new Point(ny2, nx2));
								}
							}
						}
						
					}
					else {
						int nx = tmp.x - 1;
						int ny = tmp.y;
						if(e.x == nx && e.y == ny) continue;
						for(int k = 1 ; k <= 2 ; k ++) {
							int nx1 = nx + dx[j * 2] * k;
							int ny1 = ny + dy[j * 2] * k;
							if(k == 1 && nx1 == e.x && ny1 == e.y) break;
							if(k == 2) {
								if(check(ny1, nx1) && !visited[ny1][nx1]) {
									visited[ny1][nx1] = true;
									Q.offer(new Point(ny1, nx1));
								}
							}
						}
						for(int k = 1 ; k <= 2 ; k ++) {
							int nx2 = nx + dx[j * 2 + 1] * k;
							int ny2 = ny + dy[j * 2 + 1] * k;
							if(k == 1 && nx2 == e.x && ny2 == e.y) break;
							if(k == 2) {
								if(check(ny2, nx2) && !visited[ny2][nx2]) {
									visited[ny2][nx2] = true;
									Q.offer(new Point(ny2, nx2));
								}
							}
						}
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
		Point s = new Point(Integer.parseInt(st.nextToken()) , Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		Point e = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		System.out.println(BFS(s, e));
	}
}
