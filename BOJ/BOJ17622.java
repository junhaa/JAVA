import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #17622 타일 교체
class Point{
	int y, x, dir;
	public Point(int y, int x, int dir) {
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}
public class Main {

	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 }, dy = { -1, 0, 1, 0 };
	static boolean[][] visited;
	static int N;
	
	static int getNextDir(int dir, int next) { // dir는 12시 부터 시계방향 0, 1, 2, 3 return은 다음의 방향, 불가능하면 -1
		if(dir == 0) {
			if(next == 2 || next == 3 || next == 5) return -1;
			if(next == 0) return 1;
			if(next == 1) return 3;
			if(next == 4) return 0;
		}
		else if(dir == 1) {
			if(next == 0 || next == 2 || next == 4) return -1;
			if(next == 1) return 2;
			if(next == 3) return 0;
			if(next == 5) return 1;
		}
		else if(dir == 2) {
			if(next == 0 || next == 1 || next == 5) return -1;
			if(next == 2) return 1;
			if(next == 3) return 3;
			if(next == 4) return 2;
		}
		else {
			if(next == 1 || next == 3 || next == 4) return -1;
			if(next == 0) return 2;
			if(next == 2) return 0;
			if(next == 5) return 3;
		}
		return -1;
	}
	
	static int BFS() {
		if(map[0][0] % 2 == 0) return -1;
		if(map[N - 1][N - 1] == 1 || map[N - 1][N - 1] == 3 || map[N - 1][N - 1] == 4) return -1;
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(0, 0, 1));
		visited[0][0] = true;
		int L = 1;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				Point tmp = Q.poll();
				if(tmp.y == N - 1 && tmp.x == N - 1) { 
					if(getNextDir(tmp.dir, map[tmp.y][tmp.x]) != -1) return L;
					else continue;
				}
				int nextdir = getNextDir(tmp.dir, map[tmp.y][tmp.x]);
				if(nextdir == -1) continue;
				int ny = tmp.y + dy[nextdir];
				int nx = tmp.x + dx[nextdir];
				if(ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx]) {
					Q.offer(new Point(ny, nx, nextdir));
					visited[ny][nx] = true;
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
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(K == 0) {
			System.out.println(T.BFS());
		}
		else {
			int min = Integer.MAX_VALUE;
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < N ; j ++) {
					for(int k = 0 ; k <= 5 ; k ++) {
						if(map[i][j] == k) continue;
						int tmp = map[i][j];
						map[i][j] = k;
						visited = new boolean[N][N];
						int c = T.BFS();
						if(c != -1) {
							min = Math.min(c, min);
						}
						map[i][j] = tmp;
					}
				}
			}
			if(min == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(min);
		}
	}
}
