import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #25307 시루의 백화점 구경 
class Point{
	int y, x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {

	static int N, M, K;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static boolean[][] visited;
	static Point start;
	static ArrayList<Point> list = new ArrayList<>();
	
	static int BFS(int a) { // 마네킹 2 의자 1
		Queue<Point> Q = new LinkedList<>();
		if(a == 1) {
			Q.offer(start);
			visited[start.y][start.x] = true;
		}
		else {
			for(Point x : list) {
				Q.offer(x);
				visited[x.y][x.x] = true;
			}
			if(K == 0) return -1;
		}
		int L = 0;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				Point tmp = Q.poll();
				if(a == 1 && map[tmp.y][tmp.x] == 2) return L;
				for(int j = 0 ; j < 4 ; j ++) {
					int ny = tmp.y + dy[j];
					int nx = tmp.x + dx[j];
					if(ny >= 0 && nx >= 0 && ny < N && nx < M && !visited[ny][nx]) {
						if(a == 1 && map[ny][nx] == 1) continue;
						visited[ny][nx] = true;
						Q.offer(new Point(ny, nx));
					}
				}
			}
			L ++;
			if(a == 2 && L == K) return -1;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 4) {
					start = new Point(i, j);
					map[i][j] = 0;
				}
				else if(map[i][j] == 3) {
					list.add(new Point(i, j));
				}
			}
		}
		T.BFS(2);
		System.out.println(T.BFS(1));
	}
}
