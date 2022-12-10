import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #3055 탈출
class Point{
	int x, y;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {

	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int R, C;
	
	static void flood() {
		boolean[][] changed = new boolean[R][C];
		for(int i = 0 ; i < R ; i ++) {
			for(int k = 0 ; k < C ; k ++) {
				if(map[i][k] == '*' && !changed[i][k]) {
					for(int j = 0 ; j < 4 ; j ++) {
						int yy = i + dy[j];
						int xx = k + dx[j];
						if(yy >= 0 && xx >= 0 && yy < R && xx < C && map[yy][xx] == '.') {
							map[yy][xx] = '*';
							changed[yy][xx] = true;
						}
					}
				}
			}
		}
	}
	
	static int BFS(Point S) {
		Queue<Point> Q = new LinkedList<>();
		visited[S.y][S.x] = true;
		Q.offer(S);
		int L = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				Point tmp = Q.poll();
				if(map[tmp.y][tmp.x] == '*') continue;
				if(map[tmp.y][tmp.x] == 'D') return L;
				for(int j = 0 ; j < 4 ; j ++) {
					int xx = tmp.x + dx[j];
					int yy = tmp.y + dy[j];
					if(yy >= 0 && xx >= 0 && yy < R && xx < C && (map[yy][xx] == '.' || map[yy][xx] == 'D') && !visited[yy][xx]) {
						Q.offer(new Point(yy, xx));
						visited[yy][xx] = true;
					}
				}
			}
			flood();
			L ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		Point S = new Point(0,0);
		for(int i = 0 ; i < R ; i ++) {
			String input = br.readLine();
			for(int k = 0 ; k < C; k ++) {
				map[i][k] = input.charAt(k);
				if(map[i][k] == 'S') {
					S.y = i;
					S.x = k;
					map[i][k] = '.';
				}
			}
		}
		int answer = T.BFS(S);
		if(answer == -1) {
			System.out.println("KAKTUS");
			return;
		}
		System.out.println(answer);
	}
}
