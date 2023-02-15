import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16973 직사각형 탈출
class Point{
	int x, y;
	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	
	static int[][] prefixr, prefixd;
	static boolean[][] visited;
	static int N, M, H, W, sr, sc, fr, fc;
	
	static int BFS() {
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(sr, sc));
		visited[sr][sc] = true;
		int L = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				Point tmp = Q.poll();
				if(tmp.y == fr && tmp.x == fc) return L;
				// up
				if(tmp.y - 1 >= 0 && !visited[tmp.y - 1][tmp.x]) { 
					if(prefixr[tmp.y][tmp.x + W] - prefixr[tmp.y][tmp.x] == 0) { 
						Q.offer(new Point(tmp.y - 1, tmp.x));
						visited[tmp.y - 1][tmp.x] = true;
					}
				}
				//down
				if(tmp.y + H < N && !visited[tmp.y + 1][tmp.x]) {
					if(prefixr[tmp.y + H + 1][tmp.x + W] - prefixr[tmp.y + H + 1][tmp.x] == 0) {
						Q.offer(new Point(tmp.y + 1, tmp.x));
						visited[tmp.y + 1][tmp.x] = true;
					}
				}
				//right
				if(tmp.x + W < M && !visited[tmp.y][tmp.x + 1]) {
					if(prefixd[tmp.y + H][tmp.x + W + 1] - prefixd[tmp.y][tmp.x + W + 1] == 0) {
						Q.offer(new Point(tmp.y, tmp.x + 1));
						visited[tmp.y][tmp.x + 1] = true;
					}
				}
				//left
				if(tmp.x - 1 >= 0 && !visited[tmp.y][tmp.x - 1]) {
					if(prefixd[tmp.y + H][tmp.x] - prefixd[tmp.y][tmp.x] == 0) {
						Q.offer(new Point(tmp.y, tmp.x - 1));
						visited[tmp.y][tmp.x - 1] = true;
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
		prefixr = new int[N + 1][M + 1];
		prefixd = new int[N + 1][M + 1];
		visited = new boolean[N][M];
		for(int i = 1 ; i <= N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= M ; j ++) {
				int tmp = Integer.parseInt(st.nextToken());
				prefixr[i][j] = prefixr[i][j - 1] + tmp;
				prefixd[i][j] = prefixd[i - 1][j] + tmp;
			}
		}
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken()) - 1;
		sc = Integer.parseInt(st.nextToken()) - 1;
		fr = Integer.parseInt(st.nextToken()) - 1;
		fc = Integer.parseInt(st.nextToken()) - 1;
		System.out.println(T.BFS());
	}
}
