import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #2665 미로 만들기
class Point{
	int x, y, cnt;
	public Point(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}
public class Main {
	static int[][] boom, map;
	static int n;
	static int BFS() {
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0 };
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(0, 0, 0));
		while(!Q.isEmpty()) {
			Point tmp = Q.poll();
			if(boom[tmp.y][tmp.x] < tmp.cnt) continue;
			for(int i = 0 ; i < 4 ; i ++) {
				int xx = tmp.x + dx[i];
				int yy = tmp.y + dy[i];
				if(yy >= 0  && xx >= 0 && yy < n && xx < n) {
					if(map[yy][xx] == 0 && tmp.cnt + 1 < boom[yy][xx]) {
						boom[yy][xx] = tmp.cnt + 1;
						Q.offer(new Point(xx, yy, tmp.cnt + 1));
					}
					else if(map[yy][xx] == 1 && tmp.cnt < boom[yy][xx]){
						boom[yy][xx] = tmp.cnt;
						Q.offer(new Point(xx, yy, tmp.cnt));
					}
				}
			}
		}
		return boom[n - 1][n - 1];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		boom = new int[n][n];
		map  = new int[n][n];
		for(int i = 0 ; i < n ; i ++) {
			Arrays.fill(boom[i], Integer.MAX_VALUE);
			String input = br.readLine();
			for(int k = 0 ; k < n ; k ++) {
				map[i][k] = (int)(input.charAt(k) - '0');
			}
		}
		System.out.println(T.BFS());
	}
}
