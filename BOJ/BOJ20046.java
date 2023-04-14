import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #20046 Road Reconstruction
class Point implements Comparable<Point>{
	int y, x, cnt;
	public Point(int y, int x, int cnt) {
		this.y = y;
		this.cnt = cnt;
		this.x = x;
	}
	@Override
	public int compareTo(Point o) {
		return this.cnt - o.cnt;
	}
}
public class Main {

	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	
	static int BFS(int N, int M) {
		if(map[0][0] == -1) return Integer.MAX_VALUE;
		int[][] move = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			Arrays.fill(move[i], Integer.MAX_VALUE);
		}
		PriorityQueue<Point> pQ = new PriorityQueue<>();
		pQ.offer(new Point(0, 0, map[0][0]));
		move[0][0] = map[0][0];
		while(!pQ.isEmpty()) {
			Point tmp = pQ.poll();
			if(move[tmp.y][tmp.x] < tmp.cnt) continue;
			for(int i = 0 ; i < 4 ; i ++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				if(nx >= 0 && nx < M && ny >= 0 && ny < N) {
					if(map[ny][nx] == -1) continue;
					if(tmp.cnt + map[ny][nx] < move[ny][nx]) {
						move[ny][nx] = tmp.cnt + map[ny][nx];
						pQ.offer(new Point(ny, nx, move[ny][nx]));
					}
				}
			}
		}
		return move[N - 1][M - 1];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = T.BFS(N, M);
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}
