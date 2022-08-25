import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1600 ¸»ÀÌ µÇ°íÇÂ ¿ø¼þÀÌ
class Point{
	int k;
	int x;
	int y;
	public Point(int x, int y, int k) {
		this.x = x;
		this.y = y;
		this.k = k;
	}
}

public class Main {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int[] kx = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] ky = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[][] map;
	static int[][] ch;
	static int W, H;
	static int solution(int K) { // BFS
		Queue<Point> Q = new LinkedList<>();
		int cnt = 0;
		Q.offer(new Point(0, 0, K));
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int j = 0 ; j < length ; j ++) {
				Point tmp = Q.poll();
				if(tmp.x == W - 1 && tmp.y == H - 1) return cnt;
				for(int i = 0 ; i < 4 ; i ++) {
					int xx = tmp.x + dx[i];
					int yy = tmp.y + dy[i];
					if(xx >= 0 && yy >= 0 && xx < W && yy < H && ch[yy][xx] < tmp.k && map[yy][xx] == 0) {
						ch[yy][xx] = tmp.k;
						Q.offer(new Point(xx, yy, tmp.k));
					}
				}
				if(tmp.k > 0) {
					for(int i = 0 ; i < 8 ; i ++) {
						int xx = tmp.x + kx[i];
						int yy = tmp.y + ky[i];
						if(xx >= 0 && yy >= 0 && xx < W && yy < H && ch[yy][xx] < tmp.k - 1 && map[yy][xx] == 0) {
							ch[yy][xx] = tmp.k - 1;
							Q.offer(new Point(xx, yy, tmp.k - 1));
						}
					}
				}
			}
			cnt ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		ch = new int[H][W];
		for(int i = 0 ; i < H ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < W ; k ++) {
				if(Integer.parseInt(st.nextToken()) == 1) map[i][k] = 1;
				ch[i][k] = -1;
			}
		}
		System.out.println(T.solution(K));
	}
}
