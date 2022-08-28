import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #13565 Ä§Åõ

class Point{
	int x, y;
	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static boolean[][] map, visited;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	
	static boolean BFS(int M, int N) {
		Queue<Point> Q = new LinkedList<>();
		for(int i = 0 ; i < N ; i ++) {
			if(!map[0][i]) { 
				Q.offer(new Point(0, i));
				visited[0][i] = true;
			}
		}
		if(Q.isEmpty()) return false;
		while(!Q.isEmpty()) {
			Point tmp = Q.poll();
			if(tmp.y == M - 1) return true;
			for(int i = 0 ; i < 4 ; i ++) {
				int xx = tmp.x + dx[i];
				int yy = tmp.y + dy[i];
				if(xx >= 0 && yy >= 0 && xx < N && yy < M && !visited[yy][xx] && !map[yy][xx]) {
					visited[yy][xx] = true;
					Q.offer(new Point(yy, xx));
				}
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		map = new boolean[M][N];
		visited = new boolean[M][N];
		for(int i = 0 ; i < M ; i ++) {
			String str = br.readLine();
			for(int k = 0 ; k < N ; k ++) {
				if(str.charAt(k) == '1') map[i][k] = true;
			}
		}
		if(T.BFS(M, N)) System.out.println("YES");
		else System.out.println("NO");
 	}
}
